package com.chaos.restControllers.model;

import com.chaos.entities.Post;
import com.chaos.utils.BeanCopyUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marwen on 21/12/15.
 */
public class PostsResponseDTO {

    private static final long serialVersionUID = 1L;
    private List<Post> posts;
    private long totalRecords;
    private int currentPage;
    private int pageSize;
    private boolean hasNextPage;
    private boolean hasPrevPage;

    public PostsResponseDTO(List<Post> posts, long totalRecords, int currentPage, int pageSize, boolean hasNextPage, boolean hasPrevPage) {
        this.posts = posts;
        this.totalRecords = totalRecords;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.hasNextPage = hasNextPage;
        this.hasPrevPage = hasPrevPage;
    }

    public PostsResponseDTO(Page<Post> pageData) {
        List<Post> postList = pageData.getContent();
        this.posts = new ArrayList<Post>();
        for (Post post : postList)
        {
            this.posts.add(BeanCopyUtils.copy(post));
        }
        this.totalRecords = pageData.getTotalElements();
        this.currentPage = pageData.getNumber();
        this.pageSize = pageData.getSize();
        this.hasNextPage = pageData.hasNext();
        this.hasPrevPage = pageData.hasPrevious();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
