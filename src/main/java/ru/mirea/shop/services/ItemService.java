package ru.mirea.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.shop.entities.Item;
import ru.mirea.shop.repositories.ItemRepository;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item findById(Long id) {
        return itemRepository.findById(id).get();
    }
}
