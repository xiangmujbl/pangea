@pangea_test @AEAZ-4245
Feature: EDMReservHdr AEAZ-4245

  Scenario: Full Load curation

    Given I import "/project_one/rkpf" by keyFields "rsnum"
      | rsnum       | kzver | xcale | rsdat      |
      | 10000000001 | 1     | 1     | 2018/05/17 |
      | 10000000002 | 2     | 2     | 2018/05/18 |
    And I wait "/project_one/rkpf" Async Queue complete

    Given I import "/edm/source_system_v1" by keyFields "localSourceSystem"
      | localSourceSystem | sourceSystem |
      | project_one       | CONS_LATAM   |
      | EMS               | EMS          |

    And I wait "/edm/source_system_v1" Async Queue complete

    When I submit task with xml file "xml/edm/EDMReservHdr.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/reserv_hdr" by keyFields "sourceSysCd,rsrvtnNum"
      | sourceSysCd | rsrvtnNum   | rsrvtnOrigCd | factCalndrInd | rsrvtnBsDt          |
      | CONS_LATAM  | 10000000001 | 1            | 1             | 2018-05-17T00:00:00 |
      | CONS_LATAM  | 10000000002 | 2            | 2             | 2018-05-18T00:00:00 |

  Scenario: delete all test data

    And I will remove all data with region "/edm/source_system_v1"

    And I will remove all data with region "/edm/reserv_hdr"

    And I will remove all data with region "/project_one/rkpf"


