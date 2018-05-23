package com.yd.jdk.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author Yd on  2018-05-15
 * @description
 **/
public class MapTest {
    Map map = new HashMap();
    HashSet hashSet = new HashSet();

    ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();

}
