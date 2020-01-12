/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.example.kotlinserver

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.gson.*
import io.ktor.features.ContentNegotiation

class App {
    val greeting: String
        get() {
            return "Hello world."
        }
}

fun main(args: Array<String>) {
  println(App().greeting)
  val port = System.getenv("PORT")?.toInt() ?:8080
  println("port: $port")
  embeddedServer(Netty, port) {
    install(ContentNegotiation) {
      gson {
        setPrettyPrinting()
      }
    }

    routing {
      get("/") {
        call.respondText("Hello World!", ContentType.Text.Plain)
      }
      get("/demo") {
        call.respondText("HELLO WORLD!")
      }
      get("random/{min}/{max}") {
        val min = call.parameters["min"]?.toIntOrNull() ?: 0
        val max = call.parameters["max"]?.toIntOrNull() ?: 10
        val randomString = "${(min until max).shuffled().last()}"
        call.respond(mapOf("value" to randomString))
      }
    }
  }.start()

}

