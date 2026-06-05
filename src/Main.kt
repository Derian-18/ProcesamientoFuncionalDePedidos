import data.pedidos
import data.productos

fun main(){
    // Mostrar todos los pedidos
    pedidos.forEach {
        println(it)
    }
    println()

    // Mostrar todos los productos
    productos.forEach {
        println(it)
    }

    println()

    // Filtrar por estatus como entregado
    val entregados = pedidos.filter {
        it.estatus == "Entregado"
    }

    println(entregados)

    println()

    // Filtrar productos por categoria
    val bebidas = productos.filter {
        it.categoria == "Bebidas"
    }

    println(bebidas)

    println()

    // Buscar producto por nombre
    val producto = productos.find {
        it.nombre.contains("café", ignoreCase = true)
    }

    println(producto)

    println()

    //Ordeenar productos por precio
    val ordenados = productos.sortedBy {
        it.precio
    }

    println(ordenados)

    println()

    // Ordenar pedidos por monto total
    val pedidosOrdenados = pedidos.sortedBy {
        it.total()
    }

    pedidosOrdenados.forEach {
        println("${it.id} - ${it.total()}")
    }

    println()

    // Total vendido en productos entregados
    val totalVendido = pedidos
        .filter { it.estatus == "Entregado" }
        .sumOf { it.total() }

    println("Total vendido: $totalVendido")

    println()

    // Cantidad de pedidos por estatus
    val conteo = pedidos.groupingBy {
        it.estatus
    }.eachCount()

    println(conteo)

    println()

    // Productos con precio mayor a cierta cantidad
    val caros = productos.filter {
        it.precio > 70
    }

    println(caros)

    println()

    // Mostrar cliente asociado a clada pedido
    pedidos.forEach {
        println("Pedido ${it.id}: ${it.cliente.nombre}")
    }

    println()

    // Lista resumida
    pedidos.map {
        "${it.cliente.nombre} | ${it.total()} | ${it.estatus}"
    }.forEach(::println)
}