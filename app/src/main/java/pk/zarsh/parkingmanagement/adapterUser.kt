package pk.zarsh.parkingmanagement

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

/**
 * Created by HP on 9/26/18.
 */
class adapterUser(internal var mContext: Context, internal var mData: ArrayList<_Users?>) : RecyclerView.Adapter<adapterUser.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val v: View
        v = LayoutInflater.from(mContext).inflate(R.layout.user_content, parent, false)
        return myViewHolder(v)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        var company = mData!![position]
        holder.email.text = company?.email
        holder.name.text =company?.name

    }

    override fun getItemCount(): Int {
        return mData!!.size
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView
        val email : TextView
        init {
            name =itemView.findViewById(R.id.name) as TextView
            email =itemView.findViewById(R.id.email) as TextView
        }
    }
}
