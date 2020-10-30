package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TestRun {
    String runTitle;
    String description;
    String plan;
    String environment;
    String milestone;
    String defaultAssignee;
}
