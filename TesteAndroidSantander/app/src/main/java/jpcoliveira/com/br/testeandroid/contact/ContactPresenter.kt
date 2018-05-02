package jpcoliveira.com.br.testeandroid.contact

import jpcoliveira.com.br.testeandroid.builder.LayoutGenerate
import jpcoliveira.com.br.testeandroid.builder.TypeField
import jpcoliveira.com.br.testeandroid.contact.model.CellsItem
import jpcoliveira.com.br.testeandroid.data.source.ContactRepository
import jpcoliveira.com.br.testeandroid.util.FieldValidate

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
                TypeField.text.id -> {
                    if (item.required!!
                            && view?.getTextById(item.id).isNullOrEmpty()) {
                        showErrorValidateText(item.id)
                        return
                    }
                }
                TypeField.email.id -> {
                    if (view?.isEnable(item.id)!! && !isValidEmail(view.getTextById(item.id))) {
                        showErrorValidateEmail(item.id)
                        return
                    }
                }
                TypeField.telnumber.name -> {
                    if (!isValidPhone(view?.getTextById(item.id))) {
                        showErrorValidatePhone(item.id)
                        return
                    }
                }
            }
        }

        sendMessage()
    }

    fun isValidEmail(text: String?): Boolean {
        return FieldValidate.isValidEmail(text)
    }

    fun isValidPhone(text: String?): Boolean {
        return FieldValidate.isValidPhone(text)
    }

    override fun showErrorValidateEmail(resId: Int?) {
        view?.showErrorValidateEmail(resId)
    }

    override fun showErrorValidatePhone(resId: Int?) {
        view?.showErrorValidatePhone(resId)
    }

    override fun showErrorValidateText(resId: Int?) {
        view?.showErrorValidateText(resId)
    }

    override fun sendMessage() {
        view?.sendMessage()
    }
}