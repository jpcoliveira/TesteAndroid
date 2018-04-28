package jpcoliveira.com.br.testeandroid.contact

import android.content.Context
import android.widget.LinearLayout
import jpcoliveira.com.br.testeandroid.base.BaseView

interface ContactContract {

    interface Presenter {
        fun buildForm()
    }

    interface View : BaseView<Presenter> {
        fun getContextFrag(): Context?
        fun showLayout(layout: LinearLayout?)
    }
}