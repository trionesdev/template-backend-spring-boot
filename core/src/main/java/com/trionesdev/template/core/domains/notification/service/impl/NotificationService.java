package com.trionesdev.template.core.domains.notification.service.impl;

import com.trionesdev.mes.core.domains.notification.manager.impl.NotificationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationManager notificationManager;

    public void sendSmsValidationCode(String phone) {
        notificationManager.sendSmsValidationCode(phone);
    }
}
