package jpcoliveira.com.br.testeandroid.contact

enum class Type(val id: Int) {
    field(1),
    text(2),
    image(3),
    checkbox(4),
    send(5)
}

enum class TypeField(val id: Int) {
    text(1),
    telNumber(2),
    email(3)
}