package com.example.caixa

import Caixa
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import java.util.UUID

data class Conta(val id: String, val caixa: Caixa)

fun Application.CaixaAPI() {
    routing {

        val contas = mutableMapOf<String, Caixa>()

        post("/conta/criar/{saldoInicial}") {
            val saldoInicial = call.parameters["saldoInicial"]?.toDoubleOrNull() ?: return@post call.respondText(
                "Saldo inicial inválido.", status = HttpStatusCode.BadRequest
            )
            val idConta = UUID.randomUUID().toString()
            val novaConta = Caixa(saldo = saldoInicial)
            contas[idConta] = novaConta
            call.respondText("Conta criada com sucesso! ID da conta: $idConta")
        }

        get("/conta/{id}/saldo") {
            val idConta = call.parameters["id"] ?: return@get call.respondText(
                "ID da conta não fornecido.", status = HttpStatusCode.BadRequest
            )
            val conta = contas[idConta] ?: return@get call.respondText(
                "Conta não encontrada.", status = HttpStatusCode.NotFound
            )
            val saldoAtual = conta.consultarSaldo()
            call.respondText("O saldo da conta $idConta é: $saldoAtual")
        }

        post("/conta/{id}/depositar/{valor}") {
            val idConta = call.parameters["id"] ?: return@post call.respondText(
                "ID da conta não fornecido.", status = HttpStatusCode.BadRequest
            )
            val conta = contas[idConta] ?: return@post call.respondText(
                "Conta não encontrada.", status = HttpStatusCode.NotFound
            )
            val valor = call.parameters["valor"]?.toDoubleOrNull() ?: return@post call.respondText(
                "Valor inválido.", status = HttpStatusCode.BadRequest
            )

            try {
                val novoSaldo = conta.depositar(valor)
                call.respondText("Depósito realizado com sucesso. Novo saldo: $novoSaldo")
            } catch (e: IllegalArgumentException) {
                call.respondText("Erro: ${e.message}", status = HttpStatusCode.BadRequest)
            }
        }

        post("/conta/{id}/sacar/{valor}") {
            val idConta = call.parameters["id"] ?: return@post call.respondText(
                "ID da conta não fornecido.", status = HttpStatusCode.BadRequest
            )
            val conta = contas[idConta] ?: return@post call.respondText(
                "Conta não encontrada.", status = HttpStatusCode.NotFound
            )
            val valor = call.parameters["valor"]?.toDoubleOrNull() ?: return@post call.respondText(
                "Valor inválido.", status = HttpStatusCode.BadRequest
            )

            try {
                val novoSaldo = conta.sacar(valor)
                call.respondText("Saque realizado com sucesso. Novo saldo: $novoSaldo")
            } catch (e: IllegalArgumentException) {
                call.respondText("Erro: ${e.message}", status = HttpStatusCode.BadRequest)
            }
        }
    }
}