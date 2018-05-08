package jpcoliveira.com.br.testeandroid.contact

import android.content.Context
import android.widget.LinearLayout
import jpcoliveira.com.br.testeandroid.base.BaseView
import jpcoliveira.com.br.testeandroid.contact.model.CellsItem

interface ContactContract {

    interface Presenter {
        fun buildForm()
        fun clickSendMessage(items: List<CellsItem>)
        fun sendMessage()
    }

    interface View : BaseView {
        fun setPresenter(presenter: Presenter)
        fun getContextFrag(): Context?
        fun showLayout(layout: LinearLayout?)
        fun clickSendMessage()
        fun getTextById(resId: Int?): String?
        fun sendMessage()
        fun isEnable(resId: Int?): Boolean
        fun isFieldValidationError(resId: Int): Boolean
        fun showMessageErrorValidation(resId: Int?, message: String?)
    }
}