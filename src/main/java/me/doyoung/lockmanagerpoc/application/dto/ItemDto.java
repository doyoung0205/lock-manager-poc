package me.doyoung.lockmanagerpoc.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.doyoung.lockmanagerpoc.domain.item.ItemCommand;
import me.doyoung.lockmanagerpoc.domain.item.Item;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ItemDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    public static class Response {

        private Long id;
        private String name;
        private int quantity;

        public Response(Item item) {
            this.id = item.getId();
            this.name = item.getName();
            this.quantity = item.getQuantity();

        }

        public static Response from(Item item) {
            return new Response(item);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @ToString
    public static class UpdateRequest {
        private String name;
        private int quantity;

        public ItemCommand.Update toCommand() {
            return new ItemCommand.Update(name, quantity);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @ToString
    public static class SaveRequest {
        private String name;
        private int quantity;

        public Item toEntity() {
            return new Item(name, quantity);
        }
    }
}
