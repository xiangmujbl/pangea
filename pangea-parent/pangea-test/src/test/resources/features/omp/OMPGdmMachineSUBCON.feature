@pangea_test @AEAZ-8799
Feature: OMPGdmMachineSUBCON AEAZ-8799

  @Scenario1
  Scenario: OMPGdmMachineSUBCON

#    When I import "/plan/cns_spl_pln_loc" by keyFields "sourceSystem,localNumber,vendorOrCustomer,localName"
#      | sourceSystem | localNumber | vendorOrCustomer | localName                           | localPlant | planLocTypeID | planLocTypeDesc     | specLocAtt1 | specLocAttDesc1 | specLocAtt2 | specLocAttDesc2 | specLocAtt3    | specLocAttDesc3 | localCountry | localRegion |
#      | CONS_LATAM   | 0000001188  | V                | SUPPORT PACK IND E COM LTDA         | BR11       | SUBCON        | Mfg Plant Copacker  |             |                 |             |                 | SUBCON Machine | SUB_1188        | BR           | 103         |
#      | CONS_LATAM   | 0000001189  | V                | SUPPORT PACK IND E COM LTDA         | BR12       | SUBCON        | Mfg Plant Copacker  |             |                 |             |                 | SUBCON Machine | SUB_1189        | BR           | 103         |
#      | CONS_LATAM   | 0000087047  | V                | ARCO IRIS MONT DE KITS E SHRINK PAC | BR12       | SUBCON        | Mfg Plant Copacker  |             |                 |             |                 | SUBCON Machine |                 | BR           | 103         |
#      | CONS_LATAM   | 0000015574  | V                | ASPRILLA ORTIZ FABIO                | CO01       | SUBCON        | Mfg Plant Copacker  |             |                 |             |                 | SUBCON Machine | SUB_15574       | CO           | 103         |
#      | CONS_LATAM   | 0000012398  | V                | DAVISO IND COM PROD HIGIENICOS SA   |            | VENDOR        | Ext Manufacturer FG |             |                 |             |                 |                |                 | BR           | 103         |
#    And I wait "/plan/cns_spl_pln_loc" Async Queue complete
#
#    When I import "/plan/cns_plant_attr" by keyFields "sourceSystem,localPlant"
#      | sourceSystem | localPlant | localPlantName                     | localPlanningRelevant | locationAttribute1Desc | locationAttribute1Value | locationAttribute2Desc | locationAttribute2Value | planLocTypeDesc              | planLocTypeId | plant | plantType                   |
#      | CONS_LATAM   | BR11       | J&J BR Sï¿½o Josï¿½ Campos - Indus |                       | Country                | Brazil                  | Volume                 | High                    | Internal Manufacturing Plant | IM            | BR59  | MP,ï¿½Manufacturingï¿½Plant |
#      | CONS_LATAM   | BR12       | J&J BR Sï¿½o Josï¿½ Campos - Indus | X                     | Country                | Brazil                  | Volume                 | High                    | Internal Manufacturing Plant | IM            | BR59  | MP,ï¿½Manufacturingï¿½Plant |
#      | CONS_LATAM   | CO01       | J&J Colombia - LADS (CO01)         | X                     | Country                | Colombia                | Volume                 | High                    | Internal Manufacturing Plant | IM            | CO07  | MP,ï¿½Manufacturingï¿½Plant |
#    And I wait "/plan/cns_plant_attr" Async Queue complete
#
#    When I import "/plan/cns_plan_parameter" by keyFields "sourceSystem,attribute,dataObject"
#      | sourceSystem | attribute  | dataObject  |
#      | CONS_LATAM   | CONS_LATAM | SEND_TO_OMP |
#    And I wait "/plan/cns_plan_parameter" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmMachineSUBCON.xml" and execute file "jar/pangea-view.jar"

#    Then I check region data "/omp/gdm_machine" by keyFields "machineId"
#      | machineId                 | active | activeOPRERP | activeSOPERP | building | description                | locationId      | machineCapacity | machineTypeId | parentMachineId |
#      | CONS_LATAM_BR12/SUB_1189  | YES    | YES          | NO           |          | Dummy Subcon W/C SUB_1189  | CONS_LATAM_BR12 | infinite        | Production    |                 |
#      | CONS_LATAM_CO01/SUB_15574 | YES    | YES          | NO           |          | Dummy Subcon W/C SUB_15574 | CONS_LATAM_CO01 | infinite        | Production    |                 |
#
#
#    And I delete the test data
#
#    And I will remove all data with region "/omp/gdm_machine"
#
        