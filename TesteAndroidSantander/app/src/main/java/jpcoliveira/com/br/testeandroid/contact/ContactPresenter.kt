package jpcoliveira.com.br.testeandroid.contact

import jpcoliveira.com.br.testeandroid.contact.model.CellsItem
import jpcoliveira.com.br.testeandroid.data.source.ContactRepository
import jpcoliveira.com.br.testeandroid.domain.builder.LayoutGenerate
import jpcoliveira.com.br.testeandroid.domain.builder.TypeField
import java.net.UnknownHostException

class ContactPresenter(val repository: ContactRepository?,
                       val view: ContactContract.View?) : ContactContract.Presenter {

    init {
        view?.setPresenter(this)
    }

    override fun buildForm() {

        val layoutGenerate = LayoutGenerate(view?.getContextFrag(), this)

        view?.showProgress()

        repository?.getFieldsForBuildForm(
                success = {
                    cells -> view?.showLayout(layoutGenerate.buildLayoutByListCell(cells?.cells))
                    view?.hideProgress()
                },
                failure = { throwable ->
                    when (throwable) {
                        is UnknownHostException -> view?.noInternetAvailable()
                        else -> view?.showMessageError(throwable?.message)
                    }
                    view?.hideProgress()
                }
        )
    }

    override fun clickSendMessage(items: List<CellsItem>) {
        processValidateFields(items)
    }

    private fun processValidateFields(items: List<CellsItem>) {
        items.map { item ->
            when (item.typefield) {
                TypeField.text.id,
                TypeField.telnumber.id,
                TypeField.email.id -> {
                    if (view?.isFieldValidationError(item.id!!)!!) {
                        return
                    }
                }
            }
        }

        sendMessage()
    }

    override fun sendMessage() {
        view?.sendMessage()
    }
}