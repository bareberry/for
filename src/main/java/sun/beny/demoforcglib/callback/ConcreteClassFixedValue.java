package sun.beny.demoforcglib.callback;

import net.sf.cglib.proxy.FixedValue;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-27
 * Time: 下午11:17
 * To change this template use File | Settings | File Templates.
 * 该类实现FixedValue接口，同时锁定回调值为999
 * (整型，CallbackFilter中定义的使用FixedValue型回调的方法为getConcreteMethodFixedValue，
 * 该方法返回值为整型)
 */
public class ConcreteClassFixedValue implements  FixedValue {
    @Override
    public Object loadObject() throws Exception {
        System.out.println("ConcreteClassFixedValue loadObject ...");
        Object object=999;
        return object;
    }
}
