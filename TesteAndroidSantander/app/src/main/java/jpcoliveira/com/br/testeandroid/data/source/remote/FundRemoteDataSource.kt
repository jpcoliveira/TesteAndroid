package jpcoliveira.com.br.testeandroid.data.source.remote

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FundRemoteDataSource(val api: Api) {

    fun getFund(success: (fund: Fund?) -> Unit, failure: (throwable: Throwable?) -> Unit) {
        api.getFund()?.enqueue(object : Callback<Fund> {
            override fun onResponse(call: Call<Fund>?, response: Response<Fund>?) {
                success(response?.body())
            }

            override fun onFailure(call: Call<Fund>?, t: Throwable?) {
                failure(t)
            }
        })
    }
}