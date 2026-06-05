package data
import models.Cliente
import models.Producto
import models.Pedido
import models.ArticuloPedido

val cliente1 = Cliente(1, "Juan Pérez")
val cliente2 = Cliente(2, "Ana López")
val cliente3 = Cliente(3, "Carlos Ruiz")

val cafe = Producto(1, "Café", 80.0, "Bebidas")
val te = Producto(2, "Té", 60.0, "Bebidas")
val pastel = Producto(3, "Pastel", 120.0, "Postres")
val sandwich = Producto(4, "Sándwich", 95.0, "Comida")
val galletas = Producto(5, "Galletas", 45.0, "Postres")

val productos = listOf(
    cafe,
    te,
    pastel,
    sandwich,
    galletas
)

val pedidos = listOf(
    Pedido(
        1,
        cliente1,
        listOf(
            ArticuloPedido(cafe, 2),
            ArticuloPedido(pastel, 1)
        ),
        "Entregado"
    ),
    Pedido(
        2,
        cliente2,
        listOf(
            ArticuloPedido(te, 3),
            ArticuloPedido(galletas, 2)
        ),
        "Pendiente"
    ),
    Pedido(
        3,
        cliente3,
        listOf(
            ArticuloPedido(sandwich, 2)
        ),
        "Enviado"
    ),
    Pedido(
        4,
        cliente1,
        listOf(
            ArticuloPedido(cafe, 1),
            ArticuloPedido(sandwich, 1)
        ),
        "Entregado"
    )
)