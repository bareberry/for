package sun.beny.demoforcglib.callback;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-27
 * Time: 下午10:38
 * To change this template use File | Settings | File Templates.
 *
 */
public class ConcreteClassNoInterface {

    public String getConctreteMethodA(String str){
        System.out.println("ConctreteMethodA ..." + str);
        return str;
    }

    public int getConctreteMethodB(int n){
        System.out.println("ConctreteMethodA ..."+n);
        return n+10;
    }

    public int getConcreteMethodFixedValue(int n){
        System.out.println("getConcreteMethodFixedValue..."+n);
        return n+10;
    }

}
