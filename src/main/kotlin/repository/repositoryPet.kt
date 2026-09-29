package repository
import model.Pet

interface RepositoryPet {
    fun criarPet(newPet: Pet): Pet
    fun alimentarPet(nutricao: Int): Int
    fun brincarPet(felicidade: Int): Int
    fun descansarPet(): Int
    fun passarTempoPet(): Int
    fun pegarPet(): Pet
}