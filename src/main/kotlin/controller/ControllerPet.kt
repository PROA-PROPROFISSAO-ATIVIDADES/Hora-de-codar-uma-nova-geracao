package controller

import reply.ReplyFecth
import repository.RepositoryPet

class Controllerpet(
    private val repositoryPet: RepositoryPet
) {
    fun criarPet(nome: String): ReplyFecth {
        val newPet = model.Pet(nome)
        repositoryPet.criarPet(newPet)
        return ReplyFecth(
            201,
            "Pet criado com sucesso!",
            newPet
        )
    }

    fun alimentar(nutricao: Int): ReplyFecth {
        val resultado = repositoryPet.alimentarPet(nutricao)
        return ReplyFecth(
            200,
            "Pet alimentado com sucesso! Nível de fome: ${resultado.nivelDeFome}",
            resultado
        )
    }

    fun brincar(): ReplyFecth {
        val resultado = repositoryPet.brincarPet(
            felicidade = 10,
            cansaco = 10,
            fome = 10
        )
        return ReplyFecth(
            200,
            "Pet brincou com sucesso! Cansaço: ${resultado.cansaco}, fome: ${resultado.nivelDeFome}, felicidade: ${resultado.nivelFelicidade}",
            resultado
        )
    }

    fun descansar(): ReplyFecth {
        val resultado = repositoryPet.descansarPet(cansaco = 10)
        return ReplyFecth(
            200,
            "Pet descansou com sucesso! Cansaço: ${resultado.cansaco}",
            resultado
        )
    }

    fun verificarStatus(): ReplyFecth {
        val pet = repositoryPet.pegarPet()
        return ReplyFecth(
            200,
            """
                Status do Pet:
                Nome: ${pet.nome}
                Nível de Fome: ${pet.nivelDeFome}
                Nível de Felicidade: ${pet.nivelFelicidade}
                Cansaço: ${pet.cansaco}
                Idade: ${pet.idade}
            """.trimIndent(),
            pet
        )
    }

    fun passarTempo(): ReplyFecth {
        val resultado = repositoryPet.passarTempoPet(1)

        if(resultado.idade >= 50){
            return ReplyFecth(
                210,
                "O tempo passou! Idade: ${resultado.idade}. Você venceu o jogo!",
                resultado
            )
        }

        return ReplyFecth(
            200,
            "O tempo passou! Idade: ${resultado.idade}",
            resultado
        )
    }

    fun verificarDerrota(): ReplyFecth {
        val pet = repositoryPet.pegarPet()
        if (pet.nivelDeFome >= 100) {
            return ReplyFecth(
                400,
                "O pet morreu de fome! Game Over.",
                pet
            )
        }

        if (pet.nivelFelicidade <= 0) {
            return ReplyFecth(
                400,
                "O pet morreu de tristeza! Game Over.",
                pet
            )
        }
        if(pet.cansaco >= 100){
           return ReplyFecth(
                400,
                "O pet morreu de cansaço! Game Over.",
                pet
            )
        }
        return ReplyFecth(
            200,
            "O pet está vivo e bem!",
            pet
        )
    }

    fun pegarPet(): model.Pet {
        return repositoryPet.pegarPet()
    }
}