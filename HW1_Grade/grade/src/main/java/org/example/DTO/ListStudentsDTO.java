package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.entity.Students;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
public class ListStudentsDTO implements Serializable {
    private List<Students> studentsList;
}
