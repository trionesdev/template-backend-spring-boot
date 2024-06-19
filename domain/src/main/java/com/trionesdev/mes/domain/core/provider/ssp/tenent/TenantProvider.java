package com.trionesdev.mes.domain.core.provider.ssp.tenent;

import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;

import java.util.Collection;
import java.util.List;

public interface TenantProvider {

    List<TenantMemberDTO> getMembersByMemberIds(Collection<String> memberIds);

}
