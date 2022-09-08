package me.doyoung.lockmanagerpoc.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;

    private Item(String name) {
        this.name = name;
    }

    public static Item ofName(String name) {
        return new Item(name);
    }

    public void update(Item item) {
        this.name = item.getName();
        this.quantity = item.getQuantity();
    }
}
