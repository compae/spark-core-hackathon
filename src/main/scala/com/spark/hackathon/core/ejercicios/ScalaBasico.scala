package com.spark.hackathon.core.ejercicios

import akka.event.slf4j.SLF4JLogging


object ScalaBasico extends App with SLF4JLogging {


  /** Crear un mapa de alumnos con direccion donde la clave es el nombre, requisitos:
    *
    * No repetidos
    * No queremos mayores de 30 años
    * La dirección es opcional y podemos crear un alumno sin dirección
    *
    */

  val listaNombres = Seq("Maria", "Manolo", "Juan", "Javi", "Javi", "Jose", "Patricia", "Ruben", "Sergio", "Laura")

  //TODO

  val mapaFinal = Map.empty

  log.info(s"Mapa final de alumnos: $mapaFinal")

}

