package jpcoliveira.com.br.testeandroid.contact

import jpcoliveira.com.br.testeandroid.R
import jpcoliveira.com.br.testeandroid.contact.model.CellsItem
import jpcoliveira.com.br.testeandroid.data.source.ContactRepository
import jpcoliveira.com.br.testeandroid.domain.builder.LayoutGenerate
import jpcoliveira.com.br.testeandroid.domain.builder.TypeField
import jpcoliveira.com.br.testeandroid.domain.validate.CellValidate
import jpcoliveira.com.br.testeandroid.domain.validate.EmailCellValidate
import jpcoliveira.com.br.testeandroid.domain.validate.PhoneCellValidate
import jpcoliveira.com.br.testeandroid.domain.validate.TextCellValidate
import jpcoliveira.com.br.testeandroid.exception.InvalidValidationException
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
                success = { cells ->
                    view?.showLayout(layoutGenerate.buildLayoutByListCell(cells?.cells))
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

        try {

            items.map { item ->

                val validate: CellValidate?

                validate = when (item.typefield) {
                    TypeField.text.id -> {
                        TextCellValidate(view?.getContextFrag()?.getString(R.string.required_field))
                    }
                    TypeField.telnumber.name -> {
                        PhoneCellValidate(view?.getContextFrag()?.getString(R.string.invalid_phone))
                    }
                    TypeField.email.id -> {
                        EmailCellValidate(view?.getContextFrag()?.getString(R.string.invalid_mail))
                    }
                    else -> null
                }

                checkNotValid(validate, item.id)
            }

            sendMessage()
        } catch (ex: InvalidValidationException) {
            showMessageErrorValidation(ex.resId, ex.message)
        }
    }

    private fun checkNotValid(validate: CellValidate?, resId: Int?) {
        validate?.let {
            validate.validate(view?.getTextById(resId)!!)

            if (isNotValid(validate, resId)!!) {
                throw InvalidValidationException(validate.getMessageError(), resId)
            }
        }
    }

    private fun isNotValid(validate: CellValidate?, resId: Int?): Boolean? {
        return !validate?.isValid()!! && view?.isEnable(resId)!!
    }

    private fun showMessageErrorValidation(resId: Int?, message: String?) {
        view?.showMessageErrorValidation(resId, message)
    }

    override fun sendMessage() {
        view?.sendMessage()
    }
}