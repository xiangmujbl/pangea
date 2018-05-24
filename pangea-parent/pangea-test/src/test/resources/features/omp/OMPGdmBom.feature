@pangea_test @4254
Feature: OMPGdmBom 4254

  Scenario: Full Load curation

    Given I import "/edm/matl_bom" by keyFields ""
      | srcSysCd | matlNum | plntCd | altBomNum | bomNum | bomUsgCd |
    And I wait "/edm/matl_bom" Async Queue complete

    Given I import "/plan/cns_plan_parameter" by keyFields ""
      | sourceSystem | dataObject | attribute | parameterValue |
    And I wait "/plan/cns_plan_parameter" Async Queue complete

    Given I import "/edm/matl_prod_versn" by keyFields ""
      | srcSysCd | matlNum | plntCd | altBomNum | prdntVrsnNum |
    And I wait "/edm/matl_prod_versn" Async Queue complete

    Given I import "/edm/matl_mfg_rtng" by keyFields ""
      | srcSysCd | matlNum | plntCd | rtngTypCd | rntgGrpCd | rntgGrpCntrNbr |
    And I wait "/edm/matl_mfg_rtng" Async Queue complete

    Given I import "/edm/mfg_rtng_itm_nde" by keyFields ""
      | srcSysCd | rtngTypCd | rtngGrpCd | rtngGrpCntrNbr |
    And I wait "/edm/mfg_rtng_itm_nde" Async Queue complete

    Given I import "/edm/mfg_rtng_itm" by keyFields ""
      | srcSysCd | rtngTypCd | rtngGrpCd | rtngNdeNum | operNum |
    And I wait "/edm/mfg_rtng_itm" Async Queue complete

    Given I import "/edm/bom_hdr" by keyFields ""
      | srcSysCd | bomNum | altBomNum |
    And I wait "/edm/bom_hdr" Async Queue complete


    When I submit task with xml file "xml/omp/OMPGdmBom.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/omp/gdm_bom" by keyFields ""
      | bomId | active | activeFCTERP | activeOPRERP | activeSOPERP | comments | endEff | locationId | startEff |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/edm/matl_bom" and "/omp/gdm_bom,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/omp/gdm_bom"

    And I will remove all data with region "/plan/edm_failed_data"

