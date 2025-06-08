package com.omega.api.users.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.omega.api.enums.RoleUser;
import com.omega.api.enums.StatusUsuario;
import com.omega.api.models.Role;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserDto {

    private String name;
    private String lastname;
    private String email;
    private String password;
    private RoleUser roleUser;
    private StatusUsuario statusUser;

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

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public StatusUsuario getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(StatusUsuario statusUser) {
        this.statusUser = statusUser;
    }

}
