package com.jnj.pangea;

import com.jnj.adf.curation.logic.RawDataEvent;
import com.jnj.adf.curation.logic.ViewResultBuilder;
import com.jnj.adf.curation.logic.ViewResultItem;
import com.jnj.pangea.common.BaseBo;
import com.jnj.pangea.common.FailData;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.ResultObject;
import com.jnj.pangea.common.entity.plan.PlanCnsLocTypeEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsPlantAttrEntity;
import com.jnj.pangea.omp.gdm_location_detail.service.OMPGdmLocationDetailServiceImpl;
import com.jnj.pangea.omp.gdm_location_type.service.OMPGdmLocationTypeServiceImpl;
import com.jnj.pangea.util.BeanUtil;

import java.util.*;

public class CurationDevRunner {

	public static void main(String[] args) {

		// INSTRUCTIONS
		// 1) change the data in single record to match your input data in feature file
		// 2) change service in `process` method below to your service class
		// 3) change type of `dataObject` & parameter in method `process` below to your entity class

		Map<String, Object> singleRecord = new HashMap();
		singleRecord.put("planLocTypeId","CO01");
		singleRecord.put("planLocTypeDesc","Internal Manufacturing Plant");
		singleRecord.put("planLocTypeComments","");

		curateToOne(singleRecord);
//		curateToMany(singleRecord);
	}

	/**
	 * Helpful to call correct curation method.
	 * @param record
	 * @return
	 */
	private static ResultObject curateToOne (Map<String,Object> record) {
		return process(record);
	}

	/**
	 * Calls correct curation method for multiple curations.
	 * @param record
	 * @return
	 */
	private static List<ViewResultItem> curateToMany (Map<String,Object> record) {

		List<Map<String, Object>> multiRecords = new ArrayList<>();
		multiRecords.add(record);

		return process(multiRecords);
	}

	/**
	 * Single record in -> single record out. Standard curation. Most common.
	 * @param dataMap
	 * @return ResultObject
	 */
	private static ResultObject process (Map<String,Object> dataMap) {

		//CHANGE THIS
		OMPGdmLocationTypeServiceImpl service = new OMPGdmLocationTypeServiceImpl();

		//CHANGE THIS
		PlanCnsLocTypeEntity dataObject = BeanUtil.mapToBean(dataMap, PlanCnsLocTypeEntity.class);

		return service.buildView("", dataObject, null);
	}

	/**
	 * Single record in -> many records out. Rare curation. You get list of
	 * records from single record in.
	 * @param list
	 * @return List
	 */
	private static List<ViewResultItem> process (List<Map<String, Object>> list) {

		//CHANGE THIS
		OMPGdmLocationDetailServiceImpl service = new OMPGdmLocationDetailServiceImpl();

		List<ViewResultItem> result = new ArrayList<>();

		list.forEach(raw -> {

			//CHANGE THIS
			PlanCnsPlantAttrEntity dataObject = BeanUtil.mapToBean(raw, PlanCnsPlantAttrEntity.class);

			List<ResultObject> resultObjectList = service.buildView("", dataObject, null);

			//check each record for success
			for (ResultObject resultObject:resultObjectList) {
				if (resultObject.isSuccess()) {
					BaseBo baseBo = resultObject.getBaseBo();
					ViewResultItem viewRaw = ViewResultBuilder.newResultItem(baseBo.getKey(), baseBo.toMap());
					result.add(viewRaw);
				} else if (resultObject.getFailData() != null) {
					FailData failData = resultObject.getFailData();
					ViewResultItem viewResultItem = ViewResultBuilder.newResultItem(IConstant.REGION.FAIL_DATA, failData.getKey(), failData.toMap());
					result.add(viewResultItem);
				}

				//possibility nothing will be added to result list
			}

		});

		return result;
	}

	//example input data:
	//rawDataEvent = [2018-04-26 16:17:32,430 INFO OMPGdmTransportController.process] RawDataValueImpl [pdx=struct(_PK:{"sequenceNumber":"test","tlaneName":"test"},validTo:test,originLocation:test,materialNumber:test,processTypeId:test,validFrom:test,destinationLocation:test,leadTime:test,mode:test,sequenceNumber:test,tlaneName:test)]
	//rawDataEvent List item = [2018-04-26 16:25:44,201 INFO OMPGdmTransportController.lambda$process$0] RawDataValueImpl [pdx=struct(_PK:{"sequenceNumber":"test","tlaneName":"test"},validTo:test,originLocation:test,materialNumber:test,processTypeId:test,validFrom:test,destinationLocation:test,leadTime:test,mode:test,sequenceNumber:test,tlaneName:test)]

}
