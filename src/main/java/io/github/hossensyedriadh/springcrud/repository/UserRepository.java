package io.github.hossensyedriadh.springcrud.repository;

import io.github.hossensyedriadh.springcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
