package net.ads.student.service;

import net.ads.student.model.Students;
import net.ads.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServ {
    @Autowired
    private static StudentRepo studentsrepo;

    public List<Students> getAllStudents() {
        return studentsrepo.findAll();
    }

    public void saveStudent(Students stud) {
        studentsrepo.save(stud);
    }
    public Students getStudentsById(Long id) {
        return studentsrepo.findById(id).orElse(null);
    }
    public void deleteStudentById(Long id) {
        studentsrepo.deleteById(id);
    }

    public Page<Students> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.studentsrepo.findAll(pageable);
    }
}
