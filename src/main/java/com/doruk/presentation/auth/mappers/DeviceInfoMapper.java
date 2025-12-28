package com.doruk.presentation.auth.mappers;

import com.doruk.application.auth.dto.DeviceInfo;
import com.doruk.presentation.auth.dto.DeviceInfoRequest;
import io.micronaut.context.annotation.Mapper;

public interface DeviceInfoMapper {
    @Mapper
    DeviceInfo toDeviceInfo(DeviceInfoRequest req);
}
