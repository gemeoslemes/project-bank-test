package tech.buildrun.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.buildrun.bank.entity.Wallet;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT w FROM Wallet w WHERE w.cpfCnpj = :cpfCnpj OR w.email = :email")
    Optional<Wallet> findByCpfCnpjOrEmail(String cpfCnpj, String email);
}
