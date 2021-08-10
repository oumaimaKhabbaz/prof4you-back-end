package com.prof4you.app.entities;

public enum Subject {
    PROGRAMMING ("Programming"),
    MATHS("Maths"),
    PHILOSOPHY("Philosophy"),
    PHYSICS("Physics"),
    ENGINEERING("Engineering"),
    JAVA ("Java"),
    ANGULAR("Angular"),
    SPRING_BOOT("Spring Boot"),
    REACT_JS("ReactJs"),
    SQL("php");

    private final String subject;

    Subject (String subject){

        this.subject = subject;

    }

    public String getSubject() {

        return this.subject;
    }

}
