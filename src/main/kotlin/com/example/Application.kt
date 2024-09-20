package com.example

import com.example.caixa.CaixaAPI
import com.example.calculadora.CalculadoraAPI
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {

    CalculadoraAPI()
    CaixaAPI()

data class CalcRequest(val num1: Double, val num2: Double, val operador: Char)

}
