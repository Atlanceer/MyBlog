package atlan.ceer.model;

import java.util.List;

/**
 * 查询页面的模型
 * @param <T>
 */
public class QueryPage<T> {
    //总数量
    private int totalCount;
    //总页码
    private int totalPage;
    //当前页码
    private int currentPage;
    //是否有下一页
    private boolean haveMore;
    //当前页码的数量
    private int thisPageCount;
    //存当前页的数据
    private List<T> pageList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isHaveMore() {
        return haveMore;
    }

    public void setHaveMore(boolean haveMore) {
        this.haveMore = haveMore;
    }

    public int getThisPageCount() {
        return thisPageCount;
    }

    public void setThisPageCount(int thisPageCount) {
        this.thisPageCount = thisPageCount;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    @Override
    public String toString() {
        return "QueryPage{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", haveMore=" + haveMore +
                ", thisPageCount=" + thisPageCount +
                ", pageList=" + pageList +
                '}';
    }
}
