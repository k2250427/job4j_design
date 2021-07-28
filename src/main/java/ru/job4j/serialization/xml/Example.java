package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.*;

@XmlRootElement(name = "contact")
@XmlAccessorType(XmlAccessType.FIELD)
public class Example implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlElement
    private int zipCode;
    @XmlElement
    private String phone;
    private Person person;

    public Example() {

    }

    public Example(int zipCode, String phone, String name, boolean sex, int age) {
        this.zipCode = zipCode;
        this.phone = phone;
        this.person = new Person(name, sex, age);
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
                + ", pepson=" + person
                + '}';
    }

    public static void main(String[] args) throws Exception {
        final Example contact = new Example(123456,
                "+7 (111) 111-11-11",
                "Steve Black",
                true,
                39);

        JAXBContext context = JAXBContext.newInstance(Example.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(contact, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Example result = (Example) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}