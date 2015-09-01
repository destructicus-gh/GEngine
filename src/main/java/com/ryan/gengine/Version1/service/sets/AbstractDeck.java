package com.ryan.gengine.Version1.service.sets;

import com.ryan.gengine.Version1.service.singles.AbstractCard;
import com.ryan.gengine.Version1.service.singles.Holder;

/**
 * Created by a689638 on 7/9/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */

public abstract class AbstractDeck implements Holder{
    public abstract AbstractCard drawCard();
    public abstract boolean isEmpty();
}
