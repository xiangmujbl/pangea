@pangea_test
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

    When I submit task with xml file "xml/edm/material/type/material_type.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/pangea/edm/material_type_v1" by keyFields "materialType"
      | materialType | materialTypeName |
      | 0002         | Ontario PST      |
      | 0003         | PST              |

    And I delete the test data