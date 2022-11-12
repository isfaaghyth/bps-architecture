package app.bps.architecture.data.entity

data class Person(
    val name: String,
    val age: String
) {

    override fun toString(): String {
        return "Hi! $name, umur kamu sekarang $age tahun."
    }

}