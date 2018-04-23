package jpcoliveira.com.br.testeandroid.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import jpcoliveira.com.br.testeandroid.BuildConfig
import jpcoliveira.com.br.testeandroid.data.source.remote.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = Kodein.Module {
    bind<Retrofit>() with singleton {
        Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    bind<Api>() with singleton {
        val retrofit: Retrofit = instance()
        retrofit.create(Api::class.java)
    }
}