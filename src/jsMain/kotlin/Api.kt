import io.ktor.http.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

import kotlinx.browser.window

val endpoint = window.location.origin // only needed until https://youtrack.jetbrains.com/issue/KTOR-453 is resolved

val jsonClient = HttpClient {
    install(JsonFeature) { serializer = KotlinxSerializer() }
}

suspend fun getShoppingList(): List<CarData> {
    return jsonClient.get(endpoint + CarData.path)
}

suspend fun addShoppingListItem(shoppingListItem: CarData) {
    jsonClient.post<Unit>(endpoint + CarData.path) {
        contentType(ContentType.Application.Json)
        body = shoppingListItem
    }
}

suspend fun deleteShoppingListItem(shoppingListItem: CarData) {
    jsonClient.delete<Unit>(endpoint + CarData.path + "/${shoppingListItem.uniqueID}")
}