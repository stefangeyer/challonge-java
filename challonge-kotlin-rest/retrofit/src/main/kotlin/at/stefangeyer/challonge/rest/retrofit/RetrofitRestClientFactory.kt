package at.stefangeyer.challonge.rest.retrofit

import at.stefangeyer.challonge.model.Credentials
import at.stefangeyer.challonge.rest.*
import at.stefangeyer.challonge.serializer.Serializer
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class RetrofitRestClientFactory : RestClientFactory {

    companion object {
        private const val BASE_URL = "https://api.challonge.com/v1/"
        private val MEDIA_TYPE: MediaType = MediaType.parse("application/json; charset=UTF-8")!!
    }

    private var challongeRetrofit: ChallongeRetrofit? = null

    override fun initialize(credentials: Credentials, serializer: Serializer) {
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
                .baseUrl(BASE_URL)
                .addConverterFactory(createConverterFactory(serializer))
                .build()

        this.challongeRetrofit = retrofit.create(ChallongeRetrofit::class.java)
    }

    override fun createTournamentRestClient(): TournamentRestClient {
        val local = this.challongeRetrofit
                ?: throw IllegalStateException("Attempted to create rest client before initialization")
        return RetrofitTournamentRestClient(local)
    }

    override fun createParticipantRestClient(): ParticipantRestClient {
        val local = this.challongeRetrofit
                ?: throw IllegalStateException("Attempted to create rest client before initialization")
        return RetrofitParticipantRestClient(local)
    }

    override fun createMatchRestClient(): MatchRestClient {
        val local = this.challongeRetrofit
                ?: throw IllegalStateException("Attempted to create rest client before initialization")
        return RetrofitMatchRestClient(local)
    }

    override fun createAttachmentRestClient(): AttachmentRestClient {
        val local = this.challongeRetrofit
                ?: throw IllegalStateException("Attempted to create rest client before initialization")
        return RetrofitAttachmentRestClient(local)
    }

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