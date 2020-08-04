package com.example.user;

import com.example.refresh.*;

import java.io.IOException;

/**
 * Singleton класс  User, реализующий заполнение полей из файла
 */
public class UserRefresher extends AbstractRefresher {

    @InjectValue(propertyName = "com.example.refresh.user.name", defaultValue = "Antony")
    private String name;
    @InjectValue(propertyName = "com.example.refresh.user.salary")
    private Double salary;
    @InjectValue(propertyName = "com.example.refresh.user.work")
    private WorkPlace workPlace;

    private static UserRefresher INSTANCE;

    private UserRefresher(PropertyParser parser, FieldInjector fieldInjector) throws IOException {
        super(parser, fieldInjector);
    }

    /**
     * Отложенная инициализация с синхронизированным доступом
     * @param parser - парсер файла с параметрами
     * @param fieldInjector - объект, заполняющий поля
     * @return - экземпляр класса UserRefresher
     * @throws IOException
     */
    public synchronized static UserRefresher getInstance(PropertyParser parser, FieldInjector fieldInjector) throws IOException {
        if(INSTANCE == null){
            INSTANCE = new UserRefresher(parser, fieldInjector);
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "UserRefresh{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", workPlace=" + workPlace +
                '}';
    }
}