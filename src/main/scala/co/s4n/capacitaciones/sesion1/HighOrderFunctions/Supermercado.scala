package co.s4n.capacitaciones.sesion1.HighOrderFunctions

class Supermercado {

  import Supermercado._

  def buscarProductoPorNombre(productos: Seq[Producto], nombre: String): Seq[Producto] = {
    productos.filter(_.nombre == nombre)
  }

  def buscarProductoPorFiltro(productos: Seq[Producto], filtro: FiltroProducto): Seq[Producto] = {
    productos.filter(filtro)
  }

}

object Supermercado {

  type FiltroProducto = Producto => Boolean

  type VerificadorPrecio = Double => Boolean

  def complemento[A](predicado: A => Boolean): A => Boolean = param => !predicado(param)

  def cualquiera[A](predicados: (A => Boolean)*): A => Boolean = {
    a => predicados.exists(_(a))
  }

  def ninguno[A](predicados: (A => Boolean)*): A => Boolean = {
    complemento(cualquiera(predicados: _*))
  }

  def todos[A](predicados: (A => Boolean)*): A => Boolean = {
    complemento(ninguno(predicados: _*))
  }

}
