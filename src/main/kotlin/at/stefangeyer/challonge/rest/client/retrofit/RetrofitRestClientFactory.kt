package at.stefangeyer.challonge.rest.client.retrofit

import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.rest.AttachmentRestClient
import at.stefangeyer.challonge.rest.MatchRestClient
import at.stefangeyer.challonge.rest.ParticipantRestClient
import at.stefangeyer.challonge.rest.TournamentRestClient
import at.stefangeyer.challonge.rest.client.RestClientFactory
import at.stefangeyer.challonge.rest.implementation.retrofit.RetrofitAttachmentRestClient
import at.stefangeyer.challonge.rest.implementation.retrofit.RetrofitMatchRestClient
import at.stefangeyer.challonge.rest.implementation.retrofit.RetrofitParticipantRestClient
import at.stefangeyer.challonge.rest.implementation.retrofit.RetrofitTournamentRestClient
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRestClientFactory(credentials: Credentials, gson: Gson): RestClientFactory {

    private val challonge: Challonge

    init {
        val baseUrl = "https://api.challonge.com/v1/"

        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                    .header("Authorization", credentials.toHttpAuthString()) // using HTTP Basic Authentication
                    .header("Accept", "application/json") // only accept JSON as response
                    .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val retrofit = Retrofit.Builder()
                .client(httpClientBuilder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        this.challonge = retrofit.create(Challonge::class.java)
    }

    override fun createTournamentRestClient(): TournamentRestClient = RetrofitTournamentRestClient(this.challonge)

    override fun createParticipantRestClient(): ParticipantRestClient = RetrofitParticipantRestClient(this.challonge)

    override fun createMatchRestClient(): MatchRestClient = RetrofitMatchRestClient(this.challonge)

    override fun createAttachmentRestClient(): AttachmentRestClient = RetrofitAttachmentRestClient(this.challonge)
}