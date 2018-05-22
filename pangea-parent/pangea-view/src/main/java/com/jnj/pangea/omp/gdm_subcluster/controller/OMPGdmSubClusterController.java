package com.jnj.pangea.omp.gdm_subcluster.controller;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.controller.CommonController;
import com.jnj.pangea.common.entity.plan.PlanCnsClustersEntity;
import com.jnj.pangea.common.service.ICommonService;
import com.jnj.pangea.omp.gdm_subcluster.service.OMPGdmSubClusterServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OMPGdmSubClusterController extends CommonController{

    private ICommonService service = OMPGdmSubClusterServiceImpl.getInstance();

    private Map<String, Object> extraParam = new HashMap<>();

    @Override
    public List<ViewResultItem> process(List<RawDataEvent> events) {

        List<ViewResultItem> result = new LinkedList<>();
        HashMap<String, String> existing = new HashMap<String, String>();
        events.forEach(raw -> {
            ResultObject resultObject = process(raw);
            if (null != resultObject) {
                if(resultObject.isSuccess()){
                    //Rule R1
                    HashMap<String,Object> myBo = new HashMap(resultObject.getBaseBo().toMap());
                   String cid = (String) myBo.get("clusterId");
                    String subCid = (String) myBo.get("subClusterId");
                    if(null == existing.get(subCid)){
                        existing.put(subCid,cid);
                        result.add(ViewResultBuilder.newResultItem(resultObject.getBaseBo().getKey(), resultObject.getBaseBo().toMap()));
                    }

                } else {
                    if (null != resultObject.getFailData()) {
                        FailData failData = resultObject.getFailData();
                        result.add(ViewResultBuilder.newResultItem(failData.getFailRegion(), failData.getKey(), failData.toMap()));
                    }
                }
            }
        });
        return result;
    }
    @Override
    public ResultObject process(RawDataEvent raw) {
        return service.buildView(raw.getKey(), BeanUtil.mapToBean(raw.getValue().toMap(), PlanCnsClustersEntity.class), extraParam);
    }
}
