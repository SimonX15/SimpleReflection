package com.app.simon.simplereflection;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * desc: 公司工具类（需要通过反射获取信息）
 * date: 2018/2/9
 *
 * @author xw
 */
public class CompanyUtil {
    /** TAG */
    public static final String TAG = CompanyUtil.class.getSimpleName();

    /** 反射获取类的属性 */
    public static void testReflection() {
        //第一种方式获取Class对象
        Company company = new Company();
        Class<? extends Company> class1 = company.getClass();

        //第二种方式获取Class对象
        Class<Company> class2 = Company.class;

        //第三种方式获取Class对象
        Class<?> class3 = null;
        try {
            class3 = Class.forName("com.app.simon.simplereflection.Company");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "class: " + class1.toString());
        Log.i(TAG, "class1=class2: " + (class1 == class2));
        Log.i(TAG, "class2=class3: " + (class2 == class3));
        //class1、2、3都是相同的。在运行期间，一个类，只有一个Class对象产生。

        //三种方式常用第三种，第一种对象都有了还要反射干什么。
        //第二种需要导入类的包，依赖太强，不导包就抛编译错误。
        //一般都第三种，一个字符串可以传入也可写在配置文件中等多种方法。

        try {
            getConstructors();
            getFields();
            getMethods();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取成员方法并调用：
     *
     * 1.批量的：
     * public Method[] getMethods():获取所有"公有方法"；（包含了父类的方法也包含Object类）
     * public Method[] getDeclaredMethods():获取所有的成员方法，包括私有的(不包括继承的)
     * 2.获取单个的：
     * public Method getMethod(String name,Class<?>... parameterTypes):
     * 参数：
     * name : 方法名；
     * Class ... : 形参的Class类型对象
     * public Method getDeclaredMethod(String name,Class<?>... parameterTypes)
     *
     * 调用方法：
     * Method --> public Object invoke(Object obj,Object... args):
     * 参数说明：
     * obj : 要调用方法的对象；
     * args:调用方式时所传递的实参；
     *
     * ):
     */
    private static void getMethods() throws Exception {
        //1.加载Class对象
        Class clazz = Class.forName("com.app.simon.simplereflection.Person");
        //2.获取所有公有方法  
        Log.i(TAG, "***************获取所有的”公有“方法*******************");
        Method[] methodArray = clazz.getMethods();
        for (Method m : methodArray) {
            Log.i(TAG, m.toString());
        }
        Log.i(TAG, "***************获取所有的方法，包括私有的*******************");
        methodArray = clazz.getDeclaredMethods();
        for (Method m : methodArray) {
            Log.i(TAG, m.toString());
        }
        Log.i(TAG, "***************获取私有的show()方法*******************");
        Method method = clazz.getDeclaredMethod("show");
        Log.i(TAG, method.toString());

        //实例化一个Person对象
        Object obj = clazz.getConstructor().newInstance();
        //解除私有限定
        method.setAccessible(true);
        method.invoke(obj);

        Log.i(TAG, "***************获取私有的showWithStr()方法******************");
        method = clazz.getDeclaredMethod("showWithStr", String.class);
        Log.i(TAG, method.toString());
        //解除私有限定
        method.setAccessible(true);
        Object result = method.invoke(obj, "you catch me");//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
        Log.i(TAG, "返回值：" + result);
    }

    /**
     * 获取成员变量并调用：
     *
     * 1.批量的
     * 1).Field[] getFields():获取所有的"公有字段"
     * 2).Field[] getDeclaredFields():获取所有字段，包括：私有、受保护、默认、公有；
     * 2.获取单个的：
     * 1).public Field getField(String fieldName):获取某个"公有的"字段；
     * 2).public Field getDeclaredField(String fieldName):获取某个字段(可以是私有的)
     *
     * 设置字段的值：
     * Field --> public void set(Object obj,Object value):
     * 参数说明：
     * 1.obj:要设置的字段所在的对象；
     * 2.value:要为字段设置的值；
     */
    private static void getFields() throws Exception {
        //1.加载Class对象
        Class clazz = Class.forName("com.app.simon.simplereflection.Person");

        //2.获取字段  
        Log.i(TAG, "************获取所有公有的字段********************");
        Field[] fieldArray = clazz.getFields();
        for (Field f : fieldArray) {
            Log.i(TAG, f.toString());
        }

        Log.i(TAG, "************获取所有的字段(包括私有、受保护、默认的)********************");
        fieldArray = clazz.getDeclaredFields();
        for (Field f : fieldArray) {
            Log.i(TAG, f.toString());
        }

        Log.i(TAG, "*************获取公有字段并调用***********************************");
        Field f = clazz.getField("test");
        Log.i(TAG, f.toString());

        Log.i(TAG, "*************获取某个字段并调用***********************************");
        Field field = clazz.getDeclaredField("name");
        Log.i(TAG, field.toString());

        //获取一个对象  
        Object obj = clazz.getConstructor().newInstance();//产生Student对象--》Student person = new Student();
        //解除私有限定
        field.setAccessible(true);
        //为字段设置值  
        field.set(obj, "刘德华");//为Student对象中的name属性赋值--》person.name = "刘德华"
        //验证  
        Person person = (Person) obj;
        Log.i(TAG, "验证姓名：" + person.getName());

        Log.i(TAG, "**************获取私有字段****并调用********************************");
        field = clazz.getDeclaredField("phone");
        Log.i(TAG, field.toString());
        //每次设置字段都需要设置一下权限
        field.setAccessible(true);
        field.set(obj, "18888889999");
        Log.i(TAG, "验证电话：" + person.getPhone());
    }

    /**
     * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
     *
     * 1.获取构造方法：
     * 1).批量的方法：
     * public Constructor[] getConstructors()：所有"公有的"构造方法
     * public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)
     *
     * 2).获取单个的方法，并调用：
     * public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
     * public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
     *
     * 调用构造方法：
     * Constructor-->newInstance(Object... initargs)
     */
    private static void getConstructors() throws Exception {
        //1.加载Class对象
        Class clazz = Class.forName("com.app.simon.simplereflection.Company");

        //2.获取所有公有构造方法
        Log.i(TAG, "**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for (Constructor c : conArray) {
            Log.i(TAG, c.toString() + "\n");
        }

        Log.i(TAG, "************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for (Constructor c : conArray) {
            Log.i(TAG, c.toString() + "\n");
        }

        Log.i(TAG, "*****************获取公有、无参的构造方法*******************************");
        Constructor con = clazz.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。

        Log.i(TAG, "con = " + con + "\n");
        //调用构造方法
        Object obj = con.newInstance();
        //   Log.i(TAG, "obj = " + obj);
        //  Student stu = (Student)obj;

        Log.i(TAG, "******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(String.class);
        Log.i(TAG, con.toString() + "\n");
        //解除私有限定
        con.setAccessible(true);
        //调用构造方法
        obj = con.newInstance("腾讯");
        Log.i(TAG, "obj = " + obj.toString() + "\n");
    }
}
