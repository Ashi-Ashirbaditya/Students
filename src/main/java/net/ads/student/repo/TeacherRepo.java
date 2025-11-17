package net.ads.student.repo;

import net.ads.student.model.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teachers, Long> {
    static Teachers findByEmail(String email);
}
