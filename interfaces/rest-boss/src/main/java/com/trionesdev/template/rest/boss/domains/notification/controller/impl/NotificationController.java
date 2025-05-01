package com.trionesdev.template.rest.boss.domains.notification.controller.impl;

import com.trionesdev.template.core.domains.notification.service.impl.NotificationService;
import com.trionesdev.template.rest.boss.domains.notification.controller.ro.SendSmsValidationCodeRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.trionesdev.template.rest.boss.domains.notification.internal.NotificationConstants.NOTIFICATION_PATH;

@Tag(name = "通知")
@RequiredArgsConstructor
@RestController(value = "bossNotificationController")
@RequestMapping(NOTIFICATION_PATH)
public class NotificationController {
    private final NotificationService notificationService;

    @Operation(summary = "发送短信验证码")
    @PostMapping(value = "sms/validation-code")
    public void sendSmsValidationCode(@Validated @RequestBody SendSmsValidationCodeRO args) {
        notificationService.sendSmsValidationCode(args.getPhone());
    }

}
