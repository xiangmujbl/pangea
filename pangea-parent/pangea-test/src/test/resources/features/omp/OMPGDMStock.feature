@pangea @AEAZ-5960
Feature: OMPGDMStock AEAZ-5960

  @Scenario1
  Scenario: Test PRO1
#   process_order-matlNum) = (material_global_v1-localMaterialNumber),  (process_order- sourceSystem) = (material_global_v1-sourceSystem)
    Given I import "/edm/process_order_v1" by keyFields "sourceSystem,mfgOrdrNum,mfgOrdrItemNum"
      | matlNum | plntCd | mfgOrdrNum | sourceSystem | mfgOrdrItemNum | ordrType | ordrQty | rcvdQty | ordrStts      | fctrNmrtrMeas | fctrDnmntrMeas | planClseDt | goodRcptLdDaysQty | prdtnSchdEndDt | prdntVrsnNum | delInd |
      | 55735   | CO01   | 8030471    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55738   | CO01   | 8030472    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55739   | CO01   | 8030473    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
#      (process_order- sourceSystem) != (material_global_v1-sourceSystem)
      | 55736   | CO01   | 8030473    | CONS_LATAM1  |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
#      process_order-matlNum) != (material_global_v1-localMaterialNumber)
      | 55737   | CO01   | 8030474    | CONS_LATAM2  |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
    And I wait "/edm/process_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | localPlant | materialNumber | primaryPlanningCode | sourceSystem |
      | 55735               |            | 55735          | 55737               | CONS_LATAM   |
      | 55739               |            |                |                     | CONS_LATAM   |
#      material_global_v1-primaryPlanningCode is Blank
      | 55738               |            | 55736          |                     | CONS_LATAM   |
      | 55736               |            | 55735          | 55735               | CONS_LATAM2  |
      | 55735               |            | 55735          | 55735               | CONS_LATAM1  |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute1Value,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
      | I                  | plntCd                 | CO13                        | ordrType               | PP03                        | process_order     | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | _test_reservation_ | country | localCountry | localCurrency | localPlanningRelevant | localPlant | localPlantName             | localPlantType | plant | plantType               | region                | site | sourceSystem |
      |                    | BR      | BR           | BRL           | X                     | CO01       | J&J Colombia - LADS (CO01) |                | CO07  | MP, Manufacturing Plant | CO, COLOMBIA COLOMBIA | Cali | CONS_LATAM   |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | active | dpRelevant | localMaterialNumber | localParentCode   | localPlant | materialNumber | noPlanRelevant | parentActive | ppc   | sourceSystem | spRelevant |
      | X      |            | 55735               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55738               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55739               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
    And I wait "plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmStock.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
      | stockId                       | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                 | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 55736/CONS_LATAM_CO01/8030472 | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030472    |                      |          | CONS_LATAM_CO01 | V01/55736/CONS_LATAM_CO01 | Production    | 55736     | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 55737/CONS_LATAM_CO01/8030471 | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030471    |                      |          | CONS_LATAM_CO01 | V01/55737/CONS_LATAM_CO01 | Production    | 55737     | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | /CONS_LATAM_CO01/8030473      | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030473    |                      |          | CONS_LATAM_CO01 | V01//CONS_LATAM_CO01      | Production    |           | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#    Then I check the number of records region "/omp/gdm_stock" count "1"
#
#    And I compare the number of records between "/edm/process_order_v1" and "/omp/gdm_stock"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_stock"

  @Scenario2
  Scenario: Test PRO6
    Given I import "/edm/process_order_v1" by keyFields "sourceSystem,mfgOrdrNum,mfgOrdrItemNum"
      | matlNum | plntCd | mfgOrdrNum | sourceSystem | mfgOrdrItemNum | ordrType | ordrQty | rcvdQty | ordrStts      | fctrNmrtrMeas | fctrDnmntrMeas | planClseDt | goodRcptLdDaysQty | prdtnSchdEndDt | prdntVrsnNum | delInd |
      | 55735   | CO01   | 8030471    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55738   | CO01   | 8030472    | CONS_LATAM   |                | PP01     | 5000    | 1000    | PCNF          | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55739   | CO01   | 8030473    | CONS_LATAM   |                | PP01     | 5000    | 1000    | REL           | 1             | 1              |            | 3                 | 20170626       | V01          |        |
      | 55770   | CO01   | 8030470    | CONS_LATAM   |                | PP01     | 5000    | 1000    | CRTD          | 1             | 1              |            | 3                 | 20170626       | V01          |        |
      | 55731   | CO01   | 8030474    | CONS_LATAM   |                | PP02     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55732   | CO01   | 8030475    | CONS_LATAM   |                | PP04     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
#  (cns_plan_object_filter-sourceSystem) != source system derived as in StockId".
      | 55733   | CO01   | 8030476    | CONS_LATAM1  |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
#      (process_order-ordrStts) contains at least one of status text/phrase value = CRTD or REL or PCNF
      | 55734   | CO01   | 8030477    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55740   | CO01   | 8030480    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55741   | CO01   | 8030481    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55742   | CO01   | 8030482    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55743   | CO01   | 8030483    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
#      (process_order-delInd) != " "
      | 55744   | CO01   | 8030484    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          | 123    |
    And I wait "/edm/process_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | localPlant | materialNumber | primaryPlanningCode | sourceSystem |
      | 55735               |            | 55735          | 55737               | CONS_LATAM   |
      | 55738               |            | 55736          |                     | CONS_LATAM   |
      | 55739               |            | 55738          | 55738               | CONS_LATAM   |
      | 55731               |            | 55739          | 55739               | CONS_LATAM   |
      | 55732               |            | 557310         | 557310              | CONS_LATAM   |
      | 55733               |            | 557311         | 557311              | CONS_LATAM   |
      | 55734               |            | 557313         | 557313              | CONS_LATAM   |
      | 55740               |            | 557314         | 557314              | CONS_LATAM   |
      | 55741               |            | 557315         | 557315              | CONS_LATAM   |
      | 55742               |            | 557316         | 557316              | CONS_LATAM   |
      | 55743               |            | 557317         | 557317              | CONS_LATAM   |
      | 55744               |            | 557318         | 557318              | CONS_LATAM   |
      | 55770               |            | 557319         | 557319              | CONS_LATAM   |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute1Value,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem    |
      | I                  | plntCd                 | CO01                        | ordrType               | PP01                        | process_order     | CONS_LATAM      |
#      (cns_plan_object_filter- sourceObjectTechName) != (process_order)
      | I                  | plntCd                 | CO01                        | ordrType               | PP04                        | process_order        | CONS_LATAM      |
#      cns_plan_object_filter- sourceObjectAttribute1) != plntCd
      | I                  | plntCd1                | CO01                        | ordrType               | PP02                        | process_order     | CONS_LATAM      |
#      cns_plan_object_filter-sourceSystem) != source system derived as in StockId"
      | I                  | plntCd                 | CO01                        | ordrType               | PP03                        | process_order     | CONS_LATAM_test |
##     (cns_plan_object_filter- sourceObjectAttribute2Value) not matching with ( process_order-ordrType)
      | I                  | plntCd                 | CO01                        | ordrType               | PP05                        | process_order     | CONS_LATAM      |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | _test_reservation_ | country | localCountry | localCurrency | localPlanningRelevant | localPlant | localPlantName             | localPlantType | plant | plantType               | region                | site | sourceSystem |
      |                    | BR      | BR           | BRL           | X                     | CO01       | J&J Colombia - LADS (CO01) |                | CO07  | MP, Manufacturing Plant | CO, COLOMBIA COLOMBIA | Cali | CONS_LATAM   |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | active | dpRelevant | localMaterialNumber | localParentCode   | localPlant | materialNumber | noPlanRelevant | parentActive | ppc   | sourceSystem | spRelevant |
      | X      |            | 55735               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55738               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55739               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55731               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55732               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55733               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55734               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55740               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55741               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55742               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55743               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55744               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55770               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
    And I wait "plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmStock.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
      | stockId                        | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                  | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 55736/CONS_LATAM_CO01/8030472  | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030472    |                      |          | CONS_LATAM_CO01 | V01/55736/CONS_LATAM_CO01  | Production    | 55736     | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 55738/CONS_LATAM_CO01/8030473  | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030473    |                      |          | CONS_LATAM_CO01 | V01/55738/CONS_LATAM_CO01  | Production    | 55738     | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/29 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 557319/CONS_LATAM_CO01/8030470 | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030470    |                      |          | CONS_LATAM_CO01 | V01/557319/CONS_LATAM_CO01 | Production    | 557319    | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/29 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
  # Then I check the number of records region "/omp/gdm_stock" count "1"
#
#    And I compare the number of records between "/edm/process_order_v1" and "/omp/gdm_stock"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_stock"

  @Scenario3
  Scenario: Test PRO7

    Given I import "/edm/process_order_v1" by keyFields "sourceSystem,mfgOrdrNum,mfgOrdrItemNum"
      | matlNum | plntCd | mfgOrdrNum | sourceSystem    | mfgOrdrItemNum | ordrType | ordrQty | rcvdQty | ordrStts      | fctrNmrtrMeas | fctrDnmntrMeas | planClseDt | goodRcptLdDaysQty | prdtnSchdEndDt | prdntVrsnNum | delInd |
      | 55735   | CO01   | 8030471    | CONS_LATAM      |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55738   | C013   | 8030473    | CONS_LATAM      |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
#  (process_order-plntCd) != (Plant_v1-localPlant)
      | 55739   | CO02   | 8030472    | CONS_LATAM      |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
#  (process_order - sourceSystem ) != (Plant_v1-sourceSystem)
      | 55741   | CO01   | 8030472    | CONS_LATAM_test |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
    And I wait "/edm/process_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | localPlant | materialNumber | primaryPlanningCode | sourceSystem |
      | 55735               |            | 55735          | 55737               | CONS_LATAM   |
      | 55738               |            | 55736          |                     | CONS_LATAM   |
      | 55739               |            | 55736          |                     | CONS_LATAM   |
      | 55741               |            | 55736          |                     | CONS_LATAM   |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute1Value,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
      | I                  | plntCd                 | CO13                        | ordrType               | PP03                        | process_order     | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | _test_reservation_ | country | localCountry | localCurrency | localPlanningRelevant | localPlant | localPlantName             | localPlantType | plant | plantType               | region                | site | sourceSystem |
      |                    | BR      | BR           | BRL           | X                     | CO01       | J&J Colombia - LADS (CO01) |                | CO07  | MP, Manufacturing Plant | CO, COLOMBIA COLOMBIA | Cali | CONS_LATAM   |
#  (Plant_v1-localPlanningrelevant) != X
      |                    | BR      | BR           | BRL           | Y                     | C013       | J&J Colombia - LADS (CO01) |                | CO07  | MP, Manufacturing Plant | CO, COLOMBIA COLOMBIA | Cali | CONS_LATAM   |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | active | dpRelevant | localMaterialNumber | localParentCode   | localPlant | materialNumber | noPlanRelevant | parentActive | ppc   | sourceSystem | spRelevant |
      | X      |            | 55735               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55738               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55739               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55741               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55743               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |


    And I wait "plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmStock.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
      | stockId                       | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                 | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 55737/CONS_LATAM_CO01/8030471 | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030471    |                      |          | CONS_LATAM_CO01 | V01/55737/CONS_LATAM_CO01 | Production    | 55737     | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#    Then I check the number of records region "/omp/gdm_stock" count "1"
#
#    And I compare the number of records between "/edm/process_order_v1" and "/omp/gdm_stock"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_stock"

  @Scenario4
  Scenario: Test PRO9
    Given I import "/edm/process_order_v1" by keyFields "sourceSystem,mfgOrdrNum,mfgOrdrItemNum"
      | matlNum | plntCd | mfgOrdrNum | sourceSystem | mfgOrdrItemNum | ordrType | ordrQty | rcvdQty | ordrStts      | fctrNmrtrMeas | fctrDnmntrMeas | planClseDt | goodRcptLdDaysQty | prdtnSchdEndDt | prdntVrsnNum | delInd |
      | 55735   | CO01   | 8030471    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55738   | CO01   | 8030472    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55739   | CO01   | 8030476    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      #      (process_order-matlNum) != (cns_material_plan_status_v1-localMaterialNumber
      | 55737   | CO01   | 8030473    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
#      (process_order-plntCd) != (cns_material_plan_status_v1-localPlant
      | 55735   | CO02   | 8030474    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
#   (process_order-sourceSystem) != (cns_material_plan_status-sourceSystem)
      | 55738   | CO01   | 8030475    | CONS_LATAM1  |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
#      (process_order-matlNum) != (material_global_v1-localMaterialNumber)
      | 55444   | CO01   | 8030478    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
#     (process_order-sourceSystem) != (material_global_v1-sourceSystem)
      | 55740   | CO01   | 8030479    | CONS_LATAM1  |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55741   | CO01   | 8030480    | CONS_LATAM  |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
    And I wait "/edm/process_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | localPlant | materialNumber | primaryPlanningCode | sourceSystem |
      | 55735               |            | 55735          | 55737               | CONS_LATAM   |
      | 55738               |            | 55736          |                     | CONS_LATAM   |
      | 55739               |            | 55739          |                     | CONS_LATAM   |
#     (process_order-sourceSystem) = (material_global_v1-sourceSystem)
      | 55740               |            | 55739          |                     | CONS_LATAM   |
      | 55741               |            |                |                     | CONS_LATAM   |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute1Value,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
      | I                  | plntCd                 | CO13                        | ordrType               | PP03                        | process_order     | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | _test_reservation_ | country | localCountry | localCurrency | localPlanningRelevant | localPlant | localPlantName             | localPlantType | plant | plantType               | region                | site | sourceSystem |
      |                    | BR      | BR           | BRL           | X                     | CO01       | J&J Colombia - LADS (CO01) |                | CO07  | MP, Manufacturing Plant | CO, COLOMBIA COLOMBIA | Cali | CONS_LATAM   |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | active | dpRelevant | localMaterialNumber | localParentCode   | localPlant | materialNumber | noPlanRelevant | parentActive | ppc   | sourceSystem | spRelevant |
      | X      |            | 55735               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
#      (cns_material_plan_status-noPlanRelevant) != X
      | X      |            | 55738               | 78910109411230000 | CO01       |                | Y              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55739               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55740               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55741               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
    And I wait "plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmStock.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
      | stockId                       | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                 | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 55737/CONS_LATAM_CO01/8030471 | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030471    |                      |          | CONS_LATAM_CO01 | V01/55737/CONS_LATAM_CO01 | Production    | 55737     | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 55739/CONS_LATAM_CO01/8030476 | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030476    |                      |          | CONS_LATAM_CO01 | V01/55739/CONS_LATAM_CO01 | Production    | 55739     | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | /CONS_LATAM_CO01/8030480 | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030480    |                      |          | CONS_LATAM_CO01 | V01//CONS_LATAM_CO01 | Production    |      | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#    Then I check the number of records region "/omp/gdm_stock" count "1"
#
#    And I compare the number of records between "/edm/process_order_v1" and "/omp/gdm_stock"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_stock"

 @Scenario5
  Scenario: Test PRO12
    Given I import "/edm/process_order_v1" by keyFields "sourceSystem,mfgOrdrNum,mfgOrdrItemNum"
      | matlNum | plntCd | mfgOrdrNum | sourceSystem | mfgOrdrItemNum | ordrType | ordrQty | rcvdQty | ordrStts      | fctrNmrtrMeas | fctrDnmntrMeas | planClseDt | goodRcptLdDaysQty | prdtnSchdEndDt | prdntVrsnNum | delInd |
      | 55735   | CO01   | 8030471    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
#      (process_order-prdtnSchdEndDt) + (process_order-goodRcptLdDaysQty) is Saturday
      | 55738   | CO01   | 8030472    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20180614       | V01          |        |
#      (process_order-prdtnSchdEndDt) + (process_order-goodRcptLdDaysQty) is Sunday
      | 55739   | CO01   | 8030473    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 3                 | 20180614       | V01          |        |
    And I wait "/edm/process_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | localPlant | materialNumber | primaryPlanningCode | sourceSystem |
      | 55735               |            | 55735          | 55737               | CONS_LATAM   |
      | 55738               |            | 55736          |                     | CONS_LATAM   |
      | 55739               |            | 55738          |                     | CONS_LATAM   |
    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute1Value,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
      | I                  | plntCd                 | CO13                        | ordrType               | PP03                        | process_order     | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | _test_reservation_ | country | localCountry | localCurrency | localPlanningRelevant | localPlant | localPlantName             | localPlantType | plant | plantType               | region                | site | sourceSystem |
      |                    | BR      | BR           | BRL           | X                     | CO01       | J&J Colombia - LADS (CO01) |                | CO07  | MP, Manufacturing Plant | CO, COLOMBIA COLOMBIA | Cali | CONS_LATAM   |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | active | dpRelevant | localMaterialNumber | localParentCode   | localPlant | materialNumber | noPlanRelevant | parentActive | ppc   | sourceSystem | spRelevant |
      | X      |            | 55735               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55738               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
        | X      |            | 55739               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
    And I wait "plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmStock.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
      | stockId                       | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                 | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 55736/CONS_LATAM_CO01/8030472 | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030472    |                      |          | CONS_LATAM_CO01 | V01/55736/CONS_LATAM_CO01 | Production    | 55736     | 0.0             | 4000     | 2018/06/14 00:00:00 | 0.0                | 0.0             | 2018/06/18 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 55737/CONS_LATAM_CO01/8030471 | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030471    |                      |          | CONS_LATAM_CO01 | V01/55737/CONS_LATAM_CO01 | Production    | 55737     | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 55738/CONS_LATAM_CO01/8030473 | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030473    |                      |          | CONS_LATAM_CO01 | V01/55738/CONS_LATAM_CO01 | Production    | 55738     | 0.0             | 4000     | 2018/06/14 00:00:00 | 0.0                | 0.0             | 2018/06/18 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
#    Then I check the number of records region "/omp/gdm_stock" count "1"
#
#    And I compare the number of records between "/edm/process_order_v1" and "/omp/gdm_stock"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_stock"

  @Scenario6
  Scenario: Full Load curation

    Given I import "/edm/process_order_v1" by keyFields "sourceSystem,mfgOrdrNum,mfgOrdrItemNum"
      | matlNum | plntCd | mfgOrdrNum | sourceSystem | mfgOrdrItemNum | ordrType | ordrQty | rcvdQty | ordrStts      | fctrNmrtrMeas | fctrDnmntrMeas | planClseDt | goodRcptLdDaysQty | prdtnSchdEndDt | prdntVrsnNum | delInd |
      | 55735   | CO01   | 8030471    | CONS_LATAM   |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55738   | CO01   | 8030472    | CONS_LATAM   |                | PP01     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55739   | CO01   | 8030473    | CONS_LATAM   |                | PP01     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 3                 | 20170626       | V01          |        |
      | 55731   | CO01   | 8030474    | CONS_LATAM   |                | PP02     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55732   | CO01   | 8030475    | CONS_LATAM   |                | PP04     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
      | 55733   | CO01   | 8030476    | CONS_LATAM  |                | PP03     | 5000    | 1000    | CRTD REL PCNF | 1             | 1              |            | 2                 | 20170626       | V01          |        |
    And I wait "/edm/process_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | localPlant | materialNumber | primaryPlanningCode | sourceSystem |
      | 55735               |            | 55735          | 55737               | CONS_LATAM   |
      | 55738               |            | 55736          |                     | CONS_LATAM   |
      | 55739               |            | 55738          |                     | CONS_LATAM   |
      | 55731               |            | 55739          |                     | CONS_LATAM   |
      | 55732               |            | 557310         |                     | CONS_LATAM   |
      | 55733               |            | 557311         |                     | CONS_LATAM   |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute1Value,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
      | I                  | plntCd                 | CO13                        | ordrType               | PP03                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | BR13                        | ordrType               | PP01                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | AR01                        | ordrType               | PP04                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | CO01                        | ordrType               | PP02                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | AR01                        | ordrType               | PP01                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | CO01                        | ordrType               | PP03                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | BR12                        | ordrType               | PP01                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                | CO01                        | ordrType               | PP01                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                | BR12                        | ordrType               | PP02                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                | BR12                        | ordrType               | PP04                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                | AR01                        | ordrType               | PP03                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                | CO01                        | ordrType               | PP04                        | process_order     | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | _test_reservation_ | country | localCountry | localCurrency | localPlanningRelevant | localPlant | localPlantName             | localPlantType | plant | plantType               | region                | site | sourceSystem |
      |                    | BR      | BR           | BRL           | X                     | CO01       | J&J Colombia - LADS (CO01) |                | CO07  | MP, Manufacturing Plant | CO, COLOMBIA COLOMBIA | Cali | CONS_LATAM   |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | active | dpRelevant | localMaterialNumber | localParentCode   | localPlant | materialNumber | noPlanRelevant | parentActive | ppc   | sourceSystem | spRelevant |
      | X      |            | 55735               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55738               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55739               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55731               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55732               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55733               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55734               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55740               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55741               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55742               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55743               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
      | X      |            | 55744               | 78910109411230000 | CO01       |                | X              | X            | 58722 | CONS_LATAM   | X          |
    And I wait "plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmStock.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
      | stockId                        | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                  | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 55736/CONS_LATAM_CO01/8030472  | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030472    |                      |          | CONS_LATAM_CO01 | V01/55736/CONS_LATAM_CO01  | Production    | 55736     | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 55737/CONS_LATAM_CO01/8030471  | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030471    |                      |          | CONS_LATAM_CO01 | V01/55737/CONS_LATAM_CO01  | Production    | 55737     | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 55738/CONS_LATAM_CO01/8030473  | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030473    |                      |          | CONS_LATAM_CO01 | V01/55738/CONS_LATAM_CO01  | Production    | 55738     | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/29 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 55739/CONS_LATAM_CO01/8030474  | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030474    |                      |          | CONS_LATAM_CO01 | V01/55739/CONS_LATAM_CO01  | Production    | 55739     | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 557310/CONS_LATAM_CO01/8030475 | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030475    |                      |          | CONS_LATAM_CO01 | V01/557310/CONS_LATAM_CO01 | Production    | 557310    | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 557311/CONS_LATAM_CO01/8030476 | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8030476    |                      |          | CONS_LATAM_CO01 | V01/557311/CONS_LATAM_CO01 | Production    | 557311    | 0.0             | 4000     | 2017/06/26 00:00:00 | 0.0                | 0.0             | 2017/06/28 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |

    # Then I check the number of records region "/omp/gdm_stock" count "1"
#
#    And I compare the number of records between "/edm/process_order_v1" and "/omp/gdm_stock"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_stock"
#
  @Scenario7
  Scenario: Full Load curation

    Given I import "/edm/process_order_v1" by keyFields "sourceSystem,mfgOrdrNum,mfgOrdrItemNum"
      | matlNum | plntCd | mfgOrdrNum | sourceSystem | mfgOrdrItemNum | ordrType | ordrQty | rcvdQty | ordrStts      | fctrNmrtrMeas | fctrDnmntrMeas | goodRcptLdDaysQty | prdtnSchdEndDt | prdntVrsnNum | delInd |
      | 55735  | CO01  | 8030471   | CONS_LATAM   |                | PP08     | 5000    | 1000 | CRTD REL PCNF      | 1             | 1              | 6                 | 20170626       | V01          |        |
      | 97360  | CO13  | 8028789   | CONS_LATAM   |                | PP01     | 3000    | 0    | CRTD REL PCNF      | 1             | 1              | 3                 | 20170621       | V01          |        |
      | 57039  | CO01  | 8072249   | CONS_LATAM   |                | PP06     | 2000    | 1000 | ABC XYZ            | 1             | 1              | 3                 | 20170823       | V01          |        |
      | 80990  | CO01  | 8107841   | CONS_LATAM   |                | PP03     | 4123    | 0    | CRTD                | 1             | 1              |1                 | 20171119       | V02          |        |
      | 96161  | CO01  | 8101817   | CONS_LATAM   |                | PP03     | 1236    | 0    | CRTD                | 1             | 1              |1                 | 20171215       | V01          |        |
      | 91732  | BR12  | 8091814   | CONS_LATAM   |                | PP03     | 468     | 0    | CRTD REL PCNF      | 1             | 1              | 1                 | 20171010       | V01          |        |
      | 60520  | BR13  | 8072902   | CONS_LATAM   |                | PP01     | 157     | 0    | CRTD REL PCNF      | 1             | 1              | 3                 | 20171012       | V01          |        |
      | 528395 | CO01  | 8030471    | CONS_LATAM  |                | PP08     | 5000    | 1000 | REL PCNF           | 1             | 1              | 2                 | 20170626       | V01          |        |
      | 528399 | BR12  | 8030472    | CONS_LATAM  |                | PP08     | 5000    | 1000 | REL PCNF           | 1             | 1              | 2                 | 20170626       | V01          |        |
    And I wait "/edm/process_order_v1" Async Queue complete

    Given I import "/edm/material_global_v1" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | materialNumber | primaryPlanningCode | sourceSystem |
      | 55735                 | 55735        | 55735               | CONS_LATAM   |
      | 97360                 | 97360        | 97360               | CONS_LATAM   |
      | 57039                 | 57039        | 57039               | CONS_LATAM   |
      | 80990                 | 80990        | 80990               | CONS_LATAM   |
      | 96161                 |               |                     | CONS_LATAM   |
      | 91732                 | 91732        | 91732               | CONS_LATAM   |
      | 60520                 | 60520        | 60520               | CONS_LATAM   |
      | 528395                | 528395       | 528395               | CONS_LATAM   |
      | 528399                | 528391       | 528398               | CONS_LATAM   |

    And I wait "/edm/material_global_v1" Async Queue complete

    Given I import "/plan/cns_plan_object_filter" by keyFields "sourceObjectAttribute1,sourceObjectAttribute1Value,sourceObjectAttribute2,sourceObjectAttribute2Value,sourceObjectTechName,sourceSystem"
      | inclusionExclusion | sourceObjectAttribute1 | sourceObjectAttribute1Value | sourceObjectAttribute2 | sourceObjectAttribute2Value | sourceObjectTechName | sourceSystem |
      | I                  | plntCd                 | AR01                        | ordrType               | PP04                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | AR01                        | ordrType               | PP03                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | BR12                        | ordrType               | PP04                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | CO01                        | ordrType               | PP03                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | AR01                        | ordrType               | PP01                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | BR12                        | ordrType               | PP02                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | CO01                        | ordrType               | PP04                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | CO01                        | ordrType               | PP02                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | CO01                        | ordrType               | PP01                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | BR12                        | ordrType               | PP01                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | BR12                        | ordrType               | PP03                        | process_order     | CONS_LATAM   |
      | I                  | plntCd                 | AR01                        | ordrType               | PP02                        | process_order     | CONS_LATAM   |
      | E                  | plntCd                 | AB01                        | ordrType               | PP04                        | process_order     | CONS_LATAM   |
    And I wait "/plan/cns_plan_object_filter" Async Queue complete

    Given I import "/edm/plant_v1" by keyFields "localPlant,sourceSystem"
      | localPlanningRelevant | localPlant | sourceSystem |
      | X                       | CO01      | CONS_LATAM   |
      | X                       | C013      | CONS_LATAM   |
      | X                       | BR12       | CONS_LATAM   |
      | X                       | BR13       | CONS_LATAM   |
    And I wait "/edm/plant_v1" Async Queue complete

    Given I import "plan/cns_material_plan_status" by keyFields "localMaterialNumber,localPlant,sourceSystem"
      | localMaterialNumber | localPlant |noPlanRelevant | sourceSystem |
      | 55735              | CO01         |X              | CONS_LATAM    |
      | 97360              | CO13         |              | CONS_LATAM    |
      | 57039              | CO01         |              | CONS_LATAM    |
      | 80990              | CO01         |X              | CONS_LATAM    |
      | 96161              | CO01         |              | CONS_LATAM    |
      | 91732              | BR12         |X              | CONS_LATAM    |
      | 60520              | BR13         |X              | CONS_LATAM    |
      | 528395             | CO01         |X              | CONS_LATAM    |
      | 528399             | BR12         |                | CONS_LATAM    |
    And I wait "plan/cns_material_plan_status" Async Queue complete

    When I submit task with xml file "xml/edm/EDMInventoryStock.xm" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_stock" by keyFields "stockId"
      | stockId                        | active | activeOPRERP | activeSOPERP | batchId | blockedQuantity | consignment | certaintyId | erpOrderId | inventoryLinkGroupId | vendorId | locationId      | processId                  | processTypeId | productId | qualityQuantity | quantity | receiptDate         | restrictedQuantity | returnsQuantity | startDate           | stockType | transferQuantity | transitDate         | unrestrictedQuantity |
      | 60520/CONS_LATAM_BR13/8072902  | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8072902    |                      |          | CONS_LATAM_BR13 | V01/60520/CONS_LATAM_BR13  | Production    | 60520     | 0.0             | 157     | 2017/10/12 00:00:00 | 0.0                | 0.0             | 2017/10/16 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 91732/CONS_LATAM_BR12/8091814  | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8091814    |                      |          | CONS_LATAM_BR12 | V01/91732/CONS_LATAM_BR12  | Production    | 91732     | 0.0             | 468     | 2017/10/10 00:00:00 | 0.0                | 0.0             | 2017/10/11 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |
      | 80990/CONS_LATAM_CO01/8107841  | YES    | YES          | NO           |         | 0.0             | NO          | FE          | 8107841    |                      |          | CONS_LATAM_CO01 | V02/80990/CONS_LATAM_CO01  | Production    | 80990     | 0.0             | 4123     | 2017/11/19 00:00:00 | 0.0                | 0.0             | 2017/11/20 00:00:00 | movement  | 0.0              | 1980/01/01 00:00:00 | 0.0                  |

    # Then I check the number of records region "/omp/gdm_stock" count "1"
#
#    And I compare the number of records between "/edm/process_order_v1" and "/omp/gdm_stock"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_stock"


