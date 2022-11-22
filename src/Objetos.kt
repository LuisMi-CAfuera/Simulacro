class Objetos {
    var peso =0
    var valor = 0

    constructor(peso: Int, valor: Int) {
        this.peso = peso
        this.valor = valor
    }

    override fun toString(): String {
        return "Objetos(peso=$peso, valor=$valor)"
    }


}