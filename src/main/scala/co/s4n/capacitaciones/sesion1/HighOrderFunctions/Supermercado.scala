package co.s4n.capacitaciones.sesion1.HighOrderFunctions

class Supermercado {

  import Supermercado._

  def buscarProductoPorNombre(productos: Seq[Producto], nombre: String): Seq[Producto] = {
    if (productos.isEmpty) Seq()
    else if (productos.head.nombre == nombre) Seq(productos.head)
    else buscarProductoPorNombre(productos.tail, nombre)
  }

  def buscarProductoPorFiltro(productos: Seq[Producto], filtro: FiltroProducto): Seq[Producto] = {

    if (productos.isEmpty) Seq()
    else if (filtro(productos.head)) buscarProductoPorFiltro(productos.tail, filtro) ++ Seq(productos.head)
    else buscarProductoPorFiltro(productos.tail, filtro)
  }

}

object Supermercado {

  type FiltroProducto = Producto => Boolean

  type VerificadorPrecio = Double => Boolean

  def complemento[A](predicado: A => Boolean): A => Boolean = param => !predicado(param)

  def cualquiera[A](predicados: (A => Boolean)*): A => Boolean = {
    def validate(a: A, pred: Seq[(A => Boolean)]): Boolean = {
      if (pred.isEmpty) false
      else if (pred.head(a)) true
      else validate(a, pred.tail)
    }
    a => validate(a, predicados);
  }

  def ninguno[A](predicados: (A => Boolean)*): A => Boolean = {
    def validate(a: A, pred: Seq[(A => Boolean)]): Boolean = {
      if (pred.isEmpty) true
      else if (pred.head(a)) false
      else validate(a, pred.tail)
    }
    a => validate(a, predicados);
  }

  def todos[A](predicados: (A => Boolean)*): A => Boolean = {
    def validate(a: A, pred: Seq[(A => Boolean)]): Boolean = {
      if (pred.isEmpty) true
      else if (pred.head(a)) validate(a, pred.tail)
      else false
    }
    a => validate(a, predicados);
  }

}
