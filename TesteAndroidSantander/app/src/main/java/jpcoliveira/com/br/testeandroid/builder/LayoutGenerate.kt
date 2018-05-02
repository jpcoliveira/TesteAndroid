package jpcoliveira.com.br.testeandroid.builder

import android.content.Context
import android.widget.LinearLayout
import jpcoliveira.com.br.testeandroid.contact.ContactContract
import jpcoliveira.com.br.testeandroid.contact.model.CellsItem

class LayoutGenerate(private val context: Context?, private val presenter: ContactContract.Presenter?) {

    private val layoutBuilder by lazy {
        LayoutBuilder(context)
    }

    fun buildLayoutByListCell(cells: List<CellsItem>?): LinearLayout? {

        val builder = layoutBuilder.Builder(presenter)

        builder.buildContainer()

        cells?.map { cell ->
            when (cell.type) {
                Type.field.id -> builder.buildEditText(cell)
                Type.text.id -> builder.buildTextView(cell)
                Type.image.id -> builder.buildImage(cell)
                Type.checkbox.id -> builder.buildCheckbox(cell)
                Type.send.id -> builder.buildButton(cell,cells)
                else -> builder.buildTextView(cell)
            }
        }
        return builder.build()
    }
}