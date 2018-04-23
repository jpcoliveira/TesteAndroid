package jpcoliveira.com.br.testeandroid.fund

import jpcoliveira.com.br.testeandroid.data.source.FundRepository

class FundPresenter(val repository: FundRepository?,
                    val view: FundContract.View?)
    : FundContract.Presenter {

    init {
        view?.setPresenter(this)
    }

    override fun getFund() {
        repository?.getFund(
                success = { fund ->
                    view?.showFund(fund)
                },
                failure = { throwable ->
                    view?.showMessageError(throwable?.message)
                }
        )
    }
}