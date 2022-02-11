package utilities;

import POJO.Pojo_JsonBin;
import POJO.Record;

import java.util.HashMap;
import java.util.Map;

public class ApiUtils {

    public Pojo_JsonBin createJson(String str){
        Record record = new Record(str);
        Pojo_JsonBin pojo = new Pojo_JsonBin(record);
        return pojo;
    }

    public Map createMap(String str){
        Map<String, Object> map = new HashMap<>();

        Map<String, Object> record = new HashMap<>();


        record.put("sample", str);

        map.put("record", record);
        return map;
    }


}
