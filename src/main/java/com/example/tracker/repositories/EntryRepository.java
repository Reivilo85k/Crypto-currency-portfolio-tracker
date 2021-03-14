package com.example.tracker.repositories;

import com.example.tracker.models.CryptoCurrency;
import com.example.tracker.models.Entry;
import com.example.tracker.models.WalletLocationsEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {


    List<Entry> findByWalletLocation(WalletLocationsEnum walletLocation);
    List<Entry> findByCurrency(CryptoCurrency cryptoCurrency);
}
