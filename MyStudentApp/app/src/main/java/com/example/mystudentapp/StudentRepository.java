package com.example.mystudentapp;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static final StudentRepository INST = new StudentRepository();
    private  List<Student> items = new ArrayList<>();

    private StudentRepository() {
        items.add(new Student("Gleb","33 year"));
        items.add(new Student("Sergey","30 year"));
        items.add(new Student("Danyta","18 year"));
        items.add(new Student("Polyna","34 year"));
        items.add(new Student("Tema","18 year"));
    }

    public static StudentRepository getInstance() {
        return INST;
    }

    public void add(Student item) {
        this.items.add(item);
    }

    public List<Student> getAll() {
        return this.items;
    }

    public int size() {
        return this.items.size();
    }

    public Student get(int index) {
        return this.items.get(index);
    }

    public void replace(int index, Student item) {
        this.items.set(index, item);
    }
}
