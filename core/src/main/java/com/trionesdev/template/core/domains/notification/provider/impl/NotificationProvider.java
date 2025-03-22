package com.trionesdev.template.core.domains.notification.provider.impl;

import com.trionesdev.template.core.domains.notification.manager.impl.NotificationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotificationProvider {
    private final NotificationManager notificationManager;

    public Boolean verifySmsValidationCode(String phone, String code) {
        return notificationManager.verifySmsValidationCode(phone, code);
    }

}
