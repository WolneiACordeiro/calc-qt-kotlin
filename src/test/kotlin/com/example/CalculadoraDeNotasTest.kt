import com.example.notas.CalculadoraDeNotas
import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class CalculadoraDeNotasTest {
    @Test
    fun `deve calcular a média das notas`() {
        val aluno = CalculadoraDeNotas("João", listOf(9.2, 5.5))
        val mediaEsperada = "A média das notas de João é: 7.35"
        val mediaCalculada = aluno.calcularMedia()
        assertEquals(mediaEsperada, mediaCalculada)
    }

    @Test
    fun `deve retornar mensagem quando não houver notas para calcular a média`() {
        val aluno = CalculadoraDeNotas("Maria", emptyList())
        val mensagemEsperada = "Ainda não há notas para calcular a média."
        val mediaCalculada = aluno.calcularMedia()
        assertEquals(mensagemEsperada, mediaCalculada)
    }

    @Test
    fun `deve encontrar a maior nota`() {
        val aluno = CalculadoraDeNotas("Pedro", listOf(8.5, 9.5, 7.0))
        val maiorNotaEsperada = "A maior nota de Pedro é: 9.5"
        val maiorNotaCalculada = aluno.encontrarMaiorNota()
        assertEquals(maiorNotaEsperada, maiorNotaCalculada)
    }

    @Test
    fun `deve retornar mensagem quando não houver notas para encontrar a maior`() {
        val aluno = CalculadoraDeNotas("Ana", emptyList())
        val mensagemEsperada = "Ainda não há notas para encontrar a maior."
        val maiorNotaCalculada = aluno.encontrarMaiorNota()
        assertEquals(mensagemEsperada, maiorNotaCalculada)
    }

    @Test
    fun `deve encontrar a menor nota`() {
        val aluno = CalculadoraDeNotas("Lucas", listOf(8.5, 9.5, 7.0))
        val menorNotaEsperada = "A menor nota de Lucas é: 7.0"
        val menorNotaCalculada = aluno.encontrarMenorNota()
        assertEquals(menorNotaEsperada, menorNotaCalculada)
    }

    @Test
    fun `deve retornar mensagem quando não houver notas para encontrar a menor`() {
        val aluno = CalculadoraDeNotas("Sofia", emptyList())
        val mensagemEsperada = "Ainda não há notas para encontrar a menor."
        val menorNotaCalculada = aluno.encontrarMenorNota()
        assertEquals(mensagemEsperada, menorNotaCalculada)
    }
}