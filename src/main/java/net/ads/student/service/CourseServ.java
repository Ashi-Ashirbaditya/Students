package net.ads.student.service;

import net.ads.student.model.Courses;
import net.ads.student.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServ {

    @Autowired
    private CourseRepo courserepo;

    public List<Courses> getAllCourses() {
        return courserepo.findAll();
    }

    public void saveCourse(Courses course) {
        courserepo.save(course);
    }
    public Courses getCoursesById(Long id) {
        return courserepo.findById(id).orElse(null);
    }
    public void deleteCoursesById(Long id) {
        courserepo.deleteById(id);
    }

    public Page<Courses> findPaginated2(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.courserepo.findAll(pageable);
    }
}
