package models.api;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Result {
    @Expose
    TestSuite result;
    boolean status;
}
