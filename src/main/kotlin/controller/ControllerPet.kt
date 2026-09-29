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
            "Pet alimentado com sucesso!",
            resultado
        )
    }

    fun brincar(): ReplyFecth {
        val resultado = repositoryPet.brincarPet(10)
        return ReplyFecth(
            200,
            "Pet brincou com sucesso! Cansaço: $resultado",
            resultado
        )
    }

    fun descansar(): ReplyFecth {
        val resultado = repositoryPet.descansarPet()
        return ReplyFecth(
            200,
            "Pet descansou com sucesso! Cansaço: $resultado",
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
        val resultado = repositoryPet.passarTempoPet()
        return ReplyFecth(
            200,
            "O tempo passou! Idade: $resultado",
            resultado
        )
    }

    fun pegarPet(): model.Pet {
        return repositoryPet.pegarPet()
    }
}