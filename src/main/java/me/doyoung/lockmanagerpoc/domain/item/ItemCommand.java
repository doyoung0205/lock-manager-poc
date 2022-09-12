package me.doyoung.lockmanagerpoc.domain.item;

public final class ItemCommand {
    private ItemCommand() {
    }

    public static class Update {
        private String name;
        private int quantity;

        private Update() {
        }

        public Update(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "Update{" +
                    "name='" + name + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }
}
