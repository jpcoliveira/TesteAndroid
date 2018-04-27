package jpcoliveira.com.br.testeandroid.data.source.remote

import jpcoliveira.com.br.testeandroid.contact.model.Cells
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactRemoteDataSource(val api: Api) {

    fun getFieldsForBuildForm(success: (cells: Cells?) -> Unit, failure: (throwable: Throwable?) -> Unit) {
        api.getFieldsForBuildForm()?.enqueue(object : Callback<Cells> {
            override fun onResponse(call: Call<Cells>?, response: Response<Cells>?) {
                success(response?.body())
            }

            override fun onFailure(call: Call<Cells>?, t: Throwable?) {
                failure(t)
            }
        })
    }
}