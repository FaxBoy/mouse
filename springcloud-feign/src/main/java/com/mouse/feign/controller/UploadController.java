package com.mouse.feign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName UploadController
 * @Description TODO
 * @date 2018/9/14 11:02
 */
@Controller
public class UploadController {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/upload")
    public  String  upload() {
        return "upload";
    }

    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    @ResponseBody
    public Object saveFile(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        //当前项目路径下
        File name = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes, name);
        return name.getAbsolutePath();
    }



}
