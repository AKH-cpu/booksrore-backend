package com.akhadam.kitabi.ws.controller;

import com.akhadam.kitabi.dto.BookDto;
import com.akhadam.kitabi.dto.StockDto;
import com.akhadam.kitabi.responses.StockResponse;
import com.akhadam.kitabi.service.StockService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    StockService stockService;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<StockResponse> save(StockDto stock, BookDto book) {
        StockDto stockDto = stockService.save(stock, book);
        StockResponse stockResponse = modelMapper.map(stockService, StockResponse.class);
        return new ResponseEntity<StockResponse>(stockResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<StockResponse> findByStockId(@PathVariable String stockId) {
        StockDto stockDto = stockService.findByStockId(stockId);
        StockResponse stockResponse = modelMapper.map(stockDto, StockResponse.class);
        return new ResponseEntity<>(stockResponse, HttpStatus.OK);
    }
}
