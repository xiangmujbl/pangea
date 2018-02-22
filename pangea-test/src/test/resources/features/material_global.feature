@pangea_test
Feature: MaterialGlobal-Data Model & Curation

  Scenario: Full Load curation
    #  1.test all field values. Enter valid value for fields in source and verify the same value exist in target
    #  2.test localRefDescription picks up english description (SPRAS=EN) (rule J1)
    #  3.test localRefDescription picks up portugese description (SPRAS=PT) if english description missing(rule J1)
    #  4.test localRefDescription picks up spanish description (SPRAS=ES) if EN and PT description missing(rule J1)
    #  5.test join to localGoldenMaterialLinkage is succesful (rule J2 is satisified)
    #  6.test join to localGoldenMaterialLinkage is unsuccesful i.e. no record found (rule J2 is not satisified) in which case field #7-20 should be blank (rule E1)

    #  Given: The data is available in the source region(s) as defined in the mapping document "DOMD_EDM_materialGlobal_LATAM"
    Given I import "/project_one/mara" by keyFields "matnr"
      |matnr|mtart|meins|
      |97568|FERT |KI   |
      |97570|FERT |KI   |
      |97572|FERT |KI   |

    And I import "/ngems/source_system" by keyFields "localSourceSystem"
      |localSourceSystem|localSourceSystemName|sourceSystem|sourceSystemName  |
      |project_one      |Project One          |CONS_LATAM  |Consumer Latam Ent|
      |[EMS]            |EMS                  |EMS         |EMS Ent           |

    And I import "/project_one/makt" by keyFields "matnr,spras"
      |matnr|spras|maktx                                 |
      |97568|EN   |JS COTTON BALLS 50 GRX20 T50P35       |
      |97570|PT   |BOLAS DE ALGODAO J* 20X50 LV50 PG35.  |
      |97572|ES   |REACH TOOTHBRUSH COMPACT SOFT T2P1    |

    And I import "/ngems/golden_material" by keyFields "sourceSystem,localMaterialNumber"
      |materialNumber|materialDescription                      |franchise|materialType|baseUom|parentCode    |brand   |form  |materialStatus|subBrand|manufTechnology|category|productFamily|globalBusinessUnit|
      |7891010014803 |J'S SOFT DEO HIDR MAC PROL 12XL400P320ML |FCH001   |FERT        |EA     |7891010931582 |BRD001  |116151|Active        |1V      |Wipes          |6       |Not Assigned |GFO001            |
      |7891010931582 |JS BODY DE HI 24H MA PR 12xL400 P320ML N |FCH001   |FERT        |EA     |7891010931642 |BRD001  |116151|Active        |500003  |Toothbrushes   |0006    |Not Assigned |GFO001            |
      |N6016B        |NTG Norwegian Hand Cream FFree 50mL      |010124   |FERT        |EA     |C-46016       |        |      |Active        |500003  |Band Aid       |0006    |030238       |C00018            |

    And I import "/ngems/local_golden_material_linkage" by keyFields "sourceSystem,localMaterialNumber"
      |sourceSystem           |localMaterialNumber|materialNumber|
      |[Consumer LATAM]       |97568              |7891010014803 |
      |[Consumer LATAM]       |97570              |7891010931582 |
      |[Consumer LATAM]       |97572              |7891010931532 |
      |[MD DePuy Spine JDE XE]|97570              |7891010931582 |

    #  When: The full load curation job is triggered.
    When I submit task with xml file "xml/material_global.xml" and execute file "jar/pangea-view.jar"

    #  Then:
    #  1. EDG has all the curated material records generated in the target view with all the fields as defined in the mapping document "DOMD_EDM_materialGlobal_LATAM"
    #  2. For each curated record, all the field mappings and transformation rules are applied as defined in the mapping document.
    Then I check region data "/edm/material_global" by keyFields "sourceSystem,localMaterialNumber"
      |sourceSystem|localMaterialNumber|localRefDescription                 |localMaterialType|localBaseUnit|materialNumber   |refDescription                          |materialType|baseUom|localDpParentCod|parentCode   |globalDpParentCode|form  |category|subBrand|brand |franchise|globalBusinessUnit|productFamily|localManufacturingTechnology|manufacturingTechnology|
      |CONS_LATAM  |97568              |JS COTTON BALLS 50 GRX20 T50P35     |FERT             |KI           |7891010014803    |J'S SOFT DEO HIDR MAC PROL 12XL400P320ML|FERT        |EA     |                |7891010931582|                  |116151|6       |1V      |BRD001|FCH001   |GFO001            |Not Assigned |                            |Wipes                  |
      |CONS_LATAM  |97570              |BOLAS DE ALGODAO J* 20X50 LV50 PG35.|FERT             |KI           |7891010931582    |JS BODY DE HI 24H MA PR 12xL400 P320ML N|FERT        |EA     |                |7891010931642|                  |116151|0006    |500003  |BRD001|FCH001   |GFO001            |Not Assigned |                            |Toothbrushes           |
      |CONS_LATAM  |97572              |REACH TOOTHBRUSH COMPACT SOFT T2P1  |FERT             |KI           |                 |                                        |            |       |                |             |                  |      |        |        |      |         |                  |             |                            |                       |

    #  4. EDG should capture the rejected records that do not match the mapping rules.
    #  5. The sum of curated records and rejected records should match the number of records in the  MARA table.
    And I compare the number of records between "/project_one/mara" and "/edm/material_global,/edm/material_global_failed"


  Scenario: Delta Load curation

    #  1.Test one of the relevant fields(mapped to the target) in MAKT is changed.
    #  2.Test one of the non relevant fields ( not mapped to any field in target ) in MAKT is changed.
    #  3.Test one of the relevant fields(mapped to the target) in MARA is changed.
    #  4.Test one of the non relevant fields ( not mapped to any field in target ) in MARA is changed.
    #  5.Test one of the relevant fields(mapped to the target) in goldenMaterial region is changed.
    #  6.Test one of the non relevant fields ( not mapped to any field in target ) in goldenMaterial region is changed.
    #  7.Test one of the relevant fields(mapped to the target) in sourceSystem region is changed.
    #  8.Test one of the non relevant fields ( not mapped to any field in target ) in sourceSystem region is changed.

    # Given: The data is available in the source region(s) as defined in the mapping document "DOMD_EDM_materialGlobal_LATAM"
    Given I import "/project_one/mara" by keyFields "matnr"
      |matnr|mtart   |meins|
      |97572|FERT_UPT|KI   |
      |97574|FERT    |KI   |

    And I import "/ngems/source_system" by keyFields "matnr,spras"
      |localSourceSystem|localSourceSystemName|sourceSystem   |sourceSystemName  |
      |project_one      |Project One          |CONS_LATAM_UPT |Consumer Latam Ent|
      |[EMS]            |EMS                  |EMS            |EMS Ent           |

    And I import "/project_one/makt" by keyFields "matnr,spras"
      |matnr|spras|maktx                                 |
      |97572|PT   |REACH TOOTHBRUSH COMPACT SOFT T2P1_UPT|
      |97574|ES   |REACH TOOTHBRUSH COMPACT SOFT T2P1    |

    And I import "/ngems/golden_material" by keyFields "sourceSystem,localMaterialNumber"
      |materialNumber|materialDescription                          |franchise|materialType|baseUom|parentCode    |brand   |form  |materialStatus|subBrand|manufTechnology|category|productFamily|globalBusinessUnit|
      |7891010931582 |JS BODY DE HI 24H MA PR 12xL400 P320ML N_UPT |FCH001   |FERT        |EA     |7891010931642 |BRD001  |116151|Active        |500003  |Toothbrushes   |0006    |Not Assigned |GFO001            |
      |N6016B        |NTG Norwegian Hand Cream FFree 50mL          |010124   |FERT        |EA     |C-46016       |        |      |Active        |500003  |Band Aid       |0006    |030238       |C00018            |

    And I import "/ngems/local_golden_material_linkage" by keyFields "sourceSystem,localMaterialNumber"
      |sourceSystem           |localMaterialNumber|materialNumber|
      |[Consumer LATAM]       |97572              |7891010931582 |
      |[Consumer LATAM]       |97574              |N6016B        |

    # When: The Delta load curation job is triggered.
    When I submit task with xml file "xml/material_global.xml" and execute file "jar/pangea-view.jar"

    #  Then:
    #  1. EDG has all the curated material records generated in the target view with all the fields as defined in the mapping document "DOMD_EDM_materialGlobal_LATAM"
    #  2. For each curated record, all the field mappings and transformation rules are applied as defined in the mapping document.
    Then I check region data "/edm/material_global" by keyFields "sourceSystem,localMaterialNumber"
      |sourceSystem    |localMaterialNumber|localRefDescription                    |localMaterialType|localBaseUnit|materialNumber   |refDescription                               |materialType|baseUom|localDpParentCod|parentCode   |globalDpParentCode|form  |category|subBrand|brand |franchise|globalBusinessUnit|productFamily|localManufacturingTechnology|manufacturingTechnology|
      |CONS_LATAM_UPT  |97568              |JS COTTON BALLS 50 GRX20 T50P35        |FERT             |KI           |7891010014803    |J'S SOFT DEO HIDR MAC PROL 12XL400P320ML     |FERT        |EA     |                |7891010931582|                  |116151|6       |1V      |BRD001|FCH001   |GFO001            |Not Assigned |                            |Wipes                  |
      |CONS_LATAM_UPT  |97570              |BOLAS DE ALGODAO J* 20X50 LV50 PG35.   |FERT             |KI           |7891010931582    |JS BODY DE HI 24H MA PR 12xL400 P320ML N_UPT |FERT        |EA     |                |7891010931642|                  |116151|0006    |500003  |BRD001|FCH001   |GFO001            |Not Assigned |                            |Toothbrushes           |
      |CONS_LATAM_UPT  |97572              |REACH TOOTHBRUSH COMPACT SOFT T2P1_UPT |FERT_UPT         |KI           |7891010931582    |JS BODY DE HI 24H MA PR 12xL400 P320ML N_UPT |FERT        |EA     |                |7891010931582|                  |116151|0006    |500003  |BRD001|FCH001   |GFO001            |Not Assigned |                            |Toothbrushes           |
      |CONS_LATAM_UPT  |97574              |REACH TOOTHBRUSH COMPACT SOFT T2P1     |FERT             |KI           |N6016B           |NTG Norwegian Hand Cream FFree 50mL          |FERT        |EA     |                |C-46016      |                  |      |0006    |500003  |      |010124   |C00018            |030238       |                            |Band Aid               |

    #  4. EDG should capture the rejected records that do not match the mapping rules.
    #  5. The sum of curated records and rejected records should match the number of records in the  MARA table.
    And I compare the number of records between "/project_one/mara" and "/edm/material_global,/edm/material_global_failed"