class Personaje {
    var nombre = Nombre()
    var raza = Raza()
    var estado = Edad()
    var clase = Clase()
    var mochila = Mochila()
    var objetos = Objetos()
    var monedero : MutableMap<String,Int> = mutableMapOf(
        "1" to 0,
        "5" to 0,
        "10" to 0,
        "25" to 0,
        "100" to 0
    )

    constructor(nombre: String, clase: String, estado: String, raza: String,mochila : Int, objetos : ArrayList<Objetos>, monedero : MutableMap<String,Int>) {
        this.nombre = nombre
        this.clase = clase
        this.estado = estado
        this.raza = raza
        this.mochila = mochila
        this.objetos = objetos
        this.monedero = monedero
    }


    constructor()
    constructor(clase: String, mochila: Int, monedero: MutableMap<String,Int>) {
        this.clase = clase
        this.monedero = monedero
    }

    //Funcion que me diga que raza es aleatoriamente
    fun Raza(): String {
        var raza: String = " "
        var razas = arrayOf("Elfo", "Humano", "Enano", "Goblin")

        raza = razas.random()

        return raza
    }

    //Funcion que me diga si es adolescente,adulto o anciano aleatoriamente
    fun Edad(): String {
        var edad: String = " "
        var edades = arrayOf("Adolescente", "Adulto", "Anciano")

        edad = edades.random()

        return edad
    }

    //Funcion que me de un nombre del EldenRing(Por que me apetece) aleatorio de un array de 7  nombres
    fun Nombre(): String {
        var nombre: String = " "
        var nombres = arrayOf("Gwyn", "Gwyndolin", "Gwynevere", "Malenia", "Melina", "Ranni", "Radagon")

        nombre = nombres.random()

        return nombre
    }

    //Funcion que me diga el tipo de clase
    fun Clase(): String {
        var clase: String = " "
        var clases = arrayOf("Guerrero", "Mago", "Ladron", "Berserk","Mercader")

        clase = clases.random()

        return clase
    }

    //Funcion que me de el tamaño de la mochila del 1 al 6 y luego el resultado se multiplica por 10 para que sea el peso
    fun Mochila(): Int {
        var mochila: Int = 0
        var tam = arrayOf(1, 2, 3, 4, 5, 6)

        mochila = tam.random()

        return mochila * 10
    }
    //Funcion que me devuelva un arraylist de 5 objetos de valor del 1 al 50 y que el peso de todos los objetos no supere el peso de la mochila
    fun Objetos(): ArrayList<Objetos> {
        var objetos = ArrayList<Objetos>()
        var peso = (1..10).random()
        var valor = (1..50).random()
        var contP = 0
        contP += peso
        var i = 1
        while (mochila >= contP  && i < 5) {
            peso = (1..10).random()
            valor = (1..50).random()
            objetos.add(Objetos(peso, valor))
            contP += peso
            i++
        }
        return objetos
    }

    fun crearPersonaje():Personaje{
        var personaje = Personaje()

        println("¿Que raza quieres que sea?(Elfo,Humano,Enano,Goblin o una que te inventes)")
        personaje.raza = readLine().toString()
        println("¿Que nombre quieres que tenga?(Si el personaje es un Elfo o un Goblin el nombre estara en elfico o goblin)")
        personaje.nombre = readLine().toString()
        println("¿Que edad quieres que tenga?(Adolescente,Adulto o Anciano)")
        personaje.estado = readLine().toString()
        println("¿Que clase quieres ser?(Mago,Guerrero,Ladron,Mercader o Berserk)")
        personaje.clase = readLine().toString()

        return personaje
    }

    fun intercambio(Jugador:Personaje,Mercader:Personaje):Personaje{
        var cont = 0
        var valor = 0
        var obVender = arrayListOf<Objetos>()
        var precio = 0
        var cociente = 0
        var arrval = arrayListOf<Int>()
        Mercader.clase= "Mercader"
        Mercader.mochila = 100



        do {
            println("Pon el valor de los objetos que quieres vender\n")
            println("Si no quieres vender nada mas o para acaba pon 0\n")
            println("Estos son tus objetos:\n${Jugador.objetos}\n")
            cont = 0

            do {
                valor = readLine()!!.toInt()
                if (valor != 0) {
                    arrval.add(valor)
                }
                cont++

            } while (valor != 0 && cont < 5)

            //Aqui compruebo el valo metido por pantalla y si coincide con los objetos que tiene el jugador lo añado al array que le voy a dar al mercader
            for (i in arrval) {
                cont=0
                for (j in Jugador.objetos) {
                    if (i == j.valor) {
                        obVender.add(j)
                    } else {
                        cont++
                    }
                }
            }
        }while (cont == Jugador.objetos.size)

        //Aqui compruebo que el valor de los objetos que quiere vender no supere el peso de la mochila}
        obVender.forEach(){
            precio += it.valor
        }

        println("Mercader :\n el valor de los objetos que me quieres vender es $precio\n")
        println("Te voy a dar estas monedas\n")


        //Todos estos if son para que el mercader reparta en diferentes monedas el precio
        cociente = precio / 100
        if (cociente > 0) {
            println("De 100 te voy a dar: $cociente\n")
            precio -= 100 * cociente
            Jugador.monedero["100"] = Jugador.monedero["100"]!! + cociente
            Mercader.monedero["100"] = Mercader.monedero["100"]!! - cociente


        }
        cociente = precio / 25
        if (cociente > 0) {
            println("De 25 te voy a dar: $cociente\n")
            precio -= 25 * cociente
            Jugador.monedero["25"] = Jugador.monedero["25"]!! + cociente
            Mercader.monedero["25"] = Mercader.monedero["25"]!! - cociente

        }
        cociente = precio / 10
        if (cociente > 0) {
            println("De 10 te voy a dar: $cociente\n")
            precio -= 10 * cociente
            Jugador.monedero["10"] = Jugador.monedero["10"]!! + cociente
            Mercader.monedero["10"] = Mercader.monedero["10"]!! - cociente
        }
        cociente = precio / 5
        if (cociente > 0) {
            println("De 5 te voy a dar: $cociente\n")
            precio -= 5 * cociente
            Jugador.monedero["5"] = Jugador.monedero["5"]!! + cociente
            Mercader.monedero["5"] = Mercader.monedero["5"]!! - cociente
        }
        cociente = precio / 1
        if (cociente > 0) {
            println("De 1 te voy a dar: $cociente\n")
            precio -= 1 * cociente
            Jugador.monedero["1"] = Jugador.monedero["1"]!! + cociente
            Mercader.monedero["1"] = Mercader.monedero["1"]!! - cociente
        }




        Mercader.objetos.addAll(obVender)
        Jugador.objetos.removeAll(obVender)


        println("Objetos de mercader : ${Mercader.objetos}\n")
        println("Monedero Mercader:\n${Mercader.monedero}\n")

        println("Objetos de Jugador : ${Jugador.objetos}\n")
        println("Monedero Jugador:\n${Jugador.monedero}\n")




        return Jugador
    }


    override fun toString(): String {
        return "nombre='$nombre'\n raza='$raza'\n estado='$estado'\n clase='$clase'\n mochila=$mochila\n objetos=$objetos\n monedero=$monedero"
    }




}