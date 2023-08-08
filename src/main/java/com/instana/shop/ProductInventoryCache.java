package com.instana.shop;

import java.util.HashMap;
import java.util.Map;

/**
 * Caches the current inventory Product / number of items.
 * Used by different controllers to add and remove items
 * from a cart by different users.
 */
public class ProductInventoryCache {

    private Map<Product, Integer> cache = new HashMap<>();

    public void init(Map<Product,Integer> resultFromDb) {
        cache.putAll(resultFromDb);
    }

    public int getInventory(Product PRODUCT) {
        return cache.get(PRODUCT);
    }

    public void removeFromCart(Product productId) {
        int v = cache.get(productId);
        cache.put(productId, ++v);
    }

    public void addToCart(Product productId) {
        cache.compute(productId, (k,v) -> {
            if (v >= 1) {
                return v--;
            } else {
                throw new RuntimeException("no product in stock");
            }
        });
    }

}
