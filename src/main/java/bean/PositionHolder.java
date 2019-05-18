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
    private String text ="";
    private Nod nod = new Nod();

    public PositionHolder(int index, String text) {
        this.index = index;
        this.text = text;
    }

    public PositionHolder(int index, Nod nod) {
        this.index = index;
        this.nod = nod;
    }
}

