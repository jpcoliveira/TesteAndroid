package jpcoliveira.com.br.testeandroid.fund

import jpcoliveira.com.br.testeandroid.base.BaseView

interface FundContract {

    interface Presenter {
        fun getFunds()

    }

    interface View : BaseView<Presenter> {

    }
}