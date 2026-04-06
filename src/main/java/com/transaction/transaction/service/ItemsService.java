package com.transaction.transaction.service;

import com.transaction.transaction.entity.Items;

import java.util.List;

public interface ItemsService {
    Items saveItem(Items items);

    Items getItemById(Long id);

    List<Items> getAllItems();

    void deleteItem(Long id);
}
