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
        fun showErrorValidateEmail(resId: Int?)
        fun showErrorValidatePhone(resId: Int?)
        fun showErrorValidateText(resId: Int?)
    }

    interface View : BaseView<Presenter> {
        fun getContextFrag(): Context?
        fun showLayout(layout: LinearLayout?)
        fun clickSendMessage()
        fun getTextById(id: Int?): String?
        fun showErrorValidateEmail(resId: Int?)
        fun showErrorValidatePhone(resId: Int?)
        fun showErrorValidateText(resId: Int?)
        fun sendMessage()
        fun isEnable(id: Int?): Boolean
    }
}