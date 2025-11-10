package microservice.ERP.entreprise.entrepriseClient;

import java.util.List;

public class EquipePageDTO {

    private List<EquipeDTO> content;
    private PageableDTO pageable;
    private boolean last;
    private int totalElements;
    private int totalPages;
    private int size;
    private int number;
    private SortDTO sort;
    private boolean first;
    private int numberOfElements;
    private boolean empty;

    // Getters et setters
    public List<EquipeDTO> getContent() { return content; }
    public void setContent(List<EquipeDTO> content) { this.content = content; }

    public PageableDTO getPageable() { return pageable; }
    public void setPageable(PageableDTO pageable) { this.pageable = pageable; }

    public boolean isLast() { return last; }
    public void setLast(boolean last) { this.last = last; }

    public int getTotalElements() { return totalElements; }
    public void setTotalElements(int totalElements) { this.totalElements = totalElements; }

    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public SortDTO getSort() { return sort; }
    public void setSort(SortDTO sort) { this.sort = sort; }

    public boolean isFirst() { return first; }
    public void setFirst(boolean first) { this.first = first; }

    public int getNumberOfElements() { return numberOfElements; }
    public void setNumberOfElements(int numberOfElements) { this.numberOfElements = numberOfElements; }

    public boolean isEmpty() { return empty; }
    public void setEmpty(boolean empty) { this.empty = empty; }
}

// DTO pour le tri
class SortDTO {
    private boolean empty;
    private boolean sorted;
    private boolean unsorted;

    public boolean isEmpty() { return empty; }
    public void setEmpty(boolean empty) { this.empty = empty; }

    public boolean isSorted() { return sorted; }
    public void setSorted(boolean sorted) { this.sorted = sorted; }

    public boolean isUnsorted() { return unsorted; }
    public void setUnsorted(boolean unsorted) { this.unsorted = unsorted; }
}

// DTO pour la pagination
class PageableDTO {
    private int pageNumber;
    private int pageSize;
    private SortDTO sort;
    private boolean paged;
    private boolean unpaged;
    private int offset;

    public int getPageNumber() { return pageNumber; }
    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }

    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public SortDTO getSort() { return sort; }
    public void setSort(SortDTO sort) { this.sort = sort; }

    public boolean isPaged() { return paged; }
    public void setPaged(boolean paged) { this.paged = paged; }

    public boolean isUnpaged() { return unpaged; }
    public void setUnpaged(boolean unpaged) { this.unpaged = unpaged; }

    public int getOffset() { return offset; }
    public void setOffset(int offset) { this.offset = offset; }
}
