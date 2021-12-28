package edu.khai.tp.uav.calculator.pojo.dto.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CameraAddDTO {

    private String uavCameraName;

    private String uavCameraImageSizeLength;

    private String uavCameraImageSizeWidth;

    private String uavCameraSensorSizeLength;

    private String uavCameraSensorSizeWidth;

    private String uavCameraPixelSize;

    private String uavCameraFocalLength;

    private Integer uavCameraParamMode;

}
