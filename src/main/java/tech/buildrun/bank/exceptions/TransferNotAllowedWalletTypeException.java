package tech.buildrun.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedWalletTypeException extends BankException {

    @Override
    public ProblemDetail toProblemaDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("This wallet type is not allowed to transfer.");

        return pb;
    }
}
