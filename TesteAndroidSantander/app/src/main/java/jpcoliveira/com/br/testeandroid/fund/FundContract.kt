package jpcoliveira.com.br.testeandroid.fund

import jpcoliveira.com.br.testeandroid.base.BaseView

interface FundContract {

    interface Presenter {
        fun getFund()
    }

    interface View : BaseView<Presenter> {
        fun showFund(fund: Fund?)
        fun showMessageError(message: String?)
    }
}