package models

data class Pedido(
    val id: Int,
    val cliente: Cliente,
    val articulos: List<ArticuloPedido>,
    val estatus: String
) {
    fun total(): Double {
        return articulos.sumOf {
            it.producto.precio * it.cantidad
        }
    }
}