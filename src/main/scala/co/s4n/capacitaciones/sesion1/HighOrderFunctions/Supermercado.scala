package co.s4n.capacitaciones.sesion1.HighOrderFunctions

// 1. TODO Definir entidad producto


class Supermercado {

  import Supermercado._

  // 2. TODO Definir el metodo buscar producto por nombre
  def buscarProductoPorNombre(productos: Seq[Producto], nombre: String): Seq[Producto] = ???


  // 4. TODO Definir el metodo buscar producto por filtro
  def buscarProductoPorFiltro(productos: Seq[Producto], filtro: FiltroProducto): Seq[Producto] = ???

}

object Supermercado {

  // 3. TODO Definir el tipo FiltroProducto
  type FiltroProducto = ???

  // 5. TODO Definir el tipo VerificadorPrecio
  type VerificadorPrecio = ???

  // 6. TODO Definir la funcion complemento
  def complemento[A](predicado: A => Boolean): A => Boolean = ???

  // 7.1 TODO Definir el predicado cualquiera
  def cualquiera[A](predicados: (A => Boolean)*): A => Boolean = a => ???

  // 7.2 TODO Definir el predicado ninguno
  def ninguno[A](predicados: (A => Boolean)*): A => Boolean = ???

  // 7.3 TODO Definir el predicado todos
  def todos[A](predicados: (A => Boolean)*): A => Boolean = ???

}
