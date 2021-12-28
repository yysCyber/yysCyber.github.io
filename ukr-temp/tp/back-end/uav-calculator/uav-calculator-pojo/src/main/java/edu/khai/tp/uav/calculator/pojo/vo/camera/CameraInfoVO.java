package edu.khai.tp.uav.calculator.pojo.vo.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CameraInfoVO {

    private String cpUuid;

    private String cpName;

    private Integer cpImageSizeLength;

    private Integer cpImageSizeWidth;

    private BigDecimal cpImageSensorSizeLength;

    private BigDecimal cpImageSensorSizeWidth;

    private BigDecimal cpFocalLength;

    private BigDecimal cpPixelSize;

    private String cpCreateTime;

}
