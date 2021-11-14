import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.*
import org.litote.kmongo.reactivestreams.KMongo
import com.mongodb.ConnectionString
import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.util.ArrayList





fun getFilesCount(file: String): MutableList<String> {
    val results: MutableList<String> = ArrayList()


    val files = File(file).listFiles()
//If this pathname does not denote a directory, then listFiles() returns null.

//If this pathname does not denote a directory, then listFiles() returns null.
    for (file in files) {
        if (file.isFile) {
            results.add(file.name)
        }
    }
    return results
}



fun main() {

    embeddedServer(Netty, 9090) {
        install(ContentNegotiation) {
            json()
        }
        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Patch)
            method(HttpMethod.Delete)
            anyHost()
        }
        install(Compression) {
            gzip()
        }
        routing {
            val carList = mutableListOf(
                Car("Nissan", "Skyline R33 GTST", "Blue", "160AP4", "My Car"),
                Car("Subaru", "Wrx", "Black", "680TER", "My other car"),
                Car("Nissan", "Silvia s15", "Red", "Test123","My wanted car")
            )

            route(Car.path) {
                get {
                    call.respond(carList)
                }
                post {
                    carList += call.receive<Car>()
                    call.respond(HttpStatusCode.OK)
                }
                delete("/{numberPlate}") {
                    val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
                    carList.removeIf { it.plateHash == Car.make }
                    call.respond(HttpStatusCode.OK)
                }
            }



        }
    }.start(wait = true)
}



/*
Old Get
get("/hello") {

                val fileList: MutableList<String> = getFilesCount("/home/caleb/data/www/CarsAndCoffee/images")


                val size = fileList.size
                println("DEBUG: Size of list: $size")
                val rnds = (0 until size).random() // generated random from 0 to 10 included

                    println("Request from: " + call.request.origin.remoteHost)
                    println("DEBUG: Responding with random image: " + fileList[rnds])
                    call.respondFile(File("/home/caleb/data/www/CarsAndCoffee/images/" + fileList[rnds]))



            }

 */