package com.heng.sync.reading.management.api.service.data;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heng.sync.reading.management.api.commons.enums.RespEnum;
import com.heng.sync.reading.management.api.commons.exception.BusinessException;
import com.heng.sync.reading.management.api.dto.config.AppGlobalConfigOptDto;
import com.heng.sync.reading.management.api.dto.config.AppGlobalConfigQueryDto;
import com.heng.sync.reading.management.api.entity.AccountInfo;
import com.heng.sync.reading.management.api.entity.AppGlobalConfigInfo;
import com.heng.sync.reading.management.api.mapper.AccountInfoMapper;
import com.heng.sync.reading.management.api.mapper.AppGlobalConfigInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AppGlobalConfigInfoService extends ServiceImpl<AppGlobalConfigInfoMapper, AppGlobalConfigInfo> {

    @Resource
    private AccountInfoMapper accountInfoMapper;

    /**
     * 新建/更新用户应用全局配置信息
     * @param optDto 应用全局配置信息操作数据对象
     */
    public void createOrUpdateAppGlobalConfig(AppGlobalConfigOptDto optDto) {
        if (optDto == null || StrUtil.isEmptyIfStr(optDto.getAccountId())) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        AccountInfo accountInfo = accountInfoMapper.selectById(optDto.getAccountId());
        if (accountInfo == null) {
            throw new BusinessException(RespEnum.USER_INFO_INVALID);
        }

        AppGlobalConfigInfo appGlobalConfigInfo = this.baseMapper.queryByAccountId(optDto.getAccountId());
        if (appGlobalConfigInfo == null) {
            this.baseMapper.insert(new AppGlobalConfigInfo(
                    null,
                    optDto.getAccountId(),
                    optDto.getAppTheme(),
                    optDto.getAppInfoSyncMode(),
                    optDto.getReaderTheme(),
                    optDto.getReaderFontSize(),
                    optDto.getReaderFontFamily(),
                    optDto.getReaderTextSpace()
            ));
        } else {
            appGlobalConfigInfo.setAppTheme(optDto.getAppTheme());
            appGlobalConfigInfo.setAppInfoSyncMode(optDto.getAppInfoSyncMode());
            appGlobalConfigInfo.setReaderTheme(optDto.getReaderTheme());
            appGlobalConfigInfo.setReaderFontSize(optDto.getReaderFontSize());
            appGlobalConfigInfo.setReaderFontFamily(optDto.getReaderFontFamily());
            appGlobalConfigInfo.setReaderTextSpace(optDto.getReaderTextSpace());
            this.baseMapper.updateById(appGlobalConfigInfo);
        }
    }

    /**
     * 查询指定用户应用全局配置信息
     * @param queryDto 查询数据对象
     * @return
     */
    public AppGlobalConfigInfo findByAccountId(AppGlobalConfigQueryDto queryDto) {
        if (queryDto == null || StrUtil.isEmptyIfStr(queryDto.getAccountId())) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }

        return this.baseMapper.queryByAccountId(queryDto.getAccountId());
    }

    /**
     * 删除指定用户应用全局配置信息
     * @param queryDto 查询数据对象
     */
    public void deleteAppGlobalConfigInfo(AppGlobalConfigQueryDto queryDto) {
        if (queryDto == null || StrUtil.isEmptyIfStr(queryDto.getAccountId())) {
            throw new BusinessException(RespEnum.PARAMS_INVALID);
        }
        this.baseMapper.deleteByAccountId(queryDto.getAccountId());
    }
}
