import kotlinx.serialization.Serializable


@Serializable
data class CarData(val make: String, val model: String ,val colour: String, val numberPlate: String, val description: String)
{
    var uniqueID = "temporary for now"
    companion object{
        const val path = "/carList"
    }
}
