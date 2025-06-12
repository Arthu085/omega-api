package com.omega.api.repository;

import com.omega.api.enums.RoleUser;
import com.omega.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncaoRepository extends JpaRepository<Role, Long> {

    Role findFirstByRoleName(RoleUser roleUser);

}
