package jpcoliveira.com.br.testeandroid.fund.model

import com.google.gson.annotations.SerializedName

data class Screen(@SerializedName("riskTitle")
                  val riskTitle: String = "",
                  @SerializedName("infoTitle")
                  val infoTitle: String = "",
                  @SerializedName("whatIs")
                  val whatIs: String = "",
                  @SerializedName("definition")
                  val definition: String = "",
                  @SerializedName("risk")
                  val risk: Int = 0,
                  @SerializedName("downInfo")
                  val downInfo: List<DownInfoItem>?,
                  @SerializedName("title")
                  val title: String = "",
                  @SerializedName("fundName")
                  val fundName: String = "",
                  @SerializedName("moreInfo")
                  val moreInfo: MoreInfo,
                  @SerializedName("info")
                  val info: List<InfoItem>?)