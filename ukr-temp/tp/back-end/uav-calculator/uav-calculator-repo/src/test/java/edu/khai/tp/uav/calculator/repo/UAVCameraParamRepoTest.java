package edu.khai.tp.uav.calculator.repo;

import edu.khai.tp.uav.calculator.pojo.dbo.RecordDO;
import edu.khai.tp.uav.calculator.pojo.dbo.UAVCameraParamDO;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-repo.xml"})
public class UAVCameraParamRepoTest {

    @Autowired
    private UAVCameraParamRepo repo;

    @Autowired
    private RecordRepo recordRepo;

    @Test
    public void test1() {
        List<UAVCameraParamDO> list = repo.listAllUAVCameraParams();
        for (UAVCameraParamDO param : list) {
            System.out.println(param);
        }
    }

    @Test
    public void test2() {
        UAVCameraParamDO param = repo.getUAVCameraParamByUuid("2500ABc");
        System.out.println(param);
    }

    @Test
    public void test3() {
        List<RecordDO> recordDOList = recordRepo.listAllRecords();
        for (RecordDO recordDO : recordDOList) {
            System.out.println(recordDO);
        }
    }

    @Test
    public void test4() {
        UAVCameraParamDO param = repo.getUAVCameraParamByUuid("2500ABc");
        System.out.println(param);
        param.setCpUuid("12152451");
        param.setCpImageSensorSizeLength(null);
        param.setCpImageSensorSizeWidth(null);
        param.setCpPixelSize(new BigDecimal("4.8777"));
        param.setCpCreateTime(new BigInteger(System.currentTimeMillis() + ""));
        param.setCpUpdateTime(new BigInteger(System.currentTimeMillis() + ""));
        repo.insertUAVCameraParam(param);
    }

}
