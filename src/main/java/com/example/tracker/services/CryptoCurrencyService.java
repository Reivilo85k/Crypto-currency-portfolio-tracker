package com.example.tracker.services;

import com.example.tracker.dtos.CryptoCurrencyDto;
import com.example.tracker.dtos.EntryDto;
import com.example.tracker.models.CryptoCurrency;
import com.example.tracker.models.Entry;
import com.example.tracker.models.WalletLocations;
import com.example.tracker.repositories.CryptoCurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CryptoCurrencyService {

    public List<CryptoCurrencyDto> getAllEntries(){
        return null;
    }

    @Transactional(readOnly = true)
    public List<CryptoCurrencyDto> getEntry(Long id){
        return null;
    }

    @Transactional
    public List<CryptoCurrencyDto> save(Long id){
        return null;
    }

    @Transactional
    public List<CryptoCurrencyDto> update(Long id){
        return null;
    }

    @Transactional
    public List<CryptoCurrencyDto> delete(Long id){
        return null;
    }



    private CryptoCurrencyDto mapToDto (CryptoCurrency currency){
        return null;
    }

    private CryptoCurrency mapToEntry(CryptoCurrencyDto cryptoCurrencyDto){
        return null;
    }
}
