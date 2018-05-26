import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec

class KotlinTestDemo: FunSpec() {
    init {
        test("KotlinTest is working"){
            2 + 5 shouldBe 7
        }
    }
}