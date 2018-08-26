package com.spark.hackathon.core.explicacion

import akka.event.slf4j.SLF4JLogging
import com.spark.hackathon.core.modelos.Alumno
import com.typesafe.config.ConfigFactory
import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.JavaConversions._


/**
  * Repasar las funcionalidades básicas del Core de Spark
  */
object SparkCoreMain extends App with SLF4JLogging {


  /** Creación del contexto */

  val configurationProps  = ConfigFactory.load().getConfig("spark").entrySet()
    .map(prop => (s"spark.${prop.getKey}", prop.getValue.unwrapped().toString)).toSeq

  val sparkConf = new SparkConf().setAll(configurationProps)
  val sparkContext = new SparkContext(sparkConf)

  /** Generar RDD */

  val numeroDeEventos = 1000
  val randomGenerator = scala.util.Random
  val numerosAelatorios = for (_ <- 1L to numeroDeEventos) yield {
    randomGenerator.nextInt(100000)
  }
  val rddNumeros = sparkContext.parallelize(numerosAelatorios)
  val totalEventos = rddNumeros.count()

  log.error(s"Total de eventos generados $totalEventos")

  val sample = rddNumeros.take(20)
  log.error(s"Algunos numeros generados: ${sample.mkString(",")}")


  /** Tipar un RDD */

  val alumnos = Seq("Maria", "Manolo", "Juan", "Javi", "Javi", "Jose", "Patricia", "Ruben", "Sergio", "Laura")
  val alumnosAleatorios = for (_ <- 1L to numeroDeEventos) yield {
    alumnos(randomGenerator.nextInt(alumnos.size))
  }
  val rddAlumnos = sparkContext.parallelize(alumnosAleatorios)
  val rddAlumnoTypado = rddAlumnos.map(nombre => Alumno(nombre, 0))

  val sampleAlumnos = rddAlumnoTypado.take(20)
  log.error(s"Algunos alumnos generados: ${sampleAlumnos.mkString(",")}")

  /** Transformaciones sobre RDDs: filter, map, flatMap, join, groupByKey, reduceByKey .... */


  /** Acciones sobre RDDs: take, foreach, collect, reduce, saveAsTextFile ... */

}

