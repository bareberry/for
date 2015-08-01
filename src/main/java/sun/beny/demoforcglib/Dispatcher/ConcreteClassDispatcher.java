package sun.beny.demoforcglib.Dispatcher;

import net.sf.cglib.proxy.Dispatcher;
import sun.beny.demoforcglib.lazyloader.PropertyBean;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-28
 * Time: 上午6:40
 * To change this template use File | Settings | File Templates.
 * 实现了该接口，每次回调该创建方法的时候该都会去去触发代理类的回调方法、
 */
public class ConcreteClassDispatcher implements Dispatcher {
    @Override
    public Object loadObject() throws Exception {
        System.out.println("Dispatcher loadObject ...");
        PropertyBean object=new PropertyBean();
        object.setPropertyName("PropertyBeanName!");
        object.setPropertyValue(1);
        return object;
    }
}
