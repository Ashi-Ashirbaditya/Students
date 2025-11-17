package net.ads.student.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Students {

    @Id
    private Long regd;

    private String name;
    private int std;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Courses> courses;

    public Long getRegd() {
        return regd;
    }
    public void setRegd(Long regd) {
        this.regd = regd;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getStd() {
        return std;
    }
    public void setStd(int std) {
        this.std = std;
    }

    public Set<Courses> getCourses() { return courses; }
    public void setCourses(Set<Courses> courses) { this.courses = courses; }
}
