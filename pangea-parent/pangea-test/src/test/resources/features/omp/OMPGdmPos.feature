@pangea_test @AEAZ-3214
Feature: OMPGdmPos AEAZ-3214


  @Scenario1
  Scenario: Pick POS data which has customer hierarcy valid date greater than the current date

#    And I will remove the test file on sink application "GDMPOS.tsv"

     # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000004000  | CONS_LATAM   | KI           | 178910100400070072 |
      | 000000000000005100  | CONS_LATAM   | KI1          | 178910100400070075 |

    And I wait "/edm/material_global_v1" Async Queue complete

    And I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer | localMaterial      | quantity | sourceSystem | yearMonth |
      | 104076   | 000000000000004000 | 9        | CONS_LATAM   | 199601    |
      | 104077   | 000000000000006200 | 8        | CONS_LATAM   | 201804    |
      | 104078   | 000000000000006200 | 8        | CONS_LATAM   | 201808    |
      | 104079   | 000000000000006200 | 8        | CONS_LATAM   | 201809    |
      | 104089   | 000000000000006201 | 8        | CONS_LATAM   | 201808    |

    And I wait "/plan/cns_dp_pos" Async Queue complete

    #material_global_v1-sourceSystem = cns_plan_parameter-attribute
    #cns_plan_parameter-sourceSystem = material_global_v1-sourceSystem
    #cns_plan_parameter-dataObject = 'SEND_TO_OMP'
    And I import "/plan/cns_plan_parameter" by keyFields "attribute,sourceSystem,dataObject"
      | attribute  | sourceSystem | dataObject               |
      | CONS_LATAM | CONS_LATAM   | SEND_TO_OMP              |
      | CONS_LATAM | CONS_LATAM   | cns_material_plan_status |
      | DPRelevant | CONS_LATAM   | SEND_TO_OMP              |
      | DPRelevant | CONS_LATAM   | cns_material_plan_status |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    #cns_dp_pos-customer = cns_dem_grp_asgn-customerShipTo
    And I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | demandGroup |
      | 104076     | 1           |
      | 104077     | 2           |
      | 104079     |             |
      | 104088     | 3           |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr  | hkunnr | datbi    |
      | 104076 | 104076 | 99991231 |
      | 104078 | 104089 | 19960101 |
      | 104087 | 104077 | 99991231 |
      | 104097 | 104079 | 19960101 |
      | 104099 | 104069 | 99991231 |
      | 104049 | 104069 | 19960101 |

    And I wait "/project_one/knvh" Async Queue complete

    #cns_plan_unit -localUom = material_global_v1-localBaseUom
    And I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,sourceSystem,unit"
      | localUom | localUomName | unit | sourceSystem |
      | KI       | Crate        | CA   | CONS_LATAM   |
      | BAG      | Bag          | BAG  | CONS_LATAM   |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    #cns_plan_unit-sourceSystem = source_system_v1-sourceSystem
    And I import "/edm/source_system_v1" by keyFields "localSourceSystem,sourceSystem"
      | localSourceSystem | sourceSystem |
      | EMS               | EMS          |
      | project_one       | CONS_LATAM   |

    And I wait "/edm/source_system_v1" Async Queue complete

    #cns_dp_pos-yearMonth = jnj_calendar_v1-fiscalPeriod
    And I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekToDate | weekFromDate |
      | 199601  | 199601       | 4        | 1996-01-08 | 1996-01-01   |
      | 199602  | 199601       | 4        | 1996-01-15 | 1996-01-08   |
      | 199603  | 199601       | 4        | 1996-01-22 | 1996-01-15   |
      | 199604  | 199601       | 4        | 1996-01-29 | 1996-01-22   |
      | 199605  | 199602       | 4        | 1996-02-05 | 1996-01-29   |
    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmPos.xml" and execute file "jar/pangea-view.jar"

    Then I delete the test data

#    Then A file is found on sink application with name "GDMPOS.tsv"

#    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
    Then I check region data "/omp/gdm_pos" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | volume |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2      |


    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1               | key2       | key3 | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 000000000000004000 | CONS_LATAM |      |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 000000000000005100 | CONS_LATAM |      |      |      | Unable to find Root |              |

  @Scenario1
  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario2
  Scenario:Reject the POS record if localDpParentCode is empty for the associated material

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer | localMaterial         | quantity | sourceSystem | yearMonth |
      | 104076   | 000000000000004000-J1 | 9        | CONS_LATAM   | 199601    |
      | 104097   | 000000000000004001-J1 | 8        | CONS_LATAM   | 201804    |
      | 104077   | 000000000000006200    | 8        | CONS_LATAM   | 201804    |
      | 104078   | 000000000000006200    | 8        | CONS_LATAM   | 201808    |
      | 104079   | 000000000000006200    | 8        | CONS_LATAM   | 201809    |
      | 104089   | 000000000000006201    | 8        | CONS_LATAM   | 201808    |

    And I wait "/plan/cns_dp_pos" Async Queue complete

    # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber   | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000004000-J1 | CONS_LATAM   | KI           | 178910100400070072 |
      | 000000000000004001-J1 | CONS_LATAM   | EA           |                    |
      | 000000000000005100-J1 | CONS_LATAM   | KI1          | 178910100400070075 |

    And I wait "/edm/material_global_v1" Async Queue complete

    #material_global_v1-sourceSystem = cns_plan_parameter-attribute
    #cns_plan_parameter-sourceSystem = material_global_v1-sourceSystem
    #cns_plan_parameter-dataObject = 'SEND_TO_OMP'
    And I import "/plan/cns_plan_parameter" by keyFields "attribute,sourceSystem,dataObject"
      | attribute  | sourceSystem | dataObject               |
      | CONS_LATAM | CONS_LATAM   | SEND_TO_OMP              |
      | CONS_LATAM | CONS_LATAM   | cns_material_plan_status |
      | DPRelevant | CONS_LATAM   | SEND_TO_OMP              |
      | DPRelevant | CONS_LATAM   | cns_material_plan_status |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    #cns_dp_pos-customer = cns_dem_grp_asgn-customerShipTo
    And I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | demandGroup |
      | 104076     | 1           |
      | 104077     | 2           |
      | 104079     |             |
      | 104088     | 3           |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr  | hkunnr | datbi    |
      | 104076 | 104076 | 99991231 |
      | 104078 | 104089 | 19960101 |
      | 104087 | 104077 | 99991231 |
      | 104097 | 104079 | 19960101 |
      | 104099 | 104069 | 99991231 |
      | 104049 | 104069 | 19960101 |

    And I wait "/project_one/knvh" Async Queue complete

    #cns_plan_unit -localUom = material_global_v1-localBaseUom
    And I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,sourceSystem,unit"
      | localUom | localUomName | unit | sourceSystem |
      | KI       | Crate        | CA   | CONS_LATAM   |
      | BAG      | Bag          | BAG  | CONS_LATAM   |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    #cns_plan_unit-sourceSystem = source_system_v1-sourceSystem
    And I import "/edm/source_system_v1" by keyFields "localSourceSystem,sourceSystem"
      | localSourceSystem | sourceSystem |
      | EMS               | EMS          |
      | project_one       | CONS_LATAM   |

    And I wait "/edm/source_system_v1" Async Queue complete

    #cns_dp_pos-yearMonth = jnj_calendar_v1-fiscalPeriod
    And I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekToDate | weekFromDate |
      | 199601  | 199601       | 4        | 1996-01-08 | 1996-01-01   |
      | 199602  | 199601       | 4        | 1996-01-15 | 1996-01-08   |
      | 199603  | 199601       | 4        | 1996-01-22 | 1996-01-15   |
      | 199604  | 199601       | 4        | 1996-01-29 | 1996-01-22   |
      | 199605  | 199602       | 4        | 1996-02-05 | 1996-01-29   |
    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmPos.xml" and execute file "jar/pangea-view.jar"

    Then I delete the test data

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | volume |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1                  | key2       | key3 | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 000000000000004001-J1 | CONS_LATAM |      |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 000000000000005100-J1 | CONS_LATAM |      |      |      | Unable to find Root |              |

  @Scenario2
  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario3
  Scenario:Reject the POS record if demandgroup is not assigned to shipto party

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer  | localMaterial      | quantity | sourceSystem | yearMonth |
      | 104076-T1 | 000000000000004000 | 9        | CONS_LATAM   | 199601    |
      | 104097-T1 | 000000000000004001 | 8        | CONS_LATAM   | 201804    |
      | 104077-T1 | 000000000000006200 | 8        | CONS_LATAM   | 201804    |
      | 104078    | 000000000000006200 | 8        | CONS_LATAM   | 201808    |
      | 104079-T1 | 000000000000006200 | 8        | CONS_LATAM   | 201809    |
      | 104089    | 000000000000006201 | 8        | CONS_LATAM   | 201808    |

    And I wait "/plan/cns_dp_pos" Async Queue complete

    # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000004000  | CONS_LATAM   | KI           | 178910100400070072 |
      | 000000000000004001  | CONS_LATAM   | EA           |                    |
      | 000000000000005100  | CONS_LATAM   | KI1          | 178910100400070075 |

    And I wait "/edm/material_global_v1" Async Queue complete

    #material_global_v1-sourceSystem = cns_plan_parameter-attribute
    #cns_plan_parameter-sourceSystem = material_global_v1-sourceSystem
    #cns_plan_parameter-dataObject = 'SEND_TO_OMP'
    And I import "/plan/cns_plan_parameter" by keyFields "attribute,sourceSystem,dataObject"
      | attribute  | sourceSystem | dataObject               |
      | CONS_LATAM | CONS_LATAM   | SEND_TO_OMP              |
      | CONS_LATAM | CONS_LATAM   | cns_material_plan_status |
      | DPRelevant | CONS_LATAM   | SEND_TO_OMP              |
      | DPRelevant | CONS_LATAM   | cns_material_plan_status |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    #cns_dp_pos-customer = cns_dem_grp_asgn-customerShipTo
    And I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | demandGroup |
      | 104076-T1  | 1           |
      | 104077-T1  | 2           |
      | 104079-T1  |             |
      | 104088-T1  | 3           |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr     | hkunnr    | datbi    |
      | 104076-T1 | 104076    | 99991231 |
      | 104078    | 104089    | 19960101 |
      | 104087    | 104077-T1 | 99991231 |
      | 104097    | 104079-T1 | 19960101 |
      | 104099    | 104069    | 99991231 |
      | 104049    | 104069    | 19960101 |

    And I wait "/project_one/knvh" Async Queue complete

    #cns_plan_unit -localUom = material_global_v1-localBaseUom
    And I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,sourceSystem,unit"
      | localUom | localUomName | unit | sourceSystem |
      | KI       | Crate        | CA   | CONS_LATAM   |
      | BAG      | Bag          | BAG  | CONS_LATAM   |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    #cns_plan_unit-sourceSystem = source_system_v1-sourceSystem
    And I import "/edm/source_system_v1" by keyFields "localSourceSystem,sourceSystem"
      | localSourceSystem | sourceSystem |
      | EMS               | EMS          |
      | project_one       | CONS_LATAM   |

    And I wait "/edm/source_system_v1" Async Queue complete

    #cns_dp_pos-yearMonth = jnj_calendar_v1-fiscalPeriod
    And I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekToDate | weekFromDate |
      | 199601  | 199601       | 4        | 1996-01-08 | 1996-01-01   |
      | 199602  | 199601       | 4        | 1996-01-15 | 1996-01-08   |
      | 199603  | 199601       | 4        | 1996-01-22 | 1996-01-15   |
      | 199604  | 199601       | 4        | 1996-01-29 | 1996-01-22   |
      | 199605  | 199602       | 4        | 1996-02-05 | 1996-01-29   |
    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmPos.xml" and execute file "jar/pangea-view.jar"

    Then I delete the test data

    #CONCATENATE 'LA_' ,  localDpParentCode, '-' and   cns_dem_grp_asgn-demandGroup
    #GDMPOS-DUEDATE = jnj_calender_v1-weekToDate
    #cns_plan_unit-unit
    #Distribute cns_dp_pos-quantity equally into noOfWeeks and round to the nearest integer
    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | volume |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1               | key2       | key3 | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 000000000000004001 | CONS_LATAM |      |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 000000000000005100 | CONS_LATAM |      |      |      | Unable to find Root |              |

  @Scenario3
  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario4
  Scenario:Pick the POS record with demnad group for L2 hierachy is assigned

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer | localMaterial      | quantity | sourceSystem | yearMonth |
      | 104076   | 000000000000004000 | 9        | CONS_LATAM   | 199601    |
      | 104097   | 000000000000004001 | 8        | CONS_LATAM   | 201804    |
      | 104077   | 000000000000006200 | 8        | CONS_LATAM   | 201804    |
      | 104078   | 000000000000006200 | 8        | CONS_LATAM   | 201808    |
      | 104079   | 000000000000006200 | 8        | CONS_LATAM   | 201809    |
      | 104089   | 000000000000006201 | 8        | CONS_LATAM   | 201808    |

    And I wait "/plan/cns_dp_pos" Async Queue complete

    # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000004000  | CONS_LATAM   | KI           | 178910100400070072 |
      | 000000000000004001  | CONS_LATAM   | EA           |                    |
      | 000000000000005100  | CONS_LATAM   | KI1          | 178910100400070075 |

    And I wait "/edm/material_global_v1" Async Queue complete

    #material_global_v1-sourceSystem = cns_plan_parameter-attribute
    #cns_plan_parameter-sourceSystem = material_global_v1-sourceSystem
    #cns_plan_parameter-dataObject = 'SEND_TO_OMP'
    And I import "/plan/cns_plan_parameter" by keyFields "attribute,sourceSystem,dataObject"
      | attribute  | sourceSystem | dataObject               |
      | CONS_LATAM | CONS_LATAM   | SEND_TO_OMP              |
      | CONS_LATAM | CONS_LATAM   | cns_material_plan_status |
      | DPRelevant | CONS_LATAM   | SEND_TO_OMP              |
      | DPRelevant | CONS_LATAM   | cns_material_plan_status |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    #cns_dp_pos-customer = cns_dem_grp_asgn-customerShipTo
    And I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | demandGroup |
      | 104076     | 1           |
      | 104077     | 2           |
      | 104079     |             |
      | 104088     | 3           |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr  | hkunnr | datbi    |
      | 104076 | 104076 | 99991231 |
      | 104078 | 104089 | 19960101 |
      | 104087 | 104077 | 99991231 |
      | 104097 | 104079 | 19960101 |
      | 104099 | 104069 | 99991231 |
      | 104049 | 104069 | 19960101 |

    And I wait "/project_one/knvh" Async Queue complete

    #cns_plan_unit -localUom = material_global_v1-localBaseUom
    And I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,sourceSystem,unit"
      | localUom | localUomName | unit | sourceSystem |
      | KI       | Crate        | CA   | CONS_LATAM   |
      | BAG      | Bag          | BAG  | CONS_LATAM   |

    And I wait "/plan/cns_plan_unit" Async Queue complete

    #cns_plan_unit-sourceSystem = source_system_v1-sourceSystem
    And I import "/edm/source_system_v1" by keyFields "localSourceSystem,sourceSystem"
      | localSourceSystem | sourceSystem |
      | EMS               | EMS          |
      | project_one       | CONS_LATAM   |

    And I wait "/edm/source_system_v1" Async Queue complete

    #cns_dp_pos-yearMonth = jnj_calendar_v1-fiscalPeriod
    And I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekToDate | weekFromDate |
      | 199601  | 199601       | 4        | 1996-01-08 | 1996-01-01   |
      | 199602  | 199601       | 4        | 1996-01-15 | 1996-01-08   |
      | 199603  | 199601       | 4        | 1996-01-22 | 1996-01-15   |
      | 199604  | 199601       | 4        | 1996-01-29 | 1996-01-22   |
      | 199605  | 199602       | 4        | 1996-02-05 | 1996-01-29   |
    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmPos.xml" and execute file "jar/pangea-view.jar"

    Then I delete the test data

    #CONCATENATE 'LA_' ,  localDpParentCode, '-' and   cns_dem_grp_asgn-demandGroup
    #GDMPOS-DUEDATE = jnj_calender_v1-weekToDate
    #cns_plan_unit-unit
    #Distribute cns_dp_pos-quantity equally into noOfWeeks and round to the nearest integer
    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | volume |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1               | key2       | key3 | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 000000000000004001 | CONS_LATAM |      |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 000000000000005100 | CONS_LATAM |      |      |      | Unable to find Root |              |


  @Scenario4
  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

#    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario5
  Scenario:No of output records for each AggregationId per month should be equal to the jnj calendar's no of weeks per month and qunatity has to be distributed equally between the weeks/month

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer | localMaterial      | quantity | sourceSystem | yearMonth |
      | 104071   | 000000000000004000 | 9        | CONS_LATAM   | 199601    |
      | 104072   | 000000000000004001 |          | CONS_LATAM   | 199602    |
      | 104073   | 000000000000004002 |          | CONS_LATAM   | 199603    |
      | 104074   | 000000000000004003 | 8        | CONS_LATAM   | 199604    |
      | 104075   | 000000000000004004 | 8        | CONS_LATAM   | 199605    |
    And I wait "/plan/cns_dp_pos" Async Queue complete

    # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000004000  | CONS_LATAM   | KI           | 178910100400070071 |
      | 000000000000004001  | CONS_LATAM   | EA           | 178910100400070072 |
      | 000000000000004002  | CONS_LATAM   | CA           | 178910100400070073 |
      | 000000000000004003  | CONS_LATAM   | CA           |                    |
      | 000000000000005100  | CONS_LATAM   | KI1          | 178910100400070075 |
    And I wait "/edm/material_global_v1" Async Queue complete

    #material_global_v1-sourceSystem = cns_plan_parameter-attribute cns_plan_parameter-sourceSystem = material_global_v1-sourceSystem cns_plan_parameter-dataObject = 'SEND_TO_OMP'
    And I import "/plan/cns_plan_parameter" by keyFields "attribute,sourceSystem,dataObject"
      | attribute  | sourceSystem | dataObject               |
      | CONS_LATAM | CONS_LATAM   | SEND_TO_OMP              |
      | CONS_LATAM | CONS_LATAM   | cns_material_plan_status |
      | DPRelevant | CONS_LATAM   | SEND_TO_OMP              |
      | DPRelevant | CONS_LATAM   | cns_material_plan_status |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    #cns_dp_pos-customer = cns_dem_grp_asgn-customerShipTo
    And I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | demandGroup |
      | 104071     | 1           |
      | 104072     | 2           |
      | 104073     |             |
      | 104084     | 3           |
      | 104085     | 3           |
    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr  | hkunnr | datbi    |
      | 104071 | 104081 | 99991231 |
      | 104072 | 104082 | 19960101 |
      | 104083 | 104072 | 99991231 |
      | 104084 | 104085 | 99991231 |
    And I wait "/project_one/knvh" Async Queue complete

    #cns_plan_unit -localUom = material_global_v1-localBaseUom
    And I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,sourceSystem,unit"
      | localUom | localUomName | unit | sourceSystem |
      | KI       | Crate        | CA   | CONS_LATAM   |
      | EA       | Crate        | EA   | CONS_LATAM   |
      | BAG      | Bag          | BAG  | CONS_LATAM   |
    And I wait "/plan/cns_plan_unit" Async Queue complete

  #cns_plan_unit-sourceSystem = source_system_v1-sourceSystem
    And I import "/edm/source_system_v1" by keyFields "localSourceSystem,sourceSystem"
      | localSourceSystem | sourceSystem |
      | EMS               | EMS          |
      | project_one       | CONS_LATAM   |
    And I wait "/edm/source_system_v1" Async Queue complete

    #cns_dp_pos-yearMonth = jnj_calendar_v1-fiscalPeriod
    And I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekToDate | weekFromDate |
      | 199601  | 199601       | 4        | 1996-01-08 | 1996-01-01   |
      | 199602  | 199601       | 4        | 1996-01-15 | 1996-01-08   |
      | 199603  | 199601       | 4        | 1996-01-22 | 1996-01-15   |
      | 199604  | 199601       | 4        | 1996-01-29 | 1996-01-22   |
      | 199605  | 199602       | 4        | 1996-02-05 | 1996-01-29   |
      | 199606  | 199603       | 5        | 1996-03-04 | 1996-02-26   |
      | 199607  | 199601       |          | 1996-02-29 | 1996-02-29   |
      | 199608  | 199601       | 0        | 1996-03-29 | 1996-03-29   |

    And I wait "/edm/jnj_calendar_v1" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmPos.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMPOS.tsv"

    #aggregationId： CONCATENATE 'LA_' ,  localDpParentCode, '-' and   cns_dem_grp_asgn-demandGroup
    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
#    Then I check region data "/omp/gdm_pos" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | volume |
      | LA_178910100400070071-1 | LA_178910100400070071-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2      |
      | LA_178910100400070071-1 | LA_178910100400070071-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2      |
      | LA_178910100400070071-1 | LA_178910100400070071-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2      |
      | LA_178910100400070071-1 | LA_178910100400070071-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2      |
      | LA_178910100400070072-2 | LA_178910100400070072-2-1996/02/05 00:00:00 |            | 1996/02/05 00:00:00 | 1996/01/29 00:00:00 | EA     |        |
      | LA_178910100400070071-1 | LA_178910100400070071-1-1996/02/29 00:00:00 |            | 1996/02/29 00:00:00 | 1996/02/29 00:00:00 | CA     |        |
      | LA_178910100400070071-1 | LA_178910100400070071-1-1996/03/29 00:00:00 |            | 1996/03/29 00:00:00 | 1996/03/29 00:00:00 | CA     |        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1               | key2       | key3 | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 000000000000004002 | CONS_LATAM |      |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 000000000000004003 | CONS_LATAM |      |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 000000000000005100 | CONS_LATAM |      |      |      | Unable to find Root |              |

  @Scenario5
  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

    And I will remove all data with region "/plan/edm_failed_data"
  @Scenario6
  Scenario:Pick the POS record with demnad group for L2 hierachy is assigned

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000004000  | CONS_LATAM   | KI           | 178910100400070072 |

    And I wait "/edm/material_global_v1" Async Queue complete

 # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    And I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer   | localMaterial      | quantity | sourceSystem | yearMonth |
      | 0000000095 | 000000000000004000 | 425      | CONS_LATAM   | 201801    |
      | 0000000181 | 000000000000004000 | 3        | CONS_LATAM   | 201801    |
      | 0000000364 | 000000000000004000 | 4492     | CONS_LATAM   | 201801    |
      | 0000000493 | 000000000000004000 | 149      | CONS_LATAM   | 201801    |
      | 0000000696 | 000000000000004000 | 3168     | CONS_LATAM   | 201801    |
      | 0000000711 | 000000000000004000 | 632      | CONS_LATAM   | 201801    |
      | 0000000095 | 000000000000004000 | 376      | CONS_LATAM   | 201802    |
      | 0000000095 | 000000000000004000 | 287      | CONS_LATAM   | 201803    |
      | 0000000095 | 000000000000004000 | 358      | CONS_LATAM   | 201804    |


    And I wait "/plan/cns_dp_pos" Async Queue complete

 #cns_dp_pos-customer = cns_dem_grp_asgn-customerShipTo
    And I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | demandGroup |
      | 0000000095 | 76100005    |
      | 0000000493 | 76100005    |
      | 0000000696 | 76100005    |
      | 0000000711 | 76100005    |
      | 0000000181 | 76100013    |
      | 0000000364 | 76100023    |


    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

 #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr      | hkunnr     | datbi    |
      | 0000000095 | 0000000095 | 99991231 |
      | 0000000181 | 104089     | 99991231 |
      | 104089     | 0000000364 | 99991231 |
      | 0000000493 | 104079     | 99991231 |
      | 0000000696 | 104069     | 99991231 |
      | 0000000711 | 104069     | 99991231 |

    And I wait "/project_one/knvh" Async Queue complete

 #cns_plan_unit -localUom = material_global_v1-localBaseUom
    And I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,sourceSystem,unit"
      | localUom | localUomName | unit | sourceSystem |
      | KI       | Crate        | CA   | CONS_LATAM   |
      | BAG      | Bag          | BAG  | CONS_LATAM   |

    And I wait "/plan/cns_plan_unit" Async Queue complete

 #cns_dp_pos-yearMonth = jnj_calendar_v1-fiscalPeriod
    And I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekToDate | weekFromDate |
      | 199601  | 201801       | 4        | 2018-01-08 | 2018-01-01   |
      | 199602  | 201801       | 4        | 2018-01-15 | 2018-01-08   |
      | 199603  | 201801       | 4        | 2018-01-22 | 2018-01-15   |
      | 199604  | 201801       | 4        | 2018-01-29 | 2018-01-22   |
      | 199605  | 199602       | 4        | 2018-02-05 | 2018-01-29   |
      | 199606  | 201802       | 4        | 2018-03-05 | 2018-02-25   |
      | 199607  | 201803       | 4        | 2018-04-05 | 2018-03-29   |
      | 199608  | 201804       | 4        | 2018-05-05 | 2018-04-29   |

    And I wait "/edm/jnj_calendar_v1" Async Queue complete

 #cns_plan_unit-sourceSystem = source_system_v1-sourceSystem
    And I import "/edm/source_system_v1" by keyFields "localSourceSystem,sourceSystem"
      | localSourceSystem | sourceSystem |
      | EMS               | EMS          |
      | project_one       | CONS_LATAM   |

    And I wait "/edm/source_system_v1" Async Queue complete

 #material_global_v1-sourceSystem = cns_plan_parameter-attribute
 #cns_plan_parameter-sourceSystem = material_global_v1-sourceSystem
 #cns_plan_parameter-dataObject = 'SEND_TO_OMP'
    And I import "/plan/cns_plan_parameter" by keyFields "attribute,sourceSystem,dataObject"
      | attribute  | sourceSystem | dataObject               |
      | CONS_LATAM | CONS_LATAM   | SEND_TO_OMP              |
      | CONS_LATAM | CONS_LATAM   | cns_material_plan_status |
      | DPRelevant | CONS_LATAM   | SEND_TO_OMP              |
      | DPRelevant | CONS_LATAM   | cns_material_plan_status |

    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmPos.xml" and execute file "jar/pangea-view.jar"

    Then I delete the test data


    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
#    Then I check region data "/omp/gdm_pos" by keyFields "aggregationId,forecastUploadId"
      | aggregationId                  | forecastUploadId                                   | currencyId | dueDate             | fromDueDate         | unitId | volume |
      | LA_178910100400070072-76100005 | LA_178910100400070072-76100005-2018/01/08 00:00:00 |            | 2018/01/08 00:00:00 | 2018/01/01 00:00:00 | CA     | 1094   |
      | LA_178910100400070072-76100005 | LA_178910100400070072-76100005-2018/01/15 00:00:00 |            | 2018/01/15 00:00:00 | 2018/01/08 00:00:00 | CA     | 1094   |
      | LA_178910100400070072-76100005 | LA_178910100400070072-76100005-2018/01/22 00:00:00 |            | 2018/01/22 00:00:00 | 2018/01/15 00:00:00 | CA     | 1094   |
      | LA_178910100400070072-76100005 | LA_178910100400070072-76100005-2018/01/29 00:00:00 |            | 2018/01/29 00:00:00 | 2018/01/22 00:00:00 | CA     | 1094   |
      | LA_178910100400070072-76100013 | LA_178910100400070072-76100013-2018/01/29 00:00:00 |            | 2018/01/29 00:00:00 | 2018/01/22 00:00:00 | CA     | 1      |
      | LA_178910100400070072-76100013 | LA_178910100400070072-76100013-2018/01/29 00:00:00 |            | 2018/01/29 00:00:00 | 2018/01/22 00:00:00 | CA     | 1      |
      | LA_178910100400070072-76100013 | LA_178910100400070072-76100013-2018/01/29 00:00:00 |            | 2018/01/29 00:00:00 | 2018/01/22 00:00:00 | CA     | 1      |
      | LA_178910100400070072-76100013 | LA_178910100400070072-76100013-2018/01/29 00:00:00 |            | 2018/01/29 00:00:00 | 2018/01/22 00:00:00 | CA     | 1      |
      | LA_178910100400070072-76100023 | LA_178910100400070072-76100023-2018/01/29 00:00:00 |            | 2018/01/29 00:00:00 | 2018/01/22 00:00:00 | CA     | 1123   |
      | LA_178910100400070072-76100023 | LA_178910100400070072-76100023-2018/01/29 00:00:00 |            | 2018/01/29 00:00:00 | 2018/01/22 00:00:00 | CA     | 1123   |
      | LA_178910100400070072-76100023 | LA_178910100400070072-76100023-2018/01/29 00:00:00 |            | 2018/01/29 00:00:00 | 2018/01/22 00:00:00 | CA     | 1123   |
      | LA_178910100400070072-76100023 | LA_178910100400070072-76100023-2018/01/29 00:00:00 |            | 2018/01/29 00:00:00 | 2018/01/22 00:00:00 | CA     | 1123   |
      | LA_178910100400070072-76100005 | LA_178910100400070072-76100005-2018/03/05 00:00:00 |            | 2018/03/05 00:00:00 | 2018/02/25 00:00:00 | CA     | 94     |
      | LA_178910100400070072-76100005 | LA_178910100400070072-76100005-2018/04/05 00:00:00 |            | 2018/04/05 00:00:00 | 2018/03/29 00:00:00 | CA     | 72     |
      | LA_178910100400070072-76100005 | LA_178910100400070072-76100005-2018/05/05 00:00:00 |            | 2018/05/05 00:00:00 | 2018/04/29 00:00:00 | CA     | 90     |

  @Scenario6
  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

    And I will remove all data with region "/plan/edm_failed_data"
