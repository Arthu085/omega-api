package com.omega.api.models;

import com.omega.api.enums.RoleUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleUser roleName;

}
