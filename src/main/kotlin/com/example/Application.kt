package com.example

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.netty.EngineMain
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
//    install(ContentNegotiation) {
//        json()
//    }

    routing {
        get("/") {
            call.respondText("Calculadora API!") // Responde com a mensagem "Calculadora API!" para a rota raiz.
        }

        // Define uma rota GET para "/sum/{a}/{b}".
        get("/sum/{a}/{b}") {
            val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
            val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0
            call.respondText((a + b).toString()) // Responde com a soma de 'a' e 'b'.
        }

        // Define uma rota GET para "/subtract/{a}/{b}".
        get("/subtract/{a}/{b}") {
            val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
            val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0
            call.respondText((a - b).toString()) // Responde com a subtração de 'a' e 'b'.
        }

        // Define uma rota GET para "/multiply/{a}/{b}".
        get("/multiply/{a}/{b}") {
            val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
            val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0
            call.respondText((a * b).toString()) // Responde com a multiplicação de 'a' e 'b'.
        }

        // Define uma rota GET para "/divide/{a}/{b}".
        get("/divide/{a}/{b}") {
            val a = call.parameters["a"]?.toDoubleOrNull() ?: 0.0
            val b = call.parameters["b"]?.toDoubleOrNull() ?: 0.0
            if (b == 0.0) {
                call.respondText("Erro: Divisão por zero não permitida.", status = HttpStatusCode.BadRequest)
            } else {
                call.respondText((a / b).toString()) // Responde com a divisão de 'a' e 'b'.
            }
        }

}

data class CalcRequest(val num1: Double, val num2: Double, val operador: Char)}
