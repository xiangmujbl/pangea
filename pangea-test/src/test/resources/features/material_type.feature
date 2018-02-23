@pangea_test
Feature: EDMMaterialType XREF - Curation

  Scenario: Full Load curation
    #  1. test get attributes from ems_f_mdm_material_types where zSourceSystem = [EMS] (rule T1)

    Given I import "/ems/ems_f_mdm_material_types" by keyFields "mdmCode,zSourceSystem"
      | mdmCode | zSourceSystem     | mdmName     |
      | &       | [CNS MFG - Italy] | Budget F.G. |
      | 0002    | [EMS]             | Ontario PST |

    When I submit task with xml file "xml/material_global.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/material_type_v1" by keyFields "materialType"
      | materialType | materialTypeName |
      | 0002         | Ontario PST      |

    And I compare the number of records between "/ems/ems_f_mdm_material_types" and "/edm/material_type_v1,/edm/material_type_v1_failed"
