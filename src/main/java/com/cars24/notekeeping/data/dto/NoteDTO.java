package com.cars24.notekeeping.data.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {
    private Long id;
    private String title;
    private String content;
}
