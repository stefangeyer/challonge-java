package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.util.ErrorUtil;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import retrofit2.Call;

/**
 * This factory is used to inject the ErrorUtil with Guice and to hand it over to the API calls
 *
 * @author EXSolo
 * @version 20160822.1
 */
@Singleton
public class RetrofitChallongeApiCallFactory {

    @Inject
    private ErrorUtil errorUtil;

    /**
     * Creates a new API call
     *
     * @param retrofitCall retrofitCall
     * @param <T>          Type of the call
     * @return RetrofitChallongeApiCall
     */
    public <T> RetrofitChallongeApiCall<T> createApiCall(Call<T> retrofitCall) {
        return new RetrofitChallongeApiCall<>(retrofitCall, this.errorUtil);
    }

}
