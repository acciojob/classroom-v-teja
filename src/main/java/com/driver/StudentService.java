package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {

  @Autowired
    StudentRepository studentRepository;

  public boolean addStudentByBody(Student student){
      if(studentRepository.studentMap.containsKey(student.getName())){
          return false;
      }

      studentRepository.addStudentToDB(student);
      return true;
  }

  public boolean addTeacherByBody(Teacher teacher){
      if(studentRepository.teacherMap.containsKey(teacher.getName())){
          return false;
      }

      studentRepository.addTeacherToDB(teacher);
      return true;
  }

  public boolean pairStudentTeacher(String studentName, String teacherName){
      if(!studentRepository.studentMap.containsKey(studentName)){
          return false;
      }

      if(!studentRepository.teacherMap.containsKey(teacherName)){
          return false;
      }

      return studentRepository.pairStudentTeacherToDB(studentName,teacherName);

  }

  public Student getStudent(String studentName){
      return studentRepository.getStudentFromDB(studentName);
  }

  public Teacher getTeacher(String teacherName){
      return studentRepository.getTeacherFromDB(teacherName);
  }

  public List<String> getStudentsList(String teacherName){
      if(!studentRepository.teacherMap.containsKey(teacherName)){
          return null;
      }

      if(!studentRepository.pairMap.containsKey(teacherName)){
          return null;
      }

      return studentRepository.getStudentsListFromDB(teacherName);
  }

  public List<String> getListOfStudents(){
      return studentRepository.getListOfStudentsFromDB();
  }

  public boolean deleteTeacher(String teacherName){
      if(!studentRepository.teacherMap.containsKey(teacherName)){
          return false;
      }
      return studentRepository.deleteTeacherFromDB(teacherName);
  }

  public void deleteAllTeachers(){
      studentRepository.deleteAllTeachersFromDB();
  }

}
