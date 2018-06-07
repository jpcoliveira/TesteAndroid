package jpcoliveira.com.br.testeandroid.data.source.local

import android.content.Context
import com.google.gson.Gson
import jpcoliveira.com.br.testeandroid.data.source.FundContractDataSource
import jpcoliveira.com.br.testeandroid.fund.model.Fund
import jpcoliveira.com.br.testeandroid.util.FileReader
import java.io.IOException


class FundLocalDataSource(val context: Context?) : FundContractDataSource<Fund> {
    override fun getFund(success: (fund: Fund?) -> Unit, failure: (throwable: Throwable?) -> Unit) {
        parseJsonToFund(success = { fund -> success(fund) }, failure = { throwable -> failure(throwable) })
    }

    private fun parseJsonToFund(success: (fund: Fund?) -> Unit, failure: (throwable: Throwable?) -> Unit) {
        val gson = Gson()
        try {
            val json = FileReader.readFile(context!!,"local_fund.json")
            val fund = gson.fromJson(json, Fund::class.java)
            success(fund)
        } catch (e: IOException) {
            failure(e)
        }
    }
}
