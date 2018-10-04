package pk.zarsh.parkingmanagement

import java.util.*

/**
 * Created by HP on 9/26/18.
 */
class _BookingDetail(id:String,slot:String,date: String,from:String,to:String,uid:String) {
    var slot:String
    var date:String
    var from:String
    var to:String
    var id:String
    var uid:String

    init {
        this.slot = slot
        this.date = date
        this.from = from
        this.to = to
        this.uid = uid
        this.id = id
    }
    constructor():this("","","","","","")
}