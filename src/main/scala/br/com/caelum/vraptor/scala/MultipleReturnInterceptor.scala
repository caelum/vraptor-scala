package br.com.caelum.vraptor.scala

import br.com.caelum.vraptor.interceptor.{Interceptor, ForwardToDefaultViewInterceptor, ExecuteMethodInterceptor}
import br.com.caelum.vraptor.resource.ResourceMethod
import br.com.caelum.vraptor.core.{InterceptorStack, MethodInfo}
import br.com.caelum.vraptor.{Lazy, Result, Intercepts}

@Lazy
@Intercepts(after=Array(classOf[ExecuteMethodInterceptor]), before=Array(classOf[ForwardToDefaultViewInterceptor]))
class MultipleReturnInterceptor(info:MethodInfo, result:Result) extends Interceptor {

  def accepts(m: ResourceMethod) = classOf[Product].isAssignableFrom(m.getMethod.getReturnType)

  def intercept(stack: InterceptorStack, method: ResourceMethod, instance: AnyRef) {
    for {
      tuple <- Option(info.getResult.asInstanceOf[Product])
      (key:String, value:Any) <- tuple.productIterator
    } result.include(key, value)

    stack.next(method, instance)
  }
}