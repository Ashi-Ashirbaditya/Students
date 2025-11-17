package net.ads.student.repo;

import net.ads.student.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Courses, Long> {
}
