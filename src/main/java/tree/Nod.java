package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Nod {
    private String name = "";
    private String value = "";
    private boolean isArray=false;
    private List<Nod> nodList = new ArrayList<>();
    private Nod parent;
    
    public Nod(String name, String value, Nod parent) {
        this.name = name;
        this.value = value;
        this.parent = parent;
    }
}
