package com.heng.sync.reading.management.api.service.data;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.sync.reading.management.api.commons.enums.AccountLockedEnum;
import com.heng.sync.reading.management.api.commons.enums.AccountTypeEnum;
import com.heng.sync.reading.management.api.commons.enums.RespEnum;
import com.heng.sync.reading.management.api.commons.exception.BusinessException;
import com.heng.sync.reading.management.api.commons.utils.CertUtil;
import com.heng.sync.reading.management.api.dto.account.AccountCreationDto;
import com.heng.sync.reading.management.api.dto.account.AccountUpdateDto;
import com.heng.sync.reading.management.api.entity.AccountInfo;
import com.heng.sync.reading.management.api.mapper.AccountInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountInfoService extends ServiceImpl<AccountInfoMapper, AccountInfo> {

    /**
     * 创建新用户账号
     * @param accountCreationDto 用户账号创建信息
     */
    @Transactional
    public void createAccount(AccountCreationDto accountCreationDto) {

        if (accountCreationDto == null) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        if (StrUtil.isEmptyIfStr(accountCreationDto.getAccountName()) || StrUtil.isEmptyIfStr(accountCreationDto.getAccountPasswd())) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }


        Integer ret = this.baseMapper.queryExistedByAccountName(accountCreationDto.getAccountName().trim());
        if (ret != null) {
            throw new BusinessException(RespEnum.ACCOUNT_NAME_ALREADY_USED);
        }

        AccountTypeEnum typeEnum = AccountTypeEnum.valueOf(accountCreationDto.getAccountType());
        if (typeEnum == null) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        String accountSecurityKey = CertUtil.generateAccountSecurityKey();
        String accountCert = CertUtil.genAccountCert(accountCreationDto.getAccountPasswd().trim(), accountSecurityKey);

        AccountInfo accountInfo = new AccountInfo(null,
                accountCreationDto.getAccountName(),
                accountCert,
                accountSecurityKey,
                accountCreationDto.getAccountType(),
                AccountLockedEnum.UN_LOCKED.locked());

        this.baseMapper.insert(accountInfo);
    }

    /**
     * 更新用户账号验证凭据或锁定状态
     * @param accountUpdateDto 用户账号更新信息
     */
    @Transactional
    public void updateAccountInfo(AccountUpdateDto accountUpdateDto) {
        if (accountUpdateDto == null) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        if (StrUtil.isEmptyIfStr(accountUpdateDto.getAccountId())) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        AccountInfo target = this.baseMapper.selectById(accountUpdateDto.getAccountId());
        if (target == null) {
            throw new BusinessException(RespEnum.USER_INFO_INVALID);
        }


        AccountLockedEnum lockedEnum = AccountLockedEnum.valueOf(accountUpdateDto.getLocked());
        if (lockedEnum != null) {
            target.setLocked(lockedEnum.locked());
        }

        if (!StrUtil.isEmptyIfStr(accountUpdateDto.getAccountPasswd())) {
            String securityKey = CertUtil.generateAccountSecurityKey();
            String accountCert = CertUtil.genAccountCert(accountUpdateDto.getAccountPasswd(), securityKey);
            target.setAccountSecurityKey(securityKey);
            target.setAccountCert(accountCert);
        }

        this.updateById(target);
    }

}
