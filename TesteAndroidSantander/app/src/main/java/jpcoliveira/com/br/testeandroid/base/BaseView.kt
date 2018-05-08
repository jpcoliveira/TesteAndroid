package jpcoliveira.com.br.testeandroid.base

interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun noInternetAvailable()
    fun showMessageError(message: String?)
}