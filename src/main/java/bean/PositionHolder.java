package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tree.Nod;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionHolder {

    private int index =0;
    private String key="";
    private Nod nod = new Nod();

    public PositionHolder(int index, String key) {
        this.index = index;
        this.key = key;
    }

    public PositionHolder(int index, Nod nod) {
        this.index = index;
        this.nod = nod;
    }
}

