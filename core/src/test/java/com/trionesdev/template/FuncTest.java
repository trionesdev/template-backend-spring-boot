package com.trionesdev.template;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FuncTest {

    @Test
    public void ss(){
        System.out.println(IdWorker.getIdStr());
    }

    @Test
    public void list_union(){
        var a = List.of(1,2,3);
        System.out.println(ListUtils.union(a,List.of(4)));
    }

}
