package pk.zarsh.parkingmanagement

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_receipt.*

class Receipt : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        val uids = FirebaseAuth.getInstance().currentUser?.uid
        val i =intent.extras
        var dates = i.getString("date")
        var slots = i.getString("slot")
        var froms = i.getString("from")
        var tos = i.getString("to")

        date.text = dates
        slot.text =slots
        from.text =froms
        to.text =tos
        uid.text = uids.toString()
    }
}
