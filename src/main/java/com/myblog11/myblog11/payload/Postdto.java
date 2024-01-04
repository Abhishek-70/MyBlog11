package com.myblog11.myblog11.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Postdto {
    private long id;

    private String title;

    private String description;

    private String Content;
}
