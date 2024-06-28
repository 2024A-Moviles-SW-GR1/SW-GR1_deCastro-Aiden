import java.io.File
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

// Definición de las entidades
data class Suscripcion(
    val id: String = UUID.randomUUID().toString(),
    var nombre: String,
    var fechaInicio: String, // formato: YYYY-MM-DD
    var esActiva: Boolean,
    var costoMensual: Double,
    val servicios: MutableList<Servicio> = mutableListOf()
)

data class Servicio(
    val id: String = UUID.randomUUID().toString(),
    var nombre: String,
    var descripcion: String,
    var esPremium: Boolean,
    var costo: Double
)

class SuscripcionService(private var filePath: String) {
    private val gson = Gson()
    private var suscripciones: MutableList<Suscripcion> = mutableListOf()

    init {
        loadSuscripciones()
    }

    // Función para cargar suscripciones desde el archivo
    fun loadSuscripciones() {
        val file = File(filePath)
        suscripciones = if (file.exists()) {
            try {
                val type = object : TypeToken<MutableList<Suscripcion>>() {}.type
                gson.fromJson(file.readText(), type) ?: mutableListOf()
            } catch (e: JsonSyntaxException) {
                println("Error al cargar el archivo: formato JSON no válido.")
                mutableListOf()
            }
        } else {
            mutableListOf()
        }
    }

    // Función para guardar suscripciones en el archivo
    fun saveSuscripciones() {
        val file = File(filePath)
        file.writeText(gson.toJson(suscripciones))
    }

    // Cambiar el archivo actual
    fun changeFilePath(newFilePath: String) {
        filePath = newFilePath
        loadSuscripciones()
    }

    // Operaciones CRUD para Suscripcion
    fun crearSuscripcion(suscripcion: Suscripcion) {
        suscripciones.add(suscripcion)
        saveSuscripciones()
    }

    fun leerSuscripciones(): List<Suscripcion> = suscripciones

    fun actualizarSuscripcion(id: String, suscripcionActualizada: Suscripcion) {
        val index = suscripciones.indexOfFirst { it.id == id }
        if (index != -1) {
            suscripciones[index] = suscripcionActualizada
            saveSuscripciones()
        }
    }

    fun eliminarSuscripcion(id: String) {
        suscripciones.removeIf { it.id == id }
        saveSuscripciones()
    }

    // Operaciones CRUD para Servicio dentro de una Suscripcion
    fun agregarServicio(suscripcionId: String, servicio: Servicio) {
        val suscripcion = suscripciones.find { it.id == suscripcionId }
        suscripcion?.let {
            it.servicios.add(servicio)
            saveSuscripciones()
        }
    }

    fun leerServicios(suscripcionId: String): List<Servicio>? {
        return suscripciones.find { it.id == suscripcionId }?.servicios
    }

    fun actualizarServicio(suscripcionId: String, servicioId: String, servicioActualizado: Servicio) {
        val suscripcion = suscripciones.find { it.id == suscripcionId }
        suscripcion?.let {
            val index = it.servicios.indexOfFirst { servicio -> servicio.id == servicioId }
            if (index != -1) {
                it.servicios[index] = servicioActualizado
                saveSuscripciones()
            }
        }
    }

    fun eliminarServicio(suscripcionId: String, servicioId: String) {
        val suscripcion = suscripciones.find { it.id == suscripcionId }
        suscripcion?.let {
            it.servicios.removeIf { servicio -> servicio.id == servicioId }
            saveSuscripciones()
        }
    }
}

// Función para leer entrada del usuario con validación
fun readUserInput(prompt: String): String {
    print(prompt)
    return readLine()!!
}

fun readNonEmptyString(prompt: String): String {
    while (true) {
        val input = readUserInput(prompt)
        if (input.isNotBlank()) return input
        println("Entrada no puede estar vacía.")
    }
}

fun readBooleanInput(prompt: String): Boolean {
    while (true) {
        val input = readUserInput(prompt).toLowerCase()
        if (input == "true" || input == "false") return input.toBoolean()
        println("Entrada inválida. Por favor ingrese 'true' o 'false'.")
    }
}

fun readDoubleInput(prompt: String): Double {
    while (true) {
        val input = readUserInput(prompt)
        try {
            return input.toDouble()
        } catch (e: NumberFormatException) {
            println("Entrada inválida. Por favor ingrese un número decimal.")
        }
    }
}

fun readIntInput(prompt: String): Int {
    while (true) {
        val input = readUserInput(prompt)
        try {
            return input.toInt()
        } catch (e: NumberFormatException) {
            println("Entrada inválida. Por favor ingrese un número entero.")
        }
    }
}

fun readDateInput(prompt: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    dateFormat.isLenient = false
    while (true) {
        val input = readUserInput(prompt)
        try {
            dateFormat.parse(input)
            return input
        } catch (e: Exception) {
            println("Entrada inválida. Por favor ingrese una fecha en el formato YYYY-MM-DD.")
        }
    }
}

fun seleccionarSuscripcion(suscripciones: List<Suscripcion>): Suscripcion? {
    if (suscripciones.isEmpty()) {
        println("No hay suscripciones disponibles.")
        return null
    }
    println("Seleccione una suscripción:")
    suscripciones.forEachIndexed { index, suscripcion ->
        println("${index + 1}. ${suscripcion.nombre} (ID: ${suscripcion.id})")
    }
    val seleccion = readIntInput("Ingrese el número de la suscripción: ")
    return if (seleccion in 1..suscripciones.size) {
        suscripciones[seleccion - 1]
    } else {
        println("Selección inválida.")
        null
    }
}

// Función para mostrar el menú y manejar la interacción del usuario
fun mostrarMenu() {
    var filePath = "suscripciones.json"
    val suscripcionService = SuscripcionService(filePath)

    while (true) {
        println("1. Crear Suscripción")
        println("2. Leer Suscripciones")
        println("3. Actualizar Suscripción")
        println("4. Eliminar Suscripción")
        println("5. Agregar Servicio a Suscripción")
        println("6. Leer Servicios de una Suscripción")
        println("7. Actualizar Servicio de una Suscripción")
        println("8. Eliminar Servicio de una Suscripción")
        println("9. Cargar Archivo")
        println("10. Guardar Archivo")
        println("11. Salir")
        val opcion = readIntInput("Seleccione una opción: ")

        when (opcion) {
            1 -> {
                val nombre = readNonEmptyString("Nombre: ")
                val fechaInicio = readDateInput("Fecha de Inicio (YYYY-MM-DD): ")
                val esActiva = readBooleanInput("Es Activa (true/false): ")
                val costoMensual = readDoubleInput("Costo Mensual: ")

                val suscripcion = Suscripcion(
                    nombre = nombre,
                    fechaInicio = fechaInicio,
                    esActiva = esActiva,
                    costoMensual = costoMensual
                )
                suscripcionService.crearSuscripcion(suscripcion)
            }
            2 -> {
                val suscripciones = suscripcionService.leerSuscripciones()
                println("Suscripciones:")
                suscripciones.forEach { println(it) }
            }
            3 -> {
                val suscripciones = suscripcionService.leerSuscripciones()
                val suscripcion = seleccionarSuscripcion(suscripciones)
                if (suscripcion != null) {
                    suscripcion.nombre = readNonEmptyString("Nombre (${suscripcion.nombre}): ")
                    suscripcion.fechaInicio = readDateInput("Fecha de Inicio (${suscripcion.fechaInicio}): ")
                    suscripcion.esActiva = readBooleanInput("Es Activa (${suscripcion.esActiva}): ")
                    suscripcion.costoMensual = readDoubleInput("Costo Mensual (${suscripcion.costoMensual}): ")
                    suscripcionService.actualizarSuscripcion(suscripcion.id, suscripcion)
                }
            }
            4 -> {
                val suscripciones = suscripcionService.leerSuscripciones()
                val suscripcion = seleccionarSuscripcion(suscripciones)
                if (suscripcion != null) {
                    suscripcionService.eliminarSuscripcion(suscripcion.id)
                }
            }
            5 -> {
                val suscripciones = suscripcionService.leerSuscripciones()
                val suscripcion = seleccionarSuscripcion(suscripciones)
                if (suscripcion != null) {
                    val nombre = readNonEmptyString("Nombre: ")
                    val descripcion = readNonEmptyString("Descripción: ")
                    val esPremium = readBooleanInput("Es Premium (true/false): ")
                    val costo = readDoubleInput("Costo: ")

                    val servicio = Servicio(
                        nombre = nombre,
                        descripcion = descripcion,
                        esPremium = esPremium,
                        costo = costo
                    )
                    suscripcionService.agregarServicio(suscripcion.id, servicio)
                }
            }
            6 -> {
                val suscripciones = suscripcionService.leerSuscripciones()
                val suscripcion = seleccionarSuscripcion(suscripciones)
                if (suscripcion != null) {
                    val servicios = suscripcionService.leerServicios(suscripcion.id)
                    println("Servicios:")
                    servicios?.forEach { println(it) }
                }
            }
            7 -> {
                val suscripciones = suscripcionService.leerSuscripciones()
                val suscripcion = seleccionarSuscripcion(suscripciones)
                if (suscripcion != null) {
                    val servicios = suscripcionService.leerServicios(suscripcion.id)
                    println("Servicios:")
                    servicios?.forEachIndexed { index, servicio ->
                        println("${index + 1}. ${servicio.nombre} (ID: ${servicio.id})")
                    }
                    val seleccion = readIntInput("Ingrese el número del servicio: ")
                    val servicio = servicios?.getOrNull(seleccion - 1)
                    if (servicio != null) {
                        servicio.nombre = readNonEmptyString("Nombre (${servicio.nombre}): ")
                        servicio.descripcion = readNonEmptyString("Descripción (${servicio.descripcion}): ")
                        servicio.esPremium = readBooleanInput("Es Premium (${servicio.esPremium}): ")
                        servicio.costo = readDoubleInput("Costo (${servicio.costo}): ")
                        suscripcionService.actualizarServicio(suscripcion.id, servicio.id, servicio)
                    } else {
                        println("Selección inválida.")
                    }
                }
            }
            8 -> {
                val suscripciones = suscripcionService.leerSuscripciones()
                val suscripcion = seleccionarSuscripcion(suscripciones)
                if (suscripcion != null) {
                    val servicios = suscripcionService.leerServicios(suscripcion.id)
                    println("Servicios:")
                    servicios?.forEachIndexed { index, servicio ->
                        println("${index + 1}. ${servicio.nombre} (ID: ${servicio.id})")
                    }
                    val seleccion = readIntInput("Ingrese el número del servicio: ")
                    val servicio = servicios?.getOrNull(seleccion - 1)
                    if (servicio != null) {
                        suscripcionService.eliminarServicio(suscripcion.id, servicio.id)
                    } else {
                        println("Selección inválida.")
                    }
                }
            }
            9 -> {
                val newFilePath = readNonEmptyString("Ingrese la ruta del archivo a cargar: ")
                suscripcionService.changeFilePath(newFilePath)
                println("Archivo cargado.")
            }
            10 -> {
                suscripcionService.saveSuscripciones()
                println("Archivo guardado.")
            }
            11 -> return
            else -> println("Opción no válida.")
        }
    }
}

// Ejemplo de uso
fun main() {
    mostrarMenu()
}