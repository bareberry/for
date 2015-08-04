package sun.beny.guavademo.commontest;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sun.istack.internal.Nullable;
import com.sun.xml.internal.ws.policy.AssertionSet;
import junit.framework.Assert;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: youjie
 * Date: 15-8-3
 * Time: 上午6:45
 * To change this template use File | Settings | File Templates.
 * 函数式编程的测试
 */
public class FunctionPrograme {

    /**
     *
     * @Title: testFunction
     * @Description: Fucntion<K,V>接口提供的apply是用来将对象K转换为对象V的
     * @param 设定文件
     * @return void 返回类型
     * @throws
     */
    @Test
    public void testFunction(){

        //将map存到存储到function中去
        Function<String,ClassRoom> lookup = getFunctionForDefaultMap();
        Function<ClassRoom,String> append = new Function<ClassRoom, String>() {
            @Override
            public String apply(@Nullable sun.beny.guavademo.commontest.FunctionPrograme.ClassRoom classRoom) {
                return Joiner.on(',').join(classRoom.students);
            }
        };

        // 先调用lookup.apply("三年级二班")查询班级，然后将该对象继续apply('三年二班的对象')，用逗号连接该班级中的学生名字
        Function<String,String>compose = Functions.compose(append,lookup);

        Assert.assertEquals("mysql,java",compose.apply("一班"));
    }

    private Function<String,ClassRoom> getFunctionForDefaultMap(){
        Map<String,ClassRoom> map = Maps.newHashMap();
        map.put("一班",new ClassRoom(1,"一班", Sets.newHashSet("java","mysql")));
        map.put("二班",new ClassRoom(1,"二班", Sets.newHashSet("sun","oruage")));
        return Functions.forMap(map);
    }

    private class ClassRoom{
        private int id;
        private String name;
        private Set<String> students;

        public ClassRoom(){};
        public ClassRoom(int id,String name,Set<String> students){
            super();
            this.id=id;
            this.name=name;
            this.students=students;
        }
    }


}

//日期转换的应用
class DateFormatFunction implements Function<Date,String>{

    @Override
    public String apply(@Nullable java.sql.Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        return dateFormat.format(date);
    }
}
