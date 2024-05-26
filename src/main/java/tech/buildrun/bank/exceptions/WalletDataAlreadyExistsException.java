package tech.buildrun.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletDataAlreadyExistsException extends BankException {

    private String detail;

    public WalletDataAlreadyExistsException(String detail) {
        this.detail = detail;
    }
    @Override
    public ProblemDetail toProblemaDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Wallet data already exists");
        pb.setDetail(detail);

        return pb;
    }
}
