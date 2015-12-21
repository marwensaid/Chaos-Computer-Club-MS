package com.chaos.restControllers.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by marwen on 21/12/15.
 */

@XmlRootElement
public class PostsRequestDTO {

    private int pageNo;
    private int pageSize = 5;
    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
