package sun.beny.demoforcglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-27
 * Time: 下午9:32
 * To change this template use File | Settings | File Templates.
 */
public class Trace implements MethodInterceptor {

    int ident = 1;
    static Trace callback = new Trace();

    private Trace(){

    }

    public static Object newInstance(Class clazz){
        try {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(clazz);
            enhancer.setCallback(callback);
            return enhancer.create();
        }catch (Throwable e){
            e.printStackTrace();
            throw new Error(e.getMessage());
        }

    }


    public static void main(String[] args){
        List list = (List) newInstance(Vector.class);
        Object value = "TEST" ;
        list.add(value);
        list.contains(value);
        try {
            list.set(2,"ArrayIndexOutOfBounds");
        }catch (ArrayIndexOutOfBoundsException e){

        }

        list.add(value + "1");
        list.add(value + "2");
        list.toString();
        list.equals(list);
        list.set( 0, null);
        list.toString();
        list.add(list);
        list.get(1);
        list.toArray();
        list.remove(list);
        list.remove("");
        list.containsAll(list);
        list.lastIndexOf(value);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        printIdent(ident);
        System.out.println( method );
        for( int i = 0; i < args.length; i++ ){
            printIdent(ident);
            System.out.print( "arg" + (i + 1) + ": ");
            if( obj == args[i])
                System.out.println("this");
            else
                 System.out.println(args[i]);
        }
        ident++;

        Object retValFromSuper = null;
        try {
             retValFromSuper = proxy.invokeSuper(obj, args);
            ident--;
        } catch (Throwable t) {
            ident--;
            printIdent(ident);
            System.out.println("throw " + t );
            System.out.println();
            throw t.fillInStackTrace();
        }

        printIdent(ident);
        System.out.print("return " );
        if( obj == retValFromSuper)
            System.out.println("this");
        else System.out.println(retValFromSuper);

        if(ident == 1)
            System.out.println();

        return retValFromSuper;

    }

    void printIdent( int ident ){


        while( --ident > 0 ){
            System.out.print(".......");
        }
        System.out.print("  ");
    }

}
