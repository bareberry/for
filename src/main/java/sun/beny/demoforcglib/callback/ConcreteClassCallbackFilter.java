package sun.beny.demoforcglib.callback;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import sun.beny.demoforcglib.ConcreteClassInterceptor;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-27
 * Time: 下午10:43
 * To change this template use File | Settings | File Templates.
 * getConcreteMethodA对应CallbackFilter中定义的索引1，在Callback[]数组中使用的过滤为NoOp,因此直接执行了被代理方法。
 getConcreteMethodB对应CallbackFilter中定义的索引0，在Callback[]数组中使用的过滤为MethodInterceptor，因此执行了方法拦截器进行拦截。
 getConcreteMethodFixedValue对应CallbackFilter中定义的索引2，在Callback[]数组中使用的过滤为FixedValue，因此2次赋值128和256的调用其结果均被锁定为返回999。
 */
public class ConcreteClassCallbackFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if("getConctreteMethodB".equals(method.getName())){
            return 0;
        }else if ("getConctreteMethodA".equals(method.getName())){
            return 1;
        }else if ("getConcreteMethodFixedValue".equals(method.getName())){
            return 2;
        }

        return 1;
    }

    public static void main(String[] args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteClassNoInterface.class);
        CallbackFilter callbackFilter = new ConcreteClassCallbackFilter();
        enhancer.setCallbackFilter(callbackFilter);

        Callback interceptor=new ConcreteClassInterceptor();
        Callback noOp= NoOp.INSTANCE;//(2)

        Callback fixedValue=new ConcreteClassFixedValue();//(3)
        Callback[] callbacks=new Callback[]{interceptor,noOp,fixedValue};

        enhancer.setCallbacks(callbacks);
        ConcreteClassNoInterface proxyObject=(ConcreteClassNoInterface)enhancer.create();

        System.out.println("*** NoOp Callback ***");
        proxyObject.getConctreteMethodA("abcde");

        System.out.println("*** MethodInterceptor Callback ***");
        proxyObject.getConctreteMethodB(1);

        System.out.println("*** FixedValue Callback ***");
        int fixed1=proxyObject.getConcreteMethodFixedValue(128);
        System.out.println("fixedValue1:"+fixed1);
        int fixed2=proxyObject.getConcreteMethodFixedValue(256);
        System.out.println("fixedValue2:"+fixed2);
    }
}
