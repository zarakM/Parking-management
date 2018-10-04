package pk.zarsh.parkingmanagement

/**
 * Created by HP on 9/26/18.
 */
class _Users(name:String,email:String) {
    var name:String
    var email:String

    init {
        this.name = name
        this.email = email
    }
    constructor():this("","")
}