package com.oyf.test.test4;

import com.oyf.test.util.ExcelUtil;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author ouyangfei
 * @date Created in 2021/3/24
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/processExcel")
    public void processExcel(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            List<User> list = ExcelUtil.processExcel(workbook);
            for (User user : list) {
                System.out.println(user.toString());
            }
        } catch (NotOfficeXmlFileException e) {
            e.printStackTrace();
            System.out.println("文件格式异常，请上传xlsx或者xls格式文件");
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("文件内容异常，请检查文件是否有空行、无数据");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
