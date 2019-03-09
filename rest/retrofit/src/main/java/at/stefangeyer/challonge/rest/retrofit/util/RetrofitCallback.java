package at.stefangeyer.challonge.rest.retrofit.util;

import at.stefangeyer.challonge.exception.DataAccessException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static at.stefangeyer.challonge.rest.retrofit.util.RetrofitUtil.parse;

class RetrofitCallback<T> implements Callback<T> {

    private at.stefangeyer.challonge.async.Callback<T> onSuccess;
    private at.stefangeyer.challonge.async.Callback<DataAccessException> onFailure;
    private String errorMessage;

    public RetrofitCallback(at.stefangeyer.challonge.async.Callback<T> onSuccess,
                            at.stefangeyer.challonge.async.Callback<DataAccessException> onFailure,
                            String errorMessage) {
        this.onSuccess = onSuccess;
        this.onFailure = onFailure;
        this.errorMessage = errorMessage;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            this.onSuccess.accept(parse(response));
        } catch (DataAccessException e) {
            this.onFailure.accept(e);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        this.onFailure.accept(new DataAccessException(this.errorMessage, t));
    }
}
