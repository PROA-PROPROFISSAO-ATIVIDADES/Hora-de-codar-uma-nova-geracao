package model

data class Pet(
    val nome: String,
    var nivelDeFome: Int = Atributos.NIVEL_INICIAL,
    var nivelFelicidade: Int = Atributos.NIVEL_INICIAL,
    var cansaco: Int = Atributos.MINIMO,
    var idade: Int = Atributos.MINIMO,
    var vontadeBanheiro: Int = Atributos.MINIMO,
    var sujeira: Int = Atributos.MINIMO
)