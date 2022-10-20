package com.example.springbooks.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumForm {
    private String name;
    private String author;
    private String newName;
    private String newAuthor;
}
