package tech.buildrun.bank.services;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.buildrun.bank.dto.CreateWalletDto;
import tech.buildrun.bank.entity.Wallet;
import tech.buildrun.bank.exceptions.WalletDataAlreadyExistsException;
import tech.buildrun.bank.repository.WalletRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);


    public WalletService(WalletRepository repository) {
        this.walletRepository = repository;
    }

    public Wallet createWallet(CreateWalletDto dto) {
        var walletDto = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (walletDto.isPresent()) {
            logger.debug("Registro duplicado encontrado: CPF/CNPJ: {}, E-mail: {}", dto.cpfCnpj(), dto.email());
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");

        }

        logger.debug("Criando novo registro: CPF/CNPJ: {}, E-mail: {}", dto.cpfCnpj(), dto.email());
        return walletRepository.save(dto.toWallet());
    }
}
