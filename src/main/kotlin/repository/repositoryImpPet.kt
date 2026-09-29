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

    override fun alimentarPet(nutricao: Int): Int {
        val petAtual = pet!!
        petAtual.nivelDeFome = (petAtual.nivelDeFome - nutricao).coerceAtLeast(0)
        return petAtual.nivelDeFome
    }
    override fun brincarPet(felicidade: Int): Int {
        val petAtual = pet!!
        petAtual.nivelFelicidade += felicidade
        petAtual.cansaco += 10
        return petAtual.cansaco
    }
    override fun descansarPet(): Int {
        val petAtual = pet!!
        petAtual.cansaco = (petAtual.cansaco - 10).coerceAtLeast(0)
        return petAtual.cansaco
    }
    override fun passarTempoPet(): Int {
        val petAtual = pet!!
        petAtual.idade++
        return petAtual.idade
    }

    override fun pegarPet(): Pet {
        return pet!!
    }
}