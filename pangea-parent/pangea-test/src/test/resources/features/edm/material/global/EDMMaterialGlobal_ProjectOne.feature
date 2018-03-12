@pangea_test @AEAZ-509
Feature: MaterialGlobal-Data Model & Curation

  Scenario: Full Load curation
    #  1.test all field values. Enter valid value for fields in source and verify the same value exist in target
    #  2.test localRefDescription picks up english description (SPRAS=EN) (rule J1)
    #  3.test localRefDescription picks up portugese description (SPRAS=PT) if english description missing(rule J1)
    #  4.test localRefDescription picks up spanish description (SPRAS=ES) if EN and PT description missing(rule J1)
    #  5.test join to localGoldenMaterialLinkage is succesful (rule J2 is satisified)
    #  6.test join to localGoldenMaterialLinkage is unsuccesful i.e. no record found (rule J2 is not satisified) in which case field #7-20 should be blank (rule E1)

    Given I import "/project_one/mara" by keyFields "matnr"
      | matnr | mtart | meins | matkl | lvorm | mstae | spart |
      | 97568 | FERT  | KI    | 01    |       | 08    | 10    |
      | 97570 | FERT  | KI    | 01    |       | 08    | 10    |
      | 97572 | FERT  | KI    | 01    |       | 08    | 10    |
    And I wait "/project_one/mara" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   |
      | project_one       | Project One           | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]             | EMS                   | EMS          | EMS Ent            |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/project_one/makt" by keyFields "matnr,spras"
      | matnr | spras | maktx                                |
      | 97568 | E     | JS COTTON BALLS 50 GRX20 T50P35      |
      | 97570 | P     | BOLAS DE ALGODAO J* 20X50 LV50 PG35. |
      | 97572 | E     | REACH TOOTHBRUSH COMPACT SOFT T2P1   |
    And I wait "/project_one/makt" Async Queue complete

    And I import "/ngems/golden_material" by keyFields "materialNumber"
      | materialNumber | materialDescription                      | franchise | materialType | baseUom | parentCode    | brand  | form   | materialStatus | subBrand | manufTechnology | category | productFamily | globalBusinessUnit |
      | 7891010014803  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FCH001    | FERT         | EA      | 7891010931582 | BRD001 | 116151 | Active         | 1V       | Wipes           | 6        | Not Assigned  | GFO001             |
      | 7891010931582  | JS BODY DE HI 24H MA PR 12xL400 P320ML N | FCH001    | FERT         | EA      | 7891010931642 | BRD001 | 116151 | Active         | 500003   | Toothbrushes    | 0006     | Not Assigned  | GFO001             |
      | N6016B         | NTG Norwegian Hand Cream FFree 50mL      | 010124    | FERT         | EA      | C-46016       |        |        | Active         | 500003   | Band Aid        | 0006     | 030238        | C00018             |
    And I wait "/ngems/golden_material" Async Queue complete

    And I import "/ngems/material_linkage" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem            | localMaterialNumber | materialNumber |
      | CONS_LATAM              | 97568               | 7891010014803  |
      | CONS_LATAM              | 97570               | 7891010931582  |
      | CONS_LATAM              | 97572               | 7891010931532  |
      | [MD DePuy Spine JDE XE] | 97570               | 7891010931582  |
    And I wait "/ngems/material_linkage" Async Queue complete

    When I submit task with xml file "xml/edm/material/global/EDMMaterialGlobal_ProjectOne.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
      | sourceSystem | localMaterialNumber | localRefDescription                  | localMaterialType | localBaseUnit | materialNumber | refDescription                           | materialType | baseUom | localDpParentCod | parentCode    | globalDpParentCode | form   | category | subBrand | brand  | franchise | globalBusinessUnit | productFamily | localManufacturingTechnology | manufacturingTechnology | localMaterialGroup | materialGroup | flagForDeletion | materialStatus | division |
      | CONS_LATAM   | 97568               | JS COTTON BALLS 50 GRX20 T50P35      | FERT              | KI            | 7891010014803  | J'S SOFT DEO HIDR MAC PROL 12XL400P320ML | FERT         | EA      |                  | 7891010931582 |                    | 116151 | 6        | 1V       | BRD001 | FCH001    | GFO001             | Not Assigned  |                              | Wipes                   | 01                 |               |                 | 08             | 10       |
      | CONS_LATAM   | 97570               | BOLAS DE ALGODAO J* 20X50 LV50 PG35. | FERT              | KI            | 7891010931582  | JS BODY DE HI 24H MA PR 12xL400 P320ML N | FERT         | EA      |                  | 7891010931642 |                    | 116151 | 0006     | 500003   | BRD001 | FCH001    | GFO001             | Not Assigned  |                              | Toothbrushes            | 01                 |               |                 | 08             | 10       |
      | CONS_LATAM   | 97572               | REACH TOOTHBRUSH COMPACT SOFT T2P1   | FERT              | KI            |                |                                          |              |         |                  |               |                    |        |          |          |        |           |                    |               |                              |                         | 01                 |               |                 | 08             | 10       |

    Then I check region data "/pangea/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/mara" and "/edm/material_global_v1,/pangea/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "/edm/material_global_v1"
    And I will remove all data with region "/pangea/edm_failed_data"

