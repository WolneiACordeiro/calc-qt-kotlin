package com.example.caixa

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

    fun Application.CaixaAPI() {
        routing {

            var caixaEletronico = Caixa(saldo = 1000.0)

            // Define uma rota GET para "/saldo" para consultar o saldo
            get("/saldo") {
                val saldoAtual = caixaEletronico.consultarSaldo()
                call.respondText("Seu saldo é: $saldoAtual")
            }

            // Define uma rota POST para "/depositar/{valor}" para depositar dinheiro
            post("/depositar/{valor}") {
                val valor = call.parameters["valor"]?.toDoubleOrNull() ?: 0.0
                try {
                    val novoSaldo = caixaEletronico.depositar(valor)
                    call.respondText("Depósito realizado com sucesso. Seu novo saldo é: $novoSaldo")
                } catch (e: IllegalArgumentException) {
                    call.respondText("Erro: ${e.message}", status = HttpStatusCode.BadRequest)
                }
            }

            // Define uma rota POST para "/sacar/{valor}" para sacar dinheiro
            post("/sacar/{valor}") {
                val valor = call.parameters["valor"]?.toDoubleOrNull() ?: 0.0
                try {
                    val novoSaldo = caixaEletronico.sacar(valor)
                    call.respondText("Saque realizado com sucesso. Seu novo saldo é: $novoSaldo")
                } catch (e: IllegalArgumentException) {
                    call.respondText("Erro: ${e.message}", status = HttpStatusCode.BadRequest)
                }
            }
        }
    }