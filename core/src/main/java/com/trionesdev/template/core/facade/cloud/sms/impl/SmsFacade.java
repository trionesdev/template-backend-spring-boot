package com.trionesdev.template.core.facade.cloud.sms.impl;

import com.trionesdev.csi.api.sms.SmsTemplate;
import com.trionesdev.csi.api.sms.SmsVariable;
import com.trionesdev.csi.api.sms.request.SmsSendRequest;
import com.trionesdev.template.core.facade.cloud.sms.dto.SendSmsCmd;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

@RequiredArgsConstructor
@Component
public class SmsFacade {
    private final ObjectProvider<SmsTemplate> smsTemplate;

    public void sendSms(SendSmsCmd sendSmsCmd) {
        List<SmsVariable> smsParams = new ArrayList<>();
        if (MapUtils.isNotEmpty(sendSmsCmd.getParams())) {
            List<Entry<String, String>> entries = new ArrayList<>(sendSmsCmd.getParams().entrySet());
            for (int i = 0; i < entries.size(); i++) {
                smsParams.add(SmsVariable.builder().key(entries.get(i).getKey()).value(entries.get(i).getValue()).index(i).build());
            }
        }
        SmsTemplate sms = smsTemplate.getObject();
        SmsSendRequest sendRequest = SmsSendRequest.builder().templateCode(sms.template(sendSmsCmd.getTemplateCode())).phoneNumbers(sendSmsCmd.getPhone()).variables(smsParams).build();
        sms.send(sendRequest);
    }

}
