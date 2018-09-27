package com.ezhomesixgod.core;

import com.ezhomesixgod.bean.BeanDefinition;
import com.ezhomesixgod.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author
 * @description Json文件中解析bean
 * @createTime 2018-09-26 17:39
 */
public class JsonApplicationContext extends BeanFactoryImpl{

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(JsonApplicationContext.class);

    private String applicationPath="applicationContext.xml";

    public JsonApplicationContext(String applicationPath){
        this.applicationPath =applicationPath;

        loadContext();
    }

    protected void loadContext(){
        logger.info("Init ApplicationContext");

        InputStream ins =Thread.currentThread().getContextClassLoader().getResourceAsStream(applicationPath);

        List<BeanDefinition> beanDefinitions= JsonUtils.readValue(ins,new TypeReference<List<BeanDefinition>>(){});

        if(beanDefinitions !=null && !beanDefinitions.isEmpty()){
            for(BeanDefinition beanDefinition:beanDefinitions){
                registerBean(beanDefinition.getName(), beanDefinition);
            }

        }
    }

}
