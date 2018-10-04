package pk.zarsh.parkingmanagement

/**
 * Created by HP on 9/26/18.
 */
class _Feedback(userid:String,message:String,reply:String) {
    var userid:String
    var message:String
    var reply:String

    init {
        this.userid = userid
        this.message = message
        this.reply = reply
    }
    constructor():this("","","")


}