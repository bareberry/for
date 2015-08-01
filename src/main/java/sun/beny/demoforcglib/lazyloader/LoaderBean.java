package sun.beny.demoforcglib.lazyloader;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-28
 * Time: 上午6:26
 * To change this template use File | Settings | File Templates.
 */
public class LoaderBean {

    private String loaderName;
    private int loadValue;

    private PropertyBean propertyBean;

    public LoaderBean(){
        this.loaderName = "loaderNameA";
        this.loadValue = 123;
        this.propertyBean =createPropertyBean();
    }

    public  PropertyBean createPropertyBean(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PropertyBean.class);
        return (PropertyBean) enhancer.create(PropertyBean.class,new ConcreteClasslazyLoader());
    }

    public PropertyBean getPropertyBean() {
        return propertyBean;
    }

    public void setPropertyBean(PropertyBean propertyBean) {
        this.propertyBean = propertyBean;
    }

    public int getLoadValue() {
        return loadValue;
    }

    public void setLoadValue(int loadValue) {
        this.loadValue = loadValue;
    }

    public String getLoaderName() {
        return loaderName;
    }

    public void setLoaderName(String loaderName) {
        this.loaderName = loaderName;
    }


    public static void main(String[] args){
        LoaderBean loaderBean = new LoaderBean();
        System.out.println(loaderBean.getLoaderName());
        System.out.println(loaderBean.getLoadValue());

        PropertyBean propertyBean = loaderBean.getPropertyBean();
        System.out.print(propertyBean.getPropertyName());
        System.out.println(propertyBean.getPropertyValue());
        System.out.println("After ....");
        System.out.println(propertyBean.getPropertyName());

    }
}
