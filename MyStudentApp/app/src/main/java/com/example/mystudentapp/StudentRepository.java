package com.example.mystudentapp;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static final StudentRepository INST = new StudentRepository();
    private final List<Student> items = new ArrayList<>();

    public StudentRepository() {
    }

    public static StudentRepository getStudent() {
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
