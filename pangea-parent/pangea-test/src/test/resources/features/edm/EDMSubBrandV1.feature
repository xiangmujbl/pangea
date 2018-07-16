@pangea_test @AEAZ-8297
Feature: EDMSubBrandV1 AEAZ-8297
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/infa_mdm/c_lkup_sub_brnd" by keyFields "subBrndCd"
      | subBrndCd | subBrndDescnTxt |
      #| 1A         | OGX |
      | 1A         | Neostrata |
      | 1H         | Internal Sanitary Protection |
      | 1K         | Baby Bar Soaps |
      | 1L         | Baby Cologne |
      | 1N         | Baby Hair Care |
      | 1P         | Baby Lotion/Cream ex Soft |
      | 1Q         | Baby Oil |
      | 1R         | Baby Powder |
      | 1T         | BabyCleansers ex Softwash |
      | 1U         | Band Aid Brand Adhesive Bandages |
      | 1V         | Johnson's Body Care |
      | 1X         | First Aid & Home Health Care (HHC) |
      | 1Y         | Clean & Clear |
      | 2B         | Cotton Products |
      | 2C         | Floss/Tape |
      | 2E         | Diaper Rash |
      | 2I         | Tylenol Analgesic |
      | 2L         | Digestive Health |
      | 2T         | Johnson's Adult Other |
      | 2V         | Johnson's Toddlers |
      | 3C         | Misc Baby Care Products |
      | 3D         | Neutrogena Cosmetics |
      | 3F         | Neutrogena Face |
      | 3G         | Neutrogena Suncare |
      | 3H         | Neutrogena Body |
      | 3Q         | Panty Liners |
      | 3U         | Anti-Smoking Gum |
      | 4G         | Listerine Mouthwash |
      | 4X         | RoC Suncare |
      | 4Z         | Skin Lotion Baby Wipes |
      | 5E         | Sundown |
      | 5G         | Napkins |
      | 5H         | Toothbrushes - Manual |
      | 5K         | Upper Respiratory |
      | 5N         | Tylenol UR |
      | 5O         | RoC* Face |
      | 6A         | Aveeno Body |
      | 6E         | Aveeno Baby |
      | 7B         | Listerine Whitening Mouthwash |
    And I wait "/infa_mdm/c_lkup_sub_brnd" Async Queue complete

    When I submit task with xml file "xml/edm/EDMSubBrandV1.xml" and execute file "jar/pangea-view.jar"

    And  wait 5000 millisecond
    Then I check region data "/edm/sub_brand_v1" by keyFields "subBrand"
      | subBrand  | subBrandDescription |
     # | 1A         | OGX |
      | 1A         | Neostrata |
      | 1H         | Internal Sanitary Protection |
      | 1K         | Baby Bar Soaps |
      | 1L         | Baby Cologne |
      | 1N         | Baby Hair Care |
      | 1P         | Baby Lotion/Cream ex Soft |
      | 1Q         | Baby Oil |
      | 1R         | Baby Powder |
      | 1T         | BabyCleansers ex Softwash |
      | 1U         | Band Aid Brand Adhesive Bandages |
      | 1V         | Johnson's Body Care |
      | 1X         | First Aid & Home Health Care (HHC) |
      | 1Y         | Clean & Clear |
      | 2B         | Cotton Products |
      | 2C         | Floss/Tape |
      | 2E         | Diaper Rash |
      | 2I         | Tylenol Analgesic |
      | 2L         | Digestive Health |
      | 2T         | Johnson's Adult Other |
      | 2V         | Johnson's Toddlers |
      | 3C         | Misc Baby Care Products |
      | 3D         | Neutrogena Cosmetics |
      | 3F         | Neutrogena Face |
      | 3G         | Neutrogena Suncare |
      | 3H         | Neutrogena Body |
      | 3Q         | Panty Liners |
      | 3U         | Anti-Smoking Gum |
      | 4G         | Listerine Mouthwash |
      | 4X         | RoC Suncare |
      | 4Z         | Skin Lotion Baby Wipes |
      | 5E         | Sundown |
      | 5G         | Napkins |
      | 5H         | Toothbrushes - Manual |
      | 5K         | Upper Respiratory |
      | 5N         | Tylenol UR |
      | 5O         | RoC* Face |
      | 6A         | Aveeno Body |
      | 6E         | Aveeno Baby |
      | 7B         | Listerine Whitening Mouthwash |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/infa_mdm/c_lkup_sub_brnd" and "/edm/sub_brand_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/sub_brand_v1"

    And I will remove all data with region "/plan/edm_failed_data"
