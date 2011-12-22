package br.com.caelum.vraptor.scala

import br.com.caelum.vraptor.Result

class ScalaResult(result:Result) {
  def redirect2[T](implicit manifest: Manifest[T]) = result.redirectTo(manifest.erasure).asInstanceOf[T]

  def forward2[T](implicit manifest: Manifest[T]) = result.forwardTo(manifest.erasure).asInstanceOf[T]

  def using(elements: (String, Any)*) {
    for ((name, value) <- elements) {
      result.include(name, value)
    }
  }
}