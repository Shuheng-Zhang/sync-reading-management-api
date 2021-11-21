package com.heng.sync.reading.management.api.service.process;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.heng.sync.reading.management.api.commons.config.RedisPrefixConfig;
import com.heng.sync.reading.management.api.commons.enums.AccountLockedEnum;
import com.heng.sync.reading.management.api.commons.enums.RespEnum;
import com.heng.sync.reading.management.api.commons.exception.BusinessException;
import com.heng.sync.reading.management.api.commons.utils.CertUtil;
import com.heng.sync.reading.management.api.dto.account.AccountAuthDto;
import com.heng.sync.reading.management.api.dto.account.AccountLoginDoneDto;
import com.heng.sync.reading.management.api.entity.AccountInfo;
import com.heng.sync.reading.management.api.mapper.AccountInfoMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthService {

    @Resource
    private AccountInfoMapper accountInfoMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 用户验证(登入)
     * @param authDto 验证信息
     * @return 授权 Token
     */
    public AccountLoginDoneDto auth(AccountAuthDto authDto) {
        if (authDto == null) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        if (StrUtil.isEmptyIfStr(authDto.getAccountName()) || StrUtil.isEmptyIfStr(authDto.getAccountPasswd())) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        AccountInfo target = accountInfoMapper.queryByAccountName(authDto.getAccountName());
        if (target == null) {
            throw new BusinessException(RespEnum.USERNAME_OR_CERT_INVALID);
        }

        if (target.getLocked() == AccountLockedEnum.LOCKED.locked()) {
            throw new BusinessException(RespEnum.ACCOUNT_LOCKED);
        }

        String cert = CertUtil.genAccountCert(authDto.getAccountPasswd(), target.getAccountSecurityKey());
        if (!target.getAccountCert().equals(cert)) {
            throw new BusinessException(RespEnum.USERNAME_OR_CERT_INVALID);
        }

        stringRedisTemplate.opsForValue().set(RedisPrefixConfig.REDIS_KEY_PREFIX + target.getId(), String.valueOf(target.getAccountType()));


        StpUtil.login(target.getId());

        return new AccountLoginDoneDto(target.getId(), target.getAccountName(), StpUtil.getTokenValue());
    }

    /**
     * 用户登出
     * @param accountId 目标用户账号ID
     */
    public void logout(String accountId) {
        if (StrUtil.isEmptyIfStr(accountId)) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        stringRedisTemplate.delete(RedisPrefixConfig.REDIS_KEY_PREFIX + accountId);

        StpUtil.logout(accountId);
    }
}
