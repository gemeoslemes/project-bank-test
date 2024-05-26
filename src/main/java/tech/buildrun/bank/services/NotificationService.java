package tech.buildrun.bank.services;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.buildrun.bank.client.NotificationClient;
import tech.buildrun.bank.entity.Transfer;

import org.slf4j.Logger;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transfer transfer) {
        try {
            logger.info("Sending notification...");

            var resp = notificationClient.sendNotification(transfer);

            if (resp.getStatusCode().isError()) {
                logger.error("Error while sending notification");
            }
        } catch (Exception e) {
            logger.error("Error while sending notification", e);
        }
    }
}
