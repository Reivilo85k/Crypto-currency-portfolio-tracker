package com.example.tracker.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCurrency {

    @Id
    @NonNull
    private String currencyName;
    @NonNull
    private String currencyCode;

}
