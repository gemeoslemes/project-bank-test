package tech.buildrun.bank.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import tech.buildrun.bank.entity.Transfer;

@FeignClient(
        name = "NotficationClient",
        url = "${client.notification-service.url}"
)
public interface NotificationClient {

    @PostMapping
    ResponseEntity<Void> sendNotification(Transfer transfer);
}
