package jpcoliveira.com.br.testeandroid.contact.model

import com.google.gson.annotations.SerializedName

data class CellsItem(@SerializedName("typefield")
                     val typefield: String? = "",
                     @SerializedName("hidden")
                     val hidden: Boolean? = false,
                     @SerializedName("show")
                     val show: String? = "",
                     @SerializedName("id")
                     val id: Int? = 0,
                     @SerializedName("type")
                     val type: Int? = 0,
                     @SerializedName("message")
                     val message: String? = "",
                     @SerializedName("topSpacing")
                     val topSpacing: Int? = 0,
                     @SerializedName("required")
                     val required: Boolean? = false)