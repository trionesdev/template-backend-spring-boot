package com.ms.rest.conf.security;

import com.google.common.collect.Lists;
import com.ms.core.commons.ctx.OperateContext;
import com.ms.core.commons.ctx.Operator;
import com.ms.core.commons.ctx.OperatorRoleEnum;
import com.ms.rest.conf.security.jwt.JWTFacade;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import static com.ms.rest.conf.security.jwt.ClaimsKeyConstant.OPERATOR_ID;
import static com.ms.rest.conf.security.jwt.ClaimsKeyConstant.OPERATOR_ROLE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTFacade jwtFacade;
    private final OperateContext operateContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Operator operator = new Operator();
        operator.setTime(Instant.now());
        String authorization = request.getHeader(AUTHORIZATION);
        if (StringUtils.isNotBlank(authorization)) {
            authorization = authorization.replace("Bearer ", "");
            Claims claims = jwtFacade.parse(authorization);
            if (Objects.nonNull(claims)) {
                Long operatorId = claims.get(OPERATOR_ID, Long.class);
                String role = claims.get(OPERATOR_ROLE, String.class);
                if (Objects.nonNull(operatorId) && Objects.nonNull(role)) {
                    operator.setOperatorId(operatorId);
                    operator.setOperatorRole(OperatorRoleEnum.getByName(role));
                    JWTUserDetails userDetails =
                            JWTUserDetails.builder().token(authorization).operateId(operatorId).role(role)
                                    .build();
                    List<SimpleGrantedAuthority> authorities = Lists.newArrayList(new SimpleGrantedAuthority(role));
                    Authentication authentication =
                            new JWTAuthenticationToken(userDetails.getOperateId(), userDetails, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        operateContext.setOperator(operator);
        filterChain.doFilter(request, response);
        operateContext.resetOperator();
    }
}
