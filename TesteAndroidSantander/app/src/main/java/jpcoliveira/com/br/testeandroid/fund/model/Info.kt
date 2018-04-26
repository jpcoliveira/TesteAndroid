package jpcoliveira.com.br.testeandroid.fund.model

import com.google.gson.annotations.SerializedName

open class Info(@SerializedName("data")
                val data: String = "",
                @SerializedName("name")
                val name: String = "")