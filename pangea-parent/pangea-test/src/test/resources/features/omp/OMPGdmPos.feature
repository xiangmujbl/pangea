@pangea_test @AEAZ-3214
Feature: OMPGdmPos AEAZ-3214

  @Scenario1
  Scenario: Pick POS data which has customer hierarcy valid date greater than the current date

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer  | localMaterial | quantity | sourceSystem | yearMonth |
      | 104076-J1 | 4000          | 9        | CONS_LATAM   | 199601    |
      | 104077-J1 | 6200          | 8        | CONS_LATAM   | 201804    |
      | 104078    | 6200          | 8        | CONS_LATAM   | 201808    |
      | 104079-J1 | 6200          | 8        | CONS_LATAM   | 201809    |
      | 104089    | 6201          | 8        | CONS_LATAM   | 201808    |

    And I wait "/plan/cns_dp_pos" Async Queue complete

    # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    And I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000004000  | CONS_LATAM   | KI-T4        | 178910100400070072 |
      | 000000000000005100  | CONS_LATAM   | KI1-T4       | 178910100400070075 |

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
      | customerId | customerShipTo | demandGroup |
      | 104076     | 104076-J1      | 1           |
      | 10236      | 104077-J1      | 2           |
      | 10237      | 104079-J1      |             |
      | 10238      | 104088-J1      | 3           |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr     | hkunnr    | datbi    |
      | 104076-J1 | 104076-J1 | 99991231 |
      | 104078-J1 | 104089-J1 | 19960101 |
      | 104087-J1 | 104077-J1 | 99991231 |
      | 104097-J1 | 104079-J1 | 19960101 |
      | 104099-J1 | 104069-J1 | 99991231 |
      | 104049-J1 | 104069-J1 | 19960101 |

    And I wait "/project_one/knvh" Async Queue complete

    #cns_plan_unit -localUom = material_global_v1-localBaseUom
    And I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,sourceSystem,unit"
      | localUom | localUomName | unit | sourceSystem |
      | KI-T4    | Crate        | CA   | CONS_LATAM   |
      | BAG-T4   | Bag          | BAG  | CONS_LATAM   |

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

    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | quantity |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2        |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2        |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2        |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2        |


    #CONCATENATE 'LA_' ,  localDpParentCode, '-' and   cns_dem_grp_asgn-demandGroup
    #GDMPOS-DUEDATE = jnj_calender_v1-weekToDate
    #cns_plan_unit-unit
    #Distribute cns_dp_pos-quantity equally into noOfWeeks and round to the nearest integer
    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1 | key2      | key3   | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 6200 | 104077-J1 | 201804 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 6200 | 104078    | 201808 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 6200 | 104079-J1 | 201809 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 6201 | 104089    | 201808 |      |      | Unable to find Root |              |



    Then I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario2
  Scenario:Reject the POS record if localDpParentCode is empty for the associated material

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer | localMaterial | quantity | sourceSystem | yearMonth |
      | 104076   | 4000-J1       | 9        | CONS_LATAM   | 199601    |
      | 104097   | 4001-J1       | 8        | CONS_LATAM   | 201804    |
      | 104077   | 6200          | 8        | CONS_LATAM   | 201804    |
      | 104078   | 6200          | 8        | CONS_LATAM   | 201808    |
      | 104079   | 6200          | 8        | CONS_LATAM   | 201809    |
      | 104089   | 6201          | 8        | CONS_LATAM   | 201808    |

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
      | customerId | customerShipTo | demandGroup |
      | 104076     | 104076         | 1           |
      | 10236      | 104077         | 2           |
      | 10237      | 104079         |             |
      | 10238      | 104088         | 3           |

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

    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | quantity |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2        |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2        |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2        |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1    | key2   | key3   | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 6200    | 104077 | 201804 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 6200    | 104078 | 201808 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 6200    | 104079 | 201809 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 6201    | 104089 | 201808 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 4001-J1 | 104097 | 201804 |      |      | Unable to find Root |              |


    Then I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario3
  Scenario:Reject the POS record if demandgroup is not assigned to shipto party

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer  | localMaterial | quantity | sourceSystem | yearMonth |
      | 104076-T1 | 4000          | 9        | CONS_LATAM   | 199601    |
      | 104097-T1 | 4001          | 8        | CONS_LATAM   | 201804    |
      | 104077-T1 | 6200          | 8        | CONS_LATAM   | 201804    |
      | 104078    | 6200          | 8        | CONS_LATAM   | 201808    |
      | 104079-T1 | 6200          | 8        | CONS_LATAM   | 201809    |
      | 104089    | 6201          | 8        | CONS_LATAM   | 201808    |

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
      | customerId | customerShipTo | demandGroup |
      | 104076     | 104076-T1      | 1           |
      | 10236      | 104077-T1      | 2           |
      | 10237      | 104079-T1      |             |
      | 10238      | 104088-T1      | 3           |

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

    #CONCATENATE 'LA_' ,  localDpParentCode, '-' and   cns_dem_grp_asgn-demandGroup
    #GDMPOS-DUEDATE = jnj_calender_v1-weekToDate
    #cns_plan_unit-unit
    #Distribute cns_dp_pos-quantity equally into noOfWeeks and round to the nearest integer
    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | quantity |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2        |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2        |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2        |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1 | key2      | key3   | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 6200 | 104077-T1 | 201804 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 6200 | 104078    | 201808 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 6200 | 104079-T1 | 201809 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 6201 | 104089    | 201808 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 4001 | 104097-T1 | 201804 |      |      | Unable to find Root |              |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario4
  Scenario:Pick the POS record with demnad group for L2 hierachy is assigned

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer | localMaterial | quantity | sourceSystem | yearMonth |
      | 104076   | 4000          | 9        | CONS_LATAM   | 199601    |
      | 104097   | 4001          | 8        | CONS_LATAM   | 201804    |
      | 104077   | 6200          | 8        | CONS_LATAM   | 201804    |
      | 104078   | 6200          | 8        | CONS_LATAM   | 201808    |
      | 104079   | 6200          | 8        | CONS_LATAM   | 201809    |
      | 104089   | 6201          | 8        | CONS_LATAM   | 201808    |

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
      | customerId | customerShipTo | demandGroup |
      | 104076     | 104076         | 1           |
      | 10236      | 104077         | 2           |
      | 10237      | 104079         |             |
      | 10238      | 104088         | 3           |

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

    #CONCATENATE 'LA_' ,  localDpParentCode, '-' and   cns_dem_grp_asgn-demandGroup
    #GDMPOS-DUEDATE = jnj_calender_v1-weekToDate
    #cns_plan_unit-unit
    #Distribute cns_dp_pos-quantity equally into noOfWeeks and round to the nearest integer
    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | quantity |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2        |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2        |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2        |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2        |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1 | key2   | key3   | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 6200 | 104077 | 201804 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 6200 | 104078 | 201808 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 6200 | 104079 | 201809 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 6201 | 104089 | 201808 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 4001 | 104097 | 201804 |      |      | Unable to find Root |              |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario5
  Scenario:No of output records for each AggregationId per month should be equal to the jnj calendar's no of weeks per month and qunatity has to be distributed equally between the weeks/month

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer | localMaterial | quantity | sourceSystem | yearMonth |
      | 104071   | 4000          | 9        | CONS_LATAM   | 199601    |
      | 104072   | 4001          |          | CONS_LATAM   | 199602    |
      | 104073   | 4002          |          | CONS_LATAM   | 199603    |
      | 104074   | 4003          | 8        | CONS_LATAM   | 199604    |
      | 104075   | 4004          | 8        | CONS_LATAM   | 199605    |
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
      | customerId | customerShipTo | demandGroup |
      | 104076     | 104071         | 1           |
      | 10236      | 104072         | 2           |
      | 10237      | 104073         |             |
      | 10238      | 104084         | 3           |
      | 10239      | 104085         | 3           |
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
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | quantity |
      | LA_178910100400070071-1 | LA_178910100400070071-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2        |
      | LA_178910100400070071-1 | LA_178910100400070071-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2        |
      | LA_178910100400070071-1 | LA_178910100400070071-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2        |
      | LA_178910100400070071-1 | LA_178910100400070071-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2        |
      | LA_178910100400070072-2 | LA_178910100400070072-2-1996/02/05 00:00:00 |            | 1996/02/05 00:00:00 | 1996/01/29 00:00:00 | EA     |          |
      | LA_178910100400070071-1 | LA_178910100400070071-1-1996/02/29 00:00:00 |            | 1996/02/29 00:00:00 | 1996/02/29 00:00:00 | CA     |          |
      | LA_178910100400070071-1 | LA_178910100400070071-1-1996/03/29 00:00:00 |            | 1996/03/29 00:00:00 | 1996/03/29 00:00:00 | CA     |          |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1 | key2   | key3   | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 4002 | 104073 | 199603 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 4003 | 104074 | 199604 |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 4004 | 104075 | 199605 |      |      | Unable to find Root |              |

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

    And I will remove all data with region "/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/omp/gdm_pos"

    And I will remove all data with region "/plan/edm_failed_data"

