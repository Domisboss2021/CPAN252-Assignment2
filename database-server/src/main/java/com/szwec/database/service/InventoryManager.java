package com.szwec.database.service;

import com.szwec.database.model.Inventory;

import java.util.List;
import java.util.concurrent.Future;

public interface InventoryManager {
    Future<String> add(Inventory inventory);

    List<Inventory> getInventories();

    void delete(Inventory inventory);
}
