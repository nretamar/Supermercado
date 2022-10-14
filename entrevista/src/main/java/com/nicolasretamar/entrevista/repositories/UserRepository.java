package com.nicolasretamar.entrevista.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicolasretamar.entrevista.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByDni(Integer dni);
}
