package com.heng.sync.reading.management.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heng.sync.reading.management.api.entity.AccountInfo;

public interface AccountInfoMapper extends BaseMapper<AccountInfo> {

    /**
     * 查询用户信息是否存在
     * @param accountName 用户账号名
     * @return
     */
    Integer queryExistedByAccountName(String accountName);

    /**
     * 获取用户账号信息
     * @param accountName 用户账号名
     * @return 用户账号信息
     */
    AccountInfo queryByAccountName(String accountName);
}