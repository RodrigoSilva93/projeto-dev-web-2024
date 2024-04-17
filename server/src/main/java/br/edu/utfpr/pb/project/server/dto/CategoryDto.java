package br.edu.utfpr.pb.project.server.dto;

import br.edu.utfpr.pb.project.server.enums.CategoryType;
import lombok.Data;

@Data
public class CategoryDto {

    private int id;

    private String name;

    private CategoryType type;
}
