@pangea_test @AEAZ-1482
Feature: PlanCnsSplProcTyp AEAZ-1482

  Scenario: Full Load curation
    #  1. test get sourceSystem from source_system_v1 and overwrite the value in this field (rule T1)
    #  2. test get LTEXT  from T460T and and overwrite the value in this field (rule J1)

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | BtB          |
      | project_two       | BtB          |
      | [EMS]             | BtC          |
      | [EMS]             | BtB          |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/project_one/t460a" by keyFields "sobsl"
      | sobsl |
      | E10   |
      | E20   |
      | E30   |
    And I wait "/project_one/t460a" Async Queue complete

    And I import "/project_one/t460t" by keyFields "ltext"
      | ltext        | sobsl | spras |
      | Consignment1 | E10   | E     |
      | Consignment2 | E20   | P     |
      | Consignment3 | E30   | S     |
      | Consignment4 | E40   | D     |
    And I wait "/project_one/t460t" Async Queue complete

    When I submit task with xml file "xml/plan/PlanCnsSplProcTyp.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_spl_proc_typ" by keyFields "sourceSystem,localSplProcType,localSplProcTypeDesc,splProcType,splProcTypeDesc"
      | sourceSystem | localSplProcType | localSplProcTypeDesc | splProcType | splProcTypeDesc |
      | BtB          | E10              | Consignment1         |             |                 |
      | BtB          | E20              |                      |             |                 |
      | BtB          | E20              |                      |             |                 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/t460a" and "/plan/cns_spl_proc_typ,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/plan/cns_spl_proc_typ"

    And I will remove all data with region "/plan/edm_failed_data"