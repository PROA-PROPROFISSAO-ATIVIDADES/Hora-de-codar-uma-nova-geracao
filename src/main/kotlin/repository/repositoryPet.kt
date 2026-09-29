package repository
import model.Pet

interface RepositoryPet {
    fun criarPet(newPet: Pet): Pet
    fun alimentarPet(nutricao: Int, vontadeBanheiro: Int): Pet
    fun brincarPet(felicidade: Int, cansaco: Int, fome: Int, sujeira: Int): Pet
    fun descansarPet(cansaco: Int): Pet
    fun irAoBanheiroPet(vontadeBanheiro: Int): Pet
    fun tomarBanhoPet(sujeira: Int): Pet
    fun passarTempoPet(
        fome: Int,
        felicidade: Int,
        cansaco: Int,
        idade: Int,
        vontadeBanheiro: Int,
        sujeira: Int
    ): Pet
    fun pegarPet(): Pet
}