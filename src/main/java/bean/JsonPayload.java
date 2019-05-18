package bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class JsonPayload {

    @JsonProperty
    private String type = "Type A";
    @JsonProperty("array")
    private List<Object> arrBeans = new ArrayList<>();
    @JsonProperty
    private Obj obj = new Obj();

    public JsonPayload() {
        arrBeans.add("");
        arrBeans.add("");
        arrBeans.add("");

    }



}
