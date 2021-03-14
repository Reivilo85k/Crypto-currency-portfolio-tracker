package com.example.tracker.services;

import com.example.tracker.dtos.EntryDto;
import com.example.tracker.exceptions.ElementNotFoundException;
import com.example.tracker.models.CryptoCurrency;
import com.example.tracker.models.Entry;
import com.example.tracker.models.WalletLocationsEnum;
import com.example.tracker.repositories.CryptoCurrencyRepository;
import com.example.tracker.repositories.EntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;


import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class EntryService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final EntryRepository entryRepository;

    public List<EntryDto> getAllEntries(){
        return entryRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    public List<EntryDto> getEntriesByWalletLocation(WalletLocationsEnum walletLocation){
        return entryRepository.findByWalletLocation(walletLocation)
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    public List<EntryDto> getEntriesByCurrency(CryptoCurrency cryptoCurrency){
        return entryRepository.findByCurrency(cryptoCurrency)
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public EntryDto getEntry(Long reference){
        Entry entry = entryRepository.findById(reference)
                .orElseThrow(()->new ElementNotFoundException("Entry not find with id" + reference));
        return mapToDto(entry);
    }

    @Transactional
    public void save(EntryDto entryDto){
        entryRepository.save(mapToEntry(entryDto));
    }

    @Transactional
    public void update(Long id, EntryDto entryDto){
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findById(entryDto.getCurrencyId())
                .orElseThrow(()->new ElementNotFoundException("CryptoCurrency not find with id" + entryDto.getCurrencyId()));
        Entry entry = entryRepository.getOne(id);
        entry.setCurrency(cryptoCurrency);
        entry.setAmount(entryDto.getAmount());
        entry.setCreationDateTime(Instant.now());
        entry.setWalletLocation(entryDto.getWalletLocation());
        entry.setCurrentEurosMarketValue(entry.getCurrentEurosMarketValue());
        entryRepository.save(entry);
    }

    @Transactional
    public void delete(Long reference){
        entryRepository.deleteById(reference);
    }

    private EntryDto mapToDto (Entry entry){
      return EntryDto.builder().reference(entry.getReference())
              .currencyId(entry.getCurrency().getId())
              .amount(entry.getAmount())
              .creationDateTime(entry.getCreationDateTime())
              .walletLocation(entry.getWalletLocation())
              .currentEurosMarketValue(entry.getCurrentEurosMarketValue())
              .build();
    }

    private Entry mapToEntry(EntryDto entryDto){
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findById(entryDto.getCurrencyId())
                .orElseThrow(()->new ElementNotFoundException("CryptoCurrency not find with id" + entryDto.getCurrencyId()));
        return Entry.builder().currency(cryptoCurrency)
                .amount(entryDto.getAmount())
                .creationDateTime(Instant.now())
                .walletLocation(entryDto.getWalletLocation())
                .currentEurosMarketValue(entryDto.getCurrentEurosMarketValue())
                .build();
    }

}
