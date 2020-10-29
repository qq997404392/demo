package com.oyf.test.util;

import java.util.*;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/9/28
 * @description：ASCII码排序
 */
public class ASCSortUtil {

    /**
     * ASCII码从小到大排序
     *
     * @param param 参数map
     * @return
     */
    public static String sortParamWithASCII(Map param) {
        // 获取keyList
        List<String> sortList = new LinkedList<String>(param.keySet());

        // 参数排序
        Collections.sort(sortList);
        StringBuilder sortStr = new StringBuilder();
        for (Object obj : sortList) {
            StringBuilder paramStr = new StringBuilder();
            // 对list数据排序
            if (param.get(obj) instanceof List) {
                List dataList = (List) param.get(obj);
                if (null != dataList && dataList.size() > 0) {
                    Collections.sort(dataList);
                    for (Object o : dataList) {
                        paramStr.append(o).append(",");
                    }
                    paramStr = new StringBuilder(paramStr.substring(0, paramStr.length() - 1));
                }
                sortStr.append(obj).append("=").append(paramStr).append("&");
            } else {
                sortStr.append(obj).append("=").append(param.get(obj)).append("&");
            }
        }
        sortStr = new StringBuilder(sortStr.substring(0, sortStr.length() - 1));
        return sortStr.toString();
    }

    public static void main(String args[]) {
        Map<String, Object> param = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("t");
        param.put("集合", list1);
        List<String> list2 = new ArrayList<>();
        list2.add("z");
        list2.add("d");
        param.put("合集", list2);
        param.put("hello", "world");
        param.put("aaa", "123");
        param.put("bbb", "456");
        String sortStr = sortParamWithASCII(param);
        System.out.println(sortStr);
    }

}
