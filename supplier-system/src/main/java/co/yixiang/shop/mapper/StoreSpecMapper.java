package co.yixiang.shop.mapper;

import co.yixiang.shop.domain.StoreSpec;
import co.yixiang.shop.domain.StoreSpec;
import java.util.List;

/**
 * 商品规格(独立)Mapper接口
 * 
 * @author hupeng
 * @date 2019-08-25
 */
public interface StoreSpecMapper 
{
    /**
     * 查询商品规格(独立)
     * 
     * @param id 商品规格(独立)ID
     * @return 商品规格(独立)
     */
    public StoreSpec selectStoreSpecById(Long id);

    /**
     * 查询商品规格(独立)列表
     * 
     * @param storeSpec 商品规格(独立)
     * @return 商品规格(独立)集合
     */
    public List<StoreSpec> selectStoreSpecList(StoreSpec storeSpec);

    /**
     * 新增商品规格(独立)
     * 
     * @param storeSpec 商品规格(独立)
     * @return 结果
     */
    public int insertStoreSpec(StoreSpec storeSpec);

    /**
     * 修改商品规格(独立)
     * 
     * @param storeSpec 商品规格(独立)
     * @return 结果
     */
    public int updateStoreSpec(StoreSpec storeSpec);

    /**
     * 删除商品规格(独立)
     * 
     * @param id 商品规格(独立)ID
     * @return 结果
     */
    public int deleteStoreSpecById(Long id);

    /**
     * 批量删除商品规格(独立)
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreSpecByIds(String[] ids);
}
