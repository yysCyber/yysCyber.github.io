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
public class RecordDO {

    private String recordUuid;

    private UAVCameraParamDO cameraParam;

    private Integer recordFlyingHeight;

    private Integer recordFlyingSpeed;

    private BigDecimal recordGSD;

    private Integer recordLongitudinalOverlap;

    private Integer recordLateralOverlap;

    private Integer recordActualSizeLength;

    private Integer recordActualSizeWidth;

    private BigDecimal recordPhotographyDistance;

    private BigDecimal recordRouteDistance;

    private BigDecimal recordPhotoTimeInterval;

    private Integer recordIsDeleted;

    private BigInteger recordCreateTime;

    private BigInteger recordUpdateTime;

}
