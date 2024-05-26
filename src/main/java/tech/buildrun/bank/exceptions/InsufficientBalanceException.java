package tech.buildrun.bank.exceptions;

import jakarta.validation.constraints.DecimalMin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.math.BigDecimal;

public class InsufficientBalanceException extends BankException {
    @Override
    public ProblemDetail toProblemaDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Insufficient balance");

        pb.setDetail("You cannot transfer a value bigger than your current balance.");

        return pb;
    }
}
