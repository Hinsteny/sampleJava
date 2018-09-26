package com.hinsteny.map;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


/**
 * PracticeMap.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
public class PracticeMap {

	@Test
    public void mapTest(){
        Map countryAndCapitals = new HashMap<>();
        countryAndCapitals.put("US", "Washington");
        //clear
        countryAndCapitals.clear();
        System.out.println("clear then get: "+countryAndCapitals.get("US"));
        
        countryAndCapitals.put("China", "Beijing");
        countryAndCapitals.put("UK", "London");
        countryAndCapitals.put("Japan", "Tokyo");
        countryAndCapitals.put("Australia", "Sydney");
        
        Map map = new HashMap();
        map.put("Australia2", "Sydney2");
        countryAndCapitals.putAll(map);
        System.out.println("");
        
        System.out.println("putIfAbsent: "+countryAndCapitals.putIfAbsent("China", "Ottawa"));
        System.out.println("China:" + countryAndCapitals.get("China"));
        System.out.println("putIfAbsent: "+countryAndCapitals.putIfAbsent("Canada", "Ottawa"));
        System.out.println("Canada:" + countryAndCapitals.get("Canada"));
        System.out.println("");
        
        //entrySet
        System.out.println("entrySet: "+countryAndCapitals.entrySet());
        System.out.println("");
        
        //remove
        System.out.println("remove: "+countryAndCapitals.remove("Canada"));
        System.out.println("remove: "+countryAndCapitals.remove("Australia", "Sydney"));
        System.out.println("");
        
        
        //replace
        System.out.println("replace: "+countryAndCapitals.replace("UK", "London 2"));
        System.out.println("replace get2: "+countryAndCapitals.get("UK"));
        System.out.println("replace: "+countryAndCapitals.replace("UK", "London 2", "London 3"));
        System.out.println("replace get3: "+countryAndCapitals.get("UK"));
        System.out.println("");
        
        
        //getOrDefault
        System.out.println("getOrDefault China: "+countryAndCapitals.getOrDefault("China", "Unknown"));
        System.out.println("getOrDefault Korea: "+countryAndCapitals.getOrDefault("Korea", "Unknown"));
        System.out.println("");
        
        //containsKey/containsValue
        System.out.println("containsKey China: "+countryAndCapitals.containsKey("China"));
        System.out.println("containsKey Korea: "+countryAndCapitals.containsKey("Korea"));
        System.out.println("containsValue Beijing: "+countryAndCapitals.containsValue("Beijing"));
        System.out.println("containsValue Bangkok: "+countryAndCapitals.containsValue("Bangkok"));
        System.out.println("");
        
        
        //equals
        System.out.println("equals: "+countryAndCapitals.equals("Bangkok"));
        System.out.println("");
        
        //hashCode
        System.out.println("hashCode: "+countryAndCapitals.hashCode());
        System.out.println("");
        
        //isEmpty
        System.out.println("isEmpty: "+countryAndCapitals.isEmpty());
        System.out.println("");
        
        //size
        System.out.println("size: "+countryAndCapitals.size());
        System.out.println("");
        
        //keySet
        System.out.println("keySet: "+countryAndCapitals.keySet());
        System.out.println("");
        
        //values
        System.out.println("values: "+countryAndCapitals.values());
        System.out.println("");
        
        //merge
        System.out.println("merge: "+countryAndCapitals.merge("Thailand", "Bangkok", (val,newVal) -> val + "&&"+newVal));
        System.out.println("merge: "+countryAndCapitals.merge("Thailand", "Bangkok", (val,newVal) -> val + "&&"+newVal));
        System.out.println("");
        
        //computeIfAbsent
        System.out.println("computeIfAbsent Japan: "+countryAndCapitals.computeIfAbsent("Japan", (key) -> "NEW:"+key));
        System.out.println("computeIfAbsent Thailand: "+countryAndCapitals.computeIfAbsent("Thailand", (key) -> "NEW:"+key));
        System.out.println("");
        
        //computeIfPrent
        System.out.println("computeIfPresent Japan: "+countryAndCapitals.computeIfPresent("Japan", (key,val) -> val +"*" + key));
        System.out.println("computeIfPresent Thailand: "+countryAndCapitals.computeIfPresent("Thailand", (key,val) -> val +"*" + key));
        System.out.println("");
        
        //compute
        System.out.println("compute Japan: "+countryAndCapitals.compute("Japan", (key,val) -> val +"*" + key));
        System.out.println("compute Thailand: "+countryAndCapitals.compute("Thailand", (key,val) -> val +"*" + key));
        System.out.println("");
        
    }
}
