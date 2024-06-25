package com.trionesdev.mes.infrastructure.ddd;

import java.io.Serializable;

public interface BaseRepository<T extends AggregateRoot<ID>, ID extends Serializable> {
    
}
