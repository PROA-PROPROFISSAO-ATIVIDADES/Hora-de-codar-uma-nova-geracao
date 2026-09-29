package repository
import model.Pet

interface RepositoryPet {
    fun criarPet(newPet: Pet): Pet
    fun alimentarPet(nutricao: Int): Pet
    fun brincarPet(felicidade: Int, cansaco: Int, fome: Int): Pet
    fun descansarPet(cansaco: Int): Pet
    fun passarTempoPet(idade: Int): Pet
    fun pegarPet(): Pet
}