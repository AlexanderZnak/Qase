package models.api;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class TestSuite {
    @Expose
    String title;
    @Expose
    String description;
    @Expose
    String preconditions;
    int id;

}
