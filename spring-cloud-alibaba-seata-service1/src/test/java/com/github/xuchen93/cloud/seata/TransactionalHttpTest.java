package com.github.xuchen93.cloud.seata;

import com.github.xuchen93.cloud.util.HttpPackUtil;

/**
 * @author xuchen.wang
 * @date 2023/12/27
 */
public class TransactionalHttpTest {
    public static void main(String[] args) {
        HttpPackUtil.setPort(9100);
//        System.out.println(HttpPackUtil.createGet("success").execute().body());
        System.out.println(HttpPackUtil.createGet("fail1").execute().body());
//        System.out.println(HttpPackUtil.createGet("fail2").execute().body());
    }
}
