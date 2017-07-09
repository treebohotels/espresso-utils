package com.treebo.espressocommons.datautilities;

import android.support.test.InstrumentationRegistry;
import android.util.Log;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by kishorepolisetty on 25/02/17.
 */

public class PropertiesUtils {
    private static Properties props = null;
    private static String TAG = "Load Properties";

    /**
     * Look up a property from the properties file.
     *
     * @param key The name of the property to be found
     * @return The value of the property
     */
    public static String getProperty(String key) {
        String value = null;
        try {
            value = props.getProperty(key);
            Log.i(TAG, "Property key: " + key + "   &&&&   Property Value: " + value);
        } catch (Exception IOException) {
            Log.e(TAG, "Unable to find key: " + key, IOException);
        }
        return value;
    }

    /**
     * Load properties for the given input file
     * <p>
     * If properties already loaded, appends new properties with the existing properties
     */
    public static Properties loadProperties(int rawId) {
        try {
            InputStream ins = InstrumentationRegistry.getInstrumentation().getContext().getResources().openRawResource(rawId);
            if (props == null) {
                props = new Properties();
                props.load(ins);
            } else {
                Properties properties = new Properties();
                properties.load(ins);
                props.putAll(properties);
                properties = null;
            }
            Log.i(TAG + rawId, "Loaded Properties file");
        } catch (Throwable e) {
            Log.e(TAG, "Unable to load Properties file", e);
            e.printStackTrace();
        }
        return props;
    }
}
