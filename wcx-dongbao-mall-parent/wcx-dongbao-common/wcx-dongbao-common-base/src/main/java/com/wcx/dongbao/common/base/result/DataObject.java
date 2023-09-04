package com.wcx.dongbao.common.base.result;

import javax.xml.crypto.Data;
import java.util.HashMap;

public class DataObject {
    private HashMap<String,Object> data;
    public DataObject(){
        data=new HashMap<>();
    }

    public DataObject data(String key,Object value){
        data.put(key,value);
        return this;
    }

    public static HashMap<String,Object> token(String token){
        HashMap<String,Object> map=new HashMap<>();
        map.put("token",token);
        return map;
    }

    public HashMap<String,Object> build(){
        return data;
    }

}
