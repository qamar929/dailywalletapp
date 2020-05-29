package com.qamar.walletapp.service;


import com.qamar.walletapp.entity.Wallet;
import com.qamar.walletapp.exception.WalletException;
import com.qamar.walletapp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> getAll()
    {

        return walletRepository.findAllByOrderByPriority();
    }

    public  Wallet getById(Long id)
    {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if(wallet.isPresent())
        {
        return wallet.get();
        }

        throw  new WalletException("Wallet with" + id + "does not exists!");

    }
    public Wallet creat(Wallet wallet)
    {

            walletRepository.save(wallet);

return wallet;
    }

    public Wallet udate(Wallet wallet)
    {

        walletRepository.save(wallet);
return wallet;

    }

    public  boolean delete(Long id)
    {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if(wallet.isPresent())
        {
            walletRepository.delete(wallet.get());
            return true;
        }

        throw  new WalletException("Wallet with" + id + "does not exists!");

    }



}
