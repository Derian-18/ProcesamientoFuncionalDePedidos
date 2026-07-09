import data.pedidos
import data.productos
import reportes.*

fun main() {

    println("Total vendido por categoría =====")
    totalVendidoPorCategoria(pedidos).forEach { (categoria, total) ->
        println("$categoria: $$total")
    }
    println()

    println("Total vendido por cliente =====")
    totalVendidoPorCliente(pedidos).forEach { (cliente, total) ->
        println("${cliente.nombre}: $$total")
    }
    println()

    println("Cliente con mayor monto de compras =====")
    clienteConMayorCompra(pedidos)?.let { (cliente, total) ->
        println("${cliente.nombre} con $$total")
    }
    println()

    println("Producto más vendido =====")
    productoMasVendido(pedidos)?.let { (producto, cantidad) ->
        println("${producto.nombre} con $cantidad unidades")
    }
    println()

    println("Categoría con mayor venta =====")
    categoriaConMayorVenta(pedidos)?.let { (categoria, total) ->
        println("$categoria con $$total")
    }
    println()

    println("Ticket promedio de pedidos entregados =====")
    println("$${ticketPromedioEntregados(pedidos)}")
    println()

    println("Top 5 productos más vendidos =====")
    topProductosMasVendidos(pedidos, 5).forEachIndexed { i, (producto, cantidad) ->
        println("${i + 1}. ${producto.nombre} - $cantidad unidades")
    }
    println()

    println("Top 3 clientes con mayor compra =====")
    topClientesMayorCompra(pedidos, 3).forEachIndexed { i, (cliente, total) ->
        println("${i + 1}. ${cliente.nombre} - $$total")
    }
    println()

    println("Pedidos agrupados por estatus =====")
    agruparPedidosPorEstatus(pedidos).forEach { (estatus, lista) ->
        println("$estatus (${lista.size}): ${lista.map { it.id }}")
    }
    println()

    println("Productos agrupados por categoría =====")
    agruparProductosPorCategoria(productos).forEach { (categoria, lista) ->
        println("$categoria: ${lista.map { it.nombre }}")
    }
    println()

    println("Unidades vendidas por producto =====")
    unidadesVendidasPorProducto(pedidos).forEach { (producto, cantidad) ->
        println("${producto.nombre}: $cantidad unidades")
    }
    println()

    println("Reporte general de ventas =====")
    val reporte = reporteGeneralVentas(pedidos)
    println("Total de pedidos: ${reporte.totalPedidos}")
    println("Total vendido (global): $${reporte.totalVendidoGlobal}")
    println("Producto más vendido: ${reporte.productoMasVendido?.first?.nombre}")
    println("Categoría más vendida: ${reporte.categoriaMasVendida?.first}")
    println("Cliente con mayor compra: ${reporte.clienteConMayorCompra?.first?.nombre}")
    println()

    println("Reporte de pedidos cancelados =====")
    reportePedidosCancelados(pedidos).forEach {
        println("Pedido ${it.id} - Cliente: ${it.cliente.nombre} - Total: $${it.total()}")
    }
    println()

    println("Reporte de productos no vendidos =====")
    reporteProductosNoVendidos(productos, pedidos).forEach {
        println("${it.nombre} (${it.categoria})")
    }
    println()

    println("Estadísticas generales =====")
    val stats = estadisticasGenerales(pedidos)
    println("Total de pedidos: ${stats.totalPedidos}")
    println("Pedidos entregados: ${stats.pedidosEntregados}")
    println("Pedidos cancelados: ${stats.pedidosCancelados}")
    println("Total vendido (entregados): $${stats.totalVendido}")
    println("Promedio de venta por pedido: $${stats.promedioVenta}")
}