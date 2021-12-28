package edu.khai.tp.uav.calculator.web;

import edu.khai.tp.uav.calculator.pojo.dto.camera.CameraAddDTO;
import edu.khai.tp.uav.calculator.pojo.vo.GenericResponseListVO;
import edu.khai.tp.uav.calculator.pojo.vo.GenericResponseVO;
import edu.khai.tp.uav.calculator.pojo.vo.camera.CameraInfoVO;
import edu.khai.tp.uav.calculator.service.UAVCameraParamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aerial-photography")
public class UAVCameraParamAPI {

    @Autowired
    private UAVCameraParamService uavCameraParamService;

    @PostMapping("/uav-camera/add")
    public GenericResponseVO<Object> addUAVCameraParam(@RequestBody CameraAddDTO dto) {
        GenericResponseVO<Object> responseVO = new GenericResponseVO<>();

        int i = uavCameraParamService.addUAVCameraParam(dto);

        responseVO.setState(i == 1);
        responseVO.setCode(i == 1 ? "200" : "50x");
        responseVO.setDescription(i == 1 ? "Operation Successful!" : "Operation failed");
        responseVO.setData(null);

        return responseVO;
    }

    @GetMapping("/uav-camera/all")
    public GenericResponseListVO<CameraInfoVO> listAllUAVCameraParams() {
        GenericResponseListVO<CameraInfoVO> responseListVO = new GenericResponseListVO<>();

        List<CameraInfoVO> cameraInfoVOList = uavCameraParamService.listAllUAVCameraParams();

        responseListVO.setState(true);
        responseListVO.setCode("200");
        responseListVO.setDescription("Operation Successful!" );
        responseListVO.setData(cameraInfoVOList.size() == 0 ? null : cameraInfoVOList);

        return responseListVO;
    }

    @GetMapping("/uav-camera/remove/{uuid}")
    public GenericResponseVO<Object> removeUAVCameraParam(@PathVariable("uuid") String uuid) {
        GenericResponseVO<Object> responseVO = new GenericResponseVO<>();

        int i = uavCameraParamService.removeUAVCameraParam(uuid);

        responseVO.setState(i == 1);
        responseVO.setCode(i == 1 ? "200" : "50x");
        responseVO.setDescription(i == 1 ? "Operation Successful!" : "Operation failed");
        responseVO.setData(null);

        return responseVO;
    }

}
