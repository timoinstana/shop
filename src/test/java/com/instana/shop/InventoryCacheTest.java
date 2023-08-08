package com.instana.shop;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryCacheTest {

    @Test
    void shouldAddItemOverride() {
        InventoryCache cache = new InventoryCache();
        cache.init(Map.of(new Item("item1"), 100));
        cache.init(Map.of(new Item("item1"), 200));
        assertThat(cache.getInventory(new Item("1"))).isEqualTo(200);
    }

    @Test
    void shouldHandleMissing() {
        InventoryCache cache = new InventoryCache();
        assertThat(cache.getInventory(new Item("item2"))).isEqualTo(0);
    }

    @Test
    void shouldHandleAsync() {
        InventoryCache cache = new InventoryCache();
        Item item1 = new Item("item1");
        cache.init(Map.of(item1, 1));
        Thread addController = new Thread(() -> cache.takeItem(item1));
        Thread removeController = new Thread(() -> cache.takeItem(item1));
        addController.start();
        removeController.start();
        assertThat(cache.getInventory(item1)).isEqualTo(0);
    }

}
