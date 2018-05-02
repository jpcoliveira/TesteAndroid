package jpcoliveira.com.br.testeandroid.builder

enum class Type(val id: Int) {
    field(1),
    text(2),
    image(3),
    checkbox(4),
    send(5)
}

enum class TypeField(val id: String) {
    text("1"),
    telnumber("2"),
    email("3")
}