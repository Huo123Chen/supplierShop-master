package co.yixiang.app.modular.member.service.impl;


import co.yixiang.app.common.exception.BizException;
import co.yixiang.app.common.persistence.dao.StoreCouponMapper;
import co.yixiang.app.common.persistence.dao.StoreMemberMapper;
import co.yixiang.app.common.persistence.model.StoreMember;
import co.yixiang.app.modular.member.service.IMemberService;
import co.yixiang.app.modular.member.service.vo.LoginVO;
import co.yixiang.app.modular.shop.service.dto.CouponDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import co.yixiang.app.common.persistence.dao.StoreCouponMapper;
import co.yixiang.app.common.persistence.dao.StoreMemberMapper;
import co.yixiang.app.common.persistence.model.StoreMember;
import co.yixiang.app.modular.member.service.IMemberService;
import co.yixiang.app.modular.member.service.dto.MemberDTO;
import co.yixiang.app.modular.member.service.mapper.MemberMapper;
import co.yixiang.app.modular.shop.service.dto.CouponDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberServiceImpl extends ServiceImpl<StoreMemberMapper, StoreMember>  implements IMemberService {

    private final StoreMemberMapper storeMemberMapper;
    private final MemberMapper memberMapper;
    private final StoreCouponMapper storeCouponMapper;

    @Override
    public StoreMember login(String openid) {
        // 根据登陆账号获取数据库信息


        StoreMember result = storeMemberMapper
                .selectOne(new QueryWrapper<StoreMember>().lambda()
                        .eq(StoreMember::getOpenid, openid));

        return  result;
        //return null;
    }

    @Override
    public StoreMember login(LoginVO loginVO) {
//        StoreMember agentVo = storeMemberMapper
//                .selectOne(new QueryWrapper<StoreMember>().lambda()
//                        .eq(StoreMember::getInvitCode, loginVO.getInvitCode()));
//
//        if (agentVo == null){
//            throw  new BizException("-1","邀请码不正确！");
//        }

        StoreMember result = storeMemberMapper
                .selectOne(new QueryWrapper<StoreMember>().lambda()
                        .eq(StoreMember::getNickname, loginVO.getNickname()));
        //todo 换地方处理建立代理与玩家关系
        return  result;
    }

    @Override
    public List<MemberDTO> memeberList() {
        return  memberMapper.toDto(storeMemberMapper.selectList(null));
    }

    @Override
    public List<CouponDTO> couponList(int userId, double orderTotalPrice) {
        return storeCouponMapper.couponList(userId,orderTotalPrice);
    }


}
