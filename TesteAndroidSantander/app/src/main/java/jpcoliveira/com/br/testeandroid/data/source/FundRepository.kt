package jpcoliveira.com.br.testeandroid.data.source

import jpcoliveira.com.br.testeandroid.data.source.remote.FundRemoteDataSource
import jpcoliveira.com.br.testeandroid.fund.model.Fund

class FundRepository(val dataSource: FundRemoteDataSource) {
    fun getFund(success: (fund: Fund?) -> Unit, failure: (throwable: Throwable?) -> Unit) {
        dataSource.getFund(
                success = { fund -> success(fund) },
                failure = { throwable -> failure(throwable) }
        )
    }
}