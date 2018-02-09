
### 1. 什么是反射（Reflection ）？

Java 语言的反射（Reflection）机制，就是通过动态的方式获取类的信息以及动态调用对象的方法的一种能力。


### 2. Java反射的作用
在Java运行时环境中，对于任意一个类，可以知道这个类有哪些属性和方法。对于任意一个对象，可以调用它的任意一个方法。



### 3. Class的API

http://www.android-doc.com/reference/java/lang/Class.html 


### 4. 反射的常用类和函数

Java反射机制的实现借助于4个类：Class，Constructor，Field，Method；

    1.在运行时判断任意一个对象所属的类。

    2.在运行时构造任意一个类的对象。

    3.在运行时判断任意一个类所具有的成员变量和方法。

    4.在运行时调用任意一个对象的方法。 


#### 获取类
```java
Class<?> class3 = null;
try {
    class3 = Class.forName("com.app.simon.simplereflection.Company");
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
``` 
#### 获取类的构造方法
```java
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
``` 
#### 获取类的变量
```java
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
field.set(obj, "135********");
Log.i(TAG, "验证电话：" + person.getPhone());
``` 
#### 获取类的方法
```java
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
``` 
#### GitHub
https://github.com/SimonX15/SimpleReflection

### 5. 参考

http://blog.csdn.net/sinat_38259539/article/details/71799078

http://blog.csdn.net/BuddyUU/article/details/52458241

http://www.importnew.com/21211.html

