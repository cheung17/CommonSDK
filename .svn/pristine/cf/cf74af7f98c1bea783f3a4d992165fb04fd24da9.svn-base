package com.commonsdk.uri;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * url参数解析类
 *
 * @author ztx
 */
public class UrlParse {
    /**
     * 使用示例：
     private void ParseUrl(String url, HashMap<String, String[]> map) {
     try {
     UrlParse.parseParameters(map, url.substring(url.indexOf("?") + 1), "utf-8");
     } catch (UnsupportedEncodingException e) {
     e.printStackTrace();
     }
     }
     */
    /**
     * 解析url？后面的参数，以key－value的形式返回，value是数组的形式。
     *
     * @param map      携带返回值的map对象
     * @param data     待解析的url？后面的路径。url.substring(url.indexOf("?") + 1)
     * @param encoding 编码
     * @throws UnsupportedEncodingException
     */
    public static void parseParameters(Map map, String data, String encoding) throws UnsupportedEncodingException {
        if ((data == null) || (data.length() <= 0)) {
            return;
        }

        byte[] bytes = null;
        try {
            if (encoding == null) {
                bytes = data.getBytes();
            } else {
                bytes = data.getBytes(encoding);
            }
        } catch (UnsupportedEncodingException uee) {
        }
        parseParameters(map, bytes, encoding);
    }

    /**
     * 解析url？后面的参数，以key－value的形式返回，value是数组的形式。
     *
     * @param map      携带返回值的map对象
     * @param data     待解析的url？后面的路径。url.substring(url.indexOf("?") + 1)
     * @param encoding 编码
     * @throws UnsupportedEncodingException
     */
    private static void parseParameters(Map map, byte[] data, String encoding) throws UnsupportedEncodingException {
        if ((data != null) && (data.length > 0)) {
            int ix = 0;
            int ox = 0;
            String key = null;
            String value = null;
            while (ix < data.length) {
                byte c = data[(ix++)];
                switch ((char) c) {
                    case '&':
                        value = new String(data, 0, ox, encoding);
                        if (key != null) {
                            putMapEntry(map, key, value);
                            key = null;
                        }
                        ox = 0;
                        break;
                    case '=':
                        if (key == null) {
                            key = new String(data, 0, ox, encoding);
                            ox = 0;
                        } else {
                            data[(ox++)] = c;
                        }
                        break;
                    case '+':
                        data[(ox++)] = 32;
                        break;
                    case '%':
                        data[(ox++)] = (byte) ((convertHexDigit(data[(ix++)]) << 4) + convertHexDigit(data[(ix++)]));

                        break;
                    default:
                        data[(ox++)] = c;
                }
            }

            if (key != null) {
                value = new String(data, 0, ox, encoding);
                putMapEntry(map, key, value);
            }
        }
    }

    /**
     * 将url中解析出来的key——value放到map中
     *
     * @param map   容器
     * @param name  key
     * @param value value
     */
    private static void putMapEntry(Map map, String name, String value) {
        String[] newValues = null;
        String[] oldValues = (String[]) (String[]) map.get(name);
        if (oldValues == null) {
            newValues = new String[1];
            newValues[0] = value;
        } else {
            newValues = new String[oldValues.length + 1];
            System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
            newValues[oldValues.length] = value;
        }
        map.put(name, newValues);
    }

    private static byte convertHexDigit(byte b) {
        if ((b >= 48) && (b <= 57)) {
            return (byte) (b - 48);
        }
        if ((b >= 97) && (b <= 102)) {
            return (byte) (b - 97 + 10);
        }
        if ((b >= 65) && (b <= 70)) {
            return (byte) (b - 65 + 10);
        }
        return 0;
    }
}
