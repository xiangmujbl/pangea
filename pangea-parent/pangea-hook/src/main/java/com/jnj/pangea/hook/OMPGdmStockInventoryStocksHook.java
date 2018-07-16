package com.jnj.pangea.hook;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.hook.common.DateUtils;
import com.jnj.pangea.hook.common.IConstant;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class OMPGdmStockInventoryStocksHook {

	/**
	 * INV4
	 *
	 * @param localBatchManagementRequirementIndicator
	 * @param inventoryStockList
	 * @param localBatchId
	 * @param localVendorNumber
	 * @return
	 */
	public static String getBlockedQuantity(String localBatchManagementRequirementIndicator, List<Map.Entry<String, String>> inventoryStockList, String localBatchId, String localVendorNumber) {

		float localBlockedBatchStock =0.0f;
		float localBlockedStock =0.0f;
		float localBlockedConsignmentStock =0.0f;
		float localBlkdConstStkNonBm =0.0f;
		int countTmp1 = 0;
		int countTmp2 = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localConsignmentSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localConsignmentSpecialStockIndicator"));
			String localSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localSpecialStockIndicator"));

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 1
				count1 = count1 + 1;
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 2
				count2 = count2 + 1;
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				// record 3
				count3 = count3 + 1;
			}
		}

		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localConsignmentSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localConsignmentSpecialStockIndicator"));
			String localSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localSpecialStockIndicator"));
			String localBatchIdTmp = String.valueOf(inventoryStockListMap.get("localBatchId"));
			String localVendorNumberTmp = String.valueOf(inventoryStockListMap.get("localVendorNumber"));

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp1 = countTmp1 + 1;
				// record 1
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localBlockedBatchStock")))) {
						localBlockedBatchStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localBlockedBatchStock"))) + localBlockedBatchStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf((int)localBlockedBatchStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					if (StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localBlockedStock")))) {
						localBlockedStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localBlockedStock"))) + localBlockedStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf((int)localBlockedStock);
					}

				}
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp2 = countTmp2 + 1;
				// record 2
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId) && localVendorNumberTmp.equals(localVendorNumber) &&
							StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localBlockedConsignmentStock")))) {
						localBlockedConsignmentStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localBlockedConsignmentStock"))) + localBlockedConsignmentStock;
					}

					if (countTmp2 == count2) {
						return String.valueOf((int)localBlockedConsignmentStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					if (localVendorNumberTmp.equals(localVendorNumber) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localBlkdConstStkNonBm")))) {
						localBlkdConstStkNonBm = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localBlkdConstStkNonBm"))) + localBlkdConstStkNonBm;
					}

					if (countTmp2 == count2) {
						return String.valueOf((int)localBlkdConstStkNonBm);
					}
				}
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				// record 3
				return IConstant.VALUE.ZERO;
			}
		}
		return IConstant.VALUE.ZERO;
	}

	/**
	 * INV10
	 *
	 * @param localBatchManagementRequirementIndicator
	 * @param inventoryStockList
	 * @param localBatchId
	 * @param localVendorNumber
	 * @return
	 */
	public static String getQualityQuantity(String localBatchManagementRequirementIndicator, List<Map.Entry<String, String>> inventoryStockList, String localBatchId, String localVendorNumber) {

		float localQualityInspectionBatchStock = 0.0f;
		float localQualityInspectionStock = 0.0f;
		float localQualityInspectionConsignmentStock = 0.0f;
		float localConsignmentStockInQualityInspection = 0.0f;
		float localQualityInspectionSpecialStock = 0.0f;
		int countTmp1 = 0;
		int countTmp2 = 0;
		int countTmp3 = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localConsignmentSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localConsignmentSpecialStockIndicator"));
			String localSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localSpecialStockIndicator"));

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 1
				count1 = count1 + 1;
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 2
				count2 = count2 + 1;
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				// record 3
				count3 = count3 + 1;
			}
		}

		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localConsignmentSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localConsignmentSpecialStockIndicator"));
			String localSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localSpecialStockIndicator"));
			String localBatchIdTmp = String.valueOf(inventoryStockListMap.get("localBatchId"));
			String localVendorNumberTmp = String.valueOf(inventoryStockListMap.get("localVendorNumber"));

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp1 = countTmp1 + 1;
				// record 1
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localQualityInspectionBatchStock")))) {
						localQualityInspectionBatchStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localQualityInspectionBatchStock"))) + localQualityInspectionBatchStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf((int)localQualityInspectionBatchStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					if (StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localQualityInspectionStock")))) {
						localQualityInspectionStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localQualityInspectionStock"))) + localQualityInspectionStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf((int)localQualityInspectionStock);
					}

				}
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp2 = countTmp2 + 1;
				// record 2
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId) && localVendorNumberTmp.equals(localVendorNumber)
							&& StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localQualityInspectionConsignmentStock")))) {
						localQualityInspectionConsignmentStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localQualityInspectionConsignmentStock"))) + localQualityInspectionConsignmentStock;
					}

					if (countTmp2 == count2) {
						return String.valueOf((int)localQualityInspectionConsignmentStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					if (localVendorNumberTmp.equals(localVendorNumber) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localConsignmentStockInQualityInspection")))) {
						localConsignmentStockInQualityInspection = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localConsignmentStockInQualityInspection"))) + localConsignmentStockInQualityInspection;
					}

					if (countTmp2 == count2) {
						return String.valueOf((int)localConsignmentStockInQualityInspection);
					}

				}
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				countTmp3 = countTmp3 + 1;
				// record 3
				if (localVendorNumberTmp.equals(localVendorNumber) && localBatchIdTmp.equals(localBatchId) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localQualityInspectionSpecialStock")))) {
					localQualityInspectionSpecialStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localQualityInspectionSpecialStock"))) + localQualityInspectionSpecialStock;
				}

				if (countTmp3 == count3) {
					return String.valueOf((int)localQualityInspectionSpecialStock);
				}

			}
		}
		return IConstant.VALUE.ZERO;
	}


	/**
	 * INV11
	 *
	 * @param localBatchManagementRequirementIndicator
	 * @param inventoryStockList
	 * @param localBatchId
	 * @param localVendorNumber
	 * @return
	 */
	public static String getQuantity(String localBatchManagementRequirementIndicator, List<Map.Entry<String, String>> inventoryStockList, String localBatchId, String localVendorNumber) {

		float localUnrestrictedBatchStock = 0.0f;
		float localUnrestrictedStock = 0.0f;
		float localUnrestrictedConsignmentStock = 0.0f;
		float localUnrestrictedUseConsignment = 0.0f;
		float localUnrestrictedSpecialStock = 0.0f;
		int countTmp1 = 0;
		int countTmp2 = 0;
		int countTmp3 = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localConsignmentSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localConsignmentSpecialStockIndicator"));
			String localSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localSpecialStockIndicator"));

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 1
				count1 = count1 + 1;
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 2
				count2 = count2 + 1;
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				// record 3
				count3 = count3 + 1;
			}
		}

		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localConsignmentSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localConsignmentSpecialStockIndicator"));
			String localSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localSpecialStockIndicator"));
			String localBatchIdTmp = String.valueOf(inventoryStockListMap.get("localBatchId"));
			String localVendorNumberTmp = String.valueOf(inventoryStockListMap.get("localVendorNumber"));

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp1 = countTmp1 + 1;
				// record 1
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localUnrestrictedBatchStock")))) {
						localUnrestrictedBatchStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localUnrestrictedBatchStock"))) + localUnrestrictedBatchStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf((int)localUnrestrictedBatchStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					if (StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localUnrestrictedStock")))) {
						localUnrestrictedStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localUnrestrictedStock"))) + localUnrestrictedStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf((int)localUnrestrictedStock);
					}

				}
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp2 = countTmp2 + 1;
				// record 2
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId) && localVendorNumberTmp.equals(localVendorNumber)
							&& StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localUnrestrictedConsignmentStock")))) {
						localUnrestrictedConsignmentStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localUnrestrictedConsignmentStock"))) + localUnrestrictedConsignmentStock;
					}

					if (countTmp2 == count2) {
						return String.valueOf((int)localUnrestrictedConsignmentStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					if (localVendorNumberTmp.equals(localVendorNumber) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localUnrestrictedUseConsignment")))) {
						localUnrestrictedUseConsignment = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localUnrestrictedUseConsignment"))) + localUnrestrictedUseConsignment;
					}

					if (countTmp2 == count2) {
						return String.valueOf((int)localUnrestrictedUseConsignment);
					}

				}
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				countTmp3 = countTmp3 + 1;
				// record 3
				if (localVendorNumberTmp.equals(localVendorNumber) && localBatchIdTmp.equals(localBatchId) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localUnrestrictedSpecialStock")))) {
					localUnrestrictedSpecialStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localUnrestrictedSpecialStock"))) + localUnrestrictedSpecialStock;
				}

				if (countTmp3 == count3) {
					return String.valueOf((int)localUnrestrictedSpecialStock);
				}
			}
		}
		return IConstant.VALUE.ZERO;
	}


	/**
	 * INV13
	 *
	 * @param localBatchManagementRequirementIndicator
	 * @param inventoryStockList
	 * @param localBatchId
	 * @param localVendorNumber
	 * @return
	 */
	public static String getRestrictedQuantity(String localBatchManagementRequirementIndicator, List<Map.Entry<String, String>> inventoryStockList, String localBatchId, String localVendorNumber) {

		float localRestrictedBatchStock = 0.0f;
		float localRestrictedStock = 0.0f;
		float localRestrictedConsignmentStock = 0.0f;
		float localRestrictedUseConsignment = 0.0f;
		float localRestrictedSpecialStock = 0.0f;
		int countTmp1 = 0;
		int countTmp2 = 0;
		int countTmp3 = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localConsignmentSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localConsignmentSpecialStockIndicator"));
			String localSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localSpecialStockIndicator"));

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 1
				count1 = count1 + 1;
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 2
				count2 = count2 + 1;
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				// record 3
				count3 = count3 + 1;
			}
		}

		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localConsignmentSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localConsignmentSpecialStockIndicator"));
			String localSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localSpecialStockIndicator"));
			String localBatchIdTmp = String.valueOf(inventoryStockListMap.get("localBatchId"));
			String localVendorNumberTmp = String.valueOf(inventoryStockListMap.get("localVendorNumber"));

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp1 = countTmp1 + 1;
				// record 1
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if(localBatchIdTmp.equals(localBatchId) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localRestrictedBatchStock")))){
						localRestrictedBatchStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localRestrictedBatchStock"))) + localRestrictedBatchStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf((int)localRestrictedBatchStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					if (StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localRestrictedStock")))) {
						localRestrictedStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localRestrictedStock"))) + localRestrictedStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf((int)localRestrictedStock);
					}

				}
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp2 = countTmp2 + 1;
				// record 2
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if(localBatchIdTmp.equals(localBatchId) && localVendorNumberTmp.equals(localVendorNumber)
							&& StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localRestrictedConsignmentStock")))) {
						localRestrictedConsignmentStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localRestrictedConsignmentStock"))) + localRestrictedConsignmentStock;
					}

					if (countTmp2 == count2) {
						return String.valueOf((int)localRestrictedConsignmentStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					if(localVendorNumberTmp.equals(localVendorNumber) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localRestrictedUseConsignment")))) {
						localRestrictedUseConsignment = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localRestrictedUseConsignment"))) + localRestrictedUseConsignment;
					}

					if (countTmp2 == count2) {
						return String.valueOf((int)localRestrictedUseConsignment);
					}

				}
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				countTmp3 = countTmp3 + 1;
				// record 3
				if(localVendorNumberTmp.equals(localVendorNumber) && localBatchIdTmp.equals(localBatchId) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localRestrictedSpecialStock")))) {
					localRestrictedSpecialStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localRestrictedSpecialStock"))) + localRestrictedSpecialStock;
				}

				if (countTmp3 == count3) {
					return String.valueOf((int)localRestrictedSpecialStock);
				}

			}
		}
		return IConstant.VALUE.ZERO;
	}


	/**
	 *
	 * @param localBatchManagementRequirementIndicator
	 * @param inventoryStockList
	 * @param localBatchId
	 * @return
	 */
	public static String getReturnsQuantity(String localBatchManagementRequirementIndicator, List<Map.Entry<String, String>> inventoryStockList, String localBatchId) {

		float localReturnsBatchStock = 0.0f;
		float localReturnsStock = 0.0f;
		int countTmp1 = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localConsignmentSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localConsignmentSpecialStockIndicator"));
			String localSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localSpecialStockIndicator"));

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 1
				count1 = count1 + 1;
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 2
				count2 = count2 + 1;
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				// record 3
				count3 = count3 + 1;
			}
		}

		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localConsignmentSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localConsignmentSpecialStockIndicator"));
			String localSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localSpecialStockIndicator"));
			String localBatchIdTmp = String.valueOf(inventoryStockListMap.get("localBatchId"));

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp1 = countTmp1 + 1;
				// record 1
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localReturnsBatchStock")))) {
						localReturnsBatchStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localReturnsBatchStock"))) + localReturnsBatchStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf((int)localReturnsBatchStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					if (StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localReturnsStock")))) {
						localReturnsStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localReturnsStock"))) + localReturnsStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf((int)localReturnsStock);
					}

				}
			}
		}
		return IConstant.VALUE.ZERO;
	}


	/**
	 * INV16
	 *
	 * @param localBatchManagementRequirementIndicator
	 * @param inventoryStockList
	 * @param localBatchId
	 * @param localVendorNumber
	 * @return
	 */
	public static String getTransferQuantity(String localBatchManagementRequirementIndicator, List<Map.Entry<String, String>> inventoryStockList, String localBatchId, String localVendorNumber) {

		float localStockInTransitBatch = 0.0f;
		float localStockInTransitPlantToPlant = 0.0f;
		float localStockInTransitSpecial = 0.0f;
		int countTmp1 = 0;
		int countTmp2 = 0;
		int countTmp3 = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localConsignmentSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localConsignmentSpecialStockIndicator"));
			String localSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localSpecialStockIndicator"));

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 1
				count1 = count1 + 1;
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 2
				count2 = count2 + 1;
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				// record 3
				count3 = count3 + 1;
			}
		}

		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localConsignmentSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localConsignmentSpecialStockIndicator"));
			String localSpecialStockIndicator = String.valueOf(inventoryStockListMap.get("localSpecialStockIndicator"));
			String localBatchIdTmp = String.valueOf(inventoryStockListMap.get("localBatchId"));
			String localVendorNumberTmp = String.valueOf(inventoryStockListMap.get("localVendorNumber"));

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp1 = countTmp1 + 1;
				// record 1
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localStockInTransitBatch")))) {
						localStockInTransitBatch = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localStockInTransitBatch"))) + localStockInTransitBatch;
					}

					if (countTmp1 == count1) {
						return String.valueOf((int)localStockInTransitBatch);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					if (StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localStockInTransitPlantToPlant")))) {
						localStockInTransitPlantToPlant = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localStockInTransitPlantToPlant"))) + localStockInTransitPlantToPlant;
					}

					if (countTmp1 == count1) {
						return String.valueOf((int)localStockInTransitPlantToPlant);
					}
				}
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 2
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					return IConstant.VALUE.ZERO;
				}
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				countTmp3 = countTmp3 + 1;
				// record 3
				if (localVendorNumberTmp.equals(localVendorNumber) && localBatchIdTmp.equals(localBatchId) && StringUtils.isNotEmpty(String.valueOf(inventoryStockListMap.get("localStockInTransitSpecial")))) {
					localStockInTransitSpecial = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localStockInTransitSpecial"))) + localStockInTransitSpecial;
				}

				if (countTmp3 == count3) {
					return String.valueOf((int)localStockInTransitSpecial);
				}
			}
		}
		return IConstant.VALUE.ZERO;
	}

	/**
	 *
	 * @param s
	 * @return
	 */
	public static String stampToDate(String s) {
		String res;
		String data;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		long lt = new Long(s);
		Date date = DateUtils.changeDate(new Date(), -1,0,0,0,0,0);
		res = simpleDateFormat.format(date);
		data = res + " " + "00:00:00";
		return data;
	}

	/**
	 *
	 * @param inventoryStockList
	 * @param localMaterial
	 * @param localBatchId
	 * @param localVendorNumber
	 * @param localStorageLocation
	 * @return
	 */
	public static boolean checkStockId(List<Map.Entry<String, String>> inventoryStockList,
									   String localMaterial, String localBatchId, String localVendorNumber, String localStorageLocation) {
		String localStorageLocationCheck = "";
		int count = 0;
		for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

			Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

			String localMaterialTmp = String.valueOf(inventoryStockListMap.get("localMaterial"));
			String localBatchIdTmp = String.valueOf(inventoryStockListMap.get("localBatchId"));
			String localVendorNumberTmp = String.valueOf(inventoryStockListMap.get("localVendorNumber"));

			if (localMaterialTmp.equals(localMaterial) && localBatchIdTmp.equals(localBatchId)
					&& localVendorNumberTmp.equals(localVendorNumber)) {

				count++ ;
			}
		}

		if (count >= 2) {
			for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

				Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

				String localMaterialTmp = String.valueOf(inventoryStockListMap.get("localMaterial"));
				String localBatchIdTmp = String.valueOf(inventoryStockListMap.get("localBatchId"));
				String localVendorNumberTmp = String.valueOf(inventoryStockListMap.get("localVendorNumber"));
				String localStorageLocationTmp = String.valueOf(inventoryStockListMap.get("localStorageLocation"));

				if (localMaterialTmp.equals(localMaterial) && localBatchIdTmp.equals(localBatchId)
						&& localVendorNumberTmp.equals(localVendorNumber)) {

					localStorageLocationCheck = localStorageLocationTmp;
					break;
				}
			}

			for (Map.Entry<String, String> inventoryStockListEntry : inventoryStockList) {

				Map<String, Object> inventoryStockListMap = JsonObject.append(inventoryStockListEntry.getValue()).toMap();

				String localMaterialTmp = String.valueOf(inventoryStockListMap.get("localMaterial"));
				String localBatchIdTmp = String.valueOf(inventoryStockListMap.get("localBatchId"));
				String localVendorNumberTmp = String.valueOf(inventoryStockListMap.get("localVendorNumber"));

				if (localMaterialTmp.equals(localMaterial) && localBatchIdTmp.equals(localBatchId)
						&& localVendorNumberTmp.equals(localVendorNumber) &&
						(!localStorageLocation.equals(localStorageLocationCheck))) {

					return false;
				}

			}
		}

		return true;

	}


	/**
	 *
	 * @param cnsTlaneControlTriangulationList
	 * @return
	 */
	public static String getLocalPlantByTriangulation (List<Map.Entry<String, String>> cnsTlaneControlTriangulationList) {

		String localPlant = null;
		int stepNumberCount = 0;

		for (Map.Entry<String, String> cnsTlaneControlTriangulationListEntry : cnsTlaneControlTriangulationList) {

			Map<String, Object> cnsTlaneControlTriangulationListMap = JsonObject.append(cnsTlaneControlTriangulationListEntry.getValue()).toMap();

			String destinationLocation = String.valueOf(cnsTlaneControlTriangulationListMap.get("destinationLocation"));
			String stepNumber = String.valueOf(cnsTlaneControlTriangulationListMap.get("stepNumber"));
			int stepNumberInt = Integer.parseInt(stepNumber);

			if (stepNumberCount < stepNumberInt) {

				stepNumberCount = Integer.parseInt(stepNumber);
				localPlant = destinationLocation.substring(destinationLocation.length()-4, destinationLocation.length());
			}
		}
		return localPlant;
	}
}
