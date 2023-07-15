package com.zwb.aproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AprojectApplication.class, args);

        //test();
    }

    /*public static void test() {
        //1.获取文件地址
        String fileName = "D:\\ideaWorkPlace\\guoneng\\aproject\\src\\main\\java\\excel\\test.xlsx";
        //2、调用easyExcel里面的方法实现写操作
        EasyExcel.write(fileName, UserDto.class).sheet("某某报表").doWrite(new ArrayList<>());
    }*/
}
