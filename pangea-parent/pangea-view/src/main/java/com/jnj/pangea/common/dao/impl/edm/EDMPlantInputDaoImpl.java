package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMPlantInputEntity;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.internal.compiler.ast.NullLiteral;

public class EDMPlantInputDaoImpl extends CommonDaoImpl {
    private static EDMPlantInputDaoImpl instance;

    public static EDMPlantInputDaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMPlantInputDaoImpl();
        }
        return instance;
    }

    public EDMPlantInputEntity getPlantWithSourceSystemAndLocalPlant(String sourceSystem,String localPlant) {
//        if (IConstant.EDM_PLANT_INPUT.LOCAL_PLANT == ""){
//            return null;
//        }
//        String queryString = QueryHelper.buildCriteria(IConstant.EDM_PLANT_INPUT.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.EDM_PLANT_INPUT.LOCAL_PLANT).is(localPlant).toQueryString();
//        return queryForObject(IConstant.REGION.EDM_PLANT_INPUT,queryString,EDMPlantInputEntity.class);
        return null;
    }
}
