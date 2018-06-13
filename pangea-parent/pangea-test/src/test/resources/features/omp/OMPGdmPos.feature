@pangea_test @AEAZ-3214
Feature: OMPGdmPos AEAZ-3214


  @Scenario1
  Scenario: Pick POS data which has customer hierarcy valid date greater than the current date --J1

    And I will remove the test file on sink application "GDMPOS.tsv"

     # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000004000  | CONS_LATAM   | KI           | 178910100400070072 |
      | 000000000000005000  | CONS_LATAM   | KI           | 178910100400070073 |
    And I wait "/edm/material_global_v1" Async Queue complete

    And I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer | localMaterial      | quantity | sourceSystem | yearMonth |
      | 104076   | 000000000000004000 | 9        | CONS_LATAM   | 199601    |
      | 104077   | 000000000000005000 | 9        | CONS_LATAM   | 199601    |
    And I wait "/plan/cns_dp_pos" Async Queue complete

    #cns_dp_pos-customer = cns_dem_grp_asgn-customerShipTo
    And I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | demandGroup |
      | 104076     | 1           |
      | 104077     | 2           |
    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr  | hkunnr | datbi    |
      | 104076 | 104086 | 99991231 |
      | 104077 | 104089 | 19960101 |
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
      | 199601  | 199601       | 4        | 1996-01-08 | 1996-01-01   |
      | 199602  | 199601       | 4        | 1996-01-15 | 1996-01-08   |
      | 199603  | 199601       | 4        | 1996-01-22 | 1996-01-15   |
      | 199604  | 199601       | 4        | 1996-01-29 | 1996-01-22   |
      | 199605  | 199602       | 4        | 1996-02-05 | 1996-01-29   |
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

    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
#    Then I check region data "/omp/gdm_pos" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | volume |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2      |
      | LA_178910100400070072-1 | LA_178910100400070072-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1               | key2       | key3 | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 000000000000005000 | CONS_LATAM |      |      |      | Unable to find Root |              |

  @Scenario1
  Scenario: delete all test data

    Then I delete the test data
    And I will remove all data with region "/omp/gdm_pos"
    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario2
  Scenario:Reject the POS record if localDpParentCode is empty for the associated material

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000001000  | CONS_LATAM   | KI           | 178910100400070062 |
      | 000000000000001001  | CONS_LATAM   | EA           |                    |
    And I wait "/edm/material_global_v1" Async Queue complete

     # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    And I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer | localMaterial      | quantity | sourceSystem | yearMonth |
      | 110000   | 000000000000001000 | 9        | CONS_LATAM   | 199601    |
      | 110001   | 000000000000001001 | 8        | CONS_LATAM   | 201804    |
    And I wait "/plan/cns_dp_pos" Async Queue complete

    #cns_dp_pos-customer = cns_dem_grp_asgn-customerShipTo
    And I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | demandGroup |
      | 110000     | 1           |
      | 110001     | 2           |
    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr  | hkunnr | datbi    |
      | 110000 | 104076 | 99991231 |
      | 110001 | 104089 | 99991231 |
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
      | 199601  | 199601       | 4        | 1996-01-08 | 1996-01-01   |
      | 199602  | 199601       | 4        | 1996-01-15 | 1996-01-08   |
      | 199603  | 199601       | 4        | 1996-01-22 | 1996-01-15   |
      | 199604  | 199601       | 4        | 1996-01-29 | 1996-01-22   |
      | 199605  | 199602       | 4        | 1996-02-05 | 1996-01-29   |
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

    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | volume |
      | LA_178910100400070062-1 | LA_178910100400070062-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2      |
      | LA_178910100400070062-1 | LA_178910100400070062-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2      |
      | LA_178910100400070062-1 | LA_178910100400070062-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2      |
      | LA_178910100400070062-1 | LA_178910100400070062-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1               | key2       | key3 | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 000000000000001001 | CONS_LATAM |      |      |      | Unable to find Root |              |

  @Scenario2
  Scenario: delete all test data

    Then I delete the test data
    And I will remove all data with region "/omp/gdm_pos"
    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario3
  Scenario:Reject the POS record if demandGroup is not assigned to shipTo party

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000001011  | CONS_LATAM   | KI           | 178910100400070042 |
      | 000000000000001012  | CONS_LATAM   | KI           | 178910100400070043 |
      | 000000000000001013  | CONS_LATAM   | KI           | 178910100400070044 |
    And I wait "/edm/material_global_v1" Async Queue complete

    # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    And I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer | localMaterial      | quantity | sourceSystem | yearMonth |
      | 110021   | 000000000000001011 | 9        | CONS_LATAM   | 199601    |
      | 110022   | 000000000000001012 | 8        | CONS_LATAM   | 199601    |
      | 110023   | 000000000000001013 | 8        | CONS_LATAM   | 199601    |
    And I wait "/plan/cns_dp_pos" Async Queue complete

    #cns_dp_pos-customer = cns_dem_grp_asgn-customerShipTo
    And I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | demandGroup |
      | 110021     | 1           |
      | 110022     |             |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr  | hkunnr | datbi    |
      | 110021 | 104076 | 99991231 |
      | 110022 | 104089 | 99991231 |
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
      | 199601  | 199601       | 4        | 1996-01-08 | 1996-01-01   |
      | 199602  | 199601       | 4        | 1996-01-15 | 1996-01-08   |
      | 199603  | 199601       | 4        | 1996-01-22 | 1996-01-15   |
      | 199604  | 199601       | 4        | 1996-01-29 | 1996-01-22   |
      | 199605  | 199602       | 4        | 1996-02-05 | 1996-01-29   |
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

    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | volume |
      | LA_178910100400070042-1 | LA_178910100400070042-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2      |
      | LA_178910100400070042-1 | LA_178910100400070042-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2      |
      | LA_178910100400070042-1 | LA_178910100400070042-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2      |
      | LA_178910100400070042-1 | LA_178910100400070042-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1               | key2       | key3 | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 000000000000001012 | CONS_LATAM |      |      |      | Unable to find Root |              |
      |           | DP             | OMPGdmPos   | 000000000000001013 | CONS_LATAM |      |      |      | Unable to find Root |              |

  @Scenario3
  Scenario: delete all test data

    Then I delete the test data
    And I will remove all data with region "/omp/gdm_pos"
    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario4
  Scenario:Pick the POS record with demnad group for L2 hierachy is assigned

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000001002  | CONS_LATAM   | KI           | 178910100400070052 |
      | 000000000000001003  | CONS_LATAM   | KI           | 178910100400070053 |
      | 000000000000001004  | CONS_LATAM   | KI           | 178910100400070054 |
      | 000000000000001005  | CONS_LATAM   | KI           | 178910100400070055 |
    And I wait "/edm/material_global_v1" Async Queue complete

    # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    And I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer | localMaterial      | quantity | sourceSystem | yearMonth |
      | 110011   | 000000000000001002 | 9        | CONS_LATAM   | 199601    |
      | 110012   | 000000000000001003 | 8        | CONS_LATAM   | 199601    |
      | 110013   | 000000000000001004 | 8        | CONS_LATAM   | 199601    |
      | 110015   | 000000000000001005 | 8        | CONS_LATAM   | 199601    |
    And I wait "/plan/cns_dp_pos" Async Queue complete

    #cns_dp_pos-customer = cns_dem_grp_asgn-customerShipTo
    And I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | demandGroup |
      | 110011     | 1           |
      | 110013     |             |
      | 110014     | 4           |
    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr  | hkunnr | datbi    |
      | 110011 | 104076 | 99991231 |
      | 110012 | 110011 | 99991231 |
      | 110013 | 104077 | 99991231 |
      | 110014 | 110015 | 99991231 |
      | 110015 | 110011 | 99991231 |
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
      | 199601  | 199601       | 4        | 1996-01-08 | 1996-01-01   |
      | 199602  | 199601       | 4        | 1996-01-15 | 1996-01-08   |
      | 199603  | 199601       | 4        | 1996-01-22 | 1996-01-15   |
      | 199604  | 199601       | 4        | 1996-01-29 | 1996-01-22   |
      | 199605  | 199602       | 4        | 1996-02-05 | 1996-01-29   |
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

    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | volume |
      | LA_178910100400070052-1 | LA_178910100400070052-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2      |
      | LA_178910100400070052-1 | LA_178910100400070052-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2      |
      | LA_178910100400070052-1 | LA_178910100400070052-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2      |
      | LA_178910100400070052-1 | LA_178910100400070052-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2      |
      | LA_178910100400070053-1 | LA_178910100400070053-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2      |
      | LA_178910100400070053-1 | LA_178910100400070053-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2      |
      | LA_178910100400070053-1 | LA_178910100400070053-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2      |
      | LA_178910100400070053-1 | LA_178910100400070053-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2      |
      | LA_178910100400070055-1 | LA_178910100400070055-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | CA     | 2      |
      | LA_178910100400070055-1 | LA_178910100400070055-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | CA     | 2      |
      | LA_178910100400070055-1 | LA_178910100400070055-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | CA     | 2      |
      | LA_178910100400070055-1 | LA_178910100400070055-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | CA     | 2      |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | errorCode | functionalArea | interfaceID | key1               | key2       | key3 | key4 | key5 | errorValue          | sourceSystem |
      |           | DP             | OMPGdmPos   | 000000000000001004 | CONS_LATAM |      |      |      | Unable to find Root |              |

  @Scenario4
  Scenario: delete all test data

    Then I delete the test data
    And I will remove all data with region "/omp/gdm_pos"
    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario5
  Scenario: Unit to pick from cns_plan_unit for local base unit

    And I will remove the test file on sink application "GDMPOS.tsv"

     # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000003001  | CONS_LATAM   | BA           | 178910100400030072 |
      | 000000000000003002  | CONS_LATAM   | KI           | 178910100400030073 |
    And I wait "/edm/material_global_v1" Async Queue complete

    And I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer | localMaterial      | quantity | sourceSystem | yearMonth |
      | 104076   | 000000000000003001 | 9        | CONS_LATAM   | 199601    |
      | 104077   | 000000000000003002 | 9        | CONS_LATAM   | 199601    |
    And I wait "/plan/cns_dp_pos" Async Queue complete

    #cns_dp_pos-customer = cns_dem_grp_asgn-customerShipTo
    And I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | demandGroup |
      | 104076     | 1           |
      | 104077     | 2           |
    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr  | hkunnr | datbi    |
      | 104076 | 104086 | 99991231 |
      | 104077 | 104089 | 99991231 |
    And I wait "/project_one/knvh" Async Queue complete

    #cns_plan_unit -localUom = material_global_v1-localBaseUom
    And I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,sourceSystem,unit"
      | localUom | localUomName | unit | sourceSystem |
      | BA       | Bag          | BAG  | CONS_LATAM   |
    And I wait "/plan/cns_plan_unit" Async Queue complete

     #cns_dp_pos-yearMonth = jnj_calendar_v1-fiscalPeriod
    And I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekToDate | weekFromDate |
      | 199601  | 199601       | 4        | 1996-01-08 | 1996-01-01   |
      | 199602  | 199601       | 4        | 1996-01-15 | 1996-01-08   |
      | 199603  | 199601       | 4        | 1996-01-22 | 1996-01-15   |
      | 199604  | 199601       | 4        | 1996-01-29 | 1996-01-22   |
      | 199605  | 199602       | 4        | 1996-02-05 | 1996-01-29   |
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

    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
#    Then I check region data "/omp/gdm_pos" by keyFields "aggregationId,forecastUploadId"
      | aggregationId           | forecastUploadId                            | currencyId | dueDate             | fromDueDate         | unitId | volume |
      | LA_178910100400030072-1 | LA_178910100400030072-1-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 | BAG    | 2      |
      | LA_178910100400030072-1 | LA_178910100400030072-1-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 | BAG    | 2      |
      | LA_178910100400030072-1 | LA_178910100400030072-1-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 | BAG    | 2      |
      | LA_178910100400030072-1 | LA_178910100400030072-1-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 | BAG    | 2      |
      | LA_178910100400030073-2 | LA_178910100400030073-2-1996/01/08 00:00:00 |            | 1996/01/08 00:00:00 | 1996/01/01 00:00:00 |        | 2      |
      | LA_178910100400030073-2 | LA_178910100400030073-2-1996/01/15 00:00:00 |            | 1996/01/15 00:00:00 | 1996/01/08 00:00:00 |        | 2      |
      | LA_178910100400030073-2 | LA_178910100400030073-2-1996/01/22 00:00:00 |            | 1996/01/22 00:00:00 | 1996/01/15 00:00:00 |        | 2      |
      | LA_178910100400030073-2 | LA_178910100400030073-2-1996/01/29 00:00:00 |            | 1996/01/29 00:00:00 | 1996/01/22 00:00:00 |        | 2      |

  @Scenario5
  Scenario: delete all test data

    Then I delete the test data
    And I will remove all data with region "/omp/gdm_pos"
    And I will remove all data with region "/plan/edm_failed_data"

  @Scenario6
  Scenario:No of output records for each AggregationId per month should be equal to the jnj calendar's no of weeks per month and qunatity has to be distributed equally between the weeks/month

    And I will remove the test file on sink application "GDMPOS.tsv"

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | localBaseUom | localDpParentCode  |
      | 000000000000002001  | CONS_LATAM   | KI           | 178910100400050072 |
      | 000000000000002002  | CONS_LATAM   | KI           | 178910100400050073 |
      | 000000000000002003  | CONS_LATAM   | KI           | 178910100400050073 |
      | 000000000000002004  | CONS_LATAM   | KI           | 178910100400050073 |
      | 000000000000002005  | CONS_LATAM   | KI           | 178910100400050073 |


    And I wait "/edm/material_global_v1" Async Queue complete

 # material_global_v1-localMaterialNumber = cns_dp_pos-localMaterial
    And I import "/plan/cns_dp_pos" by keyFields "localMaterial,customer,sourceSystem,yearMonth"
      | customer   | localMaterial      | quantity | sourceSystem | yearMonth |
      | 0000000091 | 000000000000002001 | 425      | CONS_LATAM   | 201801    |
      | 0000000091 | 000000000000002001 | 325      | CONS_LATAM   | 201802    |
      | 0000000091 | 000000000000002001 | 225      | CONS_LATAM   | 201803    |
      | 0000000092 | 000000000000002002 | 3        | CONS_LATAM   | 201801    |
      | 0000000093 | 000000000000002003 | 445      | CONS_LATAM   | 201801    |
      | 0000000094 | 000000000000002004 | 149      | CONS_LATAM   | 201801    |
    And I wait "/plan/cns_dp_pos" Async Queue complete

 #cns_dp_pos-customer = cns_dem_grp_asgn-customerShipTo
    And I import "/plan/cns_dem_grp_asgn" by keyFields "customerId"
      | customerId | demandGroup |
      | 0000000091 | 76100001    |
      | 0000000092 | 76100002    |
      | 0000000093 | 76100002    |
      | 0000000094 | 76100002    |

    And I wait "/plan/cns_dem_grp_asgn" Async Queue complete

    #cns_dem_grp_asgn-customerShipTo = KNVH-KUNNR
    And I import "/project_one/knvh" by keyFields "kunnr"
      | kunnr      | hkunnr     | datbi    |
      | 0000000091 | 0000000361 | 99991231 |
      | 0000000092 | 0000000362 | 99991231 |
      | 0000000093 | 0000000363 | 99991231 |
      | 0000000094 | 0000000364 | 99991231 |
    And I wait "/project_one/knvh" Async Queue complete

    #cns_plan_unit -localUom = material_global_v1-localBaseUom
    And I import "/plan/cns_plan_unit" by keyFields "localUom,localUomName,sourceSystem,unit"
      | localUom | localUomName | unit | sourceSystem |
      | KI       | Crate        | CA   | CONS_LATAM   |
      | BAG      | Bag          | BAG  | CONS_LATAM   |
    And I wait "/plan/cns_plan_unit" Async Queue complete

    #cns_dp_pos-yearMonth = jnj_calendar_v1-fiscalPeriod
    And I import "/edm/jnj_calendar_v1" by keyFields "calWeek,fiscalPeriod,noOfWeek"
      | calWeek | fiscalPeriod | noOfWeek | weekFromDate | weekToDate |
      | 199601  | 201801       | 4        | 2018-01-01   | 2018-01-08 |
      | 199602  | 201801       | 4        | 2018-01-08   | 2018-01-15 |
      | 199603  | 201801       | 4        | 2018-01-15   | 2018-01-22 |
      | 199604  | 201801       | 4        | 2018-01-22   | 2018-01-29 |
      | 199606  | 201802       | 4        | 2018-02-05   | 2018-02-25 |
      | 199607  | 201803       | 4        | 2018-03-05   | 2018-03-25 |

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

    Then A file is found on sink application with name "GDMPOS.tsv"

    Then I check file data for filename "GDMPOS.tsv" by keyFields "aggregationId,forecastUploadId"
#    Then I check region data "/omp/gdm_pos" by keyFields "aggregationId,forecastUploadId"
      | aggregationId                  | forecastUploadId                                   | currencyId | dueDate             | fromDueDate         | unitId | volume |
      | LA_178910100400050072-76100001 | LA_178910100400050072-76100001-2018/01/08 00:00:00 |            | 2018/01/08 00:00:00 | 2018/01/01 00:00:00 | CA     | 106    |
      | LA_178910100400050072-76100001 | LA_178910100400050072-76100001-2018/01/15 00:00:00 |            | 2018/01/15 00:00:00 | 2018/01/08 00:00:00 | CA     | 106    |
      | LA_178910100400050072-76100001 | LA_178910100400050072-76100001-2018/01/22 00:00:00 |            | 2018/01/22 00:00:00 | 2018/01/15 00:00:00 | CA     | 106    |
      | LA_178910100400050072-76100001 | LA_178910100400050072-76100001-2018/01/29 00:00:00 |            | 2018/01/29 00:00:00 | 2018/01/22 00:00:00 | CA     | 106    |
      | LA_178910100400050072-76100001 | LA_178910100400050072-76100001-2018/02/25 00:00:00 |            | 2018/02/25 00:00:00 | 2018/02/05 00:00:00 | CA     | 81     |
      | LA_178910100400050072-76100001 | LA_178910100400050072-76100001-2018/03/25 00:00:00 |            | 2018/03/25 00:00:00 | 2018/03/05 00:00:00 | CA     | 56     |
      | LA_178910100400050073-76100002 | LA_178910100400050073-76100002-2018/01/08 00:00:00 |            | 2018/01/08 00:00:00 | 2018/01/01 00:00:00 | CA     | 149    |
      | LA_178910100400050073-76100002 | LA_178910100400050073-76100002-2018/01/15 00:00:00 |            | 2018/01/15 00:00:00 | 2018/01/08 00:00:00 | CA     | 149    |
      | LA_178910100400050073-76100002 | LA_178910100400050073-76100002-2018/01/22 00:00:00 |            | 2018/01/22 00:00:00 | 2018/01/15 00:00:00 | CA     | 149    |
      | LA_178910100400050073-76100002 | LA_178910100400050073-76100002-2018/01/29 00:00:00 |            | 2018/01/29 00:00:00 | 2018/01/22 00:00:00 | CA     | 149    |

  @Scenario6
  Scenario: delete all test data

    Then I delete the test data
    And I will remove all data with region "/omp/gdm_pos"
    And I will remove all data with region "/plan/edm_failed_data"
