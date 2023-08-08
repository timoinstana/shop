package com.instana.shop;

import java.util.HashMap;
import java.util.Map;

/**
 * Caches the current inventory Item / number of items.
 * Used by different controllers to add and remove items
 * from a cart by different users.
 */
public class InventoryCache {

    private Map<Item, Integer> cache = new HashMap<>();

    public void init(Map<Item,Integer> resultFromDb) {
        cache.putAll(resultFromDb);
    }

    public int getInventory(Item ITEM) {
        return cache.get(ITEM);
    }

    public void returnItem(Item itemId) {
        int v = cache.get(itemId);
        cache.put(itemId, ++v);
    }

    public void takeItem(Item itemId) {
        cache.compute(itemId, (k,v) -> {
            if (v >= 1) {
                return v--;
            } else {
                throw new RuntimeException("no item in stock");
            }
        });
    }

}
