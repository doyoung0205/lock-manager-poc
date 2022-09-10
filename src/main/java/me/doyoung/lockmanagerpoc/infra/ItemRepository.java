package me.doyoung.lockmanagerpoc.infra;

import me.doyoung.lockmanagerpoc.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
