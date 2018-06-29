@pangea_test @AEAZ-6188
Feature: EDMSalesHistoryV1 AEAZ-6188

  Scenario: Full Load curation
    #  1. EDG has all the curated Purchasing Info record records generated in the target view with all the fields as defined in the mapping document "DOMD_sales_historynew"
    #  2. For each curated record, all the field mappings and transformation rules are applied as defined in the mapping document.
    #  3. EDG should capture the rejected records that do not match the mapping rules.
    #  4. The sum of curated records and rejected records should match the number of records from ECC tables specified in DOMD.

    And I will remove the test file on sink application "EDMSalesHistoryV1.tsv"

    Given I import "/project_one/vbfa" by keyFields "vbelv,posnv,vbeln,posnn,vbtypN"
      | mandt | vbelv     | posnv   | vbeln       | posnn | vbtypN | rfmng    | meins | rfmngFlt | vrkme | vbtypV | erdat    |
      | 120   | 3515417   | 110     | 4914665711  | 11    | R      | 1.000    | EA    | 1.00     | EA    | C      | 20120206 |
      | 120   | 3515419   | 10      | 4914665712  | 1     | R      | 1.000    | EA    | 1.00     | EA    | C      | 20120206 |
      | 120   | 3515419   | 20      | 4914665712  | 2     | R      | 1.000    | EA    | 1.00     | EA    | C      | 20120206 |
      | 120   | 3515419   | 30      | 4914665712  | 3     | R      | 1.000    | EA    | 1.00     | EA    | C      | 20120206 |
      | 120   | 3515419   | 40      | 4914665712  | 4     | R      | 10.000   | EA    | 10.00    | EA    | C      | 20120206 |
      | 120   | 3515419   | 50      | 4914665712  | 5     | R      | 3.000    | EA    | 3.00     | EA    | C      | 20120206 |
      | 120   | 85771969  | 900001  | 4914665711  | 1     | R      | 3.000    | EA    | 3.00     | EA    | J      | 20120206 |
      | 120   | 85771969  | 900002  | 4914665711  | 2     | R      | 3.000    | EA    | 3.00     | EA    | J      | 20120206 |
      | 120   | 85771969  | 900003  | 4914665711  | 3     | R      | 2.000    | EA    | 2.00     | EA    | J      | 20120206 |
      | 120   | 85771969  | 900004  | 4914665711  | 4     | R      | 5.000    | EA    | 5.00     | EA    | J      | 20120206 |
      | 120   | 85771969  | 900005  | 4914665711  | 5     | R      | 1.000    | EA    | 1.00     | EA    | J      | 20120206 |
      | 120   | 85771969  | 900006  | 4914665711  | 6     | R      | 5.000    | EA    | 5.00     | EA    | J      | 20120206 |
      | 120   | 85771969  | 900007  | 4914665711  | 7     | R      | 3.000    | EA    | 3.00     | EA    | J      | 20120206 |
      | 120   | 85771969  | 900008  | 4914665711  | 8     | R      | 1.000    | EA    | 1.00     | EA    | J      | 20120206 |
      | 120   | 85771969  | 900009  | 4914665711  | 9     | R      | 3.000    | EA    | 3.00     | EA    | J      | 20120206 |
      | 120   | 85771969  | 900010  | 4914665711  | 10    | R      | 2.000    | EA    | 2.00     | EA    | J      | 20120206 |
      | 120   | 85771969  | 900011  | 4914665711  | 11    | R      | 1.000    | EA    | 1.00     | EA    | J      | 20120206 |
      | 120   | 85771970  | 900001  | 4914665712  | 1     | R      | 20.000   | EA    | 240.00   | CA    | J      | 20120206 |
      | 120   | 85771970  | 900002  | 4914665712  | 2     | R      | 30.000   | EA    | 360.00   | CA    | J      | 20120206 |
      | 120   | 85771970  | 900003  | 4914665712  | 3     | R      | 40.000   | EA    | 480.00   | CA    | J      | 20120206 |

    And I wait "/project_one/vbfa" Async Queue complete

    And I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem        | localSourceSystemName    | sourceSystem | sourceSystemName   |
      | [MDD FASE]               | [MDD FASE]               | CONS_LATAM   | Consumer Latam Ent |
      | [EMS]                    | EMS                      | EMS          | EMS Ent            |
      | project_one              | [AEAZ-6188 by KG]        | CONS_LATAM   | Consumer Latam Ent |

    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMSalesHistoryV1.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "EDMSalesHistoryV1.tsv"

    Then I check file data for filename "EDMSalesHistoryV1.tsv" by keyFields "sourceSystem,localPrecDocNo,localSPrecDocLnNo,localSubsDocNo,localSubsDocLnNo,localSubDocCatg"
      | sourceSystem | localPrecDocNo | localSPrecDocLnNo | localSubsDocNo | localSubsDocLnNo | localSubDocCatg | localBaseQuantity | localBaseUom | localSalesQuantity | localSalesUom | localPrecItemCatg | localCrtDt |
      | CONS_LATAM   | 3515417        | 110               | 4914665711     | 11               | R               | 1.000             | EA           | 1.00               | EA            | C                 | 20120206   |
      | CONS_LATAM   | 3515419        | 10                | 4914665712     | 1                | R               | 1.000             | EA           | 1.00               | EA            | C                 | 20120206   |
      | CONS_LATAM   | 3515419        | 20                | 4914665712     | 2                | R               | 1.000             | EA           | 1.00               | EA            | C                 | 20120206   |
      | CONS_LATAM   | 3515419        | 30                | 4914665712     | 3                | R               | 1.000             | EA           | 1.00               | EA            | C                 | 20120206   |
      | CONS_LATAM   | 3515419        | 40                | 4914665712     | 4                | R               | 10.000            | EA           | 10.00              | EA            | C                 | 20120206   |
      | CONS_LATAM   | 3515419        | 50                | 4914665712     | 5                | R               | 3.000             | EA           | 3.00               | EA            | C                 | 20120206   |
      | CONS_LATAM   | 85771969       | 900001            | 4914665711     | 1                | R               | 3.000             | EA           | 3.00               | EA            | J                 | 20120206   |
      | CONS_LATAM   | 85771969       | 900002            | 4914665711     | 2                | R               | 3.000             | EA           | 3.00               | EA            | J                 | 20120206   |
      | CONS_LATAM   | 85771969       | 900003            | 4914665711     | 3                | R               | 2.000             | EA           | 2.00               | EA            | J                 | 20120206   |
      | CONS_LATAM   | 85771969       | 900004            | 4914665711     | 4                | R               | 5.000             | EA           | 5.00               | EA            | J                 | 20120206   |
      | CONS_LATAM   | 85771969       | 900005            | 4914665711     | 5                | R               | 1.000             | EA           | 1.00               | EA            | J                 | 20120206   |
      | CONS_LATAM   | 85771969       | 900006            | 4914665711     | 6                | R               | 5.000             | EA           | 5.00               | EA            | J                 | 20120206   |
      | CONS_LATAM   | 85771969       | 900007            | 4914665711     | 7                | R               | 3.000             | EA           | 3.00               | EA            | J                 | 20120206   |
      | CONS_LATAM   | 85771969       | 900008            | 4914665711     | 8                | R               | 1.000             | EA           | 1.00               | EA            | J                 | 20120206   |
      | CONS_LATAM   | 85771969       | 900009            | 4914665711     | 9                | R               | 3.000             | EA           | 3.00               | EA            | J                 | 20120206   |
      | CONS_LATAM   | 85771969       | 900010            | 4914665711     | 10               | R               | 2.000             | EA           | 2.00               | EA            | J                 | 20120206   |
      | CONS_LATAM   | 85771969       | 900011            | 4914665711     | 11               | R               | 1.000             | EA           | 1.00               | EA            | J                 | 20120206   |
      | CONS_LATAM   | 85771970       | 900001            | 4914665712     | 1                | R               | 20.000            | EA           | 240.00             | CA            | J                 | 20120206   |
      | CONS_LATAM   | 85771970       | 900002            | 4914665712     | 2                | R               | 30.000            | EA           | 360.00             | CA            | J                 | 20120206   |
      | CONS_LATAM   | 85771970       | 900003            | 4914665712     | 3                | R               | 40.000            | EA           | 480.00             | CA            | J                 | 20120206   |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1  | key2 | key3 | key4 | key5 | errorValue |

#    Then I check region data "/plan/sales_history_v1_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/sales_history_v1"

    And I will remove all data with region "/plan/edm_failed_data"


