package repository
import model.Pet

interface RepositoryPet {
    fun criarPet(newPet: Pet): Pet
    fun alimentarPet(): Pet
    fun brincarPet(): Pet
    fun descansarPet(horas: Int): Pet
    fun irAoBanheiroPet(): Pet
    fun tomarBanhoPet(): Pet
    fun passarTempoPet(): Pet
    fun pegarPet(): Pet
}