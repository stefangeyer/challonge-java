package at.stefangeyer.challonge.rest.retrofit;

import at.stefangeyer.challonge.async.Callback;
import at.stefangeyer.challonge.exception.DataAccessException;
import retrofit2.Call;
import retrofit2.Response;

public class RetrofitUtil {

    public static <T> T parse(Response<T> response) throws DataAccessException {
        if (!response.isSuccessful()) {
            throw new DataAccessException("Request towards " + response.raw().request().url() + " was not successful ("
                    + response.code() + ") and returned: " + response.errorBody());
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
