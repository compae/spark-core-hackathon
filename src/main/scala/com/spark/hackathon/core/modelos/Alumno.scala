package com.spark.hackathon.core.modelos

case class Alumno (nombre: String, edad: Int) {

  override def toString: String = s"$nombre-$edad"

}
