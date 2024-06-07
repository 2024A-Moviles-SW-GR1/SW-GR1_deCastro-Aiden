fun main(args: Array<String>) {
    println("Hello World!")
    //variables inmutables no se reasignn
    val ejemploVariable = " Aiden "
    ejemploVariable.trim()
    val nombreProfesor: String = " Adrian Eguez "
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") -> {
            println("casado")
        }
        ("S") -> {
            println("soltero")
        }
        else -> {
            println("Desconocido")
        }
    }
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No" // if else chiquito
    calcularSueldo(10.0)
    calcularSueldo(10.0,15.0,20.0)
    calcularSueldo(10.0, bonoEspecial = 20.0)
    calcularSueldo()

    fun calcularSueldo{
        sueldo:
    }

    println("Program arguments: ${args.joinToString()}")
}