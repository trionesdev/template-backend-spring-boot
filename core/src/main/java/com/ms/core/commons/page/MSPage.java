package com.ms.core.commons.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MSPage<T> implements Serializable {
    private int pageNum;
    private int pageSize;
    private long total;
    private int pages;
    private List<T> rows;
}
