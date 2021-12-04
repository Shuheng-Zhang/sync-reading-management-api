package com.heng.sync.reading.management.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heng.sync.reading.management.api.entity.AppGlobalConfigInfo;

public interface AppGlobalConfigInfoMapper extends BaseMapper<AppGlobalConfigInfo> {

    /**
     * 删除指定用户应用全局配置信息
     * @param accountId 目标用户账号ID
     * @return
     */
    int deleteByAccountId(String accountId);

    /**
     * 查询应用全局配置信息
     * @param accountId 目标用户账号ID
     * @return
     */
    AppGlobalConfigInfo queryByAccountId(String accountId);
}