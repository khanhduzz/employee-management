package com.fpt.fsa.employee_management.entities;

import com.fpt.fsa.employee_management.enums.EGender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private EGender gender;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, length = 20)
    private String phone;

    private String address;

    @Column(nullable = false)
    private String departmentName;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Account account;

    public void addAccount (Account account) {
        this.account = account;
        account.setEmployee(this);
    }
}
