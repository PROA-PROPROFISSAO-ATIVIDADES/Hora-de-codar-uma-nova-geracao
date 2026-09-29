package repository
import model.Pet

class RepositoryImpPet : RepositoryPet {
    var pet: Pet? = null

    //singleton preguiçoso
    override fun criarPet(newPet: Pet): Pet {
        if(pet == null) {
            pet = newPet
        }

        return pet!!
    }

    override fun alimentarPet(nutricao: Int): Pet {
        val petAtual = pet!!
        petAtual.nivelDeFome = (petAtual.nivelDeFome - nutricao).coerceAtLeast(0)
        return petAtual
    }
    override fun brincarPet(felicidade: Int, cansaco: Int, fome: Int): Pet {
        val petAtual = pet!!
        petAtual.nivelFelicidade += felicidade
        petAtual.cansaco += cansaco
        petAtual.nivelDeFome += fome
        return petAtual
    }
    override fun descansarPet(cansaco: Int): Pet {
        val petAtual = pet!!
        petAtual.cansaco = (petAtual.cansaco - cansaco).coerceAtLeast(0)
        return petAtual
    }
    override fun passarTempoPet(idade: Int): Pet {
        val petAtual = pet!!
        petAtual.idade += idade
        return petAtual
    }

    override fun pegarPet(): Pet {
        return pet!!
    }
}