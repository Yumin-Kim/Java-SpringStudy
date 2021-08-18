package kr.co.home.dashboard.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String studentCode;

    private String password;
    private String email;
    private String PhoneNumber;
    private Boolean inSchool;
    private Boolean isDeleted;
    private LocalDate createdAt;
    private LocalDate lastmodified;

}
