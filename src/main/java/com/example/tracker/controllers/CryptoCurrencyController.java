package com.example.tracker.controllers;

import com.example.tracker.dtos.CryptoCurrencyDto;
import com.example.tracker.ControllersTests.CryptoCurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
@AllArgsConstructor
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;

    @GetMapping
    public List<CryptoCurrencyDto> getAllCurrencies(){
        return cryptoCurrencyService.getAllCurrencies();
    }

    @GetMapping("/{currencyName}")
    public CryptoCurrencyDto getCryptoCurrency(@PathVariable String currencyName) {
        return cryptoCurrencyService.getCryptoCurrency(currencyName);
    }


    @PostMapping
    public void registerCurrency(@RequestBody CryptoCurrencyDto cryptoCurrencyDto){
        cryptoCurrencyService.save(cryptoCurrencyDto);
    }

    @DeleteMapping("/{currencyName}")
    public void deleteCurrency(@PathVariable String currencyName){
        cryptoCurrencyService.delete(currencyName);
    }

    @PutMapping("/{currencyName}")
    public void updateCurrency(@RequestBody CryptoCurrencyDto cryptoCurrencyDto, @PathVariable String currencyName){
        cryptoCurrencyService.update(cryptoCurrencyDto, currencyName);
    }
}
