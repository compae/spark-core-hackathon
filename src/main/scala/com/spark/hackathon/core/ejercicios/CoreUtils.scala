package com.spark.hackathon.core.ejercicios

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

trait CoreUtils {

  val sparkConf: SparkConf

  val sparkContext: SparkContext

  /** Leer fichero de vuelos de aeropuertos de USA **/
  def leerFicheroVuelos: RDD[String]

  /** Numero total de vuelos **/
  def totalVuelos(vuelos: RDD[String]): Long

  /** Hacer un word count sobre las palabras y numeros del fichero de vuelos, requisitos:
    *
    * Filtrar los NA y los vacios para no tenerlos en cuenta
    * Intentar hacerlo lo más eficiente posible
    */
  def wordCountVuelos(vuelos: RDD[String]): RDD[(String, Long)]

  /** Numero total de vuelos registrados por el par origen-destino(16-17) **/
  def totalVuelosPorOrigenDestino(vuelos: RDD[String]): RDD[(String, Long)]

  /** Top 20 de registros ordenados por 'value' **/
  def top20Registros(vuelos: RDD[(String, Long)]): Seq[(String, Long)]

}
