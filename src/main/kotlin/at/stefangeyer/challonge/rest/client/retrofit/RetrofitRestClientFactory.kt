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
import at.stefangeyer.challonge.serialization.Serializer
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class RetrofitRestClientFactory(credentials: Credentials, serializer: Serializer) : RestClientFactory {

    companion object {
        private val MEDIA_TYPE: MediaType = MediaType.parse("application/json; charset=UTF-8")!!
    }

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
                .addConverterFactory(createConverterFactory(serializer))
                .build()

        this.challonge = retrofit.create(Challonge::class.java)
    }

    override fun createTournamentRestClient(): TournamentRestClient = RetrofitTournamentRestClient(this.challonge)

    override fun createParticipantRestClient(): ParticipantRestClient = RetrofitParticipantRestClient(this.challonge)

    override fun createMatchRestClient(): MatchRestClient = RetrofitMatchRestClient(this.challonge)

    override fun createAttachmentRestClient(): AttachmentRestClient = RetrofitAttachmentRestClient(this.challonge)

    private fun createConverterFactory(serializer: Serializer): Converter.Factory {
        return object : Converter.Factory() {
            override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>,
                                               retrofit: Retrofit): Converter<ResponseBody, *> {
                return Converter<ResponseBody, Any> { value -> serializer.deserialize(value.string(), type) }
            }

            override fun requestBodyConverter(type: Type, parameterAnnotations: Array<out Annotation>,
                                              methodAnnotations: Array<out Annotation>,
                                              retrofit: Retrofit): Converter<*, RequestBody> {
                return Converter<Any, RequestBody> { value -> RequestBody.create(MEDIA_TYPE, serializer.serialize(value)) }
            }
        }
    }
}