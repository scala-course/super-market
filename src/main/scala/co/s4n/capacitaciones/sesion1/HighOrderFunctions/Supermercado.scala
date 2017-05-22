package co.s4n.capacitaciones.sesion1.HighOrderFunctions

// 1. TODO Definir entidad producto

class Supermercado {

  import Supermercado._

  // 2. TODO Definir el metodo buscar producto por nombre
  def buscarProductoPorNombre(productos: Seq[Producto], nombre: String): Seq[Producto] = {
    if (productos.isEmpty) Seq()
    else if (productos.head.nombre == nombre) Seq(productos.head)
    else buscarProductoPorNombre(productos.tail, nombre)
  }

  // 4. TODO Definir el metodo buscar producto por filtro
  def buscarProductoPorFiltro(productos: Seq[Producto], filtro: FiltroProducto): Seq[Producto] = {

    if (productos.isEmpty) Seq()
    else if (filtro(productos.head)) buscarProductoPorFiltro(productos.tail, filtro) ++ Seq(productos.head)
    else buscarProductoPorFiltro(productos.tail, filtro)
  }

}

object Supermercado {

  // 3. TODO Definir el tipo FiltroProducto
  type FiltroProducto = Producto => Boolean

  // 5. TODO Definir el tipo VerificadorPrecio
  type VerificadorPrecio = Double => Boolean

  // 6. TODO Definir la funcion complemento
  def complemento[A](predicado: A => Boolean): A => Boolean = param => !predicado(param)

  // 7.1 TODO Definir el predicado cualquiera
  def cualquiera[A](predicados: (A => Boolean)*): A => Boolean = {
    def validate(a: A, pred: Seq[(A => Boolean)]): Boolean = {
      if (pred.isEmpty) false
      else if (pred.head(a)) true
      else validate(a, pred.tail)
    }
    a => validate(a, predicados);
  }

  // 7.2 TODO Definir el predicado ninguno
  def ninguno[A](predicados: (A => Boolean)*): A => Boolean = {
    def validate(a: A, pred: Seq[(A => Boolean)]): Boolean = {
      if (pred.isEmpty) true
      else if (pred.head(a)) false
      else validate(a, pred.tail)
    }
    a => validate(a, predicados);
  }

  // 7.3 TODO Definir el predicado todos
  def todos[A](predicados: (A => Boolean)*): A => Boolean = {
    def validate(a: A, pred: Seq[(A => Boolean)]): Boolean = {
      if (pred.isEmpty) true
      else if (pred.head(a)) validate(a, pred.tail)
      else false
    }
    a => validate(a, predicados);
  }

}
