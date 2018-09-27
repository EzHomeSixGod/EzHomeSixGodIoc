package com.ezhomesixgod.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @author
 * @description
 * @createTime 2018-09-26 17:39
 */
public class BeanUtils {

    public static <T> T instanceByCglib(Class<T> clz, Constructor constructor,Object[] args){
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(NoOp.INSTANCE);
        if(constructor ==null){
            return (T)enhancer.create();
        }else{
            return (T)enhancer.create(constructor.getParameterTypes(),args);
        }
    }
}
