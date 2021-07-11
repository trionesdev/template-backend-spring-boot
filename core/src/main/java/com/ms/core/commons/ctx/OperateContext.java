package com.ms.core.commons.ctx;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;

@Component
public class OperateContext {
    public Operator getOperator(){
        return OperateContextHolder.getOperator();
    }

    public <T extends Operator> void  setOperator(T operate){
        OperateContextHolder.setOperator(operate,true);
    }

    public OperatorRoleEnum getOperatorRole(){
        Operator operator = getOperator();
        if(Objects.isNull(operator)){
            return null;
        }else {
            return operator.getOperatorRole();
        }
    }

    public Object getOperatorId(){
        Operator operator = getOperator();
        if(Objects.isNull(operator)){
            return null;
        }else {
            return operator.getOperatorId();
        }
    }

    public  <T> T getOperatorId(Class<T> requiredType){
        Operator operator = getOperator();
        if(Objects.isNull(operator)){
            return null;
        }else {
            return requiredType.cast(operator.getOperatorId());
        }
    }

    public Instant getTime(){
        Operator operator = getOperator();
        if(Objects.isNull(operator) || Objects.isNull(operator.getTime())){
            return Instant.now();
        }else {
            return operator.getTime();
        }
    }

    public OperatorRoleEnum getOperateRole(){
        Operator operator = getOperator();
        if(Objects.isNull(operator) || Objects.isNull(operator.getOperatorRole())){
            return null;
        }else {
            return operator.getOperatorRole();
        }
    }

    public String getOperateRoleName(){
        Operator operator = getOperator();
        if(Objects.isNull(operator) || Objects.isNull(operator.getOperatorRole())){
            return null;
        }else {
            return operator.getOperatorRole().name();
        }
    }

    public Long getOperatorIdAsLong(){
        Object operatorId = getOperatorId();
        return Objects.isNull(operatorId)? null : Long.valueOf(String.valueOf(operatorId));
    }

    public String getTimeAsString(){
        Instant date = getTime();
        return Objects.nonNull(date)? String.valueOf(date.getEpochSecond()) : null;
    }

    public boolean oneself(Object userId){
        Object operatorId = getOperatorId();
        if(Objects.isNull(operatorId) || Objects.isNull(userId)){
            return false;
        }
        return Objects.equals(operatorId,userId);
    }

    public boolean hasPermission(Object operateId){
        return oneself(operateId) || Objects.equals(getOperatorRole(), OperatorRoleEnum.ADMIN);
    }

    public void resetOperator(){
        OperateContextHolder.resetOperator();
    }

}

