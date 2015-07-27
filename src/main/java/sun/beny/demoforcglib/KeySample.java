package sun.beny.demoforcglib;

import net.sf.cglib.core.KeyFactory;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-27
 * Time: 下午9:07
 * To change this template use File | Settings | File Templates.
 * 简单的功能就是通过判断多个键值对的时候是两个对象是否相等。
 */
public class KeySample {

   private interface MyFactory{

       public Object newInstance(int a,char[] b,String d);
   }

   public static void main(String[] args){
       MyFactory f = (MyFactory) KeyFactory.create(MyFactory.class);
       Object key1 = f.newInstance(20,new char[]{'a','b'},"hello");
       Object key2 = f.newInstance(20,new char[]{'a','b'},"hello");
       Object key3 = f.newInstance(20,new char[]{'a','_'},"hello");
       System.out.println(key1.equals(key2));
       System.out.println(key2.equals(key3));


   }
}
