@pangea_test @AEAZ-2376
Feature:  OMPGdmLocationDetail-Curation

  Scenario: Full Load curation AEAZ-2376

    Given I import "/plan/cns_plant_attr" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlantName | localPlantType | plant | plantType | localPlanningRelevant | planLocTypeId | planLocTypeDesc | locationAttribute1Desc | locationAttribute1Value | locationAttribute2Desc | locationAttribute2Value | locationAttribute3Desc | locationAttribute3Value | locationAttribute4Desc | locationAttribute4Value |
      | CONS_LATAM   | BR12       | A              | 00             |       | 00        | Y                     | 00000000001   | DESC01          | ATTRIB1                | VALUE1                  | ATTRIB2                | VALUE2                  | ATTRIB3                | VALUE3                  | ATTRIB4                | VALUE4                  |
      | CONS_LATAM   | BR13       | A              | 00             |       | 00        | Y                     | 00000000001   | DESC02          | GLBATTR1               | GLBVAL1                 | GLBATTR2               | GLBVAL2                 | GLBATTR3               | GLBVAL3                 | GLBATTR4               | GLBVAL4                 |

    And I wait "/plan/cns_plant_attr" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMLocationDetail.tsv"

    Then I check file data for filename "GDMLocationDetail.tsv" by keyFields "locationDetailId"
      | locationDetailId                     | activeOPRERP | activeSOPERP | CLASS | comments | description | locationId      | name     | unit | value   |
      | CONS_LATAM_BR12/PGA/ATTRIB1/VALUE1   | YES          | NO           | PGA   |          |             | CONS_LATAM_BR12 | ATTRIB1  |      | VALUE1  |
      | CONS_LATAM_BR12/PGA/ATTRIB2/VALUE2   | YES          | NO           | PGA   |          |             | CONS_LATAM_BR12 | ATTRIB2  |      | VALUE2  |
      | CONS_LATAM_BR12/PGA/ATTRIB3/VALUE3   | YES          | NO           | PGA   |          |             | CONS_LATAM_BR12 | ATTRIB3  |      | VALUE3  |
      | CONS_LATAM_BR12/PGA/ATTRIB4/VALUE4   | YES          | NO           | PGA   |          |             | CONS_LATAM_BR12 | ATTRIB4  |      | VALUE4  |
      | CONS_LATAM_BR13/PGA/GLBATTR1/GLBVAL1 | YES          | NO           | PGA   |          |             | CONS_LATAM_BR13 | GLBATTR1 |      | GLBVAL1 |
      | CONS_LATAM_BR13/PGA/GLBATTR2/GLBVAL2 | YES          | NO           | PGA   |          |             | CONS_LATAM_BR13 | GLBATTR2 |      | GLBVAL2 |
      | CONS_LATAM_BR13/PGA/GLBATTR3/GLBVAL3 | YES          | NO           | PGA   |          |             | CONS_LATAM_BR13 | GLBATTR3 |      | GLBVAL3 |
      | CONS_LATAM_BR13/PGA/GLBATTR4/GLBVAL4 | YES          | NO           | PGA   |          |             | CONS_LATAM_BR13 | GLBATTR4 |      | GLBVAL4 |

    Then I check file data for filename "LocationDetail.tsv" by keyFields "locationDetailId"
      | locationDetailId                     | activeOprerp | activeSoperp | CLASS | comments | description | locationid      | name     | unit | value   |
      | CONS_LATAM_BR12/PGA/ATTRIB1/VALUE1   | YES          | NO          | PGA   |          |             | CONS_LATAM_BR12 | ATTRIB1  |      | VALUE1  |
      | CONS_LATAM_BR12/PGA/ATTRIB2/VALUE2   | YES          | NO          | PGA   |          |             | CONS_LATAM_BR12 | ATTRIB2  |      | VALUE2  |
      | CONS_LATAM_BR12/PGA/ATTRIB3/VALUE3   | YES          | NO          | PGA   |          |             | CONS_LATAM_BR12 | ATTRIB3  |      | VALUE3  |
      | CONS_LATAM_BR12/PGA/ATTRIB4/VALUE4   | YES          | NO          | PGA   |          |             | CONS_LATAM_BR12 | ATTRIB4  |      | VALUE4  |
      | CONS_LATAM_BR13/PGA/GLBATTR1/GLBVAL1 | YES          | NO          | PGA   |          |             | CONS_LATAM_BR13 | GLBATTR1 |      | GLBVAL1 |
      | CONS_LATAM_BR13/PGA/GLBATTR2/GLBVAL2 | YES          | NO          | PGA   |          |             | CONS_LATAM_BR13 | GLBATTR2 |      | GLBVAL2 |
      | CONS_LATAM_BR13/PGA/GLBATTR3/GLBVAL3 | YES          | NO          | PGA   |          |             | CONS_LATAM_BR13 | GLBATTR3 |      | GLBVAL3 |
      | CONS_LATAM_BR13/PGA/GLBATTR4/GLBVAL4 | YES          | NO          | PGA   |          |             | CONS_LATAM_BR13 | GLBATTR4 |      | GLBVAL4 |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "PANGEA_V1_gdm_location_detail.tsv"

