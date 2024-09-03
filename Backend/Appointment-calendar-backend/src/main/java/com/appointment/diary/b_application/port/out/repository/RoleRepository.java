package com.appointment.diary.b_application.port.out.repository;

import com.appointment.diary.c_domain.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

    //trae los que existen de los que le paso
    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
}
