package com.spark.hackathon.core.ejercicios

import akka.event.slf4j.SLF4JLogging


object SparkCore extends App with SLF4JLogging {


  /** Crear una implementación de CoreUtils, requisitos:
    *
    *   Dar un nombre a la app de Spark
    *   Asignarle recursos diferentes
    *   Implementar las funciones sobre RDDs
    *
    */


  /*val coreUtils = new CoreUtilsImpl
  val vuelos = coreUtils.leerFicheroVuelos
  val totalVuelos = coreUtils.totalVuelos(vuelos)
  val wordCount = coreUtils.wordCountVuelos(vuelos)
  val topWordCount = coreUtils.top20Registros(wordCount)

  log.error(s"Top word count: \n\t${topWordCount.mkString(",")}")

  val vuelosPorOrigDest = coreUtils.totalVuelosPorOrigenDestino(vuelos)
  val topVuelosPorOrigDest = coreUtils.top20Registros(vuelosPorOrigDest)

  log.error(s"Top vuelos por origen y destino: \n\t${topVuelosPorOrigDest.mkString(",")}")
*/

}

