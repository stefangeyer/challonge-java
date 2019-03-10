package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.model.Credentials;
import at.stefangeyer.challonge.rest.*;
import at.stefangeyer.challonge.rest.retrofit.converter.RetrofitConverterFactory;
import at.stefangeyer.challonge.serializer.Serializer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;

import java.nio.charset.Charset;

import static at.stefangeyer.challonge.rest.retrofit.util.RetrofitUtil.responseCount;

public class RetrofitRestClient implements RestClient {

    private static final String BASE_URL = "https://api.challonge.com/v1/";

    private ChallongeRetrofit challongeRetrofit;

    @Override
    public void initialize(Credentials credentials, Serializer serializer) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        httpClientBuilder.authenticator((route, response) -> {
            String credential = okhttp3.Credentials.basic(credentials.getUsername(), credentials.getKey(),
                    Charset.forName("UTF-8"));
            // retry authentication 5 times only
            if (responseCount(response) >= 5) {
                return null;
            }
            return response.request().newBuilder().header("Authorization", credential).build();
        });

        httpClientBuilder.addInterceptor((chain -> {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .method(original.method(), original.body());

            return chain.proceed(requestBuilder.build());
        }));

        RetrofitConverterFactory factory = new RetrofitConverterFactory(serializer);

        Retrofit retrofit = new Retrofit.Builder().client(httpClientBuilder.build()).baseUrl(BASE_URL)
                .addConverterFactory(factory).build();

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
}
