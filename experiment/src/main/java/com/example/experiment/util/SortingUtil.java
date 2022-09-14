package com.example.experiment.util;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class SortingUtil {

    private SortingUtil() { }

    private static final String ASC = "asc";
    private static final String DESC = "desc";

    public static Sort buildSort(String[] sorts) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sorts.length > 1) {
            for (String item : sorts) {
                orders.add(buildSortOrder(item));
            }
        } else {
            orders.add(buildSortOrder(sorts[0]));
        }
        return Sort.by(orders);
    }

    /**
     * Build sort order
     * @param sortOder eg: id:desc,age:asc
     */
    private static Sort.Order buildSortOrder(String sortOder) {
        String[] items = sortOder.split(":");
        return new Sort.Order(getSortDirection(items[1]), items[0]);
    }

    private static Sort.Direction getSortDirection(String sortOrder) {
        if (sortOrder.equals(ASC)) {
            return Sort.Direction.ASC;
        } else if (sortOrder.equals(DESC)) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.DESC;
    }
}
