package com.mouse.springbootshiro.services;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouse.springbootshiro.dao.MoXbbPo;
import com.mouse.springbootshiro.dao.MoXbbUserPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mouse.springbootshiro.pojo.XbbQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shilun
 * @since 2019-09-04
 */
public interface IMoXbbUserService extends IService<MoXbbUserPo> {

    List<MoXbbUserPo> list(MoXbbUserPo xbbUserPo);

    IPage<MoXbbUserPo> selectPageVo(Page<MoXbbUserPo> page,@Param(Constants.WRAPPER) Wrapper<MoXbbUserPo> queryWrapper);

    String getCodeByUuid(String uuid);

    String appointPhone(String uuid);

    MoXbbUserPo getByUuid(String Uuid);

    List<MoXbbUserPo> getByOwnerId(String ownerId);

    String createBatch(String content,String ownerId);

}
