package ru.mirea.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.shop.entities.Item;
import ru.mirea.shop.repositories.ItemRepository;
import ru.mirea.shop.services.ItemService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/items")
@PreAuthorize("hasAuthority('ADMIN')")
public class ItemController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;

    @GetMapping
    public String index(Model model){
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "items/index";
    }
    @GetMapping("/{id}")
    public String showItem(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", itemService.findById(id));
        return "items/show";
    }

    @GetMapping("/new")
    public String newItem(@ModelAttribute("item") Item item){
        return "items/new";
    }
    @PostMapping
    public String createItem(@ModelAttribute("item") Item item,
                             @RequestParam("file")MultipartFile file) throws IOException {
        if(file != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            item.setFilename(resultFilename);
        }

        itemRepository.save(item);
        return "redirect:/items";
    }
    @GetMapping("/{id}/edit")
    public String editItem(Model model, @PathVariable("id") Long id) {
        model.addAttribute("item", itemService.findById(id));
        return "items/edit";
    }

    @PatchMapping("/{id}")
    @Transactional
    public String updateItem() {
        // ДОДЕЛАТЬ
        return "redirect:/items";
    }

    @DeleteMapping("/{id}")
    @Transactional
    public String deleteItem(@PathVariable("id") Long id){
        itemRepository.deleteById(id);
        return "redirect:/items";
    }
}
