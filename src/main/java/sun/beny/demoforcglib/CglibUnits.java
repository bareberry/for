package sun.beny.demoforcglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-28
 * Time: 下午9:12
 * To change this template use File | Settings | File Templates.
 * 国外人写的代码。主要是为了更加安全
 */
public class CglibUnits {

    private static final ThreadLocal<Boolean> isNull = new ThreadLocal<Boolean>();

    public static <T> T saveInvoke(T a){
        if (a == null){
            throw new NullPointerException();
        }
        return nonNullEnhancer(a,(Class<? super T>)a.getClass());
    }

    private static<T> T nonNullEnhancer(final T value,Class<? super T> cls){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        MethodInterceptor callback = new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Object result = proxy.invokeSuper(value,args);
                if (result == null){
                    return nullEnhancer(method.getReturnType());
                }else {
                    return nonNullEnhancer(result, (Class<Object>) method.getReturnType());
                }
            }
        };
        return (T) enhancer.create();
    }


    private static <T> T nullEnhancer(Class<T> cls){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        MethodInterceptor callback = new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                isNull.set(true);
                return nullEnhancer(method.getReturnType());
            }
        };
        return (T) enhancer.create();

    }

    public static <T> boolean isNull(T t){

        isNull.set(false);
        t.hashCode();
        return isNull.get();

    }
}
