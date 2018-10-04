package pk.zarsh.parkingmanagement

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by HP on 9/26/18.
 */

class adapterSpecificBooking(internal var mContext: Context, internal var mData: ArrayList<_BookingDetail?>)
    : RecyclerView.Adapter<adapterSpecificBooking.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val v: View
        v = LayoutInflater.from(mContext).inflate(R.layout.booking_content, parent, false)
        return myViewHolder(v)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        var company = mData!![position]
        holder.date.text = company?.date
        holder.slot.text = company?.slot
        holder.from.text = company?.from
        holder.to.text = company?.to
        holder.uid.text = company?.uid

        holder.print.setOnClickListener {
            val i = Intent(mContext,Receipt::class.java)
            i.putExtra("date",company?.date)
            i.putExtra("slot",company?.slot)
            i.putExtra("from",company?.from)
            i.putExtra("to",company?.to)
            mContext.startActivity(i)
        }

        holder.itemView.setOnLongClickListener {
            val ref = FirebaseDatabase.getInstance().getReference("Booking_details/Defense")
            ref.orderByChild("id").equalTo(company?.id.toString()).addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    for (appleSnapshot in p0.getChildren()) {
                        appleSnapshot.getRef().removeValue()
                    }
                }
            })
            mData.removeAt(position)
            true
        }}

    override fun getItemCount(): Int {
        return mData!!.size
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date : TextView
        val slot : TextView
        val from : TextView
        val to : TextView
        val uid : TextView
        val print : Button
        init {
            date =itemView.findViewById(R.id.date) as TextView
            slot =itemView.findViewById(R.id.slot) as TextView
            from =itemView.findViewById(R.id.from) as TextView
            to =itemView.findViewById(R.id.to) as TextView
            uid =itemView.findViewById(R.id.uid) as TextView
            print =itemView.findViewById(R.id.print) as Button
        }
    }
}
