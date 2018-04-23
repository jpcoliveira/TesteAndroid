package jpcoliveira.com.br.testeandroid.fund.model

import com.google.gson.annotations.SerializedName

data class InfoItem(@SerializedName("data")
                    val data: String = "",
                    @SerializedName("name")
                    val name: String = "")