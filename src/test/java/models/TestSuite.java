package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TestSuite {
    String suiteName;
    String parentSuite;
    String description;
    String preconditions;

}
