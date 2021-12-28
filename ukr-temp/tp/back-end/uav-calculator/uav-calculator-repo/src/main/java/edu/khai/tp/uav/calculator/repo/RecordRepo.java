package edu.khai.tp.uav.calculator.repo;

import edu.khai.tp.uav.calculator.pojo.dbo.RecordDO;

import java.util.List;

public interface RecordRepo {

    List<RecordDO> listAllRecords();

}
