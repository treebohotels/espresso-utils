package com.treebo.espressocommons.datautilities;

import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.*;

/**
 * Created by bharatbudhani on 14/02/17.
 */

public class YamlUtils {

    /*
       readYamlData() reads data from .yml file and return the data as List<LinkedHashMap<Object,Object>>
       example ip => com.treebo.bumblebee.debug.test.R.raw.user;
       Where user is name of file (user.yml)
     */
    private String TAG = "Load YAML data";

    public List<LinkedHashMap<Object,Object>> readYamlData(int ip) {
        Log.i(TAG,"Reading data from yaml: " + ip);
        InputStream ins = InstrumentationRegistry.getInstrumentation().getContext().getResources().openRawResource(ip);
        int counter = 0;
        Yaml yaml = new Yaml();
        List<LinkedHashMap<Object,Object>> list= new ArrayList<LinkedHashMap<Object,Object>>();
        for (Object data : yaml.loadAll(ins)) {
            counter++;
            list.add((LinkedHashMap<Object,Object>)data);
        }
        return list;
    }

    /*
     readYamlRecord() reads data from .yml file and return single record as LinkedHashMap<Object,Object>
     example ip => com.treebo.bumblebee.debug.test.R.raw.user;
     Where user is name of file (user.yml)
     recordno => index of record which is required from yml file
     */
    public LinkedHashMap<Object,Object> readYamlRecord(int ip, int recordNo){
        List<LinkedHashMap<Object,Object>> list= readYamlData(ip);
        Log.i(TAG+ip,"Data loaded from Yaml File");
        LinkedHashMap<Object,Object> lhm = (LinkedHashMap<Object,Object>) list.get(recordNo);
        Log.i(TAG+ip,"Data loaded for Record: " + recordNo);
        return lhm;
    }
}
