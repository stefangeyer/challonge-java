package at.stefangeyer.challonge.rest.retrofit.util;

import at.stefangeyer.challonge.rest.retrofit.ChallongeRetrofit;
import at.stefangeyer.challonge.rest.retrofit.MockChallongeRetrofit;
import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import java.util.concurrent.TimeUnit;

public class MockChallongeRetrofitFactory {

    private static final String BASE_URL = "https://api.challonge.com/v1/";

    public static MockChallongeRetrofit create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();

        NetworkBehavior networkBehavior = NetworkBehavior.create();
        networkBehavior.setDelay(0, TimeUnit.MILLISECONDS);
        networkBehavior.setVariancePercent(0);
        networkBehavior.setErrorPercent(0);
        networkBehavior.setFailurePercent(0);

        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit).networkBehavior(networkBehavior).build();

        BehaviorDelegate<ChallongeRetrofit> delegate = mockRetrofit.create(ChallongeRetrofit.class);

        return new MockChallongeRetrofit(delegate);
    }
}
