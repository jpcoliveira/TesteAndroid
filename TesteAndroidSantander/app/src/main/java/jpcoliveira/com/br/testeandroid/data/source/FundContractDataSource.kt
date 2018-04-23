package jpcoliveira.com.br.testeandroid.data.source

interface FundContractDataSource<T> {
    fun getFund(success: (fund: T?) -> Unit, failure: (throwable: Throwable?) -> Unit)
}