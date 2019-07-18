package com.mouse.springbootshiro.services.impl;

import com.mouse.springbootshiro.dao.TestCodePo;
import com.mouse.springbootshiro.mapper.TestCodeMapper;
import com.mouse.springbootshiro.services.ITestCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shilun
 * @since 2019-07-01
 */
@Service
public class TestCodeServiceImpl extends ServiceImpl<TestCodeMapper, TestCodePo> implements ITestCodeService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TestCodeMapper testCodeMapper;

    @Override
    @Transactional
    public void saveTestCode(TestCodePo testCodePo) throws Exception {




//        testCodeMapper.updateById(testCodePo);

        this.updateTestCode(testCodePo);
//        ((ITestCodeService) AopContext.currentProxy()).updateTestCode(testCodePo);

//        try {
//            testCodeMapper.updateById(testCodePo);
//        }catch(Exception e){
//            logger.error(e.getMessage());
//            throw new RuntimeException("更新失败");
//        }
        testCodePo.setSubjectName("水立方撒谎奋可怜的发生率克己复礼客机失联的看法可怜是大解放路口");
        testCodeMapper.updateById(testCodePo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateTestCode(TestCodePo testCodePo){
        testCodeMapper.insert(testCodePo);
        testCodePo.setSubjectName("水立方撒");
        testCodeMapper.updateById(testCodePo);

    }
}
