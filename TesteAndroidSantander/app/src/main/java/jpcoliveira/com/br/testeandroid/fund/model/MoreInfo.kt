package jpcoliveira.com.br.testeandroid.fund.model

import com.google.gson.annotations.SerializedName

data class MoreInfo(@SerializedName("month")
                    val month: Month,
                    @SerializedName("year")
                    val year: Year,
                    @SerializedName("12months")
                    val Months: Months)