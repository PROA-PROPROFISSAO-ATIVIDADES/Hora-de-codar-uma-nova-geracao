package model

data class Pet(
    val nome: String,
    var nivelDeFome: Int = 50,
    var nivelFelicidade: Int = 50,
    var cansaco: Int = 0,
    var idade: Int = 0
)