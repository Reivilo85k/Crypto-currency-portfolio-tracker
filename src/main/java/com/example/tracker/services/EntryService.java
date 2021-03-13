package com.example.tracker.services;

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
public class EntryService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    public List<EntryDto> getAllEntries(){
        return null;
    }

    public List<EntryDto> getEntriesByWalletLocation(WalletLocations walletLocation){
        return null;
    }

    public List<EntryDto> getEntriesByCurrency(CryptoCurrency cryptoCurrency){
        return null;
    }

    @Transactional(readOnly = true)
    public List<EntryDto> getEntry(Long id){
        return null;
    }

    @Transactional
    public List<EntryDto> save(Long id){
        return null;
    }

    @Transactional
    public List<EntryDto> update(Long id){
        return null;
    }

    @Transactional
    public List<EntryDto> delete(Long id){
        return null;
    }



    private EntryDto mapToDto (Entry entry){
      return null;
    }

    private Entry mapToEntry(EntryDto entryDto){
        return null;
    }

}
