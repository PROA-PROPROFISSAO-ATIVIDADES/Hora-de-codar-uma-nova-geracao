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

    fun alimentar(): ReplyFecth {
        val pet = repositoryPet.pegarPet()
        if (pet.nivelDeFome == Atributos.MINIMO) {
            return ReplyFecth(
                Atributos.STATUS_VALIDACAO,
                "O pet não está com fome.",
                pet
            )
        }

        val resultado = repositoryPet.alimentarPet()
        return ReplyFecth(
            200,
            "Pet alimentado com sucesso! Nível de fome: ${resultado.nivelDeFome}, vontade de ir ao banheiro: ${resultado.vontadeBanheiro}",
            resultado
        )
    }

    fun brincar(): ReplyFecth {
        val pet = repositoryPet.pegarPet()
        if (pet.cansaco == Atributos.LIMITE) {
            return ReplyFecth(
                Atributos.STATUS_VALIDACAO,
                "O pet está muito cansado para poder brincar.",
                pet
            )
        }

        val resultado = repositoryPet.brincarPet()
        return ReplyFecth(
            200,
            "Pet brincou com sucesso! Cansaço: ${resultado.cansaco}, fome: ${resultado.nivelDeFome}, felicidade: ${resultado.nivelFelicidade}, sujeira: ${resultado.sujeira}",
            resultado
        )
    }

    fun descansar(horas: Int): ReplyFecth {
        val pet = repositoryPet.pegarPet()
        if (horas <= Atributos.MINIMO || horas > Atributos.HORAS_DESCANSO) {
            return ReplyFecth(
                Atributos.STATUS_VALIDACAO,
                "Informe entre 1 e ${Atributos.HORAS_DESCANSO} horas de descanso.",
                pet
            )
        }
        if (pet.cansaco == Atributos.MINIMO) {
            return ReplyFecth(
                Atributos.STATUS_VALIDACAO,
                "O pet não está cansado.",
                pet
            )
        }

        val horasDeSono = horas
        val resultado = repositoryPet.descansarPet(horasDeSono)
        return ReplyFecth(
            200,
            "Pet descansou por $horasDeSono hora(s)! Cansaço: ${resultado.cansaco}",
            resultado
        )
    }

    fun irAoBanheiro(): ReplyFecth {
        val pet = repositoryPet.pegarPet()
        if (pet.vontadeBanheiro == Atributos.MINIMO) {
            return ReplyFecth(
                Atributos.STATUS_VALIDACAO,
                "O pet não precisa ir ao banheiro.",
                pet
            )
        }

        val resultado = repositoryPet.irAoBanheiroPet()
        return ReplyFecth(200, "O pet foi ao banheiro!", resultado)
    }

    fun tomarBanho(): ReplyFecth {
        val pet = repositoryPet.pegarPet()
        if (pet.sujeira == Atributos.MINIMO) {
            return ReplyFecth(
                Atributos.STATUS_VALIDACAO,
                "O pet não está sujo.",
                pet
            )
        }

        val resultado = repositoryPet.tomarBanhoPet()
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
        val resultado = repositoryPet.passarTempoPet()

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
            return ReplyFecth(400, "O pet não foi ao banheiro por muito tempo! Game Over.", pet)
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