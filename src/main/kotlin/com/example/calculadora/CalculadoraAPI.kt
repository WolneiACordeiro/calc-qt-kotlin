package com.example.calculadora

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.CalculadoraAPI() {
        routing {

            // Define uma rota GET para "/sum/{a}/{b}".
            get("/sum/{a}/{b}") {
                val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
                val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0
                val resultado = Calculadora.somar(a, b)
                call.respondText(resultado.toString()) // Responde com a soma de 'a' e 'b'.
            }

            // Define uma rota GET para "/subtract/{a}/{b}".
            get("/subtract/{a}/{b}") {
                val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
                val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0
                val resultado = Calculadora.subtrair(a, b)
                call.respondText(resultado.toString()) // Responde com a subtração de 'a' e 'b'.
            }

            // Define uma rota GET para "/multiply/{a}/{b}".
            get("/multiply/{a}/{b}") {
                val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
                val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0
                val resultado = Calculadora.multiplicar(a, b)
                call.respondText(resultado.toString()) // Responde com a multiplicação de 'a' e 'b'.
            }

            // Define uma rota GET para "/divide/{a}/{b}".
            get("/divide/{a}/{b}") {
                val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
                val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0
                try {
                    val resultado = Calculadora.dividir(a, b)
                    call.respondText(resultado.toString()) // Responde com a divisão de 'a' e 'b'.
                } catch (e: IllegalArgumentException) {
                    call.respondText("Erro: ${e.message}", status = HttpStatusCode.BadRequest)
                }
            }

        }
    }