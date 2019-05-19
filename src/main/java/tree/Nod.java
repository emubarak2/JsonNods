package tree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Nod {
    private String name = "";
    private String value = "";
    private boolean isArray = false;
    private List<Nod> nodList = new ArrayList<>();
    private Nod parent;

    public Nod(String name, String value, Nod parent) {
        this.name = name;
        this.value = value;
        this.parent = parent;
    }

    public String getNameString(int index) {
        if (this.getName() == null || this.getName().isEmpty()) {
            return "";
        }
        if (this.isArray()) {
            return getName() + "[" + index + "].";
        } else {
            return getName() + ".";
        }

    }
}