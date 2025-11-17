package net.ads.student.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "courses")  // owning side is Student
    private Set<Students> students;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Set<Students> getStudents() { return students; }
    public void setStudents(Set<Students> students) { this.students = students; }
}
