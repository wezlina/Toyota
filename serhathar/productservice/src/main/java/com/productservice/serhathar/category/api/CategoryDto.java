package com.productservice.serhathar.category.api;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CategoryDto {

    private String id;
    private String name;
    private String description;
    private Date creaDate;
    private Boolean status;
}