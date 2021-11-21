package com.heng.sync.reading.management.api.commons.config;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * SaToken 权限与角色解析配置
 */
@Component
public class SaTokenRoleAndPermissionConfig implements StpInterface {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<String> getPermissionList(Object o, String s) {
        List<String> list = new ArrayList<>();
        list.add("*");
        return list;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {

        List<String> list = new ArrayList<>();
        try {
            String res = stringRedisTemplate.opsForValue().get(RedisPrefixConfig.KEY_ACCOUNT_ROLE_PREFIX + o);
            if (res != null) {
                int accountType = Integer.parseInt(res);
                switch (accountType) {
                    case 1:
                        list.add("app-admin");
                        break;
                    case 0:
                        list.add("app-reader");
                        break;
                    default:
                        break;
                }
            }
        } catch (NumberFormatException e) {
            return list;
        }
        return list;
    }
}
