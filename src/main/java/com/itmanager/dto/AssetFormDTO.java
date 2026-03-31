package com.itmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssetFormDTO {
    private Long id;
    private String name;
    private String serialNumber;
    private String category;
    private String status;
    private String notes;
}