package edu.khai.tp.uav.calculator.service.impl;

import edu.khai.tp.uav.calculator.pojo.dbo.UAVCameraParamDO;
import edu.khai.tp.uav.calculator.pojo.dto.camera.CameraAddDTO;
import edu.khai.tp.uav.calculator.pojo.vo.camera.CameraInfoVO;
import edu.khai.tp.uav.calculator.repo.UAVCameraParamRepo;
import edu.khai.tp.uav.calculator.service.UAVCameraParamService;
import edu.khai.tp.uav.calculator.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UAVCameraParamServiceImpl implements UAVCameraParamService {

    @Autowired
    private UAVCameraParamRepo uavCameraParamRepo;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @Override
    public int addUAVCameraParam(CameraAddDTO cameraAddDTO) {
        UAVCameraParamDO uavCameraParamDO = new UAVCameraParamDO();

        uavCameraParamDO.setCpUuid(StringUtils.generateUuid());
        uavCameraParamDO.setCpName(cameraAddDTO.getUavCameraName());
        uavCameraParamDO.setCpImageSizeLength(Integer.parseInt(cameraAddDTO.getUavCameraImageSizeLength()));
        uavCameraParamDO.setCpImageSizeWidth(Integer.parseInt(cameraAddDTO.getUavCameraImageSizeWidth()));
        uavCameraParamDO.setCpFocalLength(new BigDecimal(cameraAddDTO.getUavCameraFocalLength()));

        if (cameraAddDTO.getUavCameraParamMode() == 1) {
            uavCameraParamDO.setCpImageSensorSizeLength(new BigDecimal(cameraAddDTO.getUavCameraSensorSizeLength()));
            uavCameraParamDO.setCpImageSensorSizeWidth(new BigDecimal(cameraAddDTO.getUavCameraSensorSizeWidth()));
            uavCameraParamDO.setCpPixelSize(null);
        } else {
            uavCameraParamDO.setCpImageSensorSizeLength(null);
            uavCameraParamDO.setCpImageSensorSizeWidth(null);
            uavCameraParamDO.setCpPixelSize(new BigDecimal(cameraAddDTO.getUavCameraPixelSize()));
        }

        uavCameraParamDO.setCpIsDeleted(0);

        BigInteger t = new BigInteger(System.currentTimeMillis() + "");
        uavCameraParamDO.setCpCreateTime(t);
        uavCameraParamDO.setCpUpdateTime(t);

        return uavCameraParamRepo.insertUAVCameraParam(uavCameraParamDO);
    }

    @Override
    public List<CameraInfoVO> listAllUAVCameraParams() {
        List<UAVCameraParamDO> uavCameraParamDOList = uavCameraParamRepo.listAllUAVCameraParams();

        List<CameraInfoVO> cameraInfoVOList = new ArrayList<>();

        CameraInfoVO vo = null;

        for (UAVCameraParamDO val : uavCameraParamDOList) {
            vo = new CameraInfoVO();

            vo.setCpUuid(val.getCpUuid());
            vo.setCpName(val.getCpName());
            vo.setCpImageSizeLength(val.getCpImageSizeLength());
            vo.setCpImageSizeWidth(val.getCpImageSizeWidth());
            vo.setCpImageSensorSizeLength(val.getCpImageSensorSizeLength());
            vo.setCpImageSensorSizeWidth(val.getCpImageSensorSizeWidth());
            vo.setCpPixelSize(val.getCpPixelSize());
            vo.setCpFocalLength(val.getCpFocalLength());
            vo.setCpCreateTime(dateFormat.format(Long.parseLong(val.getCpCreateTime().toString())));

            cameraInfoVOList.add(vo);
        }

        return cameraInfoVOList;
    }

    @Override
    public int removeUAVCameraParam(String uuid) {
        return uavCameraParamRepo.deleteUAVCameraParamByUuid(uuid);
    }
}
