package jpcoliveira.com.br.testeandroid.contact

import jpcoliveira.com.br.testeandroid.builder.LayoutGenerate
import jpcoliveira.com.br.testeandroid.data.source.ContactRepository

class ContactPresenter(val repository: ContactRepository?,
                       val view: ContactContract.View?) : ContactContract.Presenter {

    init {
        view?.setPresenter(this)
    }

    override fun buildForm() {

        val layoutGenerate = LayoutGenerate(view?.getContextFrag())

        repository?.getFieldsForBuildForm(
                success = { cells -> view?.showLayout(layoutGenerate.buildLayoutByListCell(cells?.cells)) },
                failure = { throwable -> view?.showMessageError(throwable?.message) }
        )
    }
}