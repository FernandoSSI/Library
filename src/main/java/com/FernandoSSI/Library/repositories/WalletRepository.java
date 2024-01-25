package com.FernandoSSI.Library.repositories;

import com.FernandoSSI.Library.domain.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepository extends MongoRepository<String, Wallet> {


}
