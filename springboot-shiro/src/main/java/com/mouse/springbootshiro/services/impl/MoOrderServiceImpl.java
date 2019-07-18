package com.mouse.springbootshiro.services.impl;

import com.mouse.springbootshiro.dao.MoOrderPo;
import com.mouse.springbootshiro.mapper.MoOrderMapper;
import com.mouse.springbootshiro.services.IMoOrderService;
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
public class MoOrderServiceImpl extends ServiceImpl<MoOrderMapper, MoOrderPo> implements IMoOrderService {

}
