package com.example.refresh;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

public class FieldInjector {

    private final static Logger logger = LoggerFactory.getLogger(FieldInjector.class);

    private Map<Class<?>, Map<Field, InjectValue>> dataMap = new HashMap<>();

    /**
     * Метод, заполняющий поля.
     * В первый раз запоминаем Класс объекта и его поля в HashMap,
     * в дальнейшем берём из HashMap
     * @param object - объект, поля которого необходимо заполнить
     * @param properties - параметры, которыми необходимо заполнить поля
     */
    public void injectFields(Object object, Properties properties){

        if(!dataMap.containsKey(object.getClass())){
            dataMap.put(object.getClass(), getMap(object));
        }
        injectFields(dataMap.get(object.getClass()), object, properties);
    }

    /**
     * Получает коллекцию <Поле,Значение> для объекта
     * @param object - исходный объект
     * @return Коллекция <Поле, Значение>
     */
    private Map<Field, InjectValue> getMap(Object object){

        Map<Field,InjectValue> fieldMap = new HashMap<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            InjectValue injectValue = field.getAnnotation(InjectValue.class);

            fieldMap.put(field, injectValue);
        }
        return fieldMap;
    }

    /**
     * Заполняет поля объекта значениями
     * @param fieldMap - Коллекция <Поле, Знвечние>
     * @param object - исходный объект
     * @param properties - параметры
     */
    private void injectFields(Map<Field,InjectValue> fieldMap, Object object, Properties properties){

        for (Map.Entry<Field, InjectValue> field : fieldMap.entrySet()) {

            if( field.getValue() != null ){
                String val = properties.getProperty(field.getValue().propertyName());
                setFieldValue(object,field.getKey(), val == null ? field.getValue().defaultValue() : val);
            }
        }
    }

    /**
     * Заполняет поле в зависимости от его типа.
     * Записывает в лог ошибку заполнения
     * @param object - объекст
     * @param field - поле
     * @param val - значение для поля
     */
    private void setFieldValue(Object object, Field field, String val) {

        try {
            field.setAccessible(true);
            if(field.getType() == String.class) {
                field.set(object, val.isEmpty() ? null:val);
            } else if( field.getType() == Double.class){
                field.set(object, getDoubleValue(val));
            } else if( field.getType() == Integer.class){
                field.set(object, getIntegerValue(val));
            } else {
                field.set( object, getUserTypeFromJson(val, field.getType()));
            }
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            logger.warn(e.getMessage());
        }
    }

    /**
     * Приводит строку к пользовательскому типу.
     * Если строка пустая, возвращает null
     * @param val - строка
     * @param type - пользовательский тип
     * @return - объект пользовательского типа
     */
    private <T> T getUserTypeFromJson(String val, Class<T> type){
        return getValueOrNull(val, it->(new Gson()).fromJson(val, type));
    }

    /**
     * Приводит строку к типу Double.
     * При ошибке приведения возвращает null
     * @param val - строка
     * @return - значение Double
     */
    private Double getDoubleValue(String val){
        return getValueOrNull(val, Double::parseDouble);
    }

    /**
     * Приводит строку к типу Integer.
     * При ошибке приведения возвращает null
     * @param val - строка
     * @return - значение Integer
     */
    private Integer getIntegerValue(String val){
        return getValueOrNull(val, Integer::parseInt);
    }

    private  <T> T getValueOrNull(String val, Function<String, T> mapper) {

        if (val == null || val.isEmpty()) {
            return null;
        }

        try {
            return mapper.apply(val);
        } catch (NumberFormatException | JsonSyntaxException e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

}
