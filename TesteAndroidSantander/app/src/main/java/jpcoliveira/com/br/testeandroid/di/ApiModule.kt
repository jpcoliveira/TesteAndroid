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

var BASE_URL = BuildConfig.BASE_URL

val apiModule = Kodein.Module(allowSilentOverride = true) {
    bind<Retrofit.Builder>() with singleton {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
    }

    bind<Retrofit>() with singleton {
        val builder: Retrofit.Builder = instance()
        builder.build()
    }

    bind<Api>() with singleton {
        val retrofit: Retrofit = instance()
        retrofit.create(Api::class.java)
    }
}