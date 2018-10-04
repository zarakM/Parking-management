package pk.zarsh.parkingmanagement

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by HP on 10/1/18.
 */
internal class adapterReply(private val mCtx: Context, private val productList: List<_Feedback>) : RecyclerView.Adapter<adapterReply.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.reply_content, parent,false)
        view.getLayoutParams().width = parent.getWidth();
        return ProductViewHolder(view)
    }
    private var currentPosition = 100

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.textViewTitle.setText(product.userid)
        holder.textViewShortDesc.setText(product.message)
        holder.textreply.setText(product.reply)
        holder.textreply.setVisibility(View.GONE)
        holder.linear.setVisibility(View.GONE)

        if (currentPosition == position) {
            val slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.animat)
            holder.textreply.setVisibility(View.VISIBLE)
            holder.linear.setVisibility(View.VISIBLE)
            holder.textreply.startAnimation(slideDown)
            holder.linear.startAnimation(slideDown)
        }

        holder.textViewTitle.setOnClickListener(View.OnClickListener {
            //getting the position of the item to expand it
            currentPosition = position

            //reloding the list
            notifyDataSetChanged()
        })
        holder.updateReply.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().getReference("Feedback")
            ref.orderByChild("message").equalTo(product.message.toString()).addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    p0.children.forEach{
                        val map = HashMap<String,Any>()
                        map.put("reply",holder.reply.text.toString())
                        it.ref.updateChildren(map)
                    }}
            })
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }



    internal inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView
        var textViewShortDesc: TextView
        var textreply:TextView
        var d_reply: LinearLayout
        var linear:LinearLayout
        var reply: EditText
        var updateReply : Button
        init {
            textViewTitle = itemView.findViewById(R.id.head)
            textViewShortDesc = itemView.findViewById(R.id.desc)
            textreply = itemView.findViewById(R.id.dreply)
            d_reply = itemView.findViewById(R.id.holdtoreply)
            reply=itemView.findViewById(R.id.replies)
            linear = itemView.findViewById(R.id.vlinear)
            updateReply = itemView.findViewById(R.id.send)
        }
    }
}
