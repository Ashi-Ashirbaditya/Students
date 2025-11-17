package net.ads.student.repo;

import net.ads.student.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Students, Long> {
}
