package sun.beny.demoforcglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-26
 * Time: 上午8:14
 * To change this template use File | Settings | File Templates.
 * 主要是对method的方法进行不同的处理。
 */
public class Beans implements MethodInterceptor {

    private PropertyChangeSupport propertyChangeSupport;

    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void deletePropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public static Object newInstance(Class clazz){

        try {
            Beans interceptor = new Beans();
            Enhancer e  = new Enhancer();
            e.setSuperclass(clazz);
            e.setCallback(interceptor);
            Object bean = e.create();
            interceptor.propertyChangeSupport = new PropertyChangeSupport(bean);
            return bean;
        }catch (Throwable e){
            e.printStackTrace();
            throw new Error(e.getMessage());
        }

    }

    static final Class c[] = new Class[0];
    static final  Object emptyArgs = new Object();

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
       Object revalFromSuper =  null;
        try{
            if (!Modifier.isAbstract(method.getModifiers())){
                revalFromSuper = methodProxy.invokeSuper(o,objects);
            }
        }finally {
            String name = method.getName();
            if (name.equals("addPropertyChangeListener")){
                addPropertyChangeListener((PropertyChangeListener)objects[0]);
            }else if (name.equals("removePropertyChangeListener")){
                deletePropertyChangeListener((PropertyChangeListener) objects[0]);
            }

            if (name.startsWith("set") && objects.length==1 && method.getReturnType() == Void.TYPE){

                char proName[] = name.substring("set".length()).toCharArray();
                proName[0] = Character.toLowerCase(proName[0]);
                propertyChangeSupport.firePropertyChange( new String(proName),null,objects[0]);
            }
        }

        return revalFromSuper;
    }

    public static void main(String[] args){

        Bean bean = (Bean) newInstance(Bean.class);

        bean.addProxyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        System.out.println(evt);
                    }
                }
        );

        bean.setSampleProxy("TEST");
    }
}
