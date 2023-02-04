package pl.vectra.HomeworkFK;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Person {

    private final String name;
    private final String second_name;
    private final Long id;

}
