package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.ProjectOneVbfaEntity;

/**
 * @Name: ProjectOneVbfaDaoImpl
 * @Description: dao for vbfa project one system
 * @author KG(Kelvin Gu)
 * @date 06-12-2018 03:49:18
*/
public class ProjectOneVbfaDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_VBFA = "/project_one/vbfa";

    public static final String VBELV = "vbelv";
    public static final String POSNV = "posnv";
    public static final String VBELN = "vbeln";
    public static final String POSNN = "posnn";
    public static final String VBTYP_N = "vbtypN";
    public static final String RFMNG = "rfmng";
    public static final String MEINS = "meins";
    public static final String RFMNG_FLT = "rfmngFlt";
    public static final String VRKME = "vrkme";
    public static final String VBTYP_V = "vbtypV";
    public static final String ERDAT = "erdat";

    private static ProjectOneVbfaDaoImpl instance;

    public static ProjectOneVbfaDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneVbfaDaoImpl();
        }
        return instance;
    }

    /*public ProjectOneVbfaEntity getVbfaEntityByaAndb(String a, String b) {
        String queryString = QueryHelper.buildCriteria
             (A1).is(a)
             .and(B1).is(b)
            .toQueryString();
        ProjectOneVbfaEntity entity = queryForObject(PROJECT_ONE_VBFA_CLONE, queryString, ProjectOneVbfaEntity.class);
        return entity;
    }*/
}
