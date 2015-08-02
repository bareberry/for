package sun.beny.guavademo.priatcice;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-8-2
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 * 一个用到爪瓜的modele类
 * 使用到爪瓜的Objects，判断参数，比较的
 */
public class Person implements Comparable<Person> {

    private int id;
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
       Preconditions.checkNotNull(firstName,"firstname not be null");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        //可以先检验下id是否为空
        Preconditions.checkArgument(id>0,"id must greater than zero");
        this.id = id;
    }

    public Person(int id,String firstName,String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Person){
            Person that = (Person) obj;
            return Objects.equal(id,that.getId())
                  && Objects.equal(firstName,that.getFirstName())
                  && Objects.equal(lastName,that.getLastName());
        }
        return false;
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(id,firstName,lastName);
    }
    @Override
    public String toString() {

       return Objects.toStringHelper(this)
               .add("id",id)
               .addValue(firstName)
               .addValue(lastName)
               .toString();
    }

    @Override
    public int compareTo(Person o) {
        return ComparisonChain.start()
                .compare(id,o.getId())
                .compare(firstName,o.getFirstName())
                .compare(lastName,o.getLastName())
                .result();
    }
}
