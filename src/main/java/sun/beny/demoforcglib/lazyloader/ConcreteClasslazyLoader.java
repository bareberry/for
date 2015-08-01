package sun.beny.demoforcglib.lazyloader;

import net.sf.cglib.proxy.LazyLoader;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-28
 * Time: 上午6:31
 * To change this template use File | Settings | File Templates.
 * 实现该接口，对对象进行延迟加载。
 */
public class ConcreteClasslazyLoader implements LazyLoader {


    @Override
    public Object loadObject() throws Exception {

        System.out.println("lazyLoader loadObject...");
        PropertyBean bean = new PropertyBean();
        bean.setPropertyName("lazy Object ");
        bean.setPropertyValue(12);
        return bean;
    }
}
