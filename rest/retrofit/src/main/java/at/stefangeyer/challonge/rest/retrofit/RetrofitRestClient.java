package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.rest.*;
import at.stefangeyer.challonge.serializer.Serializer;
import okhttp3.*;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class RetrofitRestClient implements RestClient {

    private static final String BASE_URL = "https://api.challonge.com/v1/";
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private ChallongeRetrofit challongeRetrofit;

    @Override
    public void initialize(Credentials credentials, Serializer serializer) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        httpClientBuilder.addInterceptor((chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", credentials.toHttpAuthString())
                    .header("Accept", "application/json")
                    .method(original.method(), original.body());
            Request request = requestBuilder.build();

            return chain.proceed(request);
        }));

        Retrofit retrofit = new Retrofit.Builder().client(httpClientBuilder.build())
                .baseUrl(BASE_URL).addConverterFactory(createConverterFactory(serializer))
                .build();

        this.challongeRetrofit = retrofit.create(ChallongeRetrofit.class);
    }

    @Override
    public TournamentRestClient createTournamentRestClient() {
        if (this.challongeRetrofit != null) {
            return new RetrofitTournamentRestClient(this.challongeRetrofit);
        } else {
            throw new IllegalStateException("Attempted to create rest client before initialization");
        }
    }

    @Override
    public ParticipantRestClient createParticipantRestClient() {
        if (this.challongeRetrofit != null) {
            return new RetrofitParticipantRestClient(this.challongeRetrofit);
        } else {
            throw new IllegalStateException("Attempted to create rest client before initialization");
        }
    }

    @Override
    public MatchRestClient createMatchRestClient() {
        if (this.challongeRetrofit != null) {
            return new RetrofitMatchRestClient(this.challongeRetrofit);
        } else {
            throw new IllegalStateException("Attempted to create rest client before initialization");
        }
    }

    @Override
    public AttachmentRestClient createAttachmentRestClient() {
        if (this.challongeRetrofit != null) {
            return new RetrofitAttachmentRestClient(this.challongeRetrofit);
        } else {
            throw new IllegalStateException("Attempted to create rest client before initialization");
        }
    }

    private Converter.Factory createConverterFactory(Serializer serializer) {
        return new Converter.Factory() {
            public Converter<ResponseBody, Object> responseBodyConverter(Type type, Annotation[] annotations,
                                                                         Retrofit retrofit) {
                return value -> serializer.deserialize(value.string(), type);
            }

            public Converter<Object, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                                       Annotation[] methodAnnotations,
                                                                       Retrofit retrofit) {
                return value -> RequestBody.create(RetrofitRestClient.MEDIA_TYPE, serializer.serialize(value));
            }
        };
    }
}
