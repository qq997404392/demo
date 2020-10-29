package com.oyf.test.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/9/29
 * @description：分页工具类
 */
public class PageUtil {

    /**
     * 查询后再分页
     * @param list 需要分页的list
     * @param pageable 分页参数
     * @return 分页后的list
     */
    public static <T> Page<T> getPageList(List<T> list, Pageable pageable) {
        List<T> subList;
        int surplusNum = list.size();
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int next = offset + pageable.getPageSize();
        // 最后一页
        if (next >= surplusNum) {
            int startIndex = offset;
            int endIndex = surplusNum;
            if (startIndex > endIndex) {
                subList = Collections.emptyList();
            } else {
                subList = list.subList(startIndex, endIndex);
            }
        } else {
            int startIndex = offset;
            int endIndex = next;
            subList = list.subList(startIndex, endIndex);
        }
        return new PageImpl<>(subList, pageable, surplusNum);
    }

    public static void main(String args[]) {
        int page = 0;
        int size = 5;
        List<String> list = new ArrayList<>();
        Pageable pageable = new PageRequest(page, size);
        getPageList(list, pageable);
    }

}
