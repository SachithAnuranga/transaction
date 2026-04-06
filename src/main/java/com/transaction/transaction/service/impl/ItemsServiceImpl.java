package com.transaction.transaction.service.impl;

import com.transaction.transaction.repo.ItemsRepository;
import com.transaction.transaction.service.ItemsService;
import com.transaction.transaction.entity.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsRepository itemsRepository;

    @Override
    public Items saveItem(Items items) {
        return itemsRepository.save(items);
    }

    @Override
    public Items getItemById(Long id) {
        return itemsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id " + id));
    }

    @Override
    public List<Items> getAllItems() {
        return itemsRepository.findAll();
    }

    @Override
    public void deleteItem(Long id) {
        itemsRepository.deleteById(id);
    }
}
