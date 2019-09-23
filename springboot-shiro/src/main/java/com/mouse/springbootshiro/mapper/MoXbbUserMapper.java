package com.mouse.springbootshiro.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouse.springbootshiro.dao.MoXbbPo;
import com.mouse.springbootshiro.dao.MoXbbUserPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shilun
 * @since 2019-09-04
 */
public interface MoXbbUserMapper extends BaseMapper<MoXbbUserPo> {

    List<MoXbbUserPo> selectListByDao(MoXbbUserPo xbbUserPo);

    IPage<MoXbbUserPo> selectPageVo(Page<MoXbbUserPo> page,@Param(Constants.WRAPPER) Wrapper<MoXbbUserPo> queryWrapper);

    MoXbbUserPo getByUuid(String uuid);

    List<MoXbbUserPo> getByOwnerId(String ownerId);

}
