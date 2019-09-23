package com.mouse.springbootshiro.mapper;

import com.mouse.springbootshiro.dao.MoGpsPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shilun
 * @since 2019-08-02
 */
public interface MoGpsMapper extends BaseMapper<MoGpsPo> {

    List<Map<String,Object>> queryLocationByUuid(int i);

    @Transactional(readOnly = false)
    List<Map<String,Object>> explain();

    List<Map<String,Object>> timeOut();

}
