package com.ezhomesixgod.core;

/**
 * @author renyang
 * @description
 * @createTime 2018-09-26 17:38
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;
}
