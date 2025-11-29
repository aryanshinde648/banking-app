package com.obs.banking_app.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "f_name", nullable = false)
    private String fName;

    @Column(name = "m_name", nullable = false)
    private String mName;

    @Column(name = "l_name", nullable = false)
    private String lName;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "dob", nullable = false)
    private String dob;

    @Column(name = "gender", nullable = false)
    private String gender;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;

}
