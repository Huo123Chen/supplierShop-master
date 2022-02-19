package co.yixiang.app.modular.member.controller;


import co.yixiang.app.common.R;
import co.yixiang.app.common.persistence.dao.StoreMemberMapper;
import co.yixiang.app.common.persistence.model.StoreMember;
import co.yixiang.app.common.utils.CommonEnum;
import co.yixiang.app.common.utils.Md5Utils;
import co.yixiang.app.common.utils.OrderUtil;
import co.yixiang.app.common.utils.RedisUtil;
import co.yixiang.app.modular.member.service.IMemberService;
import co.yixiang.app.modular.member.service.dto.MemberDTO;
import co.yixiang.app.modular.member.service.impl.AddressServiceImpl;
import co.yixiang.app.modular.member.service.mapper.MemberMapper;
import co.yixiang.app.modular.member.service.vo.AddressVO;
import co.yixiang.app.modular.member.service.vo.ApiStoreMemberVO;
import co.yixiang.app.modular.shop.service.impl.GoodsServiceImpl;
import co.yixiang.app.modular.shop.service.impl.OrderServiceImpl;
import co.yixiang.app.modular.shop.service.mapper.GoodsMapper;
import co.yixiang.app.modular.shop.service.vo.PageVO;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @ClassName 个人中心
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/6/27
 **/

//@HasRole("user_role")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "个人中心", tags = "个人中心模块", description = "个人中心")
public class UserController {
    @Autowired
    private  IMemberService memberService;
    @Autowired
    private  MemberMapper memberMapper;
    @Autowired
    private  AddressServiceImpl addressService;
    @Autowired
    private  GoodsServiceImpl goodsService;
    @Autowired
    private  GoodsMapper goodsMapper;
    @Autowired
    private  OrderServiceImpl orderService;
    @Autowired
    private  StoreMemberMapper storeMemberMapper;

    @Autowired
    private RedisUtil redisUtil;
    @PostMapping(value = "/shop/userRegister")
    @ApiOperation(value = "API用户注册",notes = "API用户注册")
    public R userRegister(@Validated @RequestBody ApiStoreMemberVO apiStoreMemberVO){
        StoreMember storeMember = storeMemberMapper.selectOne(new QueryWrapper<StoreMember>().lambda()
                        .eq(StoreMember::getInvitCode, apiStoreMemberVO.getInvitCode()));

        if (null == storeMember){
            return R.error(CommonEnum.INVIT_CODE);
        }
        StoreMember storeMember2 = storeMemberMapper.selectOne(new QueryWrapper<StoreMember>().lambda()
                .eq(StoreMember::getNickname, apiStoreMemberVO.getNickname()));

        if (null == storeMember2){
            StoreMember storeMember1 = new StoreMember();
            storeMember1.setNickname(apiStoreMemberVO.getNickname());
            storeMember1.setSalt(""+OrderUtil.getRandom());
            String pass = Md5Utils.encryptPassword(apiStoreMemberVO.getNickname(), apiStoreMemberVO.getPassword(), storeMember1.getSalt());
            storeMember1.setPassword(pass);
            storeMember1.setInvitCode(storeMember.getInvitCode());
            storeMember1.setParentId(storeMember.getId());
            int count = storeMemberMapper.insert(storeMember1);
            if (count > 0 ){
                return R.success(CommonEnum.SUCCESS);
            }
        }

        return R.error(CommonEnum.OPERATE_ERROR);
    }



    @GetMapping(value = "/shop/user-my-info2")
    @ApiOperation(value = "我的个人信息",notes = "我的个人信息")
    public R userInfo2(int userId){
//        TokenProfile profile = ProfileHolder.getProfile();
//        int userId =  Integer.valueOf(profile.getId());

//        int userId = 0;

        redisUtil.set("name","zhangsan");

//        StoreMember member = memberService.getById(userId);
//        MemberDTO memberDTO = memberMapper.toDto(member);
//        Map<String,Integer> mapCount = orderService.countInfo(userId);
//        memberDTO.setCountInfo(mapCount);

        String name = (String)redisUtil.get("name");
        return R.success(name);
    }

    @GetMapping(value = "/shop/user-my-info")
    @ApiOperation(value = "我的个人信息",notes = "我的个人信息")
    public R userInfo(int userId){
//        TokenProfile profile = ProfileHolder.getProfile();
//        int userId =  Integer.valueOf(profile.getId());

//        int userId = 0;

        StoreMember member = memberService.getById(userId);
        MemberDTO memberDTO = memberMapper.toDto(member);
        Map<String,Integer> mapCount = orderService.countInfo(userId);
        memberDTO.setCountInfo(mapCount);
        return R.success(memberDTO);
    }

    @GetMapping(value = "/shop/user-my-address")
    @ApiOperation(value = "我的地址列表",notes = "我的地址列表")
    public R addressList(@RequestParam(value = "page",defaultValue = "0") int page,
                         @RequestParam(value = "limit",defaultValue = "10") int limit){
//        TokenProfile profile = ProfileHolder.getProfile();
//        int userId =  Integer.valueOf(profile.getId());
        int userId = 0;
        PageVO pageVO = new PageVO();
        pageVO.setPage(page);
        pageVO.setLimit(limit);
        return R.success(addressService.getList(pageVO,userId));
    }

    @PostMapping(value = "/shop/user-address-add-edit")
    @ApiOperation(value = "添加或修改地址",notes = "添加或修改地址")
    public R addAndEditAdress(@Validated @RequestBody AddressVO addressVO){
//        TokenProfile profile = ProfileHolder.getProfile();
//        int userId =  Integer.valueOf(profile.getId());

        int userId = 0;
        boolean result = addressService.addAndEdit(addressVO,userId);
        if(result){
            return R.success(CommonEnum.SUCCESS);
        }else{
            return R.error(CommonEnum.OPERATE_ERROR);
        }
    }

    @PostMapping(value = "/shop/user-address-del")
    @ApiOperation(value = "删除地址",notes = "删除地址")
    public R delAddress(@Validated @RequestBody String jsonStr){
        int addressId = Integer.valueOf(JSON.parseObject(jsonStr).get("address_id").toString());
        boolean result = addressService.removeById(addressId);
        if(result){
            return R.success(CommonEnum.SUCCESS);
        }else{
            return R.error(CommonEnum.OPERATE_ERROR);
        }
    }


    @GetMapping(value = "/shop/user-my-coupons")
    @ApiOperation(value = "我的优惠券",notes = "我的优惠券")
    public R myCoupons(@RequestParam(value = "orderTotalPrice",defaultValue = "0") double orderTotalPrice){
//        TokenProfile profile = ProfileHolder.getProfile();
//        int userId =  Integer.valueOf(profile.getId());
        int userId = 0;
        return R.success(memberService.couponList(userId,orderTotalPrice));
    }

    @GetMapping(value = "/shop/user-my-collects")
    @ApiOperation(value = "我的收藏列表",notes = "我的收藏列表")
    public R myCollect(@RequestParam(value = "page",defaultValue = "0") int page,
                       @RequestParam(value = "limit",defaultValue = "10") int limit){
//        TokenProfile profile = ProfileHolder.getProfile();
//        int userId =  Integer.valueOf(profile.getId());
        int userId = 0;
        PageVO pageVO = new PageVO();
        pageVO.setPage(page);
        pageVO.setLimit(limit);
        return R.success(goodsMapper.toDto(goodsService
                .collectGoods(pageVO.getPage(),pageVO.getLimit(),userId)));
    }

    @GetMapping(value = "/shop/user-order-list")
    @ApiOperation(value = "订单列表",notes = "订单列表")
    public R orderList(@RequestParam(value = "status",defaultValue = "0") int status,
                       @RequestParam(value = "page",defaultValue = "1") int page,
                       @RequestParam(value = "page_num",defaultValue = "10") int limit){
//        TokenProfile profile = ProfileHolder.getProfile();
//        int userId =  Integer.valueOf(profile.getId());

        int userId = 0;
        return R.success(orderService.orderList(status,userId,page,limit));
    }

    @GetMapping(value = "/shop/user-order-detail")
    @ApiOperation(value = "订单详情",notes = "订单详情")
    public R orderDetail(@RequestParam(value = "order_id",defaultValue = "0") String orderId){
//        TokenProfile profile = ProfileHolder.getProfile();
//        int userId =  Integer.valueOf(profile.getId());
        int userId = 0;
        return R.success(orderService.orderDetail(orderId,userId));
    }

    @PostMapping(value = "/shop/user-order-handle")
    @ApiOperation(value = "订单操作",notes = "订单操作")
    public R handleOrder(@Validated @RequestBody String jsonStr){
//        TokenProfile profile = ProfileHolder.getProfile();
//        int userId =  Integer.valueOf(profile.getId());
        int userId = 0;
        int orderId = Integer.valueOf(JSON.parseObject(jsonStr).get("order_id").toString());
        int type = Integer.valueOf(JSON.parseObject(jsonStr).get("type").toString());
        orderService.orderHandle(orderId,type,userId);
        return R.success("操作成功");
    }


    @GetMapping(value = "/shop/user-points-money-logs")
    @ApiOperation(value = "获取积分或者余额流水",notes = "获取积分或者余额流水")
    public R pointsMoneyLogs(@RequestParam(value = "type",defaultValue = "1") int type,
                             @RequestParam(value = "page",defaultValue = "1") int page,
                             @RequestParam(value = "limit",defaultValue = "10") int limit){
//        TokenProfile profile = ProfileHolder.getProfile();
//        int userId =  Integer.valueOf(profile.getId());

        int userId = 0;
        return R.success(orderService.pointsMoneyLogs(type,userId,page,limit));

    }






    /**
    @GetMapping(value = "/super")
    @PreAuthorize("hasAllRoles('user','admin')")
    @ApiOperation(value = "超管测试信息",notes = "超管测试信息")
    public R superInfo(){
        return R.success("超级管理员才可以访问的权限");
    }
    **/
}
