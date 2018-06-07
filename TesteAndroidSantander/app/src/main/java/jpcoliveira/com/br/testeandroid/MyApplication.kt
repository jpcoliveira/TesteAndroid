package jpcoliveira.com.br.testeandroid

import android.app.Application
import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.conf.ConfigurableKodein
import com.github.salomonbrys.kodein.singleton
import jpcoliveira.com.br.testeandroid.di.apiModule
import jpcoliveira.com.br.testeandroid.di.presenterContactModule
import jpcoliveira.com.br.testeandroid.di.presenterFundModule

class MyApplication : Application(), KodeinAware {

    override val kodein = ConfigurableKodein(mutable = true)

    override fun onCreate() {
        super.onCreate()
        injection()
    }

    fun addModule(activityModules: Kodein.Module) {
        kodein.addImport(activityModules, true)
    }

    fun injection() {
        kodein.clear()
        kodein.addImport(apiModule, true)
        kodein.addImport(presenterContactModule, true)
        kodein.addImport(presenterFundModule, true)
        kodein.addConfig {

            bind<Context>() with singleton {
                applicationContext
            }
        }
    }
}

fun Context.asApp() = this.applicationContext as MyApplication

