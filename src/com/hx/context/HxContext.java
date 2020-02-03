package com.hx.context;

import com.hx.annoation.HxAutowired;
import com.hx.annoation.HxService;
import com.hx.service.Service_1;
import com.hx.service.Service_2;
import com.hx.util.ClassUtil;


import java.lang.reflect.Field;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class HxContext {

    private static ConcurrentHashMap<String,Object> map;

    private static String path;

    private HxContext(){

    }


    public static Object getBean(String name)throws  Exception{
        if(name==null&&name.equals("")){
            throw new Exception("beanId错误");
        }
        Object object=map.get(name);
        Object obj=initAttribute(object);
        return obj;
    }

    public static void initContext(String path) throws Exception{
        if (map!=null)
            return;
        if(path==null||path.equals("")){
            throw new Exception("非法路径path");
        }
        map=new ConcurrentHashMap<>();
        Set<Class<?>> set= ClassUtil.getClasses(path);
        for (Class c :
                set) {
            String beanId;
            if (c.isAnnotationPresent(HxService.class)){
                beanId=c.getSimpleName();
                beanId=ClassUtil.toLowerCaseFirstOne(beanId);
                map.put(beanId,c.newInstance());
            }
        }
    }

    public static Object initAttribute(Object object) throws Exception{
        Class<?> c=object.getClass();
        Field[] fields=c.getDeclaredFields();
        for (Field f :
                fields) {
            if (f.isAnnotationPresent(HxAutowired.class)){
                String beanId=f.getName();
                Object attrObj=getBean(beanId);
                f.setAccessible(true);
                f.set(object,attrObj);
            }
        }
        return object;
    }
}
