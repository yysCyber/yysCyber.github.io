package edu.khai.tp.uav.calculator.pojo.dbo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UAVCameraParamDO {

    private String cpUuid;

    private String cpName;

    private Integer cpImageSizeLength;

    private Integer cpImageSizeWidth;

    private BigDecimal cpImageSensorSizeLength;

    private BigDecimal cpImageSensorSizeWidth;

    private BigDecimal cpFocalLength;

    private BigDecimal cpPixelSize;

    private Integer cpIsDeleted;

    private BigInteger cpCreateTime;

    private BigInteger cpUpdateTime;

}