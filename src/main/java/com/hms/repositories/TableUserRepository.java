package com.hms.repositories;

import com.hms.entities.TableUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableUserRepository extends JpaRepository<TableUser, Long> {

    TableUser findByEmail(String email);
}
