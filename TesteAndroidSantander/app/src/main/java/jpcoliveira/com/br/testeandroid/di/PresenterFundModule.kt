package jpcoliveira.com.br.testeandroid.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import jpcoliveira.com.br.testeandroid.data.source.FundRepository
import jpcoliveira.com.br.testeandroid.data.source.remote.FundRemote
import jpcoliveira.com.br.testeandroid.fund.FundPresenter

val presenterFundModule = Kodein.Module {

    bind<FundPresenter>() with singleton {
        FundPresenter(instance(), instance())
    }

    bind<FundRepository>() with singleton {
        FundRepository(instance())
    }

    bind<FundRemote>() with singleton {
        FundRemote(instance())
    }
}