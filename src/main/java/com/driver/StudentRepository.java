package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    public HashMap<String,Student> studentMap;
    public HashMap<String,Teacher> teacherMap;
    public HashMap<String, List<String>> pairMap;


    public StudentRepository(HashMap<String, Student> studentMap, HashMap<String, Teacher> teacherMap, HashMap<String, List<String>> pairMap) {
        this.studentMap = studentMap;
        this.teacherMap = teacherMap;
        this.pairMap = pairMap;
    }

    public void addStudentToDB(Student student){
        String studentName = student.getName();
        studentMap.put(studentName,student);
    }

    public void addTeacherToDB(Teacher teacher){
        String teacherName = teacher.getName();
        teacherMap.put(teacherName,teacher);
    }

    public boolean pairStudentTeacherToDB(String studentName, String teacherName){
        if(pairMap.containsKey(teacherName)){
            for(String str: pairMap.get(teacherName)){
                if(str.equals(studentName)){
                    return false;
                }
            }
            List<String> list = pairMap.get(teacherName);
            list.add(studentName);
            return true;
        }

        List<String> list = new ArrayList<>();
        list.add(studentName);
        pairMap.put(teacherName,list);
        return true;
    }

    public Student getStudentFromDB(String studentName){
        if(studentMap.containsKey(studentName)){
            return studentMap.get(studentName);
        }

        return null;
    }

    public Teacher getTeacherFromDB(String teacherName){
        if(teacherMap.containsKey(teacherName)){
            return teacherMap.get(teacherName);
        }

        return null;
    }

    public List<String> getStudentsListFromDB(String teacherName){
        return pairMap.get(teacherName);
    }

    public List<String> getListOfStudentsFromDB(){
        List<String> list = new ArrayList<>();
        for(String str: studentMap.keySet()){
            list.add(str);
        }

        return list;
    }

    public boolean deleteTeacherFromDB(String teacherName){
        if(pairMap.containsKey(teacherName)){
            List<String> list = pairMap.get(teacherName);
            for(String str: list){
                studentMap.remove(str);
            }
            pairMap.remove(teacherName);
        }
        teacherMap.remove(teacherName);
        return true;
    }

    public void deleteAllTeachersFromDB(){
        if(teacherMap.isEmpty()){
            return;
        }else{
            for(String str: teacherMap.keySet()){
                deleteTeacherFromDB(str);
            }
        }
    }
}
