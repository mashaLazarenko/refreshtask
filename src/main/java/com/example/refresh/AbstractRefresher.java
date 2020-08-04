package com.example.refresh;

import java.io.IOException;

/**
 * Абстрактный класс с методом заполнения полей из файла .properties
 */
public abstract class AbstractRefresher implements Refresher {

    /**
     * Парсер файла .properties
     */
    private final PropertyParser parser;

    private final FieldInjector fieldInjector;

    public AbstractRefresher(PropertyParser parser, FieldInjector fieldInjector) throws IOException {
        this.parser = parser;
        this.fieldInjector = fieldInjector;
        doRefresh();
    }

    /**
     * Метод для заполнения полей класса из файла .properties
     * @throws IOException
     */
    @Override
    public synchronized void doRefresh() throws IOException {
        fieldInjector.injectFields(this, parser.getProperties());
    }
}
