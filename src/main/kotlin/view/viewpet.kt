package view

import controller.Controllerpet
import model.Atributos

class viewpet(
    private val controller: Controllerpet
) {
    fun criarpet(){
        println("Simulador de Animal de Estimacao Virtual")
        println("Digite o nome do seu animal de estimacao:")
        val nomePet = readlnOrNull() ?: "Pet"
        val pet = controller.criarPet(nomePet)
        println(pet.message)

        menuPet()
    }

    fun menuPet() {
        val pet = controller.pegarPet()
        while (true) {
            println()
            println("Pet: ${pet.nome}")
            println("1. Alimentar")
            println("2. Brincar")
            println("3. Descansar")
            println("4. Ir ao banheiro")
            println("5. Tomar banho")
            println("6. Verificar status")
            println("7. Sair")
            print("Escolha uma opcao: ")

            val escolha = readlnOrNull()?.toIntOrNull() ?: continue

            val resultado = when (escolha) {
                1 -> controller.alimentar()
                2 -> controller.brincar()
                3 -> {
                    println("Por quantas horas o pet vai descansar?")
                    val horas = readlnOrNull()?.toIntOrNull()
                    if (horas == null || horas < 0) {
                        println("Informe uma quantidade valida de horas.")
                        continue
                    }
                    controller.descansar(horas)
                }
                4 -> controller.irAoBanheiro()
                5 -> controller.tomarBanho()
                6 -> controller.verificarStatus()
                7 -> {
                    println("Saindo do simulador. Ate logo!")
                    return
                }
                else -> {
                    println("Escolha invalida. Tente novamente.")
                    continue
                }
            }

            println(resultado.message)
            if (resultado.status == Atributos.STATUS_VALIDACAO) {
                continue
            }

            val derrota = controller.verificarDerrota()
            if (derrota.status == 400) {
                println(derrota.message)
                return
            }

            if (escolha == 6) {
                continue
            }

            val tempo = controller.passarTempo()
            if (tempo.status == 400) {
                println(tempo.message)
                return
            }
            if (tempo.status == 210) {
                println(tempo.message)
                return
            }
            println(tempo.message)
        }
    }
}