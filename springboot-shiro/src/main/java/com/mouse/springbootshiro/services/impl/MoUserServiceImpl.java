package com.mouse.springbootshiro.services.impl;

import com.mouse.springbootshiro.dao.MoUserPo;
import com.mouse.springbootshiro.mapper.MoUserMapper;
import com.mouse.springbootshiro.services.IMoUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shilun
 * @since 2019-07-15
 */
@Service
public class MoUserServiceImpl extends ServiceImpl<MoUserMapper, MoUserPo> implements IMoUserService {

}
