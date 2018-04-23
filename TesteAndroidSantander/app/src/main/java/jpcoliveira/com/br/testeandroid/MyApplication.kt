package jpcoliveira.com.br.testeandroid

import android.app.Application
import android.content.Context
import com.github.salomonbrys.kodein.*
import jpcoliveira.com.br.testeandroid.di.apiModule
import jpcoliveira.com.br.testeandroid.di.presenterContactModule
import jpcoliveira.com.br.testeandroid.di.presenterFundModule

class MyApplication : Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        import(apiModule)
        import(presenterContactModule)
        import(presenterFundModule)

        bind<Context>() with singleton {
            applicationContext
        }
    }
}
