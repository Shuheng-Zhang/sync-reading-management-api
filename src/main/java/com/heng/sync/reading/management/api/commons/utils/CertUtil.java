package com.heng.sync.reading.management.api.commons.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;

import java.nio.charset.StandardCharsets;

/**
 * 凭据工具类
 */
public class CertUtil {

    /**
     * 生成安全密钥
     *
     * @return 用户安全密钥
     */
    public static String generateAccountSecurityKey() {
        return IdUtil.simpleUUID().toUpperCase();
    }

    /**
     * 生成用户验证凭据
     *
     * @param accountPasswd      用户口令
     * @param accountSecurityKey 用户安全密钥
     * @return 验证凭据
     */
    public static String genAccountCert(String accountPasswd, String accountSecurityKey) {
        if (StrUtil.isEmptyIfStr(accountPasswd) || StrUtil.isEmptyIfStr(accountSecurityKey)) {
            return null;
        }

        byte[] securityKey = accountSecurityKey.getBytes(StandardCharsets.UTF_8);
        HMac hmac = new HMac(HmacAlgorithm.HmacSHA256, securityKey);
        return hmac.digestHex(accountPasswd, StandardCharsets.UTF_8);
    }
}
