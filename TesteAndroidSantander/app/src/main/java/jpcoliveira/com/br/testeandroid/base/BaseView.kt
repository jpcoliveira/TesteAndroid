package jpcoliveira.com.br.testeandroid.base

interface BaseView<T> {
    fun setPresenter(presenter: T)
    fun showProgress()
    fun hideProgress()
    fun noInternetAvailable()
    fun showMessageError(message: String?)
}