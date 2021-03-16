package com.example.tracker.services;

import com.example.tracker.dtos.CryptoCurrencyDto;
import com.example.tracker.exceptions.ElementNotFoundException;
import com.example.tracker.models.CryptoCurrency;
import com.example.tracker.repositories.CryptoCurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    public List<CryptoCurrencyDto> getAllCurrencies(){
        return cryptoCurrencyRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public CryptoCurrencyDto getCryptoCurrency(String currencyName){
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findById(currencyName)
                .orElseThrow(()->new ElementNotFoundException("CryptoCurrency not found with id " + currencyName));
        return mapToDto(cryptoCurrency);
    }

    @Transactional
    public void save(CryptoCurrencyDto cryptoCurrencyDto){
        cryptoCurrencyRepository.save(mapToCurrency(cryptoCurrencyDto));
    }

    @Transactional
    public void update(CryptoCurrencyDto cryptoCurrencyDto, String currencyName){
        CryptoCurrency currency = cryptoCurrencyRepository.getOne(currencyName);
        currency.setCurrencyCode(cryptoCurrencyDto.getCurrencyCode());
        currency.setCurrencyName(cryptoCurrencyDto.getCurrencyName());
        cryptoCurrencyRepository.save(currency);
    }

    @Transactional
    public void delete(String currencyName){
      cryptoCurrencyRepository.deleteById(currencyName);
    }


    public CryptoCurrencyDto mapToDto (CryptoCurrency currency){
        return CryptoCurrencyDto.builder()
                .currencyCode(currency.getCurrencyCode())
                .currencyName(currency.getCurrencyName())
                .build();
    }

    public CryptoCurrency mapToCurrency(CryptoCurrencyDto cryptoCurrencyDto){
        return CryptoCurrency.builder()
                .currencyCode(cryptoCurrencyDto.getCurrencyCode())
                .currencyName(cryptoCurrencyDto.getCurrencyName())
                .build();
    }
}
