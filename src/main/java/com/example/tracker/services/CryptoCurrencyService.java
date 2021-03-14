package com.example.tracker.services;

import com.example.tracker.dtos.CryptoCurrencyDto;
import com.example.tracker.exceptions.ElementNotFoundException;
import com.example.tracker.models.CryptoCurrency;
import com.example.tracker.repositories.CryptoCurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
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
    public CryptoCurrencyDto getCryptoCurrency(Long id){
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findById(id)
                .orElseThrow(()->new ElementNotFoundException("CryptoCurrency not find with id" + id));
        return mapToDto(cryptoCurrency);
    }

    @Transactional
    public void save(CryptoCurrencyDto cryptoCurrencyDto){
        cryptoCurrencyRepository.save(mapToCurrency(cryptoCurrencyDto));
    }

    @Transactional
    public void update(CryptoCurrencyDto cryptoCurrencyDto, Long id){
        CryptoCurrency currency = cryptoCurrencyRepository.getOne(id);
        currency.setCurrencyCode(cryptoCurrencyDto.getCurrencyCode());
        currency.setCurrencyName(cryptoCurrencyDto.getCurrencyName());
        cryptoCurrencyRepository.save(currency);
    }

    @Transactional
    public void delete(Long id){
      cryptoCurrencyRepository.deleteById(id);
    }


    private CryptoCurrencyDto mapToDto (CryptoCurrency currency){
        return CryptoCurrencyDto.builder()
                .Id(currency.getId())
                .currencyCode(currency.getCurrencyCode())
                .currencyName(currency.getCurrencyName())
                .build();
    }

    private CryptoCurrency mapToCurrency(CryptoCurrencyDto cryptoCurrencyDto){
        return CryptoCurrency.builder()
                .currencyCode(cryptoCurrencyDto.getCurrencyCode())
                .currencyName(cryptoCurrencyDto.getCurrencyName())
                .build();
    }
}
