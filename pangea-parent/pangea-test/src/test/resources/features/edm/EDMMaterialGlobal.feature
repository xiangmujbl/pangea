@pangea_test @AEAZ-2828 @AEAZ-4065
Feature: EDMMaterialGlobal AEAZ-2828 AEAZ-4065

  Scenario: Full Load curation
  # 1. Get maktx from makt where makt-matnr = mara-matnr and makt-spras = E (J1)
  # 2. Get maktx from makt where makt-matnr = mara-matnr and makt-spras = P (J1)
  # 3. Get maktx from makt where makt-matnr = mara-matnr and makt-spras = S (J1)
  # 4. Get attributes from golden_material (J2)
  # 5. If no value returned, leave blank (J2)
  # 6. Get global_material_v2-parentCode where golden_material-globalDpParentCode = blank AND global_material_v2-parentCode is not blank (J3)
  # 7. Leave blank where golden_material-globalDpParentCode = blank AND global_material_v2-parentCode is blank (J3)

    Given I import "/project_one/mara" by keyFields "matnr,mandt"
      | matnr          | mtart | meins | matkl    | lvorm | mstae | spart | xchpf | mhdrz | mhdhb | mandt |
      | 000.670.000-US | FERT  | EA    | 42290000 |       |       | SO    |       | 0     | 0     | 100   |
      | 00038100       | FERT  | EA    | 30151900 |       |       | ST    | X     | 0     | 0     | 100   |
      | 00046500       | FERT  | EA    | 30151900 |       |       | WH    | X     | 0     | 0     | 100   |
    And I wait "/project_one/mara" Async Queue complete

    Given I import "/project_one/makt" by keyFields "mandt,matnr,spras"
      | mandt | matnr          | maktx                              | spras |
      | 100   | 000.670.000-US | DRILL BIT USAGE                    | E     |
      | 100   | 00038100       | HANDLE Ø22*100                     | P     |
      | 100   | 00046500       | CANEVASIT HANDLE Ø22 L115 D0008401 | S     |
    And I wait "/project_one/makt" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | ems               | EMS                   | EMS          | ems                |
    And I wait "/edm/source_system_v1" Async Queue complete

    Given I import "/ngems/material_linkage" by keyFields "localMaterialNumber,sourceSystem"
      | localMaterialNumber | sourceSystem | materialNumber |
      | 000.670.000-US      | CONS_LATAM   | 4002           |
      | 00038100            | CONS_LATAM   | 7992           |
      | 00046500            | CONS_LATAM   | 36002          |
    And I wait "/ngems/material_linkage" Async Queue complete

    Given I import "/ngems/golden_material" by keyFields "materialNumber"
      | materialNumber | materialDescription                 | materialType | baseUom | parentCode        | globalDpParentCode | form   | category | subBrand | brand  | franchise | globalBusinessUnit | productFamily | manufTechnology | primaryPlanningCode |
      | 4002           | NAPKIN SL ADPLUS NTDAY SS WING 24X8 | FERT         | EA      | 78910106945930024 |                    | 100722 | 0150     | 5G       | BRD004 | FCH005    | GFO002             |               |                 | 100722              |
      | 7992           | NAPKIN SL ADPLUS UF SV ABA 48x8     | FERT         | EA      |                   |                    | 112365 | 0203     | 1P       | BRD002 | FCH001    | GFO001             |               |                 | 112365              |
      | 36002          | LISTERINE COOL CITRUS 250ML NAT     | FERT         | EA      | 78910109199860006 | 78910109199860007  | 100881 | 0256     | 3D       | BRD006 | FCH002    | GFO002             |               |                 | 100881              |
    And I wait "/ngems/golden_material" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMaterialGlobal.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localRefDescription                | localMaterialType | localBaseUom | materialNumber | refDescription                      | materialType | baseUom | localDpParentCode | parentCode        | globalDpParentCode | form   | category | subBrand | brand  | franchise | globalBusinessUnit | productFamily | localManufacturingTechnology | manufacturingTechnology | localMaterialGroup | materialGroup | flagForDeletion | materialStatus | division | batchManageIndicator | minRemShelfLife | totalShelfLife | primaryPlanningCode |
      | CONS_LATAM   | 000.670.000-US      | DRILL BIT USAGE                    | FERT              | EA           | 4002           | NAPKIN SL ADPLUS NTDAY SS WING 24X8 | FERT         | EA      |                   | 78910106945930024 |                    | 100722 | 0150     | 5G       | BRD004 | FCH005    | GFO002             |               |                              |                         | 42290000           |               |                 |                | SO       |                      | 0               | 0              | 100722              |
      | CONS_LATAM   | 00038100            | HANDLE Ø22*100                     | FERT              | EA           | 7992           | NAPKIN SL ADPLUS UF SV ABA 48x8     | FERT         | EA      |                   |                   |                    | 112365 | 0203     | 1P       | BRD002 | FCH001    | GFO001             |               |                              |                         | 30151900           |               |                 |                | ST       | X                    | 0               | 0              | 112365              |
      | CONS_LATAM   | 00046500            | CANEVASIT HANDLE Ø22 L115 D0008401 | FERT              | EA           | 36002          | LISTERINE COOL CITRUS 250ML NAT     | FERT         | EA      |                   | 78910109199860006 |                    | 100881 | 0256     | 3D       | BRD006 | FCH002    | GFO002             |               |                              |                         | 30151900           |               |                 |                | WH       | X                    | 0               | 0              | 100881              |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/mara" and "/edm/material_global_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/material_global_v1"

    And I will remove all data with region "/plan/edm_failed_data"

