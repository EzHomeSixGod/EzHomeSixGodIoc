package com.ezhomesixgod.core;

import com.ezhomesixgod.bean.BeanDefinition;
import com.ezhomesixgod.bean.ConstructorArg;
import com.ezhomesixgod.utils.BeanUtils;
import com.ezhomesixgod.utils.ClassUtils;
import com.ezhomesixgod.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author
 * @description
 * @createTime 2018-09-26 17:38
 */
public class BeanFactoryImpl implements BeanFactory{

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(BeanFactoryImpl.class);

    private static final ConcurrentHashMap<String,Object> beanMap = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String,BeanDefinition> beanDefineMap= new ConcurrentHashMap<>();

    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<>());


    @Override
    public Object getBean(String name) throws Exception {
        logger.info("getBean");
        Object bean = beanMap.get(name);
        if(bean !=null){
            return bean;
        }
        //if not create
        bean = createBean(beanDefineMap.get(name));
        if(bean !=null){
            populateBean(bean);

            beanMap.put(name,bean);
        }
        return bean;
    }

    protected void registerBean(String name, BeanDefinition bd){
        beanDefineMap.put(name,bd);
        beanNameSet.add(name);
    }

    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        logger.info("create bean");
        String beanName = beanDefinition.getClassName();
        Class clz = ClassUtils.loadClass(beanName);
        if(clz == null) {
            throw new Exception("can not find bean by beanName");
        }
        List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
        if(constructorArgs != null && !constructorArgs.isEmpty()){
            List<Object> objects = new ArrayList<>();
            for (ConstructorArg constructorArg : constructorArgs) {
                objects.add(getBean(constructorArg.getRef()));
            }
            return BeanUtils.instanceByCglib(clz,clz.getConstructor(),objects.toArray());
        }else {
            return BeanUtils.instanceByCglib(clz,null,null);
        }
    }

    private void populateBean(Object bean) throws Exception {
        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                String beanName = field.getName();
                beanName = StringUtils.uncapitalize(beanName);
                if (beanNameSet.contains(field.getName())) {
                    Object fieldBean = getBean(beanName);
                    if (fieldBean != null) {
                        ReflectionUtils.injectField(field,bean,fieldBean);
                    }
                }
            }
        }
    }



}
