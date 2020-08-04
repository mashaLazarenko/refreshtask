package com.example.refresh;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для заполнения поля значением из файла .properties
 * Поля:
 * propertyName - имя параметра
 * defaultValue - значение по умолчанию
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectValue {

    String propertyName();
    String defaultValue() default "";
}
