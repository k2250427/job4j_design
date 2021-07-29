package ru.job4j.serialization.json;

import org.json.JSONObject;
import java.io.*;

public class ExampleJSON implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Person person;
    private final int zipCode;
    private final String phone;

    public ExampleJSON(int zipCode, String phone, Person person) {
        this.zipCode = zipCode;
        this.phone = phone;
        this.person = person;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + ", person=" + person
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
         final ExampleJSON contact = new ExampleJSON(123456, "+7 (111) 111-11-11",
                 new Person("Steve Black", true, 39));

        JSONObject jsonPerson = new JSONObject("{\"name\":\"John White\",\"sex\":\"true\","
                                                        + "\"age\":\"23\"}");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("zipCode", contact.getZipCode());
        jsonObject.put("phone", contact.getPhone());
        jsonObject.put("person", jsonPerson);

        System.out.println(jsonObject);
        JSONObject obj = new JSONObject(contact);
        System.out.println(obj);
    }
}