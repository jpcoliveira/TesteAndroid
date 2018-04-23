package jpcoliveira.com.br.testeandroid.data.source.local

import android.content.Context
import com.google.gson.Gson
import jpcoliveira.com.br.testeandroid.data.source.FundContractDataSource
import jpcoliveira.com.br.testeandroid.fund.model.Fund
import java.io.IOException
import java.nio.charset.Charset


class FundLocalDataSource(val context: Context?) : FundContractDataSource<Fund> {
    override fun getFund(success: (fund: Fund?) -> Unit, failure: (throwable: Throwable?) -> Unit) {
        parseJsonToFund(success = { fund -> success(fund) }, failure = { throwable -> failure(throwable) })
    }

    private fun parseJsonToFund(success: (fund: Fund?) -> Unit, failure: (throwable: Throwable?) -> Unit) {
        val gson = Gson()
        try {
            val json = getJsonByFileName("local_fund.json")
            val fund = gson.fromJson(json, Fund::class.java)
            success(fund)
        } catch (e: IOException) {
            failure(e)
        }
    }

    private fun getJsonByFileName(fileName: String): String? {
        var json: String? = null

        try {
            val inputStream = context?.assets?.open(fileName)
            val size = inputStream?.available()
            val buffer = ByteArray(size ?: 0)
            inputStream?.read(buffer)
            inputStream?.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }
}
