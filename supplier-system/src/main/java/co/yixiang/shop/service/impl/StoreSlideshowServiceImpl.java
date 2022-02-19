package co.yixiang.shop.service.impl;

import java.util.List;

import co.yixiang.shop.domain.StoreSlideshow;
import co.yixiang.shop.mapper.StoreSlideshowMapper;
import co.yixiang.shop.service.IStoreSlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.yixiang.common.core.text.Convert;

/**
 * 轮播图Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-03
 */
@Service
public class StoreSlideshowServiceImpl implements IStoreSlideshowService
{
    @Autowired
    private StoreSlideshowMapper storeSlideshowMapper;

    /**
     * 查询轮播图
     * 
     * @param id 轮播图ID
     * @return 轮播图
     */
    @Override
    public StoreSlideshow selectStoreSlideshowById(String id)
    {
        return storeSlideshowMapper.selectStoreSlideshowById(id);
    }

    /**
     * 查询轮播图列表
     * 
     * @param storeSlideshow 轮播图
     * @return 轮播图
     */
    @Override
    public List<StoreSlideshow> selectStoreSlideshowList(StoreSlideshow storeSlideshow)
    {
        return storeSlideshowMapper.selectStoreSlideshowList(storeSlideshow);
    }

    /**
     * 新增轮播图
     * 
     * @param storeSlideshow 轮播图
     * @return 结果
     */
    @Override
    public int insertStoreSlideshow(StoreSlideshow storeSlideshow)
    {
        return storeSlideshowMapper.insertStoreSlideshow(storeSlideshow);
    }

    /**
     * 修改轮播图
     * 
     * @param storeSlideshow 轮播图
     * @return 结果
     */
    @Override
    public int updateStoreSlideshow(StoreSlideshow storeSlideshow)
    {
        return storeSlideshowMapper.updateStoreSlideshow(storeSlideshow);
    }

    /**
     * 删除轮播图对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStoreSlideshowByIds(String ids)
    {
        return storeSlideshowMapper.deleteStoreSlideshowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除轮播图信息
     * 
     * @param id 轮播图ID
     * @return 结果
     */
    @Override
    public int deleteStoreSlideshowById(String id)
    {
        return storeSlideshowMapper.deleteStoreSlideshowById(id);
    }
}
