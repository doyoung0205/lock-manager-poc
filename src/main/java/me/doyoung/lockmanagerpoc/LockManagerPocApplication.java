package me.doyoung.lockmanagerpoc;

import me.doyoung.lockmanagerpoc.domain.item.Item;
import me.doyoung.lockmanagerpoc.infra.ItemRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class LockManagerPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(LockManagerPocApplication.class, args);


    }

    @Component
    public static class ItemDataLoader implements ApplicationRunner {

        private final ItemRepository itemRepository;

        public ItemDataLoader(ItemRepository itemRepository) {
            this.itemRepository = itemRepository;
        }

        public void run(ApplicationArguments args) {
            itemRepository.saveAll(List.of(
                    new Item("Mac Half Jacket [Cream]", 10),
                    new Item("Raccoon Cable V Cardigan [Grey]", 10)
            ));
        }
    }

}
