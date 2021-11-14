import kotlinx.serialization.Serializable
import java.math.BigInteger
import java.security.MessageDigest

public fun makeSha512(input:String): String {
    val sha = MessageDigest.getInstance("SHA512")
    return BigInteger(1, sha.digest(input.toByteArray())).toString(16).padStart(32, '0')
}

@Serializable
data class Car(val make: String, val model: String ,val colour: String, val numberPlate: String, val description: String)
{
    var plateHash: String = makeSha512(numberPlate)


    companion object{
        const val path = "/carList"
    }
}
