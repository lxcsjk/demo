package com.betterlxc.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * Created by liuxincheng on 2018/8/9.
 */
public class ReflectTest {
    public static void main(String[] args) {
        String name;
        if (args.length > 0)
            name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name(e.g java.util.Date): ");
            name = in.next();
        }
        try {
            //print class name and superclass name (if != object)
            //调用Class 的静态方法 forName 获得类名对应的Class 对象
            Class cl = Class.forName(name);
            //获取父类所对应的Class 对象
            Class supercl = cl.getSuperclass();
            //返回对应modifiers 中位设置的修饰符的字符串表示
            String modifiers = Modifier.toString(cl.getModifiers());

            if (modifiers.length() > 0)
                System.out.print(modifiers + " ");
            System.out.print("class " + name);
            //判断是否有继承父类
            if (supercl != null && supercl != Object.class)
                System.out.print(" extends " + supercl.getName());

            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    public static void printParameterType(Class[] paramTypes) {
        for (int j = 0; j < paramTypes.length; j++) {
            if (j > 0)
                System.out.print(", ");
            System.out.print(paramTypes[j].getName());
        }
    }

    public static void printFields(Class cl) {
        //Field[] getDeclaredFields()
        //返回包含Field 对象的数组，这些对象记录了这个类的全部域
        Field[] fields = cl.getDeclaredFields();

        for (Field f : fields) {
            Class type = f.getType();
            String name = f.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0)
                System.out.print(modifiers + "  ");
            System.out.println(type.getName() + " " + name + ";");
        }
    }

    public static void printMethods(Class cl) {
        //返回包含Method 对象的数组，返回这个类或接口的全部方法，但不包括由超类继承了的方法
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
            //Class getReturnType() (在 Method 类中)
            //返回一个用于描述返回类型的Class对象
            Class retType = m.getReturnType();
            String name = m.getName();

            System.out.print("    ");
            //打印修饰符、返回类型和方法名称
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0)
                System.out.print(modifiers + " ");
            System.out.print(retType.getName() + " " + name + "(");

            printParameterType(m.getParameterTypes());
            System.out.println(");");
        }
    }

    public static void printConstructors(Class cl) {
        //返回包含Constructor 对象的数组，其中包含了Class对象的所有构造器
        Constructor[] constructors = cl.getDeclaredConstructors();

        for (Constructor c : constructors) {
            //String getName()
            //返回一个用于描述构造器、方法或域名的字符串
            String name = c.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0)
                System.out.print(modifiers + "  ");
            System.out.print(name + "(");
            //Class[] getParameterTypes() (在Constructor 和 Method 类中)
            //返回一个用于描述参数类型的Class对象数组
            printParameterType(c.getParameterTypes());
            System.out.println(");");
        }
    }
}
