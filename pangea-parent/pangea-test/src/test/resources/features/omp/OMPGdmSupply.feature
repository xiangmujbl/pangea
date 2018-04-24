@pangea_test @AEAZ-1763
Feature:  OMPGdmSupply-Curation

  Scenario: Full Load consumption


    When I submit task with xml file "xml/omp/OMPGdmSupply.xml" and execute file "jar/pangea-view.jar"

    Then A file is found on sink application with name "GdmSupply.tsv"

    And I check file data for filename "GdmSupply.tsv" by keyFields "locationTypeId,activeFctErp,activeOprErp,activeSopErp,label"
      | SupplyId | Active | ActiveOPRERP | ActiveSOPERP | FromDate | LABEL | LocationId | MACHINECAPACITY | MACHINETYPEID | MaxQuantity | MaxQuantityType | MinQuantity | MinQuantityType | PLANLEVELID | Preference | PROCESSTYPEID | ProductId | PURCHASINGGROUP | PURCHASINGORGANIZATION | SupplierId | ToDate | VENDOR | TransportType |

    And I delete the test data

    And I will remove all data with region "/omp/gdm_location_type"

    And I will remove the test file on sink application "GdmSupply.tsv"

