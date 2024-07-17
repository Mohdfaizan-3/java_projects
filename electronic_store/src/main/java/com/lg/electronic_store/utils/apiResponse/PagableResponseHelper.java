package com.lg.electronic_store.utils.apiResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PagableResponseHelper<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private boolean lastPage;

    public static <U, V> PagableResponseHelper<V> getPagableResponse(Page<U> pages, Class<V> type) {
        List<U> entityList = pages.getContent();
        ModelMapper modelMapper = new ModelMapper();

        List<V> dtoList = entityList.stream()
                .map(entity -> modelMapper.map(entity, type))
                .collect(Collectors.toList());

        PagableResponseHelper<V> pagableResponse = new PagableResponseHelper<>();
        pagableResponse.setContent(dtoList);
        pagableResponse.setPageNumber(pages.getNumber() + 1);
        pagableResponse.setPageSize(pages.getSize());
        pagableResponse.setTotalElements(pages.getTotalElements());
        pagableResponse.setLastPage(pages.isLast());

        return pagableResponse;
    }
}
