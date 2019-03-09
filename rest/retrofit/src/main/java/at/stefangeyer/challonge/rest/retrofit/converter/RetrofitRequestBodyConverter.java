package at.stefangeyer.challonge.rest.retrofit.converter;

import at.stefangeyer.challonge.serializer.Serializer;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

import java.io.IOException;

public class RetrofitRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private Serializer serializer;

    public RetrofitRequestBodyConverter(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public RequestBody convert(T value) {
        String content = this.serializer.serialize(value);
        return RequestBody.create(MEDIA_TYPE, content);
    }
}
