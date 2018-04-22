package jpcoliveira.com.br.testeandroid.data.source.remote

import retrofit2.http.GET

interface Api {

    @GET("cells.json")
    fun getFieldsForBuildForm(): List<Any>?

    @GET("fund.json")
    fun getFunds(): List<Any>?
}