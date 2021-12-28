package edu.khai.tp.uav.calculator.repo;

import edu.khai.tp.uav.calculator.pojo.dbo.UAVCameraParamDO;

import java.util.List;

public interface UAVCameraParamRepo {

    List<UAVCameraParamDO> listAllUAVCameraParams();

    UAVCameraParamDO getUAVCameraParamByUuid(String uuid);

    int insertUAVCameraParam(UAVCameraParamDO param);

    int deleteUAVCameraParamByUuid(String uuid);

}
