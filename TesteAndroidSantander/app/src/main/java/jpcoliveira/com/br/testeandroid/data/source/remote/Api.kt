package jpcoliveira.com.br.testeandroid.data.source.remote

import jpcoliveira.com.br.testeandroid.contact.model.Cells
import jpcoliveira.com.br.testeandroid.fund.model.Fund
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("cells.json")
    fun getFieldsForBuildForm(): Call<Cells>?

    @GET("fund.json")
    fun getFund(): Call<Fund>?
}