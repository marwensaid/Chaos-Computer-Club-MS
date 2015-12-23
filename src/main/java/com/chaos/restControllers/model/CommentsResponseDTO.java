package com.chaos.restControllers.model;

import com.chaos.entities.Comment;
import com.chaos.utils.BeanCopyUtils;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by marwen on 21/12/15.
 */
public class CommentsResponseDTO implements Serializable {

    public static final long serialVersionUID = 1L;
    private List<Comment> comments;
    private long totalRecords;
    private int currentPage;
    private int pageSize;
    private boolean hasNextPage;
    private boolean hasPrevPage;

    public CommentsResponseDTO(List<Comment> comments, long totalRecords, int currentPage, int pageSize, boolean hasNextPage, boolean hasPrevPage) {
        this.comments = comments;
        this.totalRecords = totalRecords;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.hasNextPage = hasNextPage;
        this.hasPrevPage = hasPrevPage;
    }

    public CommentsResponseDTO(Page<Comment> pageData) {
        List<Comment> commentList = pageData.getContent();
        this.comments = new ArrayList<Comment>();
        this.comments.addAll(commentList.stream().map(BeanCopyUtils::copy).collect(Collectors.toList()));
        this.totalRecords = pageData.getTotalElements();
        this.currentPage = pageData.getNumber();
        this.pageSize = pageData.getSize();
        this.hasNextPage = pageData.hasNext();
        this.hasPrevPage = pageData.hasPrevious();

    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPrevPage() {
        return hasPrevPage;
    }

    public void setHasPrevPage(boolean hasPrevPage) {
        this.hasPrevPage = hasPrevPage;
    }
}
