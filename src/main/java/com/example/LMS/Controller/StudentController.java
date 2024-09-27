package com.example.LMS.Controller;

import com.example.LMS.Enum.Department;
import com.example.LMS.Model.Student;
import com.example.LMS.Service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        try {
            String result = studentService.addStudent(student);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Student could not added to Database: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findDeptById")
    public ResponseEntity<Department> findDeptById(@RequestParam("Id") Integer student_Id){
        try {
            Department department = studentService.findDeptById(student_Id);
            return new ResponseEntity<>(department , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Could fetch the department from Database: " + e.getMessage());
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
    }
}
