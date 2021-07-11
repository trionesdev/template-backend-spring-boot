package com.ms.core.commons.ctx;

public class OperateContextHolder {
    private OperateContextHolder() {
    }

    private static final ThreadLocal<Operator> operateHolder = new ThreadLocal<>();

    private static final ThreadLocal<Operator> inheritableOperateHolder = new InheritableThreadLocal<>();

    public static void resetOperator() {
        operateHolder.remove();
        inheritableOperateHolder.remove();
    }

    public static void setOperator(Operator operator) {
        setOperator(operator, false);
    }

    public static void setOperator(Operator operator, boolean inheritable) {
        if (operator == null) {
            resetOperator();
        } else {
            if (inheritable) {
                inheritableOperateHolder.set(operator);
                operateHolder.remove();
            } else {
                operateHolder.set(operator);
                inheritableOperateHolder.remove();
            }
        }
    }

    public static Operator getOperator() {
        Operator operate = operateHolder.get();
        if (operate == null) {
            operate = inheritableOperateHolder.get();
        }
        return operate;
    }



}
