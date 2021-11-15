import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class HashFunctions {

    companion object {

        public fun makeSha512(input: String): String {
            try {
                val sha = MessageDigest.getInstance("SHA-512")
                return BigInteger(1, sha.digest(input.toByteArray())).toString(16).padStart(32, '0')
            } catch (e: NoSuchAlgorithmException) {
                println("ERROR Invalid algorithm")


            }
            return ""
        }
    }
}