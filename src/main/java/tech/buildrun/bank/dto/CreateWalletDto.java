package tech.buildrun.bank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tech.buildrun.bank.entity.Wallet;
import tech.buildrun.bank.entity.WalletType;

public record CreateWalletDto(@NotBlank String fullName,
                              @NotBlank String cpfCnpj,
                              @NotBlank String email,
                              @NotBlank String password,
                              @NotNull WalletType.Enum walletType
                                ) {
    public Wallet toWallet() {
        return new Wallet(
                fullName,
                cpfCnpj,
                password,
                email,
                walletType.get()
        );
    }
}
