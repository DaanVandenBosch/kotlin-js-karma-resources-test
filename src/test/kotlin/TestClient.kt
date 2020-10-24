import kotlinx.browser.document
import kotlin.js.Promise
import kotlin.test.Test
import kotlin.test.assertEquals

class TestClient {
    @Test
    fun testSayHello(): Promise<Unit> {
        val container = document.createElement("div")
        return container.say()
            .then {
                assertEquals("Hello world!", container.textContent)
            }
    }
}
