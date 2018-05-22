@pangea_test @AEAZ-2376
Feature:  OMPGdmLocationDetail-Curation

  Scenario: Full Load curation AEAZ-3680

    Given I import "/plan/cns_plant_attr" by keyFields "sourceSystem,localPlant"
      | sourceSystem | localPlant | localPlantName               | localPlantType | plant | plantType               | localPlanningRelevant | planLocTypeId | planLocTypeDesc             | locationAttribute1Desc | locationAttribute1Value | locationAttribute2Desc | locationAttribute2Value | locationAttribute3Desc | locationAttribute3Value | locationAttribute4Desc | locationAttribute4Value |
      | CONS_LATAM   | BR12       |J&J BR São José Campos - Indus|                |BR59   | MP, Manufacturing Plant | X                     | IM            | Internal Manufacturing Plant| Country                | Brazil                  | Volume                 | High                    |                        |                         |                        |                         |
      | CONS_LATAM   | CO02       |J&J Colombia - S&M (CO02)     | DC             |CO08   | DC, Distribution Center | X                     | DC            | Distribution Center         | Country                | Colombia                | Volume                 | Low                     |                        |                         |                        |                         |
      | CONS         | BR14       | A                            | 00             |       | 00                      | Y                     | 00000000001   | DESC03                      | GLBATTR1B              | GLBVAL1B                | GLBATTR2B              | GLBVAL2B                | GLBATTR3B              | GLBVAL3B                | GLBATTR4B              | GLBVAL4B                |
    And I wait "/plan/cns_plant_attr" Async Queue complete

    When I submit task with xml file "xml/omp/OMPGdmLocationDetail.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GDMLocationDetail.tsv"

    Then I check file data for filename "GDMLocationDetail.tsv" by keyFields "locationDetailId"
      | locationDetailId                    | activeOPRERP | activeSOPERP  | CLASS | comments | description | locationId      | name       | unit | value    |
      | CONS_LATAM_BR12/PGA/Country/Brazil  | YES          | NO            | PGA   |          | Pangea      | CONS_LATAM_BR12 | Country    |      | Brazil   |
      | CONS_LATAM_BR12/PGA/Volume/High     | YES          | NO            | PGA   |          | Pangea      | CONS_LATAM_BR12 | Volume     |      | High     |
      | CONS_LATAM_CO02/PGA/Country/Colombia| YES          | NO            | PGA   |          | Pangea      | CONS_LATAM_CO02 | Country    |      | Colombia |
      | CONS_LATAM_CO02/PGA/Volume/Low      | YES          | NO            | PGA   |          | Pangea      | CONS_LATAM_CO02 | Volume     |      | Low      |
      | CONS_BR14//GLBATTR1B/GLBVAL1B       | YES          | NO            |       |          | Pangea      | CONS_BR14       | GLBATTR1B  |      | GLBVAL1B |
      | CONS_BR14//GLBATTR2B/GLBVAL2B       | YES          | NO            |       |          | Pangea      | CONS_BR14       | GLBATTR2B  |      | GLBVAL2B |
      | CONS_BR14//GLBATTR3B/GLBVAL3B       | YES          | NO            |       |          | Pangea      | CONS_BR14       | GLBATTR3B  |      | GLBVAL3B |
      | CONS_BR14//GLBATTR4B/GLBVAL4B       | YES          | NO            |       |          | Pangea      | CONS_BR14       | GLBATTR4B  |      | GLBVAL4B |

#    Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_location_detail"

    And I will remove all data with region "/plan/edm_failed_data"

    And I will remove the test file on sink application "GDMLocationDetail.tsv"

