package com.hyperskill.accountservice.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email","id"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NotBlank(message = "name is required")
    private String name;
    @Column(name = "lastname")
    @NotBlank(message = "lastname is required")
    private String lastname;
    @Column(name = "password")
    @NotBlank(message = "password is required")
    private String password;
    @Column(name = "email")
    @NotBlank(message = "email is required")
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
