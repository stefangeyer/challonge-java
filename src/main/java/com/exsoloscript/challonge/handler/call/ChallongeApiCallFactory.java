package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.util.ErrorUtil;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import retrofit2.Call;

@Singleton
public class ChallongeApiCallFactory {

    @Inject
    private ErrorUtil errorUtil;

    public <T> ChallongeApiCall<T> createApiCall(Call<T> retrofitCall) {
        return new ChallongeApiCall<>(retrofitCall, this.errorUtil);
    }

}
