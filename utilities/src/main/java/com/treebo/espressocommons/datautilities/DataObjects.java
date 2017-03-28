package com.treebo.espressocommons.datautilities;

import android.util.Log;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * Created by kishorepolisetty on 24/02/17.
 */

public class DataObjects {
    private YamlUtils yamlUtils = null;
    private LinkedHashMap<Object,Object> lhm = null;
    private HashMap<String, String> dataObj = null;

    /**
    * randomString(<length>) generates random alphabetical string for the given length
    **/
    public String randomString( int len ){
        final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ ){
            sb.append( AB.charAt(rnd.nextInt(AB.length()) ) );
        }
        return sb.toString();
    }

    /**
     * readYaml(<file>, <index>) reads file name and index
     * Reads Yaml files and loads data for the given index in to Hashmap
     * Returns Hashmap<String,String> to readYaml Method
     **/
    public HashMap<String, String> readYaml(int fileName, int recordNo, String... randomStr){
        String TAG = "Read Data Objects";
        dataObj = new HashMap<String, String>();

        if(yamlUtils == null){
            try {
                yamlUtils = new YamlUtils();
                Log.i(TAG, "Loaded Yaml Utils");
            } catch (Exception IOException) {
                Log.e(TAG, "Unable to load Yaml Utils", IOException);
                return null;
            }
        }
        lhm = yamlUtils.readYamlRecord(fileName,recordNo);

        for ( Object key : lhm.keySet() ) {
            dataObj.put(key.toString(),lhm.get(key).toString());
        }
        Log.i(TAG,"Yaml Record:" + recordNo + "   &&&&   Data:" + dataObj);
        return dataObj;
    }
}
