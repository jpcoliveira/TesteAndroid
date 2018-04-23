package jpcoliveira.com.br.testeandroid.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import jpcoliveira.com.br.testeandroid.contact.ContactPresenter
import jpcoliveira.com.br.testeandroid.data.source.ContactRepository
import jpcoliveira.com.br.testeandroid.data.source.remote.ContactRemoteDataSource

val presenterContactModule = Kodein.Module {

    bind<ContactPresenter>() with singleton {
        ContactPresenter(instance(), instance())
    }

    bind<ContactRepository>() with singleton {
        ContactRepository(instance())
    }

    bind<ContactRemoteDataSource>() with singleton {
        ContactRemoteDataSource(instance())
    }
}