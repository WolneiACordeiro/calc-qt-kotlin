package com.example.calculadora

class Calculadora {
    companion object {
        fun somar(a: Double, b: Double): Double {
            return a + b
        }

        fun subtrair(a: Double, b: Double): Double {
            return a - b
        }

        fun multiplicar(a: Double, b: Double): Double {
            return a * b
        }

        fun dividir(a: Double, b: Double): Double {
            if (b == 0.0) {
                throw IllegalArgumentException("Divisão por zero não permitida.")
            }
            return a / b
        }
    }
}