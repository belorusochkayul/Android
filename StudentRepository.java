package com.example.students.model;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static StudentRepository INSTANCE;
    private List<UserData> users = new ArrayList<>();
    private int id;

    private StudentRepository() {
    }
    public static StudentRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (StudentRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new StudentRepository();
                }
            }
        }
        return INSTANCE;
    }

    public UserData findById(int id){
        for(UserData user : users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public UserData addStudent(UserData student) {
        id ++;
        student.setId(id);
        users.add(student);
        return student;
    }


}
