package jpcoliveira.com.br.testeandroid.data.source.remote

class ContactRemote(val api: Api) {

    fun getFieldsForBuildForm() {
        api.getFieldsForBuildForm()
    }
}