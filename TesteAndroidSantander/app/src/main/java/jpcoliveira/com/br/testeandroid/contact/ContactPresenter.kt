package jpcoliveira.com.br.testeandroid.contact

import jpcoliveira.com.br.testeandroid.data.source.ContactRepository

class ContactPresenter(val repository: ContactRepository?,
                       val view: ContactContract.View?) : ContactContract.Presenter {

    init {
        view?.setPresenter(this)
    }

    override fun buildForm() {
        repository?.getFieldsForBuildForm(
                success = { cells ->

                    view?.addLayout()
                },

                failure = { throwable ->
                }
        )
    }
}