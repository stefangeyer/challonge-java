package at.stefangeyer.challonge.rest.client.retrofit

import at.stefangeyer.challonge.model.Credentials
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceProvider constructor(
        private val credentials: Credentials,
        private val gson: Gson
) {

    private val retrofit: Retrofit

    init {
        val baseUrl = "https://api.challonge.com/v1/"

        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                    // Authorization Header with Basic HTTP Authentication
                    .header("Authorization", credentials.toHttpAuthString())
                    // only accept JSON as response
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        this.retrofit = Retrofit.Builder()
                .client(httpClientBuilder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    fun <S> createService(serviceClass: Class<S>): S = this.retrofit.create(serviceClass)
}