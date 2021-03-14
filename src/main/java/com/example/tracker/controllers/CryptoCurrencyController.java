package com.example.tracker.controllers;

import com.example.tracker.dtos.CryptoCurrencyDto;
import com.example.tracker.dtos.EntryDto;
import com.example.tracker.models.CryptoCurrency;
import com.example.tracker.models.WalletLocationsEnum;
import com.example.tracker.services.CryptoCurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
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

    @GetMapping("/{id}")
    public CryptoCurrencyDto getCryptoCurrency(@PathVariable Long id) {
        return cryptoCurrencyService.getCryptoCurrency(id);
    }


    @PostMapping
    public void registerCurrency(@RequestBody CryptoCurrencyDto cryptoCurrencyDto){
        cryptoCurrencyService.save(cryptoCurrencyDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCurrency(@PathVariable Long id){
        cryptoCurrencyService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateCurrency(@RequestBody CryptoCurrencyDto cryptoCurrencyDto, @PathVariable Long id){
        cryptoCurrencyService.update(cryptoCurrencyDto, id);
    }


}
