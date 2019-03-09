package at.stefangeyer.challonge.rest.retrofit.converter;

import at.stefangeyer.challonge.serializer.Serializer;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class RetrofitConverterFactory extends Converter.Factory {

    private Serializer serializer;

    public RetrofitConverterFactory(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new RetrofitResponseBodyConverter<>(this.serializer, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        return new RetrofitRequestBodyConverter<>(this.serializer);
    }
}
