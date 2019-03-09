package at.stefangeyer.challonge.rest.retrofit.converter;

import at.stefangeyer.challonge.serializer.Serializer;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

public class RetrofitResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Serializer serializer;
    private Type type;

    public RetrofitResponseBodyConverter(Serializer serializer, Type type) {
        this.serializer = serializer;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String content = value.string();
        value.close();
        return this.serializer.deserialize(content, this.type);
    }
}
