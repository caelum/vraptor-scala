package br.com.caelum.vraptor.scala

import br.com.caelum.vraptor.Result

object Conversions {
   implicit def result2scalaResult(result: Result) = new ScalaResult(result)
}