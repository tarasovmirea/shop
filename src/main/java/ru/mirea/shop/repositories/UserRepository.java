package ru.mirea.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.shop.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

}
