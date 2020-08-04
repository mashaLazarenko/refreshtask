package com.example.refresh;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

public class PropertyParserTest {

    private PropertyParser parser;

    @Test(expected = IOException.class)
    public void setWrongFilePathGetException() throws IOException {

        parser = new PropertyParser("");
        parser.getProperties();
    }

    @Test
    public void setRightFileGetProperty() throws IOException {

        parser = new PropertyParser("src\\test\\resources\\user.properties");
        Properties property = new Properties();
        property.setProperty("user.name", "Max");
        property.setProperty("user.salary", "200002.5");

        Assert.assertEquals(property, parser.getProperties());
    }
}