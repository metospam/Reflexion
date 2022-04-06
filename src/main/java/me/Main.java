package me;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("me.Input", new SubTypesScanner(false));

        Set<Class<?>> classSet = reflections.getSubTypesOf(Object.class);

        Set<Object> classesInPackage = new HashSet<>();

        for (Class clazz : classSet) {

            Object object = clazz.newInstance();

            classesInPackage.add(object);

            for (Field field : object.getClass().getDeclaredFields()) {

                if (field.getAnnotation(RandomInt.class) != null) {

                    RandomInt target = field.getAnnotation(RandomInt.class);

                    int min = target.min();
                    int max = target.max();

                    int random;

                    if (min < max) {
                        random = min + new Random().nextInt(max - min);
                    } else {
                        random = max + new Random().nextInt(min - max);
                    }

                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                        field.set(object, random);
                        field.setAccessible(false);
                    } else {
                        field.set(object, random);
                    }
                }
            }
        }
        for (Object object : classesInPackage) {
            System.out.println(object.getClass().getName());
        }
    }
}
