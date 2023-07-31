package com.alperensertoglu.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@SuperBuilder // bir sınıftan nesne türetmeyi sağlar.
@MappedSuperclass
@Data //get set metodlarını otomatik tanımlar.
@NoArgsConstructor //boş constructor oluşturur.
@AllArgsConstructor //dolu constructor oluşturur.
public class BaseEntity {

    Long createat;
    Long updateat;
    boolean state;
}