package com.doruk.presentation.dto;

import com.doruk.application.dto.PageQuery;
import com.doruk.application.enums.SortOrder;

public class PageQueryMapper {
    public static PageQuery toQuery(final PageQueryRequest pageQueryRequest) {
        int page = pageQueryRequest.page() == null ? 0 : pageQueryRequest.page() - 1;
        int size = pageQueryRequest.size() == null ? 10 : pageQueryRequest.size();
        SortOrder order = pageQueryRequest.order() == null ? SortOrder.DESC : pageQueryRequest.order();
        return new PageQuery(page, size, order);
    }
}
