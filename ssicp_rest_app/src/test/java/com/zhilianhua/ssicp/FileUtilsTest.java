package com.zhilianhua.ssicp;

import com.zhilianhua.ssicp.utils.FileUtils;
import org.junit.jupiter.api.Test;

public class FileUtilsTest {
    @Test
    public void test(){
        String fileName="123.txt";
        String type = FileUtils.getType(fileName);
        System.out.println(type);
    }
}
