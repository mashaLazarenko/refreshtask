package com.example.refresh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс-парсер файлов .properties
 */
public class PropertyParser {

    private final static Logger logger = LoggerFactory.getLogger(FieldInjector.class);

    /**
     * Путь до файла .properties
     */
    private String filePath;

    public PropertyParser(String filePath){
        this.filePath = filePath;
    }

    /**
     * Получает список параметров из файла
     * @return - список параметров
     * @throws IOException
     */
    public Properties getProperties() throws IOException {

        Properties property = new Properties();

        try (FileInputStream fis = new FileInputStream(filePath)){
            property.load(fis);
        } catch (IOException e) {
            logger.warn(e.getMessage());
            throw e;
        }
        return property;
    }
}
