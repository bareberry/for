package sun.beny.demoforcglib;


import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-7-26
 * Time: 上午8:06
 * To change this template use File | Settings | File Templates.
 */
public abstract class Bean implements Serializable{

    String sampleProxy;

    public String getSampleProxy() {
        return sampleProxy;
    }

    public void setSampleProxy(String sampleProxy) {
        this.sampleProxy = sampleProxy;
    }

    abstract public void addProxyChangeListener(PropertyChangeListener listener);
    abstract public void deleteProxyChangeListenter(PropertyChangeListener listener);


    @Override
    public String toString() {
        return "simpleProxy is "+sampleProxy;
    }
}
