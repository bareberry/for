package sun.beny.demoforcglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-27
 * Time: 下午10:55
 * To change this template use File | Settings | File Templates.
 */
public class ConcreteClassInterceptor implements MethodInterceptor {
    public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
        System.out.println("Before:"+method);
        Object object=proxy.invokeSuper(obj, arg);
        System.out.println("After:"+method);
        return object;
    }
}
