package com.example.LMS.Service;

import com.example.LMS.Enum.Department;
import com.example.LMS.Model.Student;
import com.example.LMS.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student) throws Exception{
        if(student.getRollNo() != null){
            throw new Exception("Student already exists.");
        }
        studentRepository.save(student);

        return "Student has been successfully saved to Database.";
    }

    public Department findDeptById(Integer studentId) throws Exception{
        Optional<Student> optional = studentRepository.findById(studentId);

        if(optional.isEmpty()){
            throw new Exception("Invalid Student_Id , no student is present in the database with the given Student_id.");
        }

        Student student = optional.get();
        Department department = student.getDepartment();

        return department;
    }
}
