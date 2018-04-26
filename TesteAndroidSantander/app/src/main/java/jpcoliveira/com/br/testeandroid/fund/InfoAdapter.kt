package jpcoliveira.com.br.testeandroid.fund

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import jpcoliveira.com.br.testeandroid.R
import jpcoliveira.com.br.testeandroid.fund.model.Info
import jpcoliveira.com.br.testeandroid.fund.model.InfoItem

class InfoAdapter(val infoItemList: List<Info>?) : RecyclerView.Adapter<InfoAdapter.MoreInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreInfoViewHolder {
        return MoreInfoViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_info_fund, parent, false)
        )
    }

    override fun getItemCount() = infoItemList?.size ?: 0

    override fun onBindViewHolder(holder: MoreInfoViewHolder, position: Int) {
        holder.bindData(infoItemList?.get(position))
    }

    inner class MoreInfoViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val infoName = itemView?.findViewById<TextView>(R.id.info_name)
        val infoData = itemView?.findViewById<TextView>(R.id.info_data)
        val imageDownload = itemView?.findViewById<ImageView>(R.id.info_download)

        fun bindData(moreInfo: Info?) {
            infoName?.text = moreInfo?.name

            if (moreInfo is InfoItem) {
                infoData?.text = moreInfo.data
                imageDownload?.visibility = View.GONE
            } else {
                infoData?.text = itemView?.resources?.getString(R.string.download)
                infoData?.setTextColor(itemView?.resources!!.getColor(R.color.santander_default))
                imageDownload?.visibility = View.VISIBLE
            }
        }
    }
}