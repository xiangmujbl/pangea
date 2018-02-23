@pangea_test
Feature: EDMMaterialAUOM-Curation

  Scenario: Full Load curation
    #  1. test get sourceSystem from source_system_v1 (rule T1)
    #  2. test join to materialGlobal to get materialNumber (rule J1)

    Given I import "/project_one/marm" by keyFields "matnr,meinh"
      | matnr | meinh | umrez | umren |
      | 97568 | BAG   | 1     | 20    |
      | 97570 | EA    | 1000  | 1500  |

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]             | EMS                   | EMS          | EMS Ent            |

    And I import "/edm/material_global" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | materialNumber |
      | CONS_LATAM   | 97568               | 7891010014803  |
      | EMS          | 97570               | 7891010931582  |

    When I submit task with xml file "xml/material_auom.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/material_auom_v1" by keyFields "sourceSystem,localMaterialNumber,localAuom"
      | sourceSystem | localMaterialNumber | localAuom | materialNumber | localNumerator | localDenominator | Auom | numerator | denominator |
      | CONS_LATAM   | 97568               | BAG       | 7891010014803  | 1              | 20               |      |           |             |

    And I compare the number of records between "/project_one/marm" and "/edm/material_auom_v1,/edm/material_auom_v1_failed"

