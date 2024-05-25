package tech.buildrun.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.buildrun.bank.entity.WalletType;

@Repository
public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
