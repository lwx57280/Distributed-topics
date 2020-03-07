package com.clone;

import java.io.IOException;

public class CloneDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Teacher teacher =new Teacher();
        teacher.setName("lisa");

        Student student = new Student();
        student.setName("大侠");
        student.setAge(15);
        student.setTeacher(teacher);

        Student student2 = (Student)student.deeptClone();
        System.out.println(student);
        student2.getTeacher().setName("james");
        System.out.println(student2);
    }
}
