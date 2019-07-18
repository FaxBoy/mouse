package com.mouse.springbootshiro.services;

import com.mouse.springbootshiro.dao.TestCodePo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shilun
 * @since 2019-07-01
 */
public interface ITestCodeService extends IService<TestCodePo> {


    void saveTestCode(TestCodePo testCodePo) throws Exception;

//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void updateTestCode(TestCodePo testCodePo);

}
