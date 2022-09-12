package me.doyoung.lockmanagerpoc.application.dto;

import me.doyoung.lockmanagerpoc.domain.item.Item;
import me.doyoung.lockmanagerpoc.domain.item.ItemCommand;

public final class ItemDto {
    private ItemDto() {
    }

    public static class Response {
        private Response() {
        }

        private Long id;
        private String name;
        private int quantity;

        public Response(Long id, String name, int quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }

        public Response(Item item) {
            this.id = item.getId();
            this.name = item.getName();
            this.quantity = item.getQuantity();

        }

        public static Response from(Item item) {
            return new Response(item);
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }

    public static class UpdateRequest {
        private UpdateRequest() {
        }

        private String name;
        private int quantity;

        public UpdateRequest(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public ItemCommand.Update toCommand() {
            return new ItemCommand.Update(name, quantity);
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "UpdateRequest{" +
                    "name='" + name + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }


    public static class SaveRequest {
        private SaveRequest() {
        }

        public SaveRequest(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        private String name;
        private int quantity;

        public Item toEntity() {
            return new Item(name, quantity);
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "SaveRequest{" +
                    "name='" + name + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }
}
