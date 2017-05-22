package co.s4n.capacitaciones.sesion1.HighOrderFunctions

import org.scalatest._

class SupermercadoTest extends FunSuite {

  import co.s4n.capacitaciones.sesion1.HighOrderFunctions.Supermercado._

  val productos: Seq[Producto] = Seq(
    Producto("Producto 1", "Pato", 5000),
    Producto("Producto 3", "SONY", 46989.5),
    Producto("Producto 2", "Conocida", 50000),
    Producto("Producto 4", "S4N", 999999)
  )

  test("Buscar producto por nombre") {
    val supermercado: Supermercado = new Supermercado()
    assert(supermercado.buscarProductoPorNombre(productos, "Producto 1").size == 1)

  }

  test("Buscar producto por filtro 1") {

    val supermercado: Supermercado = new Supermercado()

    def precioMinimo(precio: Double): FiltroProducto = {
      product => product.precio >= precio
    }

    def precioMaximo(precio: Double): FiltroProducto = {
      product => product.precio <= precio
    }

    def marcaX(marca: String): FiltroProducto = {
      product => product.marca.eq(marca)
    }

    def noMarcaX(marca: String): FiltroProducto = {
      product => !product.marca.eq(marca)
    }

    assert(supermercado.buscarProductoPorFiltro(productos, precioMinimo(6000)).size == 3)
    assert(supermercado.buscarProductoPorFiltro(productos, precioMaximo(5000)).size == 1)
    assert(supermercado.buscarProductoPorFiltro(productos, marcaX("Pato")).size == 1)
    assert(supermercado.buscarProductoPorFiltro(productos, noMarcaX("Pato")).size == 3)

  }

  test("Buscar producto por filtro 2") {

    val supermercado: Supermercado = new Supermercado()

    def reglaPrecio(verificador: VerificadorPrecio): FiltroProducto = {
      product => verificador(product.precio)
    }
    def precioMinimo(precio: Double): FiltroProducto = {
      product => reglaPrecio(givePrice => givePrice >= precio)(product)
    }

    def precioMaximo(precio: Double): FiltroProducto = {
      product => reglaPrecio(givePrice => givePrice <= precio)(product)
    }

    assert(supermercado.buscarProductoPorFiltro(productos, precioMinimo(6000)).size == 3)
    assert(supermercado.buscarProductoPorFiltro(productos, precioMaximo(5000)).size == 1)

  }

  test("Buscar producto por filtro 3") {

    val supermercado: Supermercado = new Supermercado()

    // Filtro de marcaX (el mismo del test anterior)
    def marcaX(marca: String): FiltroProducto = {
      product => product.marca.eq(marca)
    }
    def noMarcaX(marca: String): FiltroProducto = {
      Supermercado.complemento(marcaX(marca))
    }

    assert(supermercado.buscarProductoPorFiltro(productos, marcaX("Pato")).size == 1)
    assert(supermercado.buscarProductoPorFiltro(productos, noMarcaX("Pato")).size == 3)

  }

  test("Buscar producto por filtro 4") {

    val supermercado: Supermercado = new Supermercado()

    // Regla de precio para los filtros precioMinimo y precioMaximo (la misma del test anterior)

    // Filtros (los mismos anteriores)
    def precioMinimo(precio: Double): FiltroProducto = {
      product => product.precio >= precio
    }

    def precioMaximo(precio: Double): FiltroProducto = {
      product => product.precio <= precio
    }

    def marcaX(marca: String): FiltroProducto = {
      product => product.marca.eq(marca)
    }

    def noMarcaX(marca: String): FiltroProducto = {
      product => !product.marca.eq(marca)
    }

    // filtroMultipleCualquiera
    // Todos los productos cuyo precio sea menor o igual a 10000 o sea de la marca S4N
    def filtroMultipleCualquiera: FiltroProducto = {
      Supermercado.cualquiera(precioMaximo(10000), marcaX("S4N"))
    }
    // filtroMultipleNinguno
    // Todos los productos cuyo precio NO sea mayor o igual a 50000 (que sea menor a 50000)
    // y que NO sea menor o igual a 40000 (que sea mayor a 40000)
    def filtroMultipleNinguno: FiltroProducto = {
      Supermercado.ninguno(precioMaximo(40000), precioMinimo(50000))
    }
    // filtroMultipleTodos
    // Todos los productos cuyo precio sea menor o igual a 5000 y que no sea de la marca S4N
    def filtroMultipleTodos: FiltroProducto = {
      Supermercado.ninguno(precioMaximo(5000), noMarcaX("S4N"))
    }

    assert(supermercado.buscarProductoPorFiltro(productos, filtroMultipleCualquiera).size == 2)
    assert(supermercado.buscarProductoPorFiltro(productos, filtroMultipleNinguno).size == 1)
    assert(supermercado.buscarProductoPorFiltro(productos, filtroMultipleTodos).size == 1)

  }

}