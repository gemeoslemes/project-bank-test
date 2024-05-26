package tech.buildrun.bank.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.buildrun.bank.dto.TransferDto;
import tech.buildrun.bank.entity.Transfer;
import tech.buildrun.bank.entity.Wallet;
import tech.buildrun.bank.exceptions.InsufficientBalanceException;
import tech.buildrun.bank.exceptions.TranferNotAuthorizedException;
import tech.buildrun.bank.exceptions.TransferNotAllowedWalletTypeException;
import tech.buildrun.bank.exceptions.WalletNotFoundException;
import tech.buildrun.bank.repository.TransferRepository;
import tech.buildrun.bank.repository.WalletRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository,
                           AuthorizationService authorizationService,
                           NotificationService notificationService,
                           WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDto transferDto) {

        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {

        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedWalletTypeException();
        }

        if(!sender.isBalancerEqualsOrGeaterThan(transferDto.value())) {
            throw new InsufficientBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDto)) {
            throw new TranferNotAuthorizedException();
        }
    }
}
