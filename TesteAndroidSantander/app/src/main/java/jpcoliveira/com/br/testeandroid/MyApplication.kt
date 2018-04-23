package jpcoliveira.com.br.testeandroid

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy
import jpcoliveira.com.br.testeandroid.di.apiModule
import jpcoliveira.com.br.testeandroid.di.presenterContactModule
import jpcoliveira.com.br.testeandroid.di.presenterFundModule

class MyApplication : Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        import(apiModule)
        import(presenterContactModule)
        import(presenterFundModule)
    }
}
