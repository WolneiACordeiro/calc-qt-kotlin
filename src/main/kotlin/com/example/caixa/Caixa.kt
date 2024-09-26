class Caixa(private var saldo: Double = 0.0) {
    fun consultarSaldo(): Double {
        return saldo
    }

    fun depositar(valor: Double): Double {
        if (valor <= 0) {
            throw IllegalArgumentException("O valor de depósito deve ser maior que zero.")
        }
        saldo += valor
        return saldo
    }

    fun sacar(valor: Double): Double {
        if (valor <= 0) {
            throw IllegalArgumentException("O valor de saque deve ser maior que zero.")
        }
        if (valor > saldo) {
            throw IllegalArgumentException("Saldo insuficiente.")
        }
        saldo -= valor
        return saldo
    }
}