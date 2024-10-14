package com.erp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bookname;
    private String authorname;
    private long bookprice;
    private String bookgenre;
    private String avalability;
    private long avalabilitycount;
    private long discountprice;
    private String custname;
    private String custemail;
    private String custphoneNumber;
    private long bookspurchased;
    private String salesperson;
    private long salesid;

}
