package com.example.LMS.RequestDto;

import lombok.Data;

@Data
public class UpdateNamePenNameDto {
    private Integer author_id;
    private String newName;
    private String newPenName;
}
