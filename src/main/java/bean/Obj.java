package bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Obj {

    @JsonProperty
    private String type = "Type C";
    @JsonProperty
    private ChildObj childObj = new ChildObj();
}
