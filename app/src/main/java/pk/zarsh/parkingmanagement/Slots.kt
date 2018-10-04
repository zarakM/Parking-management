package pk.zarsh.parkingmanagement


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.database.*
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable
import kotlinx.android.synthetic.main.slots.*
import java.util.concurrent.TimeUnit
import android.app.DatePickerDialog
import java.util.*
import android.widget.TimePicker
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.view.View
import android.widget.Button


class Slots : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    var sa = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.slots)
        val i =intent.extras
        var ss:String = i.getString("area")
        Log.d("area",ss)

        but1.visibility = View.GONE
        but2.visibility = View.GONE
        but3.visibility = View.GONE
        but4.visibility = View.GONE
        but5.visibility = View.GONE
        but6.visibility = View.GONE

        val spinner = findViewById(R.id.spinner) as Spinner
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.types, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        dateopen.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->

                        val mon = month+1
                        val dates = day.toString() + "/" + mon + "/" + year
                        date.text = dates
                    }, year, month, dayOfMonth)
            datePickerDialog.getDatePicker().minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }

        timeopen.setOnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(this,
                    TimePickerDialog.OnTimeSetListener{timePicker, selectedHour, selectedMinute ->
                        if (selectedMinute<10){
                            val min = "0"+selectedMinute
                            time.setText(selectedHour.toString() + ":" + min)
                        }
                        else{
                            val min =selectedMinute
                            time.setText(selectedHour.toString() + ":" + min)
                        }
                    }, hour, minute, true)
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }

        but1.setOnClickListener {
            progressBar2.visibility = View.VISIBLE
            val database = FirebaseDatabase.getInstance()
            val bookRef = database.getReference("Booking_details/"+ss)
            val ref = bookRef.push()
            val key = ref.key
            var min = ""
            time.text.toString().filter {
                if (it ==':'){
                }
                else{
                    min+=it
                }
                true
            }
            var p= spinner.selectedItem.toString()+"00"
            Log.d("periodp",p)
            Log.d("periodp",(p.toInt()+10).toString())
            var period = p.toInt()+min.toInt()

            val c = _BookingDetail(
                    key.toString(),"1",date.text.toString(),min,period.toString(),uid.toString()
            )
            ref.setValue(c)
            val handler = Handler()
            handler.postDelayed({
                progressBar2.visibility = View.GONE
                startActivity(Intent(applicationContext, Feedback::class.java))},
                    3000)
        }

        but2.setOnClickListener {
            progressBar2.visibility = View.VISIBLE
            val database = FirebaseDatabase.getInstance()
            val bookRef = database.getReference("Booking_details/"+ss)
            val ref = bookRef.push()
            val key = ref.key
            var min = ""
            time.text.toString().filter {
                if (it ==':'){
                }
                else{
                    min+=it
                }
                true
            }
            var p= spinner.selectedItem.toString()+"00"
            Log.d("periodp",p)
            Log.d("periodp",(p.toInt()+10).toString())
            var period = p.toInt()+min.toInt()

            val c = _BookingDetail(
                    key.toString(),"2",date.text.toString(),min,period.toString(),uid.toString()
            )
            var a = ref.setValue(c)
            ref.setValue(c)
            progressBar2.visibility = View.GONE
            startActivity(Intent(this, Feedback::class.java))
        }

        but3.setOnClickListener {
            progressBar2.visibility = View.VISIBLE

            val database = FirebaseDatabase.getInstance()
            val bookRef = database.getReference("Booking_details/"+ss)
            val ref = bookRef.push()
            val key = ref.key
            var min = ""
            time.text.toString().filter {
                if (it ==':'){
                }
                else{
                    min+=it
                }
                true
            }
            var p= spinner.selectedItem.toString()+"00"
            Log.d("periodp",p)
            Log.d("periodp",(p.toInt()+10).toString())
            var period = p.toInt()+min.toInt()

            val c = _BookingDetail(
                    key.toString(),"3",date.text.toString(),min,period.toString(),uid.toString()
            )
            var a = ref.setValue(c)
            ref.setValue(c)
            progressBar2.visibility = View.GONE
            startActivity(Intent(this, Feedback::class.java))
        }

        but4.setOnClickListener {
            progressBar2.visibility = View.VISIBLE

            val database = FirebaseDatabase.getInstance()
            val bookRef = database.getReference("Booking_details/" + ss)
            val ref = bookRef.push()
            val key = ref.key
            var min = ""
            time.text.toString().filter {
                if (it == ':') {
                } else {
                    min += it
                }
                true
            }
            var p = spinner.selectedItem.toString() + "00"
            Log.d("periodp", p)
            Log.d("periodp", (p.toInt() + 10).toString())
            var period = p.toInt() + min.toInt()

            val c = _BookingDetail(
                    key.toString(), "4", date.text.toString(), min, period.toString(), uid.toString()
            )
            ref.setValue(c)
            progressBar2.visibility = View.GONE
            startActivity(Intent(this, Feedback::class.java))
        }

        but5.setOnClickListener {
            progressBar2.visibility = View.VISIBLE

            val database = FirebaseDatabase.getInstance()
            val bookRef = database.getReference("Booking_details/"+ss)
            val ref = bookRef.push()
            val key = ref.key
            var min = ""
            time.text.toString().filter {
                if (it ==':'){
                }
                else{
                    min+=it
                }
                true
            }
            var p= spinner.selectedItem.toString()+"00"
            Log.d("periodp",p)
            Log.d("periodp",(p.toInt()+10).toString())
            var period = p.toInt()+min.toInt()

            val c = _BookingDetail(
                    key.toString(),"5",date.text.toString(),min,period.toString(),uid.toString()
            )
            var a = ref.setValue(c)
            ref.setValue(c)
            progressBar2.visibility = View.GONE
            startActivity(Intent(this, Feedback::class.java))
        }

        but6.setOnClickListener {
            progressBar2.visibility = View.VISIBLE
            val database = FirebaseDatabase.getInstance()
            val bookRef = database.getReference("Booking_details/"+ss)
            val ref = bookRef.push()
            val key = ref.key
            var min = ""
            time.text.toString().filter {
                if (it ==':'){
                }
                else{
                    min+=it
                }
                true
            }
            var p= spinner.selectedItem.toString()+"00"
            Log.d("periodp",p)
            Log.d("periodp",(p.toInt()+10).toString())
            var period = p.toInt()+min.toInt()

            val c = _BookingDetail(
                    key.toString(),"6",date.text.toString(),min,period.toString(),uid.toString()
            )
            ref.setValue(c)
            progressBar2.visibility = View.GONE
            startActivity(Intent(this, Feedback::class.java))
        }


        set.setOnClickListener {
            var p= spinner.selectedItem.toString()+"00"
            var min = ""
            time.text.toString().filter {
                if (it ==':'){
                }
                else{
                    min+=it
                }
                true
            }
            var pe = p.toInt()+min.toInt()
            checka(date.text.toString(),pe.toString(),min,ss)
        }
    }

    fun checka(date:String,from:String,to:String,ss:String) {
        val database = FirebaseDatabase.getInstance()
        val bookRef = database.getReference("Booking_details/"+ss)
        for (i in 1..6){
            var btn:Button =findViewById(R.id.but1) as Button
            if (i ==1){ btn = findViewById(R.id.but1) as Button
            btn.setBackgroundColor(Color.rgb(86,84,247))
            btn.isClickable = true
            }

            if (i ==2){ btn = findViewById(R.id.but2) as Button
                btn.setBackgroundColor(Color.rgb(86,84,247))
                btn.isClickable = true}

            if (i ==3){ btn = findViewById(R.id.but3) as Button
                btn.setBackgroundColor(Color.rgb(86,84,247))
                btn.isClickable = true}

            if (i ==4){ btn = findViewById(R.id.but4) as Button
                btn.setBackgroundColor(Color.rgb(86,84,247))
                btn.isClickable = true}

            if (i ==5){ btn = findViewById(R.id.but5) as Button
                btn.setBackgroundColor(Color.rgb(86,84,247))
                btn.isClickable = true}

            if (i ==6){ btn = findViewById(R.id.but6) as Button
                btn.setBackgroundColor(Color.rgb(86,84,247))
                btn.isClickable = true
            }

            bookRef.orderByChild("slot").equalTo(i.toString()).addValueEventListener(object :ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                    override fun onDataChange(p0: DataSnapshot) {
                        p0.children.forEach{
                            val data = it.getValue(_BookingDetail::class.java)
                            val datea = data?.date.toString()
                            val froma = data?.from?.toInt()
                            val toa = data?.to?.toInt()

                            if(date==datea) {
                                if (toa != null &&froma != null) {
                                    Log.d("tom",from)
                                    Log.d("tomm",to)
                                    Log.d("tommm",froma.toString())
                                    Log.d("tommmm",toa.toString())

                                    if((from.toInt()< froma &&to.toInt()<froma)||(toa<from.toInt()&&toa<to.toInt())){}

                                    else{
                                        btn.setBackgroundColor(Color.RED)
                                        btn.isClickable = false
                                    }
                                }
                                }
                            }
                        }
                })
        }
        but1.visibility = View.VISIBLE
        but2.visibility = View.VISIBLE
        but3.visibility = View.VISIBLE
        but4.visibility = View.VISIBLE
        but5.visibility = View.VISIBLE
        but6.visibility = View.VISIBLE
    }
}
