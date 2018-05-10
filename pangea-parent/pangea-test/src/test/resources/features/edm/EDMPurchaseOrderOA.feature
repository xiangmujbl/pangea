@pangea_test @AEAZ-3175
Feature: EDMPurchaseOrderOA AEAZ-3175

  Scenario: Full Load curation

    Given I import "/project_one/ekpo" by keyFields "ebelp,ebeln,mandt"
    |    ebeln   | mandt | ebelp | loekz | matnr | werks | lgort | bednr | menge | meins | umrez | umren | insmk | elikz | pstyp | knttp | konnr | ktpnr | lmein | adrnr | plifz | sobkz | bstae | lblkz | adrn2 |    banfn   | bnfpo |  j1bnbm  | reslo | webaz |
    | 3000793950 |  120  |  10   |   L   |118342 |  BR12 |  BR04 |       | 2.000 |  ST   |   1   |   1   |       |   X   |   0   |       |       |   0   |   ST  |       |   10  |       |       |       |       | 1598366192 |  10   | 85168090 |       |  0    |
    | 3000793951 |  120  |  11   |   L   |118343 |  BR12 |  BR04 |       | 2.000 |  ST   |   1   |   1   |       |   X   |   0   |       |       |   0   |   ST  |       |   10  |       |       |       |       | 1598366193 |  11   | 85168091 |       |  0    |
    | 3000793952 |  120  |  12   |   L   |118344 |  BR12 |  BR04 |       | 2.000 |  ST   |   1   |   1   |       |   X   |   0   |       |       |   0   |   ST  |       |   10  |       |       |       |       | 1598366194 |  12   | 85168092 |       |  0    |
    | 3000793953 |  120  |  13   |   L   |118345 |  BR12 |  BR04 |       | 2.000 |  ST   |   1   |   1   |       |   X   |   0   |       |       |   0   |   ST  |       |   10  |       |       |       |       | 1598366195 |  13   | 85168093 |       |  0    |
    | 3000793954 |  120  |  14   |   L   |118346 |  BR12 |  BR04 |       | 2.000 |  ST   |   1   |   1   |       |   X   |   0   |       |       |   0   |   ST  |       |   10  |       |       |       |       | 1598366196 |  14   | 85168094 |       |  0    |

    And I wait "/project_one/ekpo" Async Queue complete

    Given I import "/project_one/ekko" by keyFields "ebeln,mandt"
    |    ebeln   | bstyp | bsart |   aedat  | lifnr | ekorg | ekgrp | bukrs | waers |   bedat  | kdatb | kdate | llief | reswk | frgrl | lphis | mandt |
    | 3000793950 |   F   |  NB   | 20170102 |  134  |  BR00 |  B56  |  7680 |  BRL  | 20170102 |       |       |       |       |       |       |  120  |
    | 3000793951 |   F   |  NB   | 20170102 |  134  |  BR00 |  B56  |  7680 |  BRL  | 20170102 |       |       |       |       |       |       |  120  |
    | 3000793952 |   F   |  NB   | 20170102 |  134  |  BR00 |  B56  |  7680 |  BRL  | 20170102 |       |       |       |       |       |       |  120  |
    | 3000793953 |   F   |  NB   | 20170102 |  134  |  BR00 |  B56  |  7680 |  BRL  | 20170102 |       |       |       |       |       |       |  120  |
    | 3000793954 |   F   |  NB   | 20170102 |  134  |  BR00 |  B56  |  7680 |  BRL  | 20170102 |       |       |       |       |       |       |  120  |

    And I wait "/project_one/ekko" Async Queue complete

    Given I import "/project_one/ekbe" by keyFields "belnr,buzei,ebeln,ebelp,gjahr,mandt,vgabe,zekkn"
    | vgabe | gjahr |   belnr    | buzei | bewtp | bwart |   budat  | menge |   ebeln    | ebelp | mandt | zekkn |
    |   8   |  0000 | 0197371531 |  0010 |   L   |       | 20171002 | 50.000| 3000793950 |  10   |  120  |  00   |
    |   8   |  0000 | 0197371532 |  0015 |   L   |       | 20171002 | 50.000| 3000793951 |  11   |  120  |  00   |
    |   8   |  0000 | 0197371533 |  0020 |   L   |       | 20171002 | 50.000| 3000793952 |  12   |  120  |  00   |
    |   8   |  0000 | 0197371534 |  0025 |   L   |       | 20171002 | 50.000| 3000793953 |  13   |  120  |  00   |
    |   8   |  0000 | 0197371535 |  0030 |   L   |       | 20171002 | 50.000| 3000793954 |  14   |  120  |  00   |

    And I wait "/project_one/ekbe" Async Queue complete

    Given I import "/project_one/ekes" by keyFields "ebeln,ebelp,etens,mandt"
    | ebtyp | eindt | erdat | menge | dabmg | estkz | vbeln | vbelp | charg |    ebeln   | ebelp | etens | mandt |
    |       |       |       |       |       |       |       |       |       | 3000793950 |  10   |  001  |  120  |
    |       |       |       |       |       |       |       |       |       | 3000793951 |  11   |  002  |  120  |
    |       |       |       |       |       |       |       |       |       | 3000793952 |  12   |  003  |  120  |
    |       |       |       |       |       |       |       |       |       | 3000793953 |  13   |  004  |  120  |
    |       |       |       |       |       |       |       |       |       | 3000793954 |  14   |  005  |  120  |

    And I wait "/project_one/ekes" Async Queue complete

    Given I import "/project_one/eket" by keyFields "ebeln,ebelp,mandt"
    |   eindt  |   menge  |   wemng  |   banfn    | bnfpo |   glmng  | dabmg  | etenr |    ebeln   | ebelp | mandt |
    | 20171002 | 1968.000 | 1968.000 | 1603571115 |   10  | 1968.000 |  0.000 |  1    | 3000793950 |   10  |  120  |
    | 20171002 | 2068.000 | 2068.000 | 1603571116 |   10  | 2068.000 |  0.000 |  1    | 3000793951 |   11  |  120  |
    | 20171002 | 3568.000 | 3568.000 | 1603571117 |   10  | 3568.000 |  0.000 |  1    | 3000793952 |   12  |  120  |
    | 20171002 | 3968.000 | 3968.000 | 1603571118 |   10  | 3968.000 |  0.000 |  1    | 3000793953 |   13  |  120  |
    | 20171002 | 5968.000 | 5968.000 | 1603571119 |   10  | 5968.000 |  0.000 |  1    | 3000793954 |   14  |  120  |

    And I wait "/project_one/eket" Async Queue complete

    Given I import "/project_one/ekpv" by keyFields "ebeln,ebelp,mandt"
    | kunnr  |    ebeln   | ebelp | mandt |
    | 157377 | 3000793950 |   10  |  120  |
    | 157377 | 3000793951 |   11  |  120  |
    | 157377 | 3000793952 |   12  |  120  |
    | 157377 | 3000793953 |   13  |  120  |
    | 157377 | 3000793954 |   14  |  120  |

    And I wait "/project_one/ekpv" Async Queue complete


    When I submit task with xml file "xml/edm/EDMPurchaseOrderOA.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/edm/purchase_order_oa_v1" by keyFields ""
    | sourceSystem | poNum | poLineNbr | evTypeCd | matlMvmttYr | matlMvmtNum | matlMvmtSeqNbr | delvSchedCntNbr | poCatTypeCd | poTypeCd | crtOnDt | supNum | prchsngOrgNum | prchsngGrpNum | prchsngCoCd | crncyCd | poDt | vldFromDt | vldToDt | suplVendNum | suplPlntCd | rlseCmpltInd | rlseDocInd | delInd | matlNum | plntCd | slocCd | intrnlRefNum | poLineQty | poUomCd | localNumerator | localDenominator | stkTypeCd | delvCmpltInd | lineItemTypeCd | acctAsgnmtCatCd | prinPrchAgmtNum | prinPrchAgmtLineNbr | localBaseUOM | delvAddrNum | localPDT | localSpecialStock | cnfrmCd | subcntrcInd | localdelvAddrNum | prDocId | prLineNbr | localBrazilianNCMCode | isuSlocCd | poHistCatCd | localMovementType | localPostingDate | recvEaQty | cnfrmSeqNbr | delvDt | crtOnDt | cnfrmQty | mrpAdjQty | localCreationIndVendorConf | slsOrdrNum | slsOrdrLineNbr | vendBtchNum | localdelvDt | schdQty | recvQty | localPurchaseReq | localPurchaseReqItem | stkTfrRecvQty | localmrpAdjQty | grLeadTimeDays | custNum |


    And I will remove all data with region "/project_one/ekpo"

    And I will remove all data with region "/project_one/ekko"

    And I will remove all data with region "/project_one/ekbe"

    And I will remove all data with region "/project_one/ekes"

    And I will remove all data with region "/project_one/eket"

    And I will remove all data with region "/project_one/ekpv"

    And I will remove all data with region "/plan/edm_failed_data"

