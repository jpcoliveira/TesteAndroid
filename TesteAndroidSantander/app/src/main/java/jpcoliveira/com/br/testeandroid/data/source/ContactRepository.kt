package jpcoliveira.com.br.testeandroid.data.source

import jpcoliveira.com.br.testeandroid.contact.model.Cells
import jpcoliveira.com.br.testeandroid.data.source.remote.ContactRemoteDataSource

class ContactRepository(val dataSource: ContactRemoteDataSource) {

    fun getFieldsForBuildForm(success: (cells: Cells?) -> Unit, failure: (throwable: Throwable?) -> Unit) {
        dataSource.getFieldsForBuildForm(
                success = { cells -> success(cells) },
                failure = { throwable -> failure(throwable) }
        )
    }
}