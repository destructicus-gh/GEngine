package com.ryan.gengine.Version1.exception;

/**
 * Created by a689638 on 7/10/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class WrongHoldableException extends IllegalArgumentException{
    public WrongHoldableException(String message){
        super(message);
    }
}
