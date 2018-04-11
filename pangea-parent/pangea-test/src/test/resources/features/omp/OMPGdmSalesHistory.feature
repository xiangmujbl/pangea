@pangea_test
Feature:  OMPGdmSalesHistory-Curation

  Scenario: Full Load curation

    Given I import "/edm/sales_order_v1" by keyFields "sourceSystem,salesOrderNo,salesOrderItem"
      | sourceSystem | salesOrderNo | salesOrderItem | localSalesOrg | localShipToParty | localOrderCreateDt | localOrderType | localPlant | localMaterialNumber | localitemCategory | localSdItemCurrency | localRequestedDate | localRejReason | salesOrderQty | localNumtoBase | localDentoBase | localRoute |

    And I wait "/edm/sales_order_v1" Async Queue complete

    Given I import "/plan/cns_cust_excl" by keyFields "salesOrg"
      | salesOrg | customerShipTo |

    And I wait "/plan/cns_cust_excl" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,dataObject,attribute,parameter"
      | sourceSystem | dataObject | attribute | parameter | inclusionExclusion | parameterValues |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/plan/cns_so_type_incl" by keyFields "salesOrg,orderType,country"
      | salesOrg | orderType | country |

    And I wait "/plan/cns_so_type_incl" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant"
      | localPlant | country |

    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "/plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant"
      | localPlant | localMaterialNumber | dpParent |

    And I wait "/plan/cns_material_plan_status" Async Queue complete

    Given I import "/plan/cns_certainty_determine" by keyFields "certaintyKey"
      | certaintyKey | salesOrg | orderType | itemCategory |

    And I wait "/plan/cns_certainty_determine" Async Queue complete

    Given I import "/edm/currency_v1" by keyFields "localCurrency"
      | currencyCode | localCurrency |

    And I wait "/edm/currency_v1" Async Queue complete

    Given I import "/plan/cns_dem_grp_asgn" by keyFields "demandGroup"
      | demandGroup | customerId | salesOrganization |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    Given I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr | vkorg | datbi | hkunnr |

    And I wait "/project_one/knvh" Async Queue complete

    Given I import "/project_one/tvro" by keyFields "trazt"
      | trazt | route |

    And I wait "/project_one/tvro" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber"
      | localMaterialNumber | primaryPlanningCode | localBasesunit |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_ord_rej" by keyFields "salesOrg"
      | salesOrg | rejCd |

    And I wait "/plan/cns_ord_rej" Async Queue complete

    Given I import "/plan/cns_plan_unit" by keyFields "localUom,unit"
      | unit | localUom |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmSalesHistory.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_sales_history" by keyFields ""
      | salesHistoryId | activeFCTERP | certaintyId | conversionFactorXx | currencyId | customerId | demandStreamId | dueDate | forecastItemId | fromDueDate | locationId | orderReason | orderStatus | orderSubType | orderType | productId | quantity | salesUnit | unitId | validValueXx |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/sales_order_v1" and "/omp/gdm_sales_history,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_sales_history"

    And I will remove all data with region "/edm/sales_order_v1"

