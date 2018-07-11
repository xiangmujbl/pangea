@pangea_test @AEAZ-8294
Feature: EDMCategoryV1 AEAZ-8294
  # test copy attributes from source system to target system

  Scenario: Full Load curation

    Given I import "/infa_mdm/c_lkup_catg" by keyFields "catgCd"
      | catgCd   | catDescnTxt            |
      | 0003     |	Body Care Wash          |
      | 0004     |	Body Cleansing Bar Soap    |
      | 0006     |	Body Care Moisturizing    |
      | 0021     |	Ultra Thin Pads    |
      | 0034     |	Nursing Pads    |
      | 0035     |	Other Misc.Baby Care Products    |
      | 0037     |	Baby Hair Care    |
      | 0071     |	Consumer First Aid Prod    |
      | 0112     |	Clean & Clear    |
      | 0113     |	Neostrata    |
      | 0132     |	RoC* Face    |
      | 0135     |	Toothbrushes - Manual    |
      | 0136     |	Floss/Tape    |
      | 0142     |	Band Aid Brand Adhesive Bandages    |
      | 0150     |	Thick Pads    |
      | 0156     |	Digital    |
      | 0163     |	Panty Liners    |
      | 0201     |	HIPOGLOS    |
      | 0203     |	BabyCleansers ex Softwash    |
      | 0206     |	Baby Powder    |
      | 0215     |	Skin Lotion Baby Wipes    |
      | 0238     |	Neutrogena Body    |
      | 0239     |	Neutrogena Face    |
      | 0256     |	Neutrogena Cosmetics    |
      | 0280     |	Neutrogena Suncare    |
      | 0281     |	RoC Suncare    |
      | 0453     |	Aveeno Body    |
      | 0457     |	Aveeno Baby    |
      | 1054     |	Baby Cologne    |
      | 1068     |	Cotton Buds/Swabs    |
      | 1120     |	Sundown    |
      | 1197     |	Children's TYLENOL*    |
      | 1499     |	Baby Oil    |
      | 1500     |	Baby Lotion/Cream ex Soft    |
      | 1712     |	Johnson's Toddlers    |
      | 1770     |	Adult TYLENOL*    |
      | 1799     |	TYLENOL* Sinus    |
      | 1811     |	Baby Bar Soaps    |
      | 1850     |                     |
      | 1877     |	Johnson´s Adult Deodorant    |
      | 1949     |	Mylanta/Gas    |
      | 5504     |	PCH Baby Skin Treatments    |
      | 5507     |	Listerine Adult Mouthwash    |
      | 5561     |	Anti-Smoking Aids (Blister)    |
      | 5589     |	Listerine Whitening Mouthwash    |
      | 5610     |	OGX - Hair    |

    And I wait "/infa_mdm/c_lkup_catg" Async Queue complete

    When I submit task with xml file "xml/edm/EDMCategoryV1.xml" and execute file "jar/pangea-view.jar"

    And  wait 8000 millisecond
    Then I check region data "/edm/category_v1" by keyFields "category"
      | category | categoryName               |
      | 0003     |	Body Care Wash             |
      | 0004     |	Body Cleansing Bar Soap   |
      | 0006     |	Body Care Moisturizing    |
      | 0021     |	Ultra Thin Pads            |
      | 0034     |	Nursing Pads               |
      | 0035     |	Other Misc.Baby Care Products    |
      | 0037     |	Baby Hair Care    |
      | 0071     |	Consumer First Aid Prod    |
      | 0112     |	Clean & Clear    |
      | 0113     |	Neostrata    |
      | 0132     |	RoC* Face    |
      | 0135     |	Toothbrushes - Manual    |
      | 0136     |	Floss/Tape    |
      | 0142     |	Band Aid Brand Adhesive Bandages    |
      | 0150     |	Thick Pads    |
      | 0156     |	Digital    |
      | 0163     |	Panty Liners    |
      | 0201     |	HIPOGLOS    |
      | 0203     |	BabyCleansers ex Softwash    |
      | 0206     |	Baby Powder    |
      | 0215     |	Skin Lotion Baby Wipes    |
      | 0238     |	Neutrogena Body    |
      | 0239     |	Neutrogena Face    |
      | 0256     |	Neutrogena Cosmetics    |
      | 0280     |	Neutrogena Suncare    |
      | 0281     |	RoC Suncare    |
      | 0453     |	Aveeno Body    |
      | 0457     |	Aveeno Baby    |
      | 1054     |	Baby Cologne    |
      | 1068     |	Cotton Buds/Swabs    |
      | 1120     |	Sundown    |
      | 1197     |	Children's TYLENOL*    |
      | 1499     |	Baby Oil    |
      | 1500     |	Baby Lotion/Cream ex Soft    |
      | 1712     |	Johnson's Toddlers    |
      | 1770     |	Adult TYLENOL*    |
      | 1799     |	TYLENOL* Sinus    |
      | 1811     |	Baby Bar Soaps    |
      | 1850     |                     |
      | 1877     |	Johnson´s Adult Deodorant    |
      | 1949     |	Mylanta/Gas    |
      | 5504     |	PCH Baby Skin Treatments    |
      | 5507     |	Listerine Adult Mouthwash    |
      | 5561     |	Anti-Smoking Aids (Blister)    |
      | 5589     |	Listerine Whitening Mouthwash    |
      | 5610     |	OGX - Hair    |

    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/infa_mdm/c_lkup_catg" and "/edm/category_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/edm/category_v1"

    And I will remove all data with region "/plan/edm_failed_data"
