@pangea_test @AEAZ-494
Feature: EDMMaterialType XREF - Curation

  Scenario: Full Load curation
    #  1. test get attributes from ems_f_mdm_material_types where zSourceSystem = [EMS] (rule T1)

    Given I import "/ems/ems_f_mdm_material_types" by keyFields "mdmCode,zSourceSystem"
      | mdmCode | zSourceSystem     | mdmName     |
      | &       | [CNS MFG - Italy] | Budget F.G. |
      | 0001    | [MDD Mentor]      | HST         |
      | 0002    | [EMS]             | Ontario PST |
      | 0003    | [EMS]             | PST         |
    And I wait "/ems/ems_f_mdm_material_types" Async Queue complete

    When I submit task with xml file "xml/edm/material/type/MaterialType_Ems.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/material_type_v1" by keyFields "materialType"
      | materialType | materialTypeName |
      | 0002         | Ontario PST      |
      | 0003         | PST              |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1                | key2        | key3            | key4 | key5 | errorValue |
      |DP              |EDMMaterialType|T1       |EMS           |              |[CNS MFG - Italy]    |&              |Budget F.G.    |        |      |z_source_system value is not [EMS]            |
      |DP              |EDMMaterialType|T1       |EMS           |              |[MDD Mentor]         |0001           |HST            |       |      |z_source_system value is not [EMS]            |

    And I compare the number of records between "/ems/ems_f_mdm_material_types" and "/edm/material_type_v1,/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/material_type_v1"
    And I will remove all data with region "/plan/edm_failed_data"