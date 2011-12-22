package br.com.caelum.vraptor.scala

import br.com.caelum.vraptor.{Result, Validator}
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.core.Localization
import br.com.caelum.vraptor.proxy.Proxifier
import br.com.caelum.vraptor.validator._
import br.com.caelum.vraptor.view.ValidationViewsFactory
import java.util.List

@Component
class ScalaValidator(result:Result, factory:ValidationViewsFactory, outjector:Outjector, proxifier:Proxifier,
                     beanValidators:List[BeanValidator], localization:Localization)
        extends DefaultValidator(result, factory, outjector, proxifier, beanValidators, localization) {

  def check(test: Boolean, i18nKey: String = "", category: String = ""): Validator = {
    checking(new Validations {
      that(test, category, i18nKey)
    })
    this
  }

  def onError(f: Result => Unit) {
    if (hasErrors) {
      result.include("errors", getErrors)
      outjector.outjectRequestMap()
      f(result)
      throw new ValidationException(getErrors)
    }
  }
}