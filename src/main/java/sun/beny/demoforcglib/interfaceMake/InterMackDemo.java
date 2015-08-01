package sun.beny.demoforcglib.interfaceMake;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.beny.demoforcglib.callback.ConcreteClassNoInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-28
 * Time: 上午6:48
 * To change this template use File | Settings | File Templates.
 * 接口生成器。
 */
public class InterMackDemo {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        InterfaceMaker interfaceMaker = new InterfaceMaker();
        interfaceMaker.add(ConcreteClassNoInterface.class);
        Class cl = interfaceMaker.create();
        System.out.println(cl.isInterface());
        System.out.println(cl.getName());

        Method[] methods = cl.getMethods();
        for (Method method : methods){
            System.out.println(method.getName());
        }

        Object obj = Enhancer.create(Object.class,new Class[]{cl},
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        return "interfute" ;
                    }
                }) ;

        Method method = obj.getClass().getMethod("getConctreteMethodA",new Class[]{String.class});
        System.out.println(method.invoke(obj, new Object[]{"12345"}));
    }

}
