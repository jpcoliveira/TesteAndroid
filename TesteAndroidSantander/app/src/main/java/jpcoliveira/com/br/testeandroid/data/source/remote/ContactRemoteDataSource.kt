package jpcoliveira.com.br.testeandroid.data.source.remote

class ContactRemoteDataSource(val api: Api) {

    fun getFieldsForBuildForm() {
        api.getFieldsForBuildForm()
    }
}