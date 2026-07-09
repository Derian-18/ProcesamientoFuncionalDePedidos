package reportes

import models.Cliente
import models.Pedido
import models.Producto

// --- 1. Total vendido por categoría ---
fun totalVendidoPorCategoria(pedidos: List<Pedido>): Map<String, Double> =
    pedidos.flatMap { it.articulos }
        .groupBy { it.producto.categoria }
        .mapValues { (_, articulos) -> articulos.sumOf { it.producto.precio * it.cantidad } }

// --- 2. Total vendido por cliente ---
fun totalVendidoPorCliente(pedidos: List<Pedido>): Map<Cliente, Double> =
    pedidos.groupBy { it.cliente }
        .mapValues { (_, pedidosCliente) -> pedidosCliente.sumOf { it.total() } }

// --- 3. Cliente con mayor monto de compras ---
fun clienteConMayorCompra(pedidos: List<Pedido>): Pair<Cliente, Double>? =
    totalVendidoPorCliente(pedidos).maxByOrNull { it.value }?.toPair()

// --- 11. Unidades vendidas por producto (usada por varios reportes) ---
fun unidadesVendidasPorProducto(pedidos: List<Pedido>): Map<Producto, Int> =
    pedidos.flatMap { it.articulos }
        .groupBy { it.producto }
        .mapValues { (_, articulos) -> articulos.sumOf { it.cantidad } }

// --- 4. Producto más vendido ---
fun productoMasVendido(pedidos: List<Pedido>): Pair<Producto, Int>? =
    unidadesVendidasPorProducto(pedidos).maxByOrNull { it.value }?.toPair()

// --- 5. Categoría con mayor venta ---
fun categoriaConMayorVenta(pedidos: List<Pedido>): Pair<String, Double>? =
    totalVendidoPorCategoria(pedidos).maxByOrNull { it.value }?.toPair()

// --- 6. Ticket promedio de pedidos entregados ---
fun ticketPromedioEntregados(pedidos: List<Pedido>): Double =
    pedidos.filter { it.estatus == "Entregado" }
        .map { it.total() }
        .let { totales -> if (totales.isEmpty()) 0.0 else totales.average() }

// --- 7. Top N productos más vendidos (por unidades) ---
fun topProductosMasVendidos(pedidos: List<Pedido>, n: Int = 5): List<Pair<Producto, Int>> =
    unidadesVendidasPorProducto(pedidos)
        .entries
        .sortedByDescending { it.value }
        .take(n)
        .map { it.toPair() }

// --- 8. Top N clientes con mayor compra ---
fun topClientesMayorCompra(pedidos: List<Pedido>, n: Int = 3): List<Pair<Cliente, Double>> =
    totalVendidoPorCliente(pedidos)
        .entries
        .sortedByDescending { it.value }
        .take(n)
        .map { it.toPair() }

// --- 9. Agrupar pedidos por estatus ---
fun agruparPedidosPorEstatus(pedidos: List<Pedido>): Map<String, List<Pedido>> =
    pedidos.groupBy { it.estatus }

// --- 10. Agrupar productos por categoría ---
fun agruparProductosPorCategoria(productos: List<Producto>): Map<String, List<Producto>> =
    productos.groupBy { it.categoria }

// --- 13. Reporte de pedidos cancelados ---
fun reportePedidosCancelados(pedidos: List<Pedido>): List<Pedido> =
    pedidos.filter { it.estatus == "Cancelado" }

// --- 14. Reporte de productos no vendidos ---
fun reporteProductosNoVendidos(productos: List<Producto>, pedidos: List<Pedido>): List<Producto> {
    val vendidos = pedidos.flatMap { it.articulos }.map { it.producto }.toSet()
    return productos.filterNot { it in vendidos }
}

// --- 15. Estadísticas generales ---
data class EstadisticasGenerales(
    val totalPedidos: Int,
    val pedidosEntregados: Int,
    val pedidosCancelados: Int,
    val totalVendido: Double,
    val promedioVenta: Double
)

fun estadisticasGenerales(pedidos: List<Pedido>): EstadisticasGenerales {
    val entregados = pedidos.count { it.estatus == "Entregado" }
    val cancelados = pedidos.count { it.estatus == "Cancelado" }
    val totalVendido = pedidos.filter { it.estatus == "Entregado" }.sumOf { it.total() }
    val promedio = if (pedidos.isEmpty()) 0.0 else pedidos.sumOf { it.total() } / pedidos.size

    return EstadisticasGenerales(
        totalPedidos = pedidos.size,
        pedidosEntregados = entregados,
        pedidosCancelados = cancelados,
        totalVendido = totalVendido,
        promedioVenta = promedio
    )
}

// --- 12. Reporte general de ventas (agrupa varios de los anteriores) ---
data class ReporteGeneral(
    val totalPedidos: Int,
    val totalVendidoGlobal: Double,
    val totalPorCategoria: Map<String, Double>,
    val totalPorCliente: Map<Cliente, Double>,
    val productoMasVendido: Pair<Producto, Int>?,
    val categoriaMasVendida: Pair<String, Double>?,
    val clienteConMayorCompra: Pair<Cliente, Double>?,
    val estadisticas: EstadisticasGenerales
)

fun reporteGeneralVentas(pedidos: List<Pedido>): ReporteGeneral =
    ReporteGeneral(
        totalPedidos = pedidos.size,
        totalVendidoGlobal = pedidos.sumOf { it.total() },
        totalPorCategoria = totalVendidoPorCategoria(pedidos),
        totalPorCliente = totalVendidoPorCliente(pedidos),
        productoMasVendido = productoMasVendido(pedidos),
        categoriaMasVendida = categoriaConMayorVenta(pedidos),
        clienteConMayorCompra = clienteConMayorCompra(pedidos),
        estadisticas = estadisticasGenerales(pedidos)
    )