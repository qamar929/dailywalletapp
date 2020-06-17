package com.qamar.walletapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Min(1)
    @NotNull(message = "amount cann't be blank")
    private  double amount;

    private  String description;

    @Min(1)
    @Max(3)
    private int type;
@JsonFormat(pattern = "yyyy-mm-dd")
    private Date transactionDate;

@ManyToOne(fetch = FetchType.LAZY)

@JoinColumn(name="wallet_id",nullable = false)
@JsonIgnore
    private  Wallet wallet;
    @PrePersist

    public  void setTransactionDate()
    {
        this.transactionDate= new Date();
    }

}
