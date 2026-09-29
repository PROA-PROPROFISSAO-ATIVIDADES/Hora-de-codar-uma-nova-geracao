package controller

import model.Atributos
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
        val resultado = repositoryPet.alimentarPet(
            nutricao,
            vontadeBanheiro = Atributos.VALOR_ALIMENTAR
        )
        return ReplyFecth(
            200,
            "Pet alimentado com sucesso! Nível de fome: ${resultado.nivelDeFome}, vontade de ir ao banheiro: ${resultado.vontadeBanheiro}",
            resultado
        )
    }

    fun brincar(): ReplyFecth {
        val resultado = repositoryPet.brincarPet(
            Atributos.VALOR_BRINCAR,
            Atributos.VALOR_BRINCAR,
            Atributos.VALOR_BRINCAR,
            Atributos.VALOR_BRINCAR
        )
        return ReplyFecth(
            200,
            "Pet brincou com sucesso! Cansaço: ${resultado.cansaco}, fome: ${resultado.nivelDeFome}, felicidade: ${resultado.nivelFelicidade}, sujeira: ${resultado.sujeira}",
            resultado
        )
    }

    fun descansar(horas: Int): ReplyFecth {
        val horasDeSono = horas.coerceIn(Atributos.MINIMO, Atributos.HORAS_DESCANSO)
        val resultado = repositoryPet.descansarPet(
            cansaco = horasDeSono * Atributos.LIMITE / Atributos.HORAS_DESCANSO
        )
        return ReplyFecth(
            200,
            "Pet descansou por $horasDeSono hora(s)! Cansaço: ${resultado.cansaco}",
            resultado
        )
    }

    fun irAoBanheiro(): ReplyFecth {
        val resultado = repositoryPet.irAoBanheiroPet(
            Atributos.VALOR_BANHEIRO
        )
        return ReplyFecth(200, "O pet foi ao banheiro!", resultado)
    }

    fun tomarBanho(): ReplyFecth {
        val resultado = repositoryPet.tomarBanhoPet(sujeira = Atributos.VALOR_BANHO)
        return ReplyFecth(200, "O pet tomou banho!", resultado)
    }

    fun verificarStatus(): ReplyFecth {
        val pet = repositoryPet.pegarPet()
        return ReplyFecth(
            200,
            """
                --------------------
                Status do Pet
                --------------------
                Nome: ${pet.nome}
                Fome: ${pet.nivelDeFome}/${Atributos.LIMITE}
                Felicidade: ${pet.nivelFelicidade}/${Atributos.LIMITE}
                Cansaco: ${pet.cansaco}/${Atributos.LIMITE}
                Idade: ${pet.idade}
                Banheiro: ${pet.vontadeBanheiro}/${Atributos.LIMITE}
                Sujeira: ${pet.sujeira}/${Atributos.LIMITE}
            """.trimIndent(),
            pet
        )
    }

    fun passarTempo(): ReplyFecth {
        val resultado = repositoryPet.passarTempoPet(
            Atributos.FOME_POR_CICLO,
            Atributos.FELICIDADE_POR_CICLO,
            Atributos.CANSACO_POR_CICLO,
            Atributos.IDADE_POR_CICLO,
            Atributos.BANHEIRO_POR_CICLO,
            Atributos.SUJEIRA_POR_CICLO
        )

        if(resultado.idade >= Atributos.META_IDADE){
            return ReplyFecth(
                210,
                "O tempo passou! Idade: ${resultado.idade}. Você venceu o jogo!",
                resultado
            )
        }

        val derrota = verificarDerrota()
        if (derrota.status == 400) {
            return derrota
        }

        return ReplyFecth(
            200,
            "O tempo passou! Idade: ${resultado.idade}",
            resultado
        )
    }

    fun verificarDerrota(): ReplyFecth {
        val pet = repositoryPet.pegarPet()
        if (pet.nivelDeFome >= Atributos.LIMITE) {
            return ReplyFecth(
                400,
                "O pet morreu de fome! Game Over.",
                pet
            )
        }

        if (pet.nivelFelicidade <= Atributos.MINIMO) {
            return ReplyFecth(
                400,
                "O pet morreu de tristeza! Game Over.",
                pet
            )
        }
        if(pet.cansaco >= Atributos.LIMITE){
           return ReplyFecth(
                400,
                "O pet morreu de cansaço! Game Over.",
                pet
            )
        }
        if (pet.vontadeBanheiro >= Atributos.LIMITE) {
            return ReplyFecth(400, "O pet não foi ao banheiro! Game Over.", pet)
        }
        if (pet.sujeira >= Atributos.LIMITE) {
            return ReplyFecth(400, "O pet ficou sujo demais! Game Over.", pet)
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