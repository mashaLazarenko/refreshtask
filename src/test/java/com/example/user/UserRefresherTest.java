package com.example.user;

import com.example.refresh.FieldInjector;
import com.example.refresh.PropertyParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRefresherTest {

    private FieldInjector fieldInjector;
    private Properties properties;
    private PropertyParser parser;

    private UserRefresher userRefresher;

    private final String expectedString = "UserRefresh{name='Max', salary=200002.5, " +
            "workPlace=WorkPlace{department='Development', experience=5, " +
            "job=Job{name='engineer', workingHours=8}}}";

    @Before
    public void init() throws IOException {
        fieldInjector = new FieldInjector();
        properties = new Properties();
        properties.setProperty("com.example.refresh.user.name", "Max");
        properties.setProperty("com.example.refresh.user.salary", "200002.5");
        properties.setProperty("com.example.refresh.user.work",
                "{department: Development, experience: 5, " +
                        "job: {name: engineer, workingHours: 8}}");
        parser = mock(PropertyParser.class);
        when(parser.getProperties()).thenReturn(properties);
    }

    @Test
    public void doRefreshAtInitialization() throws IOException {
        userRefresher = UserRefresher.getInstance(parser, fieldInjector);
        Assert.assertEquals(expectedString, userRefresher.toString());
    }

    @Test
    public void doRefreshInCycle() throws IOException {
        userRefresher = UserRefresher.getInstance(parser, fieldInjector);
        for(int i = 0; i<100; i++){
            userRefresher.doRefresh();
        }
        Assert.assertEquals(expectedString, userRefresher.toString());
    }
}