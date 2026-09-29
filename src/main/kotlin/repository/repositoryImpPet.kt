package repository
import model.Atributos
import model.Pet

class RepositoryImpPet : RepositoryPet {
    var pet: Pet? = null

    private fun petAtual(): Pet {
        return pet ?: throw IllegalStateException("O pet ainda não foi criado.")
    }

    //singleton preguiçoso
    override fun criarPet(newPet: Pet): Pet {
        if(pet == null) {
            pet = newPet
        }

        return petAtual()
    }

    override fun alimentarPet(): Pet {
        val petAtual = petAtual()
        petAtual.nivelDeFome =
            (petAtual.nivelDeFome - Atributos.VALOR_ALIMENTAR).coerceAtLeast(Atributos.MINIMO)
        petAtual.vontadeBanheiro =
            (petAtual.vontadeBanheiro + Atributos.VALOR_ALIMENTAR).coerceAtMost(Atributos.LIMITE)
        return petAtual
    }
    override fun brincarPet(): Pet {
        val petAtual = petAtual()
        petAtual.nivelFelicidade =
            (petAtual.nivelFelicidade + Atributos.VALOR_BRINCAR).coerceAtMost(Atributos.LIMITE)
        petAtual.cansaco =
            (petAtual.cansaco + Atributos.VALOR_BRINCAR).coerceAtMost(Atributos.LIMITE)
        petAtual.nivelDeFome =
            (petAtual.nivelDeFome + Atributos.VALOR_BRINCAR).coerceAtMost(Atributos.LIMITE)
        petAtual.sujeira =
            (petAtual.sujeira + Atributos.VALOR_BRINCAR).coerceAtMost(Atributos.LIMITE)
        return petAtual
    }
    override fun descansarPet(horas: Int): Pet {
        val petAtual = petAtual()
        petAtual.cansaco =
            (petAtual.cansaco -
                horas * Atributos.LIMITE / Atributos.HORAS_DESCANSO)
                .coerceAtLeast(Atributos.MINIMO)
        return petAtual
    }
    override fun irAoBanheiroPet(): Pet {
        val petAtual = petAtual()
        petAtual.vontadeBanheiro =
            (petAtual.vontadeBanheiro - Atributos.VALOR_BANHEIRO)
                .coerceAtLeast(Atributos.MINIMO)
        return petAtual
    }
    override fun tomarBanhoPet(): Pet {
        val petAtual = petAtual()
        petAtual.sujeira =
            (petAtual.sujeira - Atributos.VALOR_BANHO).coerceAtLeast(Atributos.MINIMO)
        return petAtual
    }
    override fun passarTempoPet(): Pet {
        val petAtual = petAtual()
        petAtual.nivelDeFome =
            (petAtual.nivelDeFome + Atributos.FOME_POR_CICLO).coerceAtMost(Atributos.LIMITE)
        petAtual.nivelFelicidade =
            (petAtual.nivelFelicidade - Atributos.FELICIDADE_POR_CICLO)
                .coerceAtLeast(Atributos.MINIMO)
        petAtual.cansaco =
            (petAtual.cansaco + Atributos.CANSACO_POR_CICLO).coerceAtMost(Atributos.LIMITE)
        petAtual.idade += Atributos.IDADE_POR_CICLO
        petAtual.vontadeBanheiro =
            (petAtual.vontadeBanheiro + Atributos.BANHEIRO_POR_CICLO)
                .coerceAtMost(Atributos.LIMITE)
        petAtual.sujeira =
            (petAtual.sujeira + Atributos.SUJEIRA_POR_CICLO)
                .coerceAtMost(Atributos.LIMITE)
        return petAtual
    }

    override fun pegarPet(): Pet {
        return petAtual()
    }
}