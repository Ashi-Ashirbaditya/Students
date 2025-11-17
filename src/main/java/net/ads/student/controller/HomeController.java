package net.ads.student.controller;

import net.ads.student.model.Courses;
import net.ads.student.model.Students;
import net.ads.student.service.CourseServ;
import net.ads.student.service.StudentServ;

import net.ads.student.service.TeacherServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private StudentServ studentser;
    private CourseServ courseser;
    @Autowired
    private TeacherServ teacherser;

    @GetMapping("/")//Home page
    public String home() {
        return "home";
    }

    //Show Registered Students
    @GetMapping("/students")
    public String viewHomePage(Model model) {
        model.addAttribute("students", studentser.getAllStudents());
        model.addAttribute("students", new Students());
        return findPaginated(1, "id", "asc", model);
    }

    //Show Available courses
    @GetMapping("/courses")
    public String viewCategoryPage(Model model) {
        model.addAttribute("courses", courseser.getAllCourses());
        model.addAttribute("courses", new Courses());
        return findPaginated2(1, "id", "asc", model);
    }

    // Show form to add a new Student
    @GetMapping("/showNewStudentForm")
    public String showNewProductForm(Model model) {
        Students stud = new Students();
        model.addAttribute("students", stud);
        model.addAttribute("course", courseser.getAllCourses());
        return "new-student";
    }


    // Show form to add a new Course
    @GetMapping("/showNewCourseForm")
    public String showNewCourseForm(Model model) {
        Courses courses = new Courses();
        model.addAttribute("courses", courses);
        return "new-course";
    }

    // Save or update student details
    @PostMapping("/saveStudents")
    public String saveStudent(@ModelAttribute("students") Students students) {
        StudentServ.saveStudent(students);
        return "redirect:/students";
    }

    // Save course
    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Courses courses) {
       CourseServ.saveCourse(courses);
        return "redirect:/courses";
    }

    // Show form to update a Student details
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable("id") Long id, Model model) {
        Students student = StudentServ.getStudentsById(id);
        model.addAttribute("students", student);
        model.addAttribute("courses", CourseServ.getAllCourses());
        return "update-student";
    }

    // Delete a Student
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        this.StudentServ.deleteStudentById(id);
        return "redirect:/students";
    }

    // Delete Course
    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable("id") long id) {
        this.CourseServ.deleteCourseyById(id);
        return "redirect:/courses";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 4;

        Page<Students> page = studentser.findPaginated(pageNo, pageSize, sortField, sortDir);
        List< Students > listStudents = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listStudents", listStudents);
        return "students";
    }

    @GetMapping("/page2/{pageNo2}")
    public String findPaginated2(@PathVariable(value = "pageNo2") int pageNo2,
                                 @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir,
                                 Model model) {
        int pageSize = 4;

        Page<Courses> page = courseser.findPaginated2(pageNo2, pageSize, sortField, sortDir);
        List< Courses > listCourses = page.getContent();

        model.addAttribute("currentPage", pageNo2);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCourses", listCourses);
        return "courses";
    }
}

