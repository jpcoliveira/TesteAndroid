package jpcoliveira.com.br.testeandroid.contact

import jpcoliveira.com.br.testeandroid.data.source.ContactRepository

class ContactPresenter(val repository: ContactRepository?,
                       val view: ContactContract.View?) : ContactContract.Presenter {

    init {
        view?.setPresenter(this)
    }

    override fun buildForm() {

        val layoutBuilder = LayoutBuilder(view?.getContextFrag())



        repository?.getFieldsForBuildForm(
                success = { cells ->

                    val builder = layoutBuilder.Builder()

                    cells?.cells?.map { cell ->
                        when (cell.typefield?.toInt()) {
                            ContactPresenter.FIELD -> {
                                builder.buildEditText(cell)
                            }

                            ContactPresenter.TEXT -> {
                                builder.buildTextView(cell)
                            }

                            ContactPresenter.IMAGE -> {
                                builder.buildImage(cell)
                            }

                            ContactPresenter.CHECKBOX -> {
                                builder.buildCheckbox(cell)
                            }

                            ContactPresenter.BUTTON -> {
                                builder.buildButton(cell)
                            }
                            else -> {
                                builder.buildTextView(cell)
                            }
                        }
                    }

                    view?.showLayout(layoutBuilder.build())


                },

                failure = { throwable ->
                }
        )
    }

    companion object {
        val FIELD = 1
        val TEXT = 2
        val IMAGE = 3
        val CHECKBOX = 4
        val BUTTON = 5
    }
}