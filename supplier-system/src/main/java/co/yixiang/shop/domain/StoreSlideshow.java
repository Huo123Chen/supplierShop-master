package co.yixiang.shop.domain;

import java.util.Date;

import co.yixiang.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import co.yixiang.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 轮播图对象 store_slideshow
 *
 * @author ruoyi
 * @date 2022-02-03
 */
public class StoreSlideshow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 轮播图 */
    @Excel(name = "轮播图")
    private String slideshowLogo;

    /** 轮播图名称 */
    @Excel(name = "轮播图名称")
    private String slideshowTitle;

    /** 轮播图描述 */
    @Excel(name = "轮播图描述")
    private String slideshowDesc;

    /** 轮播图分类排序 */
    @Excel(name = "轮播图分类排序")
    private String slideshowSort;

    /** 轮播图状态(1有效,0无效) */
    @Excel(name = "轮播图状态(1有效,0无效)")
    private String slideshowStatus;

    /** 轮播图状态(1删除,0未删除) */
    @Excel(name = "轮播图状态(1删除,0未删除)")
    private String isDeleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createAt;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setSlideshowLogo(String slideshowLogo)
    {
        this.slideshowLogo = slideshowLogo;
    }

    public String getSlideshowLogo()
    {
        return slideshowLogo;
    }
    public void setSlideshowTitle(String slideshowTitle)
    {
        this.slideshowTitle = slideshowTitle;
    }

    public String getSlideshowTitle()
    {
        return slideshowTitle;
    }
    public void setSlideshowDesc(String slideshowDesc)
    {
        this.slideshowDesc = slideshowDesc;
    }

    public String getSlideshowDesc()
    {
        return slideshowDesc;
    }
    public void setSlideshowSort(String slideshowSort)
    {
        this.slideshowSort = slideshowSort;
    }

    public String getSlideshowSort()
    {
        return slideshowSort;
    }
    public void setSlideshowStatus(String slideshowStatus)
    {
        this.slideshowStatus = slideshowStatus;
    }

    public String getSlideshowStatus()
    {
        return slideshowStatus;
    }
    public void setIsDeleted(String isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted()
    {
        return isDeleted;
    }
    public void setCreateAt(Date createAt)
    {
        this.createAt = createAt;
    }

    public Date getCreateAt()
    {
        return createAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("slideshowLogo", getSlideshowLogo())
                .append("slideshowTitle", getSlideshowTitle())
                .append("slideshowDesc", getSlideshowDesc())
                .append("slideshowSort", getSlideshowSort())
                .append("slideshowStatus", getSlideshowStatus())
                .append("isDeleted", getIsDeleted())
                .append("createAt", getCreateAt())
                .toString();
    }
}
