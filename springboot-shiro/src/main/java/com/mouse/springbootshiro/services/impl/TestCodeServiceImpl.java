package com.mouse.springbootshiro.services.impl;

import com.mouse.springbootshiro.dao.TestCodePo;
import com.mouse.springbootshiro.mapper.TestCodeMapper;
import com.mouse.springbootshiro.services.ITestCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void saveTestCode(TestCodePo testCodePo) throws Exception {
        testCodeMapper.insert(testCodePo);

        testCodePo.setVersion(1);
        testCodePo.setSubjectName("水立方撒谎奋斗啦水立方哈佛挥洒浪费哈舒服阿双方拉升");

        try {
            testCodeMapper.updateById(testCodePo);
        }catch(Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException("更新失败");
        }

    }
}
