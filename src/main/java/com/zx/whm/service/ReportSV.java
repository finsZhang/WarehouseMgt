package com.zx.whm.service;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.vo.ShipmentRecordTotalVo;

import java.util.List;

/**
 * Created by Walter on 2017/4/20.
 */

public interface ReportSV {
    public ResultDTO queryPageList(final ShipmentRecordTotalVo shipmentRecordTotalVo, ResultDTO resultDTO) throws Exception;
    public List<ShipmentRecordTotalVo> queryReportList(final ShipmentRecordTotalVo shipmentRecordTotalVo) throws Exception;
}
