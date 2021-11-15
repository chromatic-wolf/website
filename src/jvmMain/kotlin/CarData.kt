import kotlinx.serialization.Serializable
import HashFunctions
import java.math.BigInteger
import java.security.MessageDigest
import java.util.UUID






@Serializable
data class CarData(val make: String, val model: String ,val colour: String, val numberPlate: String, val description: String)
{
    val uniqueID = UUID.randomUUID().toString()
    companion object{
        const val path = "/carList"
    }
}
