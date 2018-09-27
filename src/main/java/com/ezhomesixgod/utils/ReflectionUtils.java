package com.ezhomesixgod.utils;

import java.lang.reflect.Field;

/**
 * @author renyang
 * @description
 * @createTime 2018-09-26 17:39
 */
public class ReflectionUtils {

    public static void injectField(Field field,Object obj,Object value){

        try {
            if(field !=null){
                field.setAccessible(true);
                field.set(obj,value);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
