package jpcoliveira.com.br.testeandroid.data.source.remote

import jpcoliveira.com.br.testeandroid.fund.model.Fund

class FundRemote(val api: Api) {

    fun getFunds(): List<Fund>? {
        return api.getFunds() as? List<Fund>
    }
}