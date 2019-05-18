package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
//@Data
public class Nod {
    private String name = "";
    private String value = "";
    private boolean isArray=false;
    private Nod next;
    private List<Nod> nodList = new ArrayList<>();
    private Nod parent;

    public Nod(String name, String value, Nod parent) {
        this.name = name;
        this.value = value;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    public Nod getNext() {
        return next;
    }

    public void setNext(Nod next) {
        this.next = next;
    }

    public List<Nod> getNodList() {
        return nodList;
    }

    public void setNodList(List<Nod> nodList) {
        this.nodList = nodList;
    }

    public Nod getParent() {
        return parent;
    }

    public void setParent(Nod parent) {
        this.parent = parent;
    }
}
