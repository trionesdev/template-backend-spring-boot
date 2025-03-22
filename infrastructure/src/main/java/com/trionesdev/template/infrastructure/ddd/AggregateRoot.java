package com.trionesdev.template.infrastructure.ddd;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class AggregateRoot<ID extends Serializable> extends Entity<ID> implements Serializable {
}
