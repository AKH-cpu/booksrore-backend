package com.akhadam.kitabi.serviceImpl;

import com.akhadam.kitabi.dto.BookDto;
import com.akhadam.kitabi.dto.StockDto;
import com.akhadam.kitabi.entity.StockEntity;
import com.akhadam.kitabi.repository.StockRepository;
import com.akhadam.kitabi.service.StockService;
import com.akhadam.kitabi.shared.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    Utils utils;

    ModelMapper modelMapper= new ModelMapper();

    @Override
    public StockDto save(StockDto stockDto, BookDto bookDto) {

        stockDto.setStockId(utils.generateId(30));
        stockDto.setBook(bookDto);

        StockEntity stockEntity = modelMapper.map(stockDto, StockEntity.class);

        StockEntity newStock = stockRepository.save(stockEntity);
        return modelMapper.map(newStock, StockDto.class);
    }

    @Override
    public StockDto findByStockId(String stockId) {
        StockEntity foundedStock = stockRepository.findByStockId(stockId);
        if (foundedStock == null) throw  new RuntimeException("stock doesn't exist");
        return modelMapper.map(foundedStock, StockDto.class);
    }
}
