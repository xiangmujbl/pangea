package com.jnj.pangea;

import com.jnj.adf.client.api.JsonObject;
import com.jnj.pangea.common.IConstant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class OMPGdmStockInventoryStocksHook {

	/**
	 *
	 * @param localBatchManagementRequirementIndicator
	 * @param inventoryStockList
	 * @return
	 */
	public static String getBlockedQuantity(String localBatchManagementRequirementIndicator, List<Map.Entry<String, String>> inventoryStockList, String localBatchId) {

		float localBlockedBatchStock =0.0f;
		float localBlockedStock =0.0f;
		float localBlockedConsignmentStock =0.0f;
		float localBlkdConstStkNonBm =0.0f;
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

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp1 = countTmp1 + 1;
				// record 1
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId)) {
						localBlockedBatchStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localBlockedBatchStock"))) + localBlockedBatchStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf(localBlockedBatchStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					localBlockedStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localBlockedStock"))) + localBlockedStock;
					if (countTmp1 == count1) {
						return String.valueOf(localBlockedStock);
					}

				}
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp2 = countTmp2 + 1;
				// record 2
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId)) {
						localBlockedConsignmentStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localBlockedConsignmentStock"))) + localBlockedConsignmentStock;
					}

					if (countTmp2 == count2) {
						return String.valueOf(localBlockedConsignmentStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					localBlkdConstStkNonBm = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localBlkdConstStkNonBm"))) + localBlkdConstStkNonBm;
					if (countTmp2 == count2) {
						return String.valueOf(localBlkdConstStkNonBm);
					}
				}
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				// record 3
				return IConstant.VALUE.FLOAT_ZERO;
			}
		}
		return IConstant.VALUE.FLOAT_ZERO;
	}

	/**
	 *
	 * @param localBatchManagementRequirementIndicator
	 * @param inventoryStockList
	 * @return
	 */
	public static String getQualityQuantity(String localBatchManagementRequirementIndicator, List<Map.Entry<String, String>> inventoryStockList, String localBatchId) {

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

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp1 = countTmp1 + 1;
				// record 1
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId)) {
						localQualityInspectionBatchStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localQualityInspectionBatchStock"))) + localQualityInspectionBatchStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf(localQualityInspectionBatchStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					localQualityInspectionStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localQualityInspectionStock"))) + localQualityInspectionStock;
					if (countTmp1 == count1) {
						return String.valueOf(localQualityInspectionStock);
					}

				}
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp2 = countTmp2 + 1;
				// record 2
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId)) {
						localQualityInspectionConsignmentStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localQualityInspectionConsignmentStock"))) + localQualityInspectionConsignmentStock;
					}

					if (countTmp2 == count2) {
						return String.valueOf(localQualityInspectionConsignmentStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId)) {
						localConsignmentStockInQualityInspection = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localConsignmentStockInQualityInspection"))) + localConsignmentStockInQualityInspection;
					}

					if (countTmp2 == count2) {
						return String.valueOf(localConsignmentStockInQualityInspection);
					}

				}
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				countTmp3 = countTmp3 + 1;
				// record 3
				if (localBatchIdTmp.equals(localBatchId)) {
					localQualityInspectionSpecialStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localQualityInspectionSpecialStock"))) + localQualityInspectionSpecialStock;
				}

				if (countTmp3 == count3) {
					return String.valueOf(localQualityInspectionSpecialStock);
				}

			}
		}
		return IConstant.VALUE.FLOAT_ZERO;
	}

	/**
	 *
	 * @param localBatchManagementRequirementIndicator
	 * @param inventoryStockList
	 * @return
	 */
	public static String getQuantity(String localBatchManagementRequirementIndicator, List<Map.Entry<String, String>> inventoryStockList, String localBatchId) {

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

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp1 = countTmp1 + 1;
				// record 1
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId)) {
						localUnrestrictedBatchStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localUnrestrictedBatchStock"))) + localUnrestrictedBatchStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf(localUnrestrictedBatchStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					localUnrestrictedStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localUnrestrictedStock"))) + localUnrestrictedStock;
					if (countTmp1 == count1) {
						return String.valueOf(localUnrestrictedStock);
					}

				}
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp2 = countTmp2 + 1;
				// record 2
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId)) {
						localUnrestrictedConsignmentStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localUnrestrictedConsignmentStock"))) + localUnrestrictedConsignmentStock;
					}

					if (countTmp2 == count2) {
						return String.valueOf(localUnrestrictedConsignmentStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					localUnrestrictedUseConsignment = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localUnrestrictedUseConsignment"))) + localUnrestrictedUseConsignment;
					if (countTmp2 == count2) {
						return String.valueOf(localUnrestrictedUseConsignment);
					}

				}
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				countTmp3 = countTmp3 + 1;
				// record 3
				if (localBatchIdTmp.equals(localBatchId)) {
					localUnrestrictedSpecialStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localUnrestrictedSpecialStock"))) + localUnrestrictedSpecialStock;
				}

				if (countTmp3 == count3) {
					return String.valueOf(localUnrestrictedSpecialStock);
				}
			}
		}
		return IConstant.VALUE.FLOAT_ZERO;
	}

	/**
	 *
	 * @param localBatchManagementRequirementIndicator
	 * @param inventoryStockList
	 * @return
	 */
	public static String getRestrictedQuantity(String localBatchManagementRequirementIndicator, List<Map.Entry<String, String>> inventoryStockList, String localBatchId) {

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

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp1 = countTmp1 + 1;
				// record 1
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if(localBatchIdTmp.equals(localBatchId)){
						localRestrictedBatchStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localRestrictedBatchStock"))) + localRestrictedBatchStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf(localRestrictedBatchStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					localRestrictedStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localRestrictedStock"))) + localRestrictedStock;
					if (countTmp1 == count1) {
						return String.valueOf(localRestrictedStock);
					}

				}
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp2 = countTmp2 + 1;
				// record 2
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if(localBatchIdTmp.equals(localBatchId)) {
						localRestrictedConsignmentStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localRestrictedConsignmentStock"))) + localRestrictedConsignmentStock;
					}

					if (countTmp2 == count2) {
						return String.valueOf(localRestrictedConsignmentStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					localRestrictedUseConsignment = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localRestrictedUseConsignment"))) + localRestrictedUseConsignment;
					if (countTmp2 == count2) {
						return String.valueOf(localRestrictedUseConsignment);
					}

				}
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				countTmp3 = countTmp3 + 1;
				// record 3
				if(localBatchIdTmp.equals(localBatchId)) {
					localRestrictedSpecialStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localRestrictedSpecialStock"))) + localRestrictedSpecialStock;
				}

				if (countTmp3 == count3) {
					return String.valueOf(localRestrictedSpecialStock);
				}

			}
		}
		return IConstant.VALUE.FLOAT_ZERO;
	}

	/**
	 *
	 * @param localBatchManagementRequirementIndicator
	 * @param inventoryStockList
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

					if (localBatchIdTmp.equals(localBatchId)) {
						localReturnsBatchStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localReturnsBatchStock"))) + localReturnsBatchStock;
					}

					if (countTmp1 == count1) {
						return String.valueOf(localReturnsBatchStock);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					localReturnsStock = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localReturnsStock"))) + localReturnsStock;
					if (countTmp1 == count1) {
						return String.valueOf(localReturnsStock);
					}

				}
			}
		}
		return IConstant.VALUE.FLOAT_ZERO;
	}

	/**
	 *
	 * @param localBatchManagementRequirementIndicator
	 * @param inventoryStockList
	 * @return
	 */
	public static String getTransferQuantity(String localBatchManagementRequirementIndicator, List<Map.Entry<String, String>> inventoryStockList, String localBatchId) {

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

			if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				countTmp1 = countTmp1 + 1;
				// record 1
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					if (localBatchIdTmp.equals(localBatchId)) {
						localStockInTransitBatch = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localStockInTransitBatch"))) + localStockInTransitBatch;
					}

					if (countTmp1 == count1) {
						return String.valueOf(localStockInTransitBatch);
					}

				} else if (IConstant.VALUE.BLANK.equals(localBatchManagementRequirementIndicator)) {

					localStockInTransitPlantToPlant = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localStockInTransitPlantToPlant"))) + localStockInTransitPlantToPlant;
					if (countTmp1 == count1) {
						return String.valueOf(localStockInTransitPlantToPlant);
					}
				}
			} else if(IConstant.VALUE.K.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.BLANK.equals(localSpecialStockIndicator)) {

				// record 2
				if (IConstant.VALUE.X.equals(localBatchManagementRequirementIndicator)) {

					return IConstant.VALUE.FLOAT_ZERO;
				}
			} else if (IConstant.VALUE.BLANK.equals(localConsignmentSpecialStockIndicator) && IConstant.VALUE.O.equals(localSpecialStockIndicator)) {

				countTmp3 = countTmp3 + 1;
				// record 3
				if (localBatchIdTmp.equals(localBatchId)) {
					localStockInTransitSpecial = Float.parseFloat(String.valueOf(inventoryStockListMap.get("localStockInTransitSpecial"))) + localStockInTransitSpecial;
				}

				if (countTmp3 == count3) {
					return String.valueOf(localStockInTransitSpecial);
				}
			}
		}
		return IConstant.VALUE.FLOAT_ZERO;
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
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		data = res + " " + "00:00:00";
		return data;
	}
}
