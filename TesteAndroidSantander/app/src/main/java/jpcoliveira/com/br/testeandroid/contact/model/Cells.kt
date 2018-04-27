package jpcoliveira.com.br.testeandroid.contact.model

import com.google.gson.annotations.SerializedName

data class Cells(@SerializedName("cells")
                 val cells: List<CellsItem>?)