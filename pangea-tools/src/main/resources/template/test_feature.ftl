@pangea_test @${jira}
Feature: ${fullName} ${jira}

  Scenario: Full Load curation

<#list entities as entity>
    Given I import "${entity.path}" by keyFields ""
      |<#list entity.fields as field> ${field} |</#list>
    And I wait "${entity.path}" Async Queue complete
</#list>

    When I submit task with xml file "xml/${system}/${fullName}.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "${view.path}" by keyFields ""
      |<#list view.fields as field> ${field} |</#list>

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "${main.path}" and "${view.path},/plan/edm_failed_data"

    And I delete the test data

    And I will remove all data with region "${view.path}"

    And I will remove all data with region "${main.path}"

