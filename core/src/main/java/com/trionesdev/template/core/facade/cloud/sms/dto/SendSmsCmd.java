package com.trionesdev.template.core.facade.cloud.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SendSmsCmd {
    private List<String> phone;
    private Map<String, String> params;
    private String templateCode;
}
