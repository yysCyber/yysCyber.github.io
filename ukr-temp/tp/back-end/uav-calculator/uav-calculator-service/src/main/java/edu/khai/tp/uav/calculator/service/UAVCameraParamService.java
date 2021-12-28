package edu.khai.tp.uav.calculator.service;

import edu.khai.tp.uav.calculator.pojo.dto.camera.CameraAddDTO;
import edu.khai.tp.uav.calculator.pojo.vo.camera.CameraInfoVO;

import java.util.List;

public interface UAVCameraParamService {

    int addUAVCameraParam(CameraAddDTO cameraAddDTO);

    List<CameraInfoVO> listAllUAVCameraParams();

    int removeUAVCameraParam(String uuid);

}
