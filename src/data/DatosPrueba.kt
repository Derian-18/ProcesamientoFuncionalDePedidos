package data
import models.Cliente
import models.Producto
import models.Pedido
import models.ArticuloPedido

// Clientes
val cliente1 = Cliente(1, "Juan Pérez")
val cliente2 = Cliente(2, "Ana López")
val cliente3 = Cliente(3, "Carlos Ruiz")
val cliente4 = Cliente(4, "María Torres")
val cliente5 = Cliente(5, "Luis Gómez")

val clientes = listOf(cliente1, cliente2, cliente3, cliente4, cliente5)

// Productos
val cafe = Producto(1, "Café", 80.0, "Bebidas")
val te = Producto(2, "Té", 60.0, "Bebidas")
val pastel = Producto(3, "Pastel", 120.0, "Postres")
val sandwich = Producto(4, "Sándwich", 95.0, "Comida")
val galletas = Producto(5, "Galletas", 45.0, "Postres")
val agua = Producto(6, "Agua embotellada", 25.0, "Bebidas")
val ensalada = Producto(7, "Ensalada", 85.0, "Comida")
val pizza = Producto(8, "Pizza personal", 110.0, "Comida")
val jugo = Producto(9, "Jugo natural", 40.0, "Bebidas")      // no se vende en ningún pedido
val croissant = Producto(10, "Croissant", 55.0, "Postres")    // no se vende en ningún pedido

val productos = listOf(
    cafe, te, pastel, sandwich, galletas,
    agua, ensalada, pizza, jugo, croissant
)

// Pedidos
val pedidos = listOf(
    Pedido(
        1, cliente1,
        listOf(ArticuloPedido(cafe, 2), ArticuloPedido(pastel, 1)),
        "Entregado"
    ),
    Pedido(
        2, cliente2,
        listOf(ArticuloPedido(te, 3), ArticuloPedido(galletas, 2)),
        "Pendiente"
    ),
    Pedido(
        3, cliente3,
        listOf(ArticuloPedido(sandwich, 2)),
        "Enviado"
    ),
    Pedido(
        4, cliente1,
        listOf(ArticuloPedido(cafe, 1), ArticuloPedido(sandwich, 1)),
        "Entregado"
    ),
    Pedido(
        5, cliente4,
        listOf(ArticuloPedido(pizza, 2), ArticuloPedido(agua, 2)),
        "Entregado"
    ),
    Pedido(
        6, cliente5,
        listOf(ArticuloPedido(ensalada, 1), ArticuloPedido(te, 1)),
        "Cancelado"
    ),
    Pedido(
        7, cliente2,
        listOf(ArticuloPedido(cafe, 4), ArticuloPedido(galletas, 3)),
        "Entregado"
    ),
    Pedido(
        8, cliente3,
        listOf(ArticuloPedido(pizza, 1)),
        "Cancelado"
    ),
    Pedido(
        9, cliente4,
        listOf(ArticuloPedido(cafe, 3), ArticuloPedido(pastel, 2), ArticuloPedido(agua, 1)),
        "Entregado"
    ),
    Pedido(
        10, cliente5,
        listOf(ArticuloPedido(sandwich, 3), ArticuloPedido(ensalada, 2)),
        "Pendiente"
    ),
    Pedido(
        11, cliente1,
        listOf(ArticuloPedido(galletas, 5)),
        "Entregado"
    ),
    Pedido(
        12, cliente2,
        listOf(ArticuloPedido(pizza, 3), ArticuloPedido(cafe, 2)),
        "Enviado"
    )
)