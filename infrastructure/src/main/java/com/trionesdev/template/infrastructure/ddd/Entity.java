package com.trionesdev.template.infrastructure.ddd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class Entity<ID extends Serializable> {
    private ID id;
}
