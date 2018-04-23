package jpcoliveira.com.br.testeandroid.fund.model

import com.google.gson.annotations.SerializedName

data class Months(@SerializedName("fund")
                  val fund: Double = 0.0,
                  @SerializedName("CDI")
                  val cdi: Double = 0.0)