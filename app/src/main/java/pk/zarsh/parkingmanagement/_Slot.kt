package pk.zarsh.parkingmanagement

/**
 * Created by HP on 9/26/18.
 */
class _Slot(slot:String,available:String) {
    var slot:String
    var available:String

    init {
        this.slot = slot
        this.available = available
    }
    constructor():this("","")
}