package sun.beny.demoforcglib.Dispatcher;

import net.sf.cglib.proxy.Enhancer;
import sun.beny.demoforcglib.lazyloader.PropertyBean;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-28
 * Time: 上午6:39
 * To change this template use File | Settings | File Templates.
 */
public class DispatcherBean {

    private String name;
    private String value;
    private PropertyBean propertyBean;
    public DispatcherBean(){
        this.name="DispatcherBean";
        this.value="abc";
        this.propertyBean=createDispatcherBean();
    }
    protected PropertyBean createDispatcherBean(){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(PropertyBean.class);
        return (PropertyBean)enhancer.create(PropertyBean.class,new ConcreteClassDispatcher());
    }

    public PropertyBean getPropertyBean() {
        return propertyBean;
    }

    public void setPropertyBean(PropertyBean propertyBean) {
        this.propertyBean = propertyBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args){
        DispatcherBean dispatcherBean=new DispatcherBean();
        System.out.println(dispatcherBean.getName());
        System.out.println(dispatcherBean.getValue());

        PropertyBean pb=dispatcherBean.getPropertyBean();
        System.out.println(pb.getPropertyName());
//在每次访问时都要进行回调
        System.out.println(pb.getPropertyValue());
    }
}
