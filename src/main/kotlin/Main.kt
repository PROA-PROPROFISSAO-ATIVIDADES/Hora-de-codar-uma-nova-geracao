
fun main() {
    val repository = repository.RepositoryImpPet()
    val controller = controller.Controllerpet(repository)
    val view = view.viewpet(controller)

    view.criarpet()
}