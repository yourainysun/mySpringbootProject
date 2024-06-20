package com.work.springbootinit.model.dto;


import com.work.springbootinit.model.entity.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasePageDto extends PageRequest {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分页请求信息")
    protected PageRequest pageInfo = new PageRequest();

    public long getTotalCount() {
        return pageInfo.getTotalCount();
    }

    public void setTotalCount(long totalCount) {
        pageInfo.setTotalCount(totalCount);
    }

    public long getPageNum() {
        if (pageInfo.getPageNum() < 1) {
            return 1;
        }
        return pageInfo.getPageNum();
    }

    public void setPageNum(long pageNum) {
        if (pageNum < 1) {
            pageInfo.setPageNum(1);
        } else {
            pageInfo.setPageNum(pageNum);
        }
    }

    public long getPageSize() {
        if (pageInfo.getPageSize() < 1) {
            return 1;
        }
        return pageInfo.getPageSize() > 1000 ? 1000 : pageInfo.getPageSize();
    }

    public void setPageSize(long pageSize) {
        if (pageSize < 1) {
            pageInfo.setPageSize(1L);
        } else {
            pageInfo.setPageSize(pageSize);
        }
    }

}
