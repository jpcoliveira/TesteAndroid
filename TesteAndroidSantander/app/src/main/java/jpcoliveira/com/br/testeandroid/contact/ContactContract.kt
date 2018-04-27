package jpcoliveira.com.br.testeandroid.contact

import jpcoliveira.com.br.testeandroid.base.BaseView

interface ContactContract {

    interface Presenter {
        fun buildForm()

    }

    interface View : BaseView<Presenter> {

    }
}