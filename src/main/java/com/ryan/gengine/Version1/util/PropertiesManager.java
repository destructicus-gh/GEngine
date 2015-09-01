package com.ryan.gengine.Version1.util;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by a689638 on 6/18/2015.
 * Copyright (C) 2015 HEB
 * @author Ryan Anders
 * This software is the confidential and proprietary information
 * of HEB
 */
public class PropertiesManager  {
    private static PropertiesConfiguration propertiesConfiguration;
    static{
        try {
            propertiesConfiguration = new PropertiesConfiguration("C:\\Users\\a689638\\IdeaProjects\\GEngine\\src\\main\\java\\resources\\config.properties");
            propertiesConfiguration.setDelimiterParsingDisabled(true);

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }
    public static String getPropertyString(String propertyName){
        return propertiesConfiguration.getString(propertyName);
    }
    public static String[] getPropertyList(String propertyName){
        return propertiesConfiguration.getStringArray(propertyName);

    }
}
