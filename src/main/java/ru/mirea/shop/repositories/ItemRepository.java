package ru.mirea.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mirea.shop.entities.Item;

import javax.persistence.Id;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

//    void updateById(Long id, Item item);

    void deleteById(Long id);




}
