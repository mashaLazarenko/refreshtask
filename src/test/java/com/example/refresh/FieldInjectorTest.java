package com.example.refresh;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FieldInjectorTest {

    private FieldInjector fieldInjector;
    private Properties properties;
    private PropertyParser parser;
    private final String addressPropertyString= "{street: Lenina, house: 50, city: {name: Bryansk, code: 241000}}";
    private final String addressCheckString = "Address: Lenina, 50, City: Bryansk, 241000";

    @Before
    public void init() throws IOException {
        fieldInjector = new FieldInjector();
        properties = new Properties();
        parser = mock(PropertyParser.class);
        when(parser.getProperties()).thenReturn(properties);
    }

    /*INTEGER*/

    @Test
    public void injectIntegerWithAnnotation() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "integer")
            private Integer integer;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Integer getInteger(){
                return integer;
            }
        }

        properties.setProperty("integer", "10");

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        Assert.assertEquals(0, testClass.getInteger().compareTo(10));
    }

    @Test
    public void injectIntegerWithoutAnnotation() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            private Integer integer;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Integer getInteger(){
                return integer;
            }
        }

        properties.setProperty("integer", "10");

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        assertNull(testClass.getInteger());
    }

    @Test
    public void InjectIntegerWithAnnotationAndEmptyProperty() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "integer")
            private Integer integer;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Integer getInteger(){
                return integer;
            }
        }

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        assertNull(testClass.getInteger());
    }

    @Test
    public void injectIntegerWithDefaultValue() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "integer", defaultValue = "20")
            private Integer integer;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Integer getInteger(){
                return integer;
            }
        }

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        Assert.assertEquals(0, testClass.getInteger().compareTo(20));
    }

    @Test
    public void injectIntegerWrongValue() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "integer", defaultValue = "twenty")
            private Integer integer;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Integer getInteger(){
                return integer;
            }
        }

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        assertNull(testClass.getInteger());
    }

    /*DOUBLE*/

    @Test
    public void injectDoubleWithAnnotation() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "double")
            private Double aDouble;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Double getDouble(){
                return aDouble;
            }
        }

        properties.setProperty("double", "10.76");

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        Assert.assertEquals(0, testClass.getDouble().compareTo(10.76));
    }

    @Test
    public void injectDoubleWithoutAnnotation() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            private Double aDouble;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Double getDouble(){
                return aDouble;
            }
        }

        properties.setProperty("double", "10.77");

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        assertNull(testClass.getDouble());
    }

    @Test
    public void InjectDoubleWithAnnotationAndEmptyProperty() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "double")
            private Double aDouble;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Double getDouble(){
                return aDouble;
            }
        }

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        assertNull(testClass.getDouble());
    }

    @Test
    public void injectDoubleWithDefaultValue() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "double", defaultValue = "20.43")
            private Double aDouble;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Double getDouble(){
                return aDouble;
            }
        }

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        Assert.assertEquals(0, testClass.getDouble().compareTo(20.43));
    }

    @Test
    public void injectDoubleWrongValue() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "double", defaultValue = "twenty.five")
            private Double aDouble;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Double getaDouble(){
                return aDouble;
            }
        }

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        assertNull(testClass.getaDouble());
    }

    /*STRING*/

    @Test
    public void injectStringWithAnnotation() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "string")
            private String string;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public String getString(){
                return string;
            }
        }

        properties.setProperty("string", "hello property");

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        Assert.assertEquals(0, testClass.getString().compareTo("hello property"));
    }

    @Test
    public void injectStringWithoutAnnotation() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            private String string;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public String getString(){
                return string;
            }
        }

        properties.setProperty("string", "hello property");

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        assertNull(testClass.getString());
    }

    @Test
    public void injectStringWithAnnotationAndEmptyProperty() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "string")
            private String string;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public String getString(){
                return string;
            }
        }

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        assertNull(testClass.getString());
    }

    @Test
    public void injectStringWithDefaultValue() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "string", defaultValue = "hello annotation")
            private String string;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public String getString(){
                return string;
            }
        }

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        Assert.assertEquals(0, testClass.getString().compareTo("hello annotation"));
    }

    /*USER TYPE JSON*/

    class City{
        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return "City: " + name + ", " + code;
        }
    }

    class Address{
        private String street;
        private int house;
        private City city;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public int getHouse() {
            return house;
        }

        public void setHouse(int house) {
            this.house = house;
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return "Address: " + street + ", " + house + ", " + city;
        }
    }

    @Test
    public void injectJsonWithAnnotation() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "address")
            private Address address;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public String getAddressString(){
                return address.toString();
            }
        }

        properties.setProperty("address", addressPropertyString);

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        Assert.assertEquals(addressCheckString, testClass.getAddressString());
    }

    @Test
    public void injectJsonWithoutAnnotation() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            private Address address;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Address getAddress(){
                return address;
            }
        }

        properties.setProperty("string", addressPropertyString);

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        assertNull(testClass.getAddress());
    }

    @Test
    public void injectJsonWithAnnotationAndEmptyProperty() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "address")
            private Address address;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Address getAddress(){
                return address;
            }
        }

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        assertNull(testClass.getAddress());
    }

    @Test
    public void injectJsonWithDefaultValue() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "address",
                    defaultValue = "{street: Lenina, house: 50, city: {name: Bryansk, code: 241000}}")
            private Address address;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public String getAddressString(){
                return address.toString();
            }
        }

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        Assert.assertEquals(addressCheckString, testClass.getAddressString());
    }

    @Test
    public void injectJsonWithAnnotationWrongFormat() throws IOException {

        class TestClassRefresher extends AbstractRefresher{

            @InjectValue(propertyName = "address")
            private Address address;

            public TestClassRefresher(PropertyParser parser, FieldInjector fieldInject) throws IOException {
                super(parser, fieldInject);
            }

            public Address getAddress(){
                return address;
            }
        }

        properties.setProperty("address", addressPropertyString+ "///////");

        TestClassRefresher testClass = new TestClassRefresher(parser, fieldInjector);
        assertNull(testClass.getAddress());
    }

}