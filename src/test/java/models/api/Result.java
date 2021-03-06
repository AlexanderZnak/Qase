package models.api;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;
import models.TestSuite;

@Builder
@Data
public class Result {
    @Expose
    TestSuite result;
    boolean status;
}
