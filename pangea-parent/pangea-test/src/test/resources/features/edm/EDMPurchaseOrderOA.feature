@pangea_test @AEAZ-3175
Feature: EDMPurchaseOrderOA AEAZ-3175

  Scenario: Full Load curation

    Given I import "/project_one/ekpo" by keyFields "ebelp,ebelp,mandt"
    | ebelp | loekz | matnr | werks | lgort | bednr | menge | meins | umrez | umren | insmk | elikz | pstyp | knttp | konnr | ktpnr | lmein | adrnr | plifz | sobkz | bstae | lblkz | adrn2 | banfn | bnfpo | j1bnbm | reslo | webaz |

    And I wait "/project_one/ekpo" Async Queue complete

    Given I import "/project_one/ekko" by keyFields "ebeln,mandt"
    | ebeln | bstyp | bsart | aedat | lifnr | ekorg | ekgrp | bukrs | waers | bedat | kdatb | kdate | llief | reswk | frgrl | lphis | mandt |

    And I wait "/project_one/ekko" Async Queue complete

    Given I import "/project_one/ekbe" by keyFields "belnr,buzei,ebeln,ebelp,gjahr,mandt,vgabe,zekkn"
    | vgabe | gjahr | belnr | buzei | bewtp | bwart | budat | menge | ebeln | ebelp | mandt | vgabe | zekkn |

    And I wait "/project_one/ekbe" Async Queue complete

    Given I import "/project_one/ekes" by keyFields "ebeln,ebelp,etens,mandt"
    | ebtyp | eindt | erdat | menge | dabmg | estkz | vbeln | vbelp | charg | ebeln | ebelp | etens | mandt |

    And I wait "/project_one/ekes" Async Queue complete

    Given I import "/project_one/eket" by keyFields "ebeln,ebelp,mandt"
    | eindt | menge | wemng | banfn | bnfpo | glmng | dabmg | etenr | ebeln | ebelp | mandt |

    And I wait "/project_one/eket" Async Queue complete

    Given I import "/project_one/ekpv" by keyFields "ebeln,ebelp,mandt"
    | kunnr | ebeln | ebelp | mandt |

    And I wait "/project_one/ekpv" Async Queue complete


    When I submit task with xml file "xml/edm/EDMPurchaseOrderOA.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/purchase_order_oa_v1" by keyFields ""
    | sourceSystem | poNum | poLineNbr | evTypeCd | matlMvmttYr | matlMvmtNum | matlMvmtSeqNbr | delvSchedCntNbr | poCatTypeCd | poTypeCd | crtOnDt | supNum | prchsngOrgNum | prchsngGrpNum | prchsngCoCd | crncyCd | poDt | vldFromDt | vldToDt | suplVendNum | suplPlntCd | rlseCmpltInd | rlseDocInd | delInd | matlNum | plntCd | slocCd | intrnlRefNum | poLineQty | poUomCd | localNumerator | localDenominator | stkTypeCd | delvCmpltInd | lineItemTypeCd | acctAsgnmtCatCd | prinPrchAgmtNum | prinPrchAgmtLineNbr | localBaseUOM | delvAddrNum | localPDT | localSpecialStock | cnfrmCd | subcntrcInd | localdelvAddrNum | prDocId | prLineNbr | localBrazilianNCMCode | isuSlocCd | poHistCatCd | localMovementType | localPostingDate | recvEaQty | cnfrmSeqNbr | delvDt | crtOnDt | cnfrmQty | mrpAdjQty | localCreationIndVendorConf | slsOrdrNum | slsOrdrLineNbr | vendBtchNum | localdelvDt | schdQty | recvQty | localPurchaseReq | localPurchaseReqItem | stkTfrRecvQty | localmrpAdjQty | grLeadTimeDays | custNum |

  #    Then I check region data "/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
#      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I will remove all data with region "/project_one/ekpo"

    And I will remove all data with region "/project_one/ekko"

    And I will remove all data with region "/project_one/ekbe"

    And I will remove all data with region "/project_one/ekes"

    And I will remove all data with region "/project_one/eket"

    And I will remove all data with region "/project_one/ekpv"

    And I will remove all data with region "/plan/edm_failed_data"

