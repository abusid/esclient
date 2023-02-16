package com.elasticsearch.clients;

public class Person {

    private String personId;
    private String name;

    private String age;

    private String lastName;

    public Person() {
    }
    public Person(String personId, String name, String age, String lastName) {
        this.personId = personId;
        this.name = name;
        this.age = age;
        this.lastName = lastName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
