package br.com.caelum.vraptor.scala

import br.com.caelum.vraptor.{Validator, Result}


object Conversions {
   implicit def result2scalaResult(result: Result) = new ScalaResult(result)

   implicit def validator2scalaValidator(validator:Validator) = {
     validator.asInstanceOf[ScalaValidator]
   }
}