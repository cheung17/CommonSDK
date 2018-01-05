package com.commonsdk.util;//package com.commonsdk.util;
//
//import org.dom4j.Document;
//import org.dom4j.Element;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
///**
// * XML解析工具类
// * 调用示例代码：
// * <ul>
// * <li>Document doc = DocumentHelper.parseText(xmlcontent);</li>
// * <li>Map<String, Object> map = XmlUtils.dom2Map(doc);</li>
// * </ul>
// *
// * @author ztx
// */
//public class XmlUtils {
//    /**
//     * 将所有节点以HashMap的形式返回，如果在同级含有多个相同名字的节点，将会被覆盖，请使用：dom2Array。
//     *
//     * @param doc XML文件对象
//     * @return 节点键值对
//     */
//    public static Map<String, Object> dom2Map(Document doc) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        if (doc == null) {
//            return map;
//        }
//        Element root = doc.getRootElement();
//        for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
//            Element e = (Element) iterator.next();
//            // System.out.println(e.getName());
//            List list = e.elements();
//            if (list.size() > 0) {
//                map.put(e.getName(), dom2Map(e));
//            } else {
//                map.put(e.getName(), e.getText());
//            }
//        }
//        return map;
//    }
//
//    /**
//     * 将所有节点以Array的形式返回。
//     *
//     * @param doc XML文件对象
//     * @return 节点键值对列表
//     */
//    public static ArrayList<HashMap<String, String>> dom2Array(Document doc) {
//        ArrayList<HashMap<String, String>> listResult = new ArrayList<>();
//        if (doc == null) {
//            return listResult;
//        }
//        Element root = doc.getRootElement();
//        for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
//            Element e = (Element) iterator.next();
//            listResult.add((HashMap<String, String>) dom2Map(e));
//        }
//        return listResult;
//    }
//
//    /**
//     * 将XML中的节点解析成HashMap。
//     *
//     * @param e XML节点对象
//     * @return 节点键值对
//     */
//    public static Map dom2Map(Element e) {
//        Map map = new HashMap();
//        List list = e.elements();
//        if (list.size() > 0) {
//            for (int i = 0; i < list.size(); i++) {
//                Element iter = (Element) list.get(i);
//                List mapList = new ArrayList();
//
//                if (iter.elements().size() > 0) {
//                    Map m = dom2Map(iter);
//                    if (map.get(iter.getName()) != null) {
//                        Object obj = map.get(iter.getName());
//                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
//                            mapList = new ArrayList();
//                            mapList.add(obj);
//                            mapList.add(m);
//                        }
//                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
//                            mapList = (List) obj;
//                            mapList.add(m);
//                        }
//                        map.put(iter.getName(), mapList);
//                    } else {
//                        map.put(iter.getName(), m);
//                    }
//                } else {
//                    if (map.get(iter.getName()) != null) {
//                        Object obj = map.get(iter.getName());
//                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
//                            mapList = new ArrayList();
//                            mapList.add(obj);
//                            mapList.add(iter.getText());
//                        }
//                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
//                            mapList = (List) obj;
//                            mapList.add(iter.getText());
//                        }
//                        map.put(iter.getName(), mapList);
//                    } else {
//                        map.put(iter.getName(), iter.getText());
//                    }
//                }
//            }
//        } else {
//            map.put(e.getName(), e.getText());
//        }
//        return map;
//    }
//}
