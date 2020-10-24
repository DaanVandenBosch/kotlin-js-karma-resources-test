import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Node
import kotlin.js.Promise

fun main() {
    window.onload = { document.body?.say() }
}

fun Node.say(): Promise<Unit> =
    window.fetch("data.json")
        .then { it.json() }
        .then { it.asDynamic()["say"] as String }
        .then {
            appendChild(document.createTextNode(it))
        }
