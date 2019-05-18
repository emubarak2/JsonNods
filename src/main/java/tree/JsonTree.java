package tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class JsonTree {

    List<Nod> nods = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Nod {

        String nodName = "";
        String value = "";
        Nod next;

      public  Nod(String nodName, String value) {
            this.nodName = nodName;
            this.value = value;
        }


    }


}
