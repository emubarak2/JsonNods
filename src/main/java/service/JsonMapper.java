package service;

import bean.PositionHolder;

import lombok.extern.java.Log;
import tree.Nod;
import utility.FileUtility;

import java.util.HashMap;
import java.util.Map;


@Log
public class JsonMapper {
    private static Nod nod = new Nod("", "", null);
    private static Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {

        JsonMapper mapper = new JsonMapper();
        String jsonString = FileUtility.convertStreamToString("sample.json");
        mapper.buildTree(0, jsonString, nod);
        mapper.fillMap(nod, "","type");
        System.out.println(map);
    }


    public void fillMap(Nod nod, String parentString, String attribute) {
        if (nod == null)
            return;

        for (int i = 0; i < nod.getNodList().size(); i++) {
            StringBuilder s = new StringBuilder();
            s.append(parentString);
            s.append(nod.getNameString(i));
            Nod currentNod = nod.getNodList().get(i);
            fillMap(currentNod, s.toString(), attribute);
            s.append(currentNod.getName());
            if (currentNod.getName().equals(attribute)) {
                map.put(s.toString(), currentNod.getValue());
            }
        }
    }

    public PositionHolder buildTree(int k, String s, Nod nod) {

        for (int i = k; i < s.length(); i++) {
            char c = s.charAt(i);
            Nod inner = new Nod();

            if (c == '}')
                return new PositionHolder(i, nod.getParent());

            if (c == '{') {
                PositionHolder key = getKey(i + 1, s, inner);
                i = key.getIndex();
                inner.setName(key.getText().trim());
                PositionHolder value = getValue(i + 1, s, inner);
                i = value.getIndex() - 1;
                inner.setValue(value.getText().trim());
                inner.setParent(nod);
                nod.getNodList().add(inner);
                nod = inner;

            } else if (c == ',') {
                PositionHolder key = getKey(i + 1, s, nod);
                i = key.getIndex();
                if (!key.getText().equals("")) {
                    inner.setName(key.getText().trim());
                    nod.getParent().getNodList().add(inner);
                    inner.setParent(nod.getParent());
                    PositionHolder value = getValue(i, s, inner);
                    inner.setValue(value.getText().trim());
                    i = value.getIndex();
                    nod = inner;
                } else {
                    i = key.getIndex();
                }
            }
        }
        return null;
    }

    public PositionHolder getValue(int k, String s, Nod nod) {

        StringBuilder sb = new StringBuilder();
        for (int i = k; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '\n')
                continue;

            if (c == '[')
                nod.setArray(true);

            if (c == '{') {
                return buildTree(i, s, nod);
            } else if (c == ',' || c == '}') {
                return new PositionHolder(i, sb.toString().replace("\"", "").replace(":", ""), nod);
            } else {
                sb.append(c);
            }
        }
        return null;
    }


    public PositionHolder getKey(int k, String s, Nod nod) {

        boolean f = false;
        StringBuilder sb = new StringBuilder();
        for (int i = k; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '[')
                nod.getParent().setArray(true);

            if (c == '\n')
                continue;

            if (c == '{') {
                return buildTree(i, s, nod);
            } else if (c == '"' && f) {
                return new PositionHolder(i, sb.toString().replace("\"", ""));
            } else {
                if (c == '"')
                    f = true;

                sb.append(c);
            }
        }
        return null;
    }

}
