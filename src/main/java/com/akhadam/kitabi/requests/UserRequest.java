/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhadam.kitabi.requests;
import javax.validation.constraints.*;


/**
 * @author AKH
 */
public class UserRequest {

    @NotBlank(message = "name is required")
    @Size(min = 3, message = "name must be at least 3 characters ")
    private String name;

    @NotNull(message = "email is required")
    @Email(message = "email invalid")
    private String email;

    @NotNull(message = "password is required")
    @Size(min = 8, message = "password must be at least characters ")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message = " password must contain uppercase and lowercase letters and numbers ")
    private String password;

    private Boolean isAdmin = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
