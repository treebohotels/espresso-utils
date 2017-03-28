package com.treebo.espressocommons.configurations;

import android.util.Log;

import com.treebo.espressocommons.android.AysncTaskSchedulerRule;
import com.treebo.espressocommons.datautilities.PropertiesUtils;

import org.junit.BeforeClass;
import org.junit.Rule;

import java.io.File;
import java.util.Properties;

/**
 * Created by kishorepolisetty on 27/02/17.
 */


public class BaseTest {

    @Rule
    public AysncTaskSchedulerRule aysncTaskSchedulerRule = new AysncTaskSchedulerRule();

    public static Properties configProps = null;

    //private static String sdkPath = PropertiesUtils.getProperty("sdkPath");
    //private static String adbPath = sdkPath + PropertiesUtils.getProperty("adbPath");
    //private static String emulatorPath = sdkPath + PropertiesUtils.getProperty("emulatorPath");

    /**
     * Starts an emulator for the provided AVD name
     * Load Configuration properties
     **/
    @BeforeClass
    public static void initTestRun(){
        //loadConfigs();
        //launchEmulator();
    }

    public static void launchEmulator() {
        /*String TAG = "Emulator Status";
        String nameOfAVD = PropertiesUtils.getProperty("avdName");

        Log.i(TAG,"Starting emulator for '" + nameOfAVD + "' ...");

        String[] aCommand = new String[] { emulatorPath, "-avd", nameOfAVD };
        try {
            Process process = new ProcessBuilder(aCommand).start();
            process.wait(180000);
            //process.waitFor(180, TimeUnit.SECONDS);
            Log.i(TAG,"Emulator launched successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG,"Emulator failed to launch");
        }*/
    }

    /*public static Properties loadConfigs(){
        configProps = PropertiesUtils.loadProperties(DataFiles.ConfigPropertiesFile);
        return configProps;
    }*/
}
