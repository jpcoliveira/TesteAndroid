package jpcoliveira.com.br.testeandroid.data.source

import android.util.MalformedJsonException
import jpcoliveira.com.br.testeandroid.data.source.local.FundLocalDataSource
import jpcoliveira.com.br.testeandroid.data.source.remote.FundRemoteDataSource
import jpcoliveira.com.br.testeandroid.fund.model.Fund

class FundRepository(val dataSource: FundRemoteDataSource, val localDatasource: FundLocalDataSource) {
    fun getFund(success: (fund: Fund?) -> Unit, failure: (throwable: Throwable?) -> Unit) {
        dataSource.getFund(
                success = { fund -> success(fund) },
                failure = {
                    if (it is MalformedJsonException) {
                        localDatasource.getFund(
                                success = { fund -> success(fund) },
                                failure = { throwable1 -> failure(throwable1) }
                        )
                    } else {
                        failure(it)
                    }
                }
        )
    }
}