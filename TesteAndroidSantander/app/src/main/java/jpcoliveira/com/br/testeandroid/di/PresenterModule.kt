package jpcoliveira.com.br.testeandroid.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import jpcoliveira.com.br.testeandroid.data.source.remote.Api
import retrofit2.Retrofit

val presenterModule = Kodein.Module {



   /* bind<>() with singleton {
        val retrofit: Retrofit = instance()
        retrofit.create(Api::class.java)
    }*/
}