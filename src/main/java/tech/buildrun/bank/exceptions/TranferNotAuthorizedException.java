package tech.buildrun.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TranferNotAuthorizedException extends BankException {

    @Override
    public ProblemDetail toProblemaDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Transfer not authorized");
        pb.setDetail("Authorization service not authorization this transfer.");

        return pb;
    }
}
