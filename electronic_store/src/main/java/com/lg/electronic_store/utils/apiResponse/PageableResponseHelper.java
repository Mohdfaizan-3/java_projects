package com.lg.electronic_store.utils.apiResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PageableResponseHelper<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private boolean lastPage;

    public static <U, V> PageableResponseHelper<V> getPageableResponse(Page<U> pages, Class<V> type) {
        List<U> entityList = pages.getContent();
        ModelMapper modelMapper = new ModelMapper();

        List<V> dtoList = entityList.stream()
                .map(entity -> modelMapper.map(entity, type))
                .collect(Collectors.toList());

        PageableResponseHelper<V> pagableResponse = new PageableResponseHelper<>();
        pagableResponse.setContent(dtoList);
        pagableResponse.setPageNumber(pages.getNumber() + 1);
        pagableResponse.setPageSize(pages.getSize());
        pagableResponse.setTotalElements(pages.getTotalElements());
        pagableResponse.setLastPage(pages.isLast());

        return pagableResponse;
    }
}
