package jpcoliveira.com.br.testeandroid.base

interface BaseView<T> {
    fun setPresenter(presenter: T)
}