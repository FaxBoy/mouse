package com.mouse.springbootshiro.mapper;

import com.mouse.springbootshiro.dao.MoXbbPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mouse.springbootshiro.pojo.XbbQuery;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shilun
 * @since 2019-08-23
 */
public interface MoXbbMapper extends BaseMapper<MoXbbPo> {

    List<MoXbbPo> xbbQuery(XbbQuery xbbQuery);

    List<MoXbbPo> successList(XbbQuery xbbQuery);
}
