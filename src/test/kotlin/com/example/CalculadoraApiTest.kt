import com.example.calculadora.Calculadora
import com.example.calculadora.CalculadoraAPI
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CalculadoraApiTest {

    // Testes Unitários
    @Test
    fun `shouldReturn8For5Plus3Addition`() {
        // Arrange
        val a = 5.0
        val b = 3.0

        // Act
        val result = Calculadora.somar(a, b)

        // Assert
        assertEquals(8.0, result)
    }

    @Test
    fun `shouldReturn6For10Minus4Subtraction`() {
        // Arrange
        val a = 10.0
        val b = 4.0

        // Act
        val result = Calculadora.subtrair(a, b)

        // Assert
        assertEquals(6.0, result)
    }

    @Test
    fun `shouldReturn14For2Times7Multiplication`() {
        // Arrange
        val a = 2.0
        val b = 7.0

        // Act
        val result = Calculadora.multiplicar(a, b)

        // Assert
        assertEquals(14.0, result)
    }

    @Test
    fun `shouldReturn5For15DividedBy3Division`() {
        // Arrange
        val a = 15.0
        val b = 3.0

        // Act
        val result = Calculadora.dividir(a, b)

        // Assert
        assertEquals(5.0, result)
    }

    @Test
    fun `shouldThrowExceptionForDivisionByZero`() {
        // Arrange
        val a = 10.0
        val b = 0.0

        // Act & Assert
        assertFailsWith<IllegalArgumentException> {
            Calculadora.dividir(a, b)
        }
    }

    // Testes de Integração
    @Test
    fun `shouldReturn8ForSumRouteWith5And3`() = testApplication {
        // Arrange
        application { CalculadoraAPI() }

        // Act
        val response = client.get("/sum/5/3")

        // Assert
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("8.0", response.bodyAsText())
    }

    @Test
    fun `shouldReturn6ForSubtractRouteWith10And4`() = testApplication {
        // Arrange
        application { CalculadoraAPI() }

        // Act
        val response = client.get("/subtract/10/4")

        // Assert
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("6.0", response.bodyAsText())
    }

    @Test
    fun `shouldReturn14ForMultiplyRouteWith2And7`() = testApplication {
        // Arrange
        application { CalculadoraAPI() }

        // Act
        val response = client.get("/multiply/2/7")

        // Assert
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("14.0", response.bodyAsText())
    }

    @Test
    fun `shouldReturn5ForDivideRouteWith15And3`() = testApplication {
        // Arrange
        application { CalculadoraAPI() }

        // Act
        val response = client.get("/divide/15/3")

        // Assert
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("5.0", response.bodyAsText())
    }

    @Test
    fun `shouldReturnErrorForDivideRouteWith10And0`() = testApplication {
        // Arrange
        application { CalculadoraAPI() }

        // Act
        val response = client.get("/divide/10/0")

        // Assert
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals("Erro: Divisão por zero não é permitida.", response.bodyAsText())
    }
}