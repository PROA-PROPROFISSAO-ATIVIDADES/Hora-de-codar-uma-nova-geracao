package view

import controller.Controllerpet
import kotlin.system.exitProcess

class viewpet(
    private val controller: Controllerpet
) {
    fun criarpet(){
        println("Bem-vindo ao Simulador de Animal de Estimação Virtual!")
        println("Digite o nome do seu animal de estimação:")
        val nomePet = readLine() ?: "Baltazar Guilherme Tenório"
        val pet = controller.criarPet(nomePet)
        println(pet.message)

        menuPet()
    }

    fun menuPet() {
        val pet = controller.pegarPet()
        while (true) {
            println("\nEscolha uma ação:")
            println("1. Alimentar ${pet.nome}")
            println("2. Brincar com ${pet.nome}")
            println("3. Descansar ${pet.nome}")
            println("4. Verificar o status de ${pet.nome}")
            println("5. Sair")

            val escolha = readLine()?.toIntOrNull() ?: continue

            val resultado = when (escolha) {
                1 -> controller.alimentar(10)
                2 -> controller.brincar()
                3 -> controller.descansar()
                4 -> controller.verificarStatus()
                5 -> {
                    println("Saindo do Simulador de Animal de Estimação Virtual. Adeus!")
                    return
                }
                else -> {
                    println("Escolha inválida. Tente novamente.")
                    continue
                }
            }

            println(resultado.message)

            val derrota = controller.verificarDerrota()
            if (derrota.status == 400) {
                println(derrota.message)
                return
            }
            val tempo = controller.passarTempo()

            if(tempo.status == 210) {
                println(tempo.message)
                exitProcess(0)
            }
        }
    }
}