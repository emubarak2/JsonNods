package bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChildObj {

    @JsonProperty
    private String type = "Type D";
    @JsonProperty
    private List<String> childArr = new ArrayList<>();
}
