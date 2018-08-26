package com.spark.hackathon.core.explicacion

import akka.event.slf4j.SLF4JLogging
import com.spark.hackathon.core.modelos.Alumno
import com.spark.hackathon.core.modelos.SexoEnum.SexoEnum


/**
  * Repasar las funcionalidades basicas de Scala enfocandonos en las colecciones, funciones lambda y la herencia
  */
object ScalaBasicoMain extends App with SLF4JLogging {

  /** Scala básico **/

  val profesor = "Jose Carlos"

  val sabeBigData = {
    if ("Jose Carlos trabaja en Stratio".contains("Stratio"))
      true
    else false
  }

  val tenemosProfesor = if (sabeBigData)
    Option("Sí tenemos profesor")
  else None

  log.info(s"¿Tenemos profesor para aprender Spark? ${tenemosProfesor.getOrElse("No")}")

  profesor match {
    case "Jose Carlos" =>
      log.info("¿Aprenderemos Spark en tan solo 12 horas?")
    case _ =>
      log.info("Spark no es mas que un framework para tener colecciones distribuidas en Scala, lo puede impartir cualquiera")
  }


  /** Herencia **/

  trait Persona {

    val name: String

    val sexo: SexoEnum

    private lazy val ciudadBonita = "Granada"

    def comer(): Unit

    def beber(): Unit

    def andar(): Unit

    def casarse(otraPersona: Persona): Boolean

    def cualEsLaCiudadMasBonita(): String = ciudadBonita

    def dondeIrDeVacaciones(ultimoLugarVisitado: String)(deseosDeFuturo: => String): String

    override def toString: String = s"$name-$sexo"

  }

  case class Español(name: String, sexo: SexoEnum, edad: Int = 30) extends Persona {

    override def andar(): Unit = {
      log.info(s"Un español como $name es capaz de andar")
    }

    override def beber(): Unit = {
      log.info(s"Un español como $name es capaz de beber y no debe de beber cruzcampo")
    }

    override def comer(): Unit = {
      log.info(s"Un español como $name es capaz de comer y suele comer tortilla de patatas y paella")
    }

    override def casarse(otraPersona: Persona): Boolean = {
      if (otraPersona.name == profesor) {
        log.info(s"Con $profesor no se casa $name que no es de fiar")
        false
      } else {
        log.info(s"Con un/una ${otraPersona.name} se casa $name")
        true
      }
    }

    override def dondeIrDeVacaciones(ultimoLugarVisitado: String)(deseosDeFuturo: => String): String = {
      val visitare = {
        if (ultimoLugarVisitado != cualEsLaCiudadMasBonita())
          cualEsLaCiudadMasBonita()
        else deseosDeFuturo
      }

      visitare
    }
  }

  import com.spark.hackathon.core.modelos.SexoEnum._

  val unaManoloCualquiera = Español("Manolo", Hombre, 33)

  unaManoloCualquiera.andar()
  unaManoloCualquiera.beber()
  unaManoloCualquiera.comer()

  if (!unaManoloCualquiera.casarse(Español("Jose Carlos", Hombre)))
    unaManoloCualquiera.casarse(Español("Lucia", Mujer))

  val ciudadFuturasVacaciones = unaManoloCualquiera.dondeIrDeVacaciones("Benidorm") {
    val ciudades = Seq("Madrid", "Barcelona")
    ciudades.head
  }

  log.info(s"${unaManoloCualquiera.name} se va de vacaciones a $ciudadFuturasVacaciones")

  /** Colecciones y funciones lambda **/

  val listaNombres1 = Seq("Javi", "Jose", "Patricia", "Ruben", "Sergio", "Laura")
  val listaNombres2 = Seq("Maria", "Manolo", "Juan", "Javi")
  val listasNombresUnidas = listaNombres1 ++ listaNombres2

  log.info(s"Lista de nombres: $listasNombresUnidas")

  val listaNombresOrdenados = listasNombresUnidas.sorted

  log.info(s"Lista de nombres: $listaNombresOrdenados")

  val listaNombresDistinct = listaNombresOrdenados.distinct

  log.info(s"Lista de nombres distintos: $listaNombresDistinct")

  val listaNombresModified = listaNombresDistinct.map { nombre =>
    if (nombre == "Javi")
      "Javier"
    else nombre
  }

  log.info(s"Lista de nombres modificados: $listaNombresModified")

  val listaNombresFiltrada1 = listaNombresModified.filter(nombre => nombre != "Jose")
  val listaNombresFiltrada2 = listaNombresModified.flatMap { nombre =>
    if (nombre == "Jose")
      Seq.empty
    else Seq(nombre)
  }
  val listaNombresFiltrada3 = listaNombresModified.flatMap { nombre =>
    if (nombre == "Jose")
      None
    else Option(nombre)
  }

  log.info(s"Lista de nombres filtrados: $listaNombresFiltrada1")

  val Final = listaNombresFiltrada3 :+ "Jose Carlos"

  log.info(s"Lista final: $Final")

  val randomGenerator = scala.util.Random
  val listaTuplaNombreEdad = Final.map(nombre => (nombre, randomGenerator.nextInt(50)))

  log.info(s"Lista con nombre y edad: $listaTuplaNombreEdad")

  val listaAlumno = listaTuplaNombreEdad.map(tupla => Alumno(tupla._1, tupla._2))

  log.info(s"Lista de alumnos: $listaTuplaNombreEdad")

  val mapaDeAlumnos = listaAlumno.map(alumno => alumno.nombre -> alumno).toMap

  log.info(s"Mapa de alumnos: $mapaDeAlumnos")


  /** Implicitos **/

  implicit val amigoDeTodos = Español("El amigo de todos", Hombre)

  implicit def stringToEspañol(name: String): Español = Español(name, NA)

  val personaTipada: Persona = "Persona Magica"

  log.info(s"Esta es la persona magica creada por un implicito: $personaTipada")

  def funcionMagica(amigo: String)(implicit elAmigoDeTodos: Persona): Unit = {
    log.info(s"La persona '$elAmigoDeTodos' es amigo de $amigo")
  }

  funcionMagica("Alumnos UAX")

}

