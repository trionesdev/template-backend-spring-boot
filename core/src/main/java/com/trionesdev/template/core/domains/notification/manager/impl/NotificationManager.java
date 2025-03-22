package com.trionesdev.template.core.domains.notification.manager.impl;

import cn.hutool.core.util.RandomUtil;
import com.trionesdev.template.core.facade.cloud.sms.dto.SendSmsCmd;
import com.trionesdev.template.core.facade.cloud.sms.impl.SmsFacade;
import com.trionesdev.spring.cache.CacheFacade;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class NotificationManager {
    private final CacheFacade<String, String> cacheFacade;
    private final SmsFacade smsFacade;

    public String generateSmsValidationCode(String phone) {
        var randomNumber = RandomUtil.randomNumbers(6);
        cacheFacade.set(smsCaptchaKey(phone), randomNumber, Duration.ofMinutes(15));
        return randomNumber;
    }

    public void sendSmsValidationCode(String phone) {
        var randomNumber = generateSmsValidationCode(phone);
        Map<String, String> params = new HashMap<>();
        params.put("code", randomNumber);
        smsFacade.sendSms(SendSmsCmd.builder().params(params).phone(List.of(phone)).templateCode("validationCode").build());
    }

    public Boolean verifySmsValidationCode(String phone, String captcha) {
        var captchaSnap = cacheFacade.get(smsCaptchaKey(phone));
        return StringUtils.isNotBlank(captchaSnap) && Objects.equals(captcha, captchaSnap);
    }

    private String smsCaptchaKey(String phone) {
        return "sms:validationCode:" + phone;
    }

}
