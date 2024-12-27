package com.trionesdev.template.infrastructure.ddd;

import java.io.Serializable;

public interface BaseRepository<T extends AggregateRoot<ID>, ID extends Serializable> {
    
}
