package com.mouse.springbootshiro.services;

import com.mouse.springbootshiro.dao.MoXbbPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mouse.springbootshiro.pojo.XbbQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shilun
 * @since 2019-08-26
 */
public interface IMoXbbService extends IService<MoXbbPo> {

    List<MoXbbPo> xbbQuery(XbbQuery xbbQuery);

    String updateList(List<MoXbbPo> list);

    List<MoXbbPo> successList(XbbQuery xbbQuery);

    boolean dbReptile(String telFirst,String telSecond,boolean isRandom);

}
