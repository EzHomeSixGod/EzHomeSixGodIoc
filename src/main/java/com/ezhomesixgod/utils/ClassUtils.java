package com.ezhomesixgod.utils;

/**
 * @author
 * @description Java class utils
 * @createTime 2018-09-26 17:39
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class loadClass(String className){
        try {
            return getDefaultClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
