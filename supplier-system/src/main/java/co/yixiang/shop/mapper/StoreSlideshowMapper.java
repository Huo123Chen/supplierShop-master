package co.yixiang.shop.mapper;

import co.yixiang.shop.domain.StoreSlideshow;

import java.util.List;

/**
 * 轮播图Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-03
 */
public interface StoreSlideshowMapper 
{
    /**
     * 查询轮播图
     * 
     * @param id 轮播图ID
     * @return 轮播图
     */
    public StoreSlideshow selectStoreSlideshowById(String id);

    /**
     * 查询轮播图列表
     * 
     * @param storeSlideshow 轮播图
     * @return 轮播图集合
     */
    public List<StoreSlideshow> selectStoreSlideshowList(StoreSlideshow storeSlideshow);

    /**
     * 新增轮播图
     * 
     * @param storeSlideshow 轮播图
     * @return 结果
     */
    public int insertStoreSlideshow(StoreSlideshow storeSlideshow);

    /**
     * 修改轮播图
     * 
     * @param storeSlideshow 轮播图
     * @return 结果
     */
    public int updateStoreSlideshow(StoreSlideshow storeSlideshow);

    /**
     * 删除轮播图
     * 
     * @param id 轮播图ID
     * @return 结果
     */
    public int deleteStoreSlideshowById(String id);

    /**
     * 批量删除轮播图
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreSlideshowByIds(String[] ids);
}
