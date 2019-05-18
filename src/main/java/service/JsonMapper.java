package service;

import bean.PositionHolder;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import tree.Nod;
import utility.FileUtility;

import java.util.*;

import static com.jayway.jsonpath.JsonPath.using;


@Log
@Slf4j
public class JsonMapper {
    static Nod nod = new Nod("root", "", null);

    static Map<String, String> map = new HashMap<>();
    boolean arrayFlag = false;

    public static void main(String[] args) {
        BasicConfigurator.configure();

        JsonMapper mapper = new JsonMapper();
        String jsonString = FileUtility.convertStreamToString("sample.json");
        mapper.buildTreeNods(0, jsonString, nod);
//        mapper.buildMap(nod);
        System.out.println(map);
    }



//    public Map<String,String> buildMap(Nod nod) {
//        StringBuilder sb = new StringBuilder();
//        List<Nod> outer = nod.getNodList();
//        for (int i =0; i < outer.size(); i++) {
//            List<Nod> inner = outer.get(i).getNodList();
//            sb.append(outer.get(i).getName()).append(".");
//            if ( outer.get(i).getName().equals("type")) {
//                map.put(sb.toString(), outer.get(i).getValue());
//                sb = new StringBuilder();
//            }
//            for ( int k =0; k < inner.size(); k++) {
//                if ( outer.get(i).isArray() ) {
//                    sb.append("[").append(i).append("]");
//                } else {sb.append(outer.get(i).getName());}
//
//                    if ( outer.get(i).getName().equals("type")) {
//                        map.put(sb.toString(), outer.get(i).getValue());
//                    }else { sb.append(inner.get(k).getName()).append(".");}
//                if ( inner.get(k).getName().equals("type")) {
//                    map.put(sb.toString(), inner.get(k).getValue());
//                    sb = new StringBuilder();
//                }
//            }
//
//        }
//        return map;
//
//    }


    public PositionHolder buildTreeNods(int k, String s, Nod nod) {

        for (int i = k; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '}') {
                return new PositionHolder(i, nod.getParent());
            }
            if (c == '{') {
                Nod inner = new Nod();
                PositionHolder pos = getKey(i + 1, s, inner);
                i = pos.getIndex();
                inner.setName(pos.getKey());
                PositionHolder value = getValue(i + 1, s, inner);
                i = value.getIndex()-1;
                inner.setValue(value.getKey());
                inner.setParent(nod);
                nod.getNodList().add(inner);
                nod = inner;



            } else if (c == ',') {
                Nod inner = new Nod();
                PositionHolder key = getKey(i + 1, s, nod);
                i = key.getIndex();
                if ( !key.getKey().equals("")) {
                inner.setName(key.getKey());
                nod.getParent().getNodList().add(inner);
                inner.setParent(nod.getParent());
                PositionHolder value = getValue(i, s, inner);
                inner.setValue(value.getKey());
                i = value.getIndex();
                nod = inner;} else { i = key.getIndex();}

            }


        }

        return null;
    }

    public PositionHolder getValue(int k, String s, Nod nod) {

        StringBuilder sb = new StringBuilder();
        for (int i = k; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\n' || c == ' ') {
                continue;
            }

            if ( c == '[')
                nod.setArray(true);

            if (c == '{') {
                 PositionHolder pos  = buildTreeNods(i, s, nod);
                 return pos;
            } else if (c == ',' || c == '}') {
                return new PositionHolder(i, sb.toString().trim().replace("\"", "").replace(":", ""),nod);
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

            if ( c == '[')
                nod.getParent().setArray(true);

            if (c == '\n' || c == ' ') {
                continue;
            }
            if (c == '{') {
                PositionHolder pos  = buildTreeNods(i, s, nod);
                return pos;
            } else
                if (c == '"' && f) {
                return new PositionHolder(i, sb.toString().replace("\"",""));
            } else {
                if (c == '"')
                    f = true;

                sb.append(c);
            }
        }
        return null;
    }


    public void getTypePaths(String fileName) {

        String jsonString = FileUtility.convertStreamToString(fileName);
        Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
        List<String> pathList = using(conf).parse(jsonString).read("$..type");
        for (String path : pathList) {
            System.out.println(path);
        }

    }

}
