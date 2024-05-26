package tech.buildrun.bank.services;

import org.springframework.stereotype.Service;
import tech.buildrun.bank.client.AuthorizationClient;
import tech.buildrun.bank.dto.TransferDto;
import tech.buildrun.bank.exceptions.BankException;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transfer) {
        var resp = authorizationClient.isAuthorized();

        if(resp.getStatusCode().isError()) {
            throw  new BankException();
        }

        return resp.getBody().authorized();

    }
}
