@pangea_test
Feature: EDMMaterialType XREF - Curation

  Scenario: Full Load curation
    #  1. test get attributes from ems_f_mdm_material_types where zSourceSystem = [EMS] (rule T1)

#    #  Given: The data is available in the source region(s) as defined in the mapping document ""DOMD_EDM_materialType_LATAM""
#    Given I import "/ems/ems_f_mdm_material_types" by keyFields "mdmCode,zSourceSystem"
#      |mdmCode|zSourceSystem       |mdmName    |
#      |&      |[CNS MFG - Italy]   |Budget F.G.|
#      |0002   |[EMS]               |Ontario PST|
#
#    #  When: The full load curation job is triggered.
#    When I submit task with xml file "xml/material_global.xml" and execute file "jar/pangea-view.jar"
#
#    #  Then:
#    #  1. EDG has all the curated Material Type records generated in the target view with all the fields as defined in the mapping document ""DOMD_EDM_materialType_LATAM""
#    #  2. For each curated record, all the field mappings and transformation rules are applied as defined in the mapping document.
#    Then I check region data "/edm/material_type" by keyFields "materialType"
#      |materialType|materialTypeName|
#      |0002        |Ontario PST     |

    #  4. EDG should capture the rejected records that do not match the mapping rules.
    #  5. The sum of curated records and rejected records should match the number of records from old EMS Material Type table.
    And I compare the number of records between "/ems/ems_f_mdm_material_types" and "/edm/material_type,/edm/material_type_failed"
