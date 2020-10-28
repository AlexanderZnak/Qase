package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TestProject {
    String projectName;
    String projectCode;
    String description;
}
