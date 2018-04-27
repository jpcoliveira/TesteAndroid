package jpcoliveira.com.br.testeandroid.fund

import jpcoliveira.com.br.testeandroid.data.source.FundRepository
import java.net.UnknownHostException

class FundPresenter(val repository: FundRepository?,
                    val view: FundContract.View?) : FundContract.Presenter {

    init {
        view?.setPresenter(this)
    }

    override fun getFund() {

        view?.showProgress()

        repository?.getFund(
                success = { fund ->
                    view?.showFund(fund)
                    view?.hideProgress()
                },
                failure = { throwable ->
                    when (throwable) {
                        is UnknownHostException -> view?.noInternet()
                        else -> view?.showMessageError(throwable?.message)
                    }
                    view?.hideProgress()
                }
        )
    }
}