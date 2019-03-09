package at.stefangeyer.challonge.rest.retrofit.util;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class RetrofitUtil {

    public static <T> T parse(Response<T> response) throws DataAccessException {
        if (!response.isSuccessful()) {
            try {
                throw new DataAccessException("Request towards " + response.raw().request().url() + " was not successful ("
                        + response.code() + ") and returned: " + (response.errorBody() != null ? response.errorBody().string() : null));
            } catch (IOException e) {
                throw new DataAccessException("Cannot read error body", e);
            }

        } else {
            T body = response.body();
            if (body != null) {
                return body;
            } else {
                throw new DataAccessException("Received response body was null");
            }
        }
    }

    public static <T> retrofit2.Callback<T> callback(Callback<T> onSuccess, Callback<DataAccessException> onFailure,
                                                     String errorMessage) {
        return new retrofit2.Callback<>() {
            public void onFailure(Call<T> call, Throwable t) {
                onFailure.accept(new DataAccessException(errorMessage, t));
            }

            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    onSuccess.accept(parse(response));
                } catch (DataAccessException e) {
                    onFailure.accept(e);
                }
            }
        };
    }
}
