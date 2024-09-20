class Calc {
    fun calcular(num1: Double, num2: Double, operador: Char): Double {
        return when (operador) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> if (num2 != 0.0) num1 / num2 else throw IllegalArgumentException("Divisão por zero não permitida.")
            else -> throw IllegalArgumentException("Operador inválido.")
        }
    }
}