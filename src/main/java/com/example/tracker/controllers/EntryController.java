package com.example.tracker.controllers;

import com.example.tracker.dtos.EntryDto;
import com.example.tracker.models.CryptoCurrency;
import com.example.tracker.models.WalletLocationsEnum;
import com.example.tracker.ControllersTests.CryptoCurrencyService;
import com.example.tracker.ControllersTests.EntryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping("/api/entry")
@AllArgsConstructor
public class EntryController {

    private final EntryService entryService;
    private final CryptoCurrencyService cryptoCurrencyService;


    @GetMapping
    public List<EntryDto> getAllEntries(){
        return entryService.getAllEntries();
    }

    @GetMapping("/combined")
    public String getCurrentPortfolioValue(){
        Float result = entryService.getCurrentPortfolioValue();
        DecimalFormat myFormatter = new DecimalFormat("###,###,###,###.###");
        String resultString = "The total value of the portfolio updated to current market value is " + myFormatter.format(result) + " Euros";
        System.out.println(resultString);
        return resultString;
    }

    @GetMapping("/{reference}")
    public EntryDto getEntry(@PathVariable Long reference) {
        return entryService.getEntry(reference);
    }

    @GetMapping("/wallet/{walletLocation}")
    public List<EntryDto> getEntriesByWalletLocation(@PathVariable WalletLocationsEnum walletLocation) {
        return entryService.getEntriesByWalletLocation(walletLocation);
    }

    @GetMapping("/currency/{currencyName}")
    public List<EntryDto> getEntriesByCurrency(@PathVariable String currencyName) {
        CryptoCurrency cryptoCurrency = cryptoCurrencyService
                .mapToCurrency(cryptoCurrencyService
                        .getCryptoCurrency(currencyName));
        return entryService.getEntriesByCurrency(cryptoCurrency);
    }


    @PostMapping
    @ResponseBody
    public List<EntryDto> createEntry(@RequestBody EntryDto entryDto){
        return entryService.save(entryDto);
    }

    @DeleteMapping("/{reference}")
    public void deleteEntry(@PathVariable Long reference){
        entryService.delete(reference);
    }

    @PutMapping("/{reference}")
    public void updateEntry(@PathVariable Long reference , @RequestBody EntryDto entryDto){
        entryService.update(reference, entryDto);
    }

}
