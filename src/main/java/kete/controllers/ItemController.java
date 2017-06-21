package kete.controllers;

import kete.entities.Item;
import kete.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping
    public void createItem(Item item) {
        itemRepository.save(item);
    }

    @GetMapping
    public List<Item> retrieveAll() {
        return itemRepository.findAll();
    }

    @PostMapping("/populate")
    public void populate() {
        Item item1 = new Item();
        item1.setDescricao("Item1Desc");
        item1.setEspecie("Item1Especie");

        Item item2 = new Item();
        item2.setDescricao("Item2Desc");
        item2.setEspecie("Item2Especie");

        itemRepository.save(item1);
        itemRepository.save(item2);
    }

}
