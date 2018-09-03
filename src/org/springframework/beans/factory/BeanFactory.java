package org.springframework.beans.factory;

import org.springframework.beans.factory.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    private Map<String, Object> singletons = new HashMap<String, Object>();

    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }

    public void instantiate(String basePackage) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String path = basePackage.replace('.', '/');
        try {
            Enumeration<URL> resources = classLoader.getResources(path); //IOException
            while (resources.hasMoreElements()) {
                URL res = resources.nextElement();
                File file = new File(res.toURI()); //URISyntaxException
                for (File classFile : file.listFiles()) {
                    String fileName = classFile.getName(); //ProductService.class
                    if (fileName.endsWith(".class")) {
                        String className = fileName.substring(0, fileName.lastIndexOf('.'));
                        Class classObject = Class.forName(basePackage + "." + className); //ClassNotFoundException
                        if (classObject.isAnnotationPresent(Component.class)) {
                            System.out.println("Component: " + classObject);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
