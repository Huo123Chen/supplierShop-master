package co.yixiang.shop.mapper;

import co.yixiang.shop.domain.StoreOrder;
import co.yixiang.shop.domain.StoreOrder;
import java.util.List;

/**
 * 订单主Mapper接口
 * 
 * @author hupeng
 * @date 2019-08-29
 */
public interface StoreOrderMapper 
{


    /**
     * 查询订单主
     * 
     * @param orderId 订单主ID
     * @return 订单主
     */
    public StoreOrder selectStoreOrderById(Integer orderId);

    /**
     * 查询订单主列表
     * 
     * @param storeOrder 订单主
     * @return 订单主集合
     */
    public List<StoreOrder> selectStoreOrderList(StoreOrder storeOrder);

    /**
     * 新增订单主
     * 
     * @param storeOrder 订单主
     * @return 结果
     */
    public int insertStoreOrder(StoreOrder storeOrder);

    /**
     * 修改订单主
     * 
     * @param storeOrder 订单主
     * @return 结果
     */
    public int updateStoreOrder(StoreOrder storeOrder);

    /**
     * 删除订单主
     * 
     * @param orderId 订单主ID
     * @return 结果
     */
    public int deleteStoreOrderById(Integer orderId);

    /**
     * 批量删除订单主
     * 
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreOrderByIds(String[] orderIds);
}
