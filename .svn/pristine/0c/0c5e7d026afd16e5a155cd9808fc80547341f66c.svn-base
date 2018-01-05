package com.commonsdk.util;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import java.util.Set;

/**
 * Created by yjzx13 on 2016/12/22.
 */
public class ModeUtil {

    public static String getJsonStr(HashMap<String, String> map) {
        JSONObject mJSONObject = new JSONObject();
        String jsonStr = "";
        try {
            //获取map集合中的所有键的Set集合
            Set<String> keySet = map.keySet();
            //有了Set集合就可以获取其迭代器，取值

            Iterator<String> it = keySet.iterator();
            while (it.hasNext()) {
                String key = it.next();
                String values = map.get(key);
                mJSONObject.put(key, values);
            }

            jsonStr = mJSONObject.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

}
