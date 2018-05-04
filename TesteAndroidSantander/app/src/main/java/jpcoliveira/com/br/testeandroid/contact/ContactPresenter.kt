package jpcoliveira.com.br.testeandroid.contact

import jpcoliveira.com.br.testeandroid.contact.model.CellsItem
import jpcoliveira.com.br.testeandroid.data.source.ContactRepository
import jpcoliveira.com.br.testeandroid.domain.builder.LayoutGenerate
import jpcoliveira.com.br.testeandroid.domain.builder.TypeField

class ContactPresenter(val repository: ContactRepository?,
                       val view: ContactContract.View?) : ContactContract.Presenter {

    init {
        view?.setPresenter(this)
    }

    override fun buildForm() {

        val layoutGenerate = LayoutGenerate(view?.getContextFrag(), this)

        repository?.getFieldsForBuildForm(
                success = { cells -> view?.showLayout(layoutGenerate.buildLayoutByListCell(cells?.cells)) },
                failure = { throwable -> view?.showMessageError(throwable?.message) }
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