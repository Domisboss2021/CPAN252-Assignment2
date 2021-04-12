package com.szwec.database.service;

import com.szwec.database.model.Item;
import com.szwec.database.model.Inventory;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;


@Singleton
@Remote(InventoryManager.class)
public class InventoryService implements InventoryManager {
    private List<Inventory> inventories;
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    @PostConstruct
    public void addItem() {
        inventories = new ArrayList<>();
        inventories.add(new Item(1,"Soccer ball", "Soccer", 5, 4.99, "01-25-2020"));
        inventories.add(new Item(1,"Basketball", "Basketball", 20, 7.99, "07-17-2019"));
        inventories.add(new Item(1,"Tennis ball", "Tennis", 200, 2.99, "03-12-2021"));
        inventories.add(new Item(1,"Golf Tees", "Golf", 1000, 0.99, "04-05-2021"));
    }

    @Override
    @Asynchronous
    public Future<String> add(Inventory inventory) {
        inventories.add(inventory);
        return new AsyncResult<>("Added");
    }

    @Override
    public List<Inventory> getInventories() {
        return inventories;
    }

    @Override
    public void delete(Inventory inventory) {
        inventories.removeIf(next -> next.getName().equals(inventory.getName()));
    }
}
