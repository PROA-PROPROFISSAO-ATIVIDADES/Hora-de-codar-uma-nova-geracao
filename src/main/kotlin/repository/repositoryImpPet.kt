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

    override fun alimentarPet(nutricao: Int, vontadeBanheiro: Int): Pet {
        val petAtual = petAtual()
        petAtual.nivelDeFome =
            (petAtual.nivelDeFome - nutricao).coerceAtLeast(Atributos.MINIMO)
        petAtual.vontadeBanheiro =
            (petAtual.vontadeBanheiro + vontadeBanheiro).coerceAtMost(Atributos.LIMITE)
        return petAtual
    }
    override fun brincarPet(felicidade: Int, cansaco: Int, fome: Int, sujeira: Int): Pet {
        val petAtual = petAtual()
        petAtual.nivelFelicidade =
            (petAtual.nivelFelicidade + felicidade).coerceAtMost(Atributos.LIMITE)
        petAtual.cansaco = (petAtual.cansaco + cansaco).coerceAtMost(Atributos.LIMITE)
        petAtual.nivelDeFome = (petAtual.nivelDeFome + fome).coerceAtMost(Atributos.LIMITE)
        petAtual.sujeira = (petAtual.sujeira + sujeira).coerceAtMost(Atributos.LIMITE)
        return petAtual
    }
    override fun descansarPet(cansaco: Int): Pet {
        val petAtual = petAtual()
        petAtual.cansaco =
            (petAtual.cansaco - cansaco).coerceAtLeast(Atributos.MINIMO)
        return petAtual
    }
    override fun irAoBanheiroPet(vontadeBanheiro: Int): Pet {
        val petAtual = petAtual()
        petAtual.vontadeBanheiro =
            (petAtual.vontadeBanheiro - vontadeBanheiro).coerceAtLeast(Atributos.MINIMO)
        return petAtual
    }
    override fun tomarBanhoPet(sujeira: Int): Pet {
        val petAtual = petAtual()
        petAtual.sujeira = (petAtual.sujeira - sujeira).coerceAtLeast(Atributos.MINIMO)
        return petAtual
    }
    override fun passarTempoPet(
        fome: Int,
        felicidade: Int,
        cansaco: Int,
        idade: Int,
        vontadeBanheiro: Int,
        sujeira: Int
    ): Pet {
        val petAtual = petAtual()
        petAtual.nivelDeFome = (petAtual.nivelDeFome + fome).coerceAtMost(Atributos.LIMITE)
        petAtual.nivelFelicidade -= felicidade
        petAtual.cansaco = (petAtual.cansaco + cansaco).coerceAtMost(Atributos.LIMITE)
        petAtual.idade += idade
        petAtual.vontadeBanheiro =
            (petAtual.vontadeBanheiro + vontadeBanheiro).coerceAtMost(Atributos.LIMITE)
        petAtual.sujeira =
            (petAtual.sujeira + sujeira).coerceAtMost(Atributos.LIMITE)
        return petAtual
    }

    override fun pegarPet(): Pet {
        return petAtual()
    }
}