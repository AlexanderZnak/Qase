package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TestSuite {
    @SerializedName("title")
    @Expose
    String suiteName;
    String parentSuite;
    @Expose
    String description;
    @Expose
    String preconditions;
    @SerializedName("parent_id")
    @Expose
    int parentId;
    @SerializedName("cases_count")
    @Expose
    int casesCount;
    int id;
}
