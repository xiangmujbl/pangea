@pangea_test@AEAZ-1482
Feature: PlanCnsSplProcTyp-Curation

  Scenario: Full Load curation
    #  1. test get sourceSystem from source_system_v1 and overwrite the value in this field (rule T1)
    #  2. test get LTEXT  from T460T and and overwrite the value in this field (rule J1)

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one   | BtB       |
      | project_two   | BtB      |
      | [EMS]         | BtC       |
      | [EMS]         | BtB      |
    And I wait "/edm/source_system_v1" Async Queue complete

    And I import "/project_one/t460a" by keyFields "sobsl"
    |sobsl|
    | E10 |
    | E20 |
    | E30 |
    And I wait "/project_one/t460a" Async Queue complete

    And I import "/project_one/t460t" by keyFields "ltext"
      | ltext        | sobsl | spras |
      | Consignment1 | E10   | E     |
      | Consignment2 | E20   | P     |
      | Consignment3 | E30   | S     |
      | Consignment4 | E40   | D     |
    And I wait "/project_one/t460t" Async Queue complete

    When I submit task with xml file "xml/edm/PlanCnsSplProcTyp.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/plan/cns_spl_proc_typ" by keyFields "sourceSystem,localSplProcType,localSplProcTypeDesc,splProcType,splProcTypeDesc"
    |sourceSystem|localSplProcType|localSplProcTypeDesc|splProcType|splProcTypeDesc|
    |BtB         |E10             |Consignment1        |           |               |
    |BtB         |E20             |                    |           |               |
    |BtB         |E20             |                    |           |               |

    And I compare the number of records between "/project_one/t460a" and "/plan/cns_spl_proc_typ"

