package jpcoliveira.com.br.testeandroid.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

val apiModule = Kodein.Module {
    /*bind<Retrofit>() with singleton {
        Retrofit.Builder()
                .baseUrl(WEATHER_API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient())
                .build()
    }

    bind<WeatherLookupAPI>() with singleton {
        val retrofit: Retrofit = instance()
        retrofit.create(WeatherLookupAPI::class.java)
    }*/
}