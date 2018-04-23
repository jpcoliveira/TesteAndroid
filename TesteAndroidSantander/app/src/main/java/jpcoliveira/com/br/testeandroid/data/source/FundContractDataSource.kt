package jpcoliveira.com.br.testeandroid.data.source

interface FundContractDataSource {
    fun <T> getFund(success: (fund: T?) -> Unit, failure: (throwable: Throwable?) -> Unit)
}