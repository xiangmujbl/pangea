@pangea_test @AEAZ-2828 @AEAZ-4065 @AEAZ-6809
Feature: EDMMaterialGlobal AEAZ-2828 AEAZ-4065 AEAZ-6809

  Scenario: Full Load curation

    Given I import "/dev/project_one/mara" by keyFields "matnr,mandt"
		|	matnr		|	mtart	|	meins	|	zzplnrootid 		|	matkl	|	lvorm	|	mstae	|	spart	|	xchpf	|	mhdrz	|	mhdhb	|	mandt	|
		|	414.740V	|	FERT	|	ST		|	8105080005140010	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	456.770S	|	FERT	|	ST		|	8105080005070012	|	999999	|	X		|	I		|	10		|	X		|	36		|			|	100		|
		|	456.771S	|	FERT	|	ST		|	8105080005070012	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	456.772S	|	FERT	|	ST		|	8105080004150024	|	999999	|	X		|	L		|	10		|	X		|	180		|	0		|	100		|
		|	456.773S	|	FERT	|	ST		|	78962124225240003	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	456.774S	|	FERT	|	ST		|	78962124225240003	|	999999	|	X		|	L		|	10		|	X		|	180		|	0		|	100		|
		|	456.775S	|	FERT	|	ST		|	78962124225170036	|	999999	|	X		|	I		|	10		|	X		|	36		|			|	100		|
		|	456.776S	|	FERT	|	ST		|	78962124225170036	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	456.777S	|	FERT	|	ST		|						|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	456.778S	|	FERT	|	ST		|	78962124135600024	|	999999	|	X		|	L		|	10		|	X		|	180		|	0		|	100		|
		|	456.779S	|	FERT	|	ST		|	78962124135600024	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	456.781S	|	FERT	|	ST		|	78962124060430006	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	456.782S	|	FERT	|	ST		|	78920316074490012	|	999999	|	X		|	L		|	10		|	X		|	1		|	0		|	100		|
		|	498.002VS	|	FERT	|	ST		|	78920316074490012	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	498.004VS	|	FERT	|	ST		|	78920316074320012	|	999999	|	X		|	L		|	10		|	X		|	180		|	0		|	100		|
		|	498.012VS	|	FERT	|	ST		|	78912684040360024	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	498.013VS	|	FERT	|	ST		|	78912684040360024	|	999999	|	X		|	L		|	10		|	X		|	1		|	0		|	100		|
		|	498.015VS	|	FERT	|	ST		|	78912684040290024	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	498.016VS	|	FERT	|	ST		|	78912684040290024	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	498.018VS	|	FERT	|	ST		|	78912684020250024	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|	
		|	498.023VS	|	FERT	|	ST		|	78912684020180024	|	999999	|	X		|	L		|	10		|	X		|	1		|	0		|	100		|
		|	498.024VS	|	FERT	|	ST		|	8105080004150024	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	498.025VS	|	FERT	|	ST		|	8105080004150024	|	999999	|	X		|	L		|	10		|	X		|	36		|			|	100		|
		|	498.027VS	|	FERT	|	ST		|	8105080004150024	|	999999	|	X		|	L		|	10		|	X		|	18		|	0		|	100		|
		|	498.028VS	|	FERT	|	ST		|	8105080004150024	|	999999	|	X		|	L		|	10		|	X		|	180		|			|	100		|
		|	498.030VS	|	FERT	|	ST		|	8105080004150024	|	999999	|	X		|	L		|	10		|	X		|	18		|	0		|	100		|
		|	498.031VS	|	FERT	|	ST		|	78962124060430006	|	999999	|	X		|	L		|	10		|	X		|	180		|			|	100		|
		|	498.160VS	|	FERT	|	ST		|	78962124225240003	|	999999	|	X		|	L		|	10		|	X		|	36		|	0		|	100		|
		|	498.215VS	|	FERT	|	ST		|	78962124225240003	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	498.220VS	|	FERT	|	ST		|	8105080004150024	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	498.225VS	|	FERT	|	ST		|	8105080004150024	|	999999	|	X		|	L		|	10		|	X		|	180		|	0		|	100		|
		|	498.250VS	|	FERT	|	ST		|	8105080004150024	|	999999	|	X		|	L		|	10		|	X		|	18		|			|	100		|
		|	498.251VS	|	FERT	|	ST		|	8105080004150024	|	999999	|	X		|	L		|	10		|	X		|	36		|	0		|	100		|
    And I wait "/dev/project_one/mara" Async Queue complete

    Given I import "/dev/project_one/makt" by keyFields "mandt,matnr,spras"
		|	matnr		|	maktx										|	spras	|	mandt	|
		|	414.740V	|	SUNDOWN REGULAR FPS 60 STARCK				|	E		|	100		|
		|	456.770S	|	J'S BABY WIPES COOLING TOUCH BIL			|	E		|	100		|
		|	456.771S	|	SUNDOWN GOLD LOCAO FPS15 STARCK 12X120ML	|	E		|	100		|
		|	456.772S	|	LISTERINE ZERO PORT 6X1,5L					|	E		|	100		|
		|	456.772S	|	LISTERINE ZERO PORT 6X1,5L-PORTUGESE		|	P		|	100		|
		|	456.772S	|	LISTERINE ZERO PORT 6X1,5L - SPANISH		|	S		|	100		|
		|	456.773S	|	RoC Clarifiant Eyes 6X15G					|	P		|	100		|
		|	456.773S	|	RoC Clarifiant Eyes 6X15G - SPANISH			|	S		|	100		|
		|	456.774S	|	SUNDOWN REGULAR FPS50 STARCK 12X120ML		|	S		|	100		|
		|	456.775S	|	NTG ACNE PROOFING TONER 6X 200 ML BIL		|	E		|	100		|
		|	456.776S	|	SActive Triple Firming Neck Cream 2g		|	E		|	100		|
		|	456.777S	|	ROC RETIN-OX CORREXI SERUM A-RUGA 6X30ML	|	E		|	100		|
		|	456.778S	|	ROC C-SUPÃ‰RIEUR CONCENTRADO 6% 6X15G		|	E		|	100		|
		|	456.779S	|	PR BAR SOAP BABY SPECIAL PRICE				|	E		|	100		|
		|	456.781S	|	JS BABY SAB HORA DE BRINCAR NAC 72X80G		|	E		|	100		|
		|	456.782S	|	ROC PRO CLEANSE 6X200 ML					|	E		|	100		|
		|	498.002VS	|	LIST GUM DEFENSE 12X250ML					|	E		|	100		|
		|	498.004VS	|	BABAB FLEX FABRIC XL 10â€™S					|	E		|	100		|
		|	498.012VS	|	CREME PENTEAR JS BABY HIDR INT 12X150ML		|	E		|	100		|
		|	498.013VS	|	NEUT SUN FRESH FPS 70 CORPO 6X200ML			|	E		|	100		|
		|	498.015VS	|	J'S COTTON SWABS COTONETES - TRILINGUAL		|	E		|	100		|
		|	498.016VS	|	NAPKIN SL UF COTTON WIN 48x8				|	E		|	100		|
		|	498.018VS	|	P LINER CF EVERY DAY FLEXI UNSC 64X15		|	E		|	100		|
		|	498.023VS	|	PR OB MEDIO L20P16 20X20UN					|	E		|	100		|
		|	498.024VS	|	COTONETES CARTRIDGE 30X150					|	E		|	100		|
		|	498.025VS	|	PR SHAMP 200ML + SAB LIQ HTT 200ML			|	E		|	100		|
		|	498.027VS	|	PR CURAT B-AID TRANSP L10P8 144X10UN		|	E		|	100		|
		|	498.028VS	|	COND OGX BRAZIL KERAT 6X385ML BIL			|	E		|	100		|
		|	498.030VS	|	J'S SOFT PELE DOS SONHOS 12X200ML			|	E		|	100		|
		|	498.031VS	|	NEUT DEEP CLEAN ADSTR FACIAL 6X200ML BIL	|	E		|	100		|
		|	498.160VS	|	PROT DIARIO CAREFREE PROT S/PERF 48X15UN	|	E		|	100		|
		|	498.215VS	|	AVEENO SKIN RELIEF COCO BODY LOT 354mL		|	E		|	100		|
		|	498.220VS	|	PR ROC MI OIL CONT FP30 GR ROC C-SUP 6CJ	|	E		|	100		|
		|	498.225VS	|	ABS HIG SL TRI-PR MAX SV S/ABA 4X12X8UN		|	E		|	100		|
		|	498.250VS	|	ROC MINESOL ACT UNIFY FPS60 AG 240X5G		|	E		|	100		|
		|	498.251VS	|	NTG SUNFRESH RADIANCE SPF60 200ML			|	E		|	100		|
    And I wait "/dev/project_one/makt" Async Queue complete

    Given I import "/dev/edm/source_system_v1" by keyFields "localSourceSystem"
        | localSourceSystem | localSourceSystemName | sourceSystem | sourceSystemName   | sourceSystemType |
        | Project_One       | Project One           | CONS_LATAM   | Consumer Latam Ent | NGEMS            |
        | EMS               | EMS                   | EMS          | ems                | SAP              |
    And I wait "/dev/edm/source_system_v1" Async Queue complete

    Given I import "/dev/ngems/material_linkage" by keyFields "localMaterialNumber,sourceSystem"
		|	sourceSystem	|	localMaterialNumber	|	materialNumber	|
		|	CONS_LATAM		|	414.740V			|	99131			|
		|	CONS_LATAM		|	456.770S			|	97424			|
		|	CONS_LATAM		|	456.771S			|					|
		|	CONS_LATAM		|	456.772S			|	53942			|
		|	CONS_LATAM		|	456.773S			|	528207			|
		|	CONS_LATAM		|	456.774S			|	61144			|
		|	CONS_LATAM		|	456.775S			|	97489			|
		|	CONS_LATAM		|	456.776S			|					|
		|	CONS_LATAM		|	456.777S			|	56428			|
		|	CONS_LATAM		|	456.778S			|	56681			|
		|	CONS_LATAM		|	456.779S			|	89278			|
		|	CONS_LATAM		|	456.781S			|	60563			|
		|	CONS_LATAM		|	456.782S			|	60072			|
		|	CONS_LATAM		|	498.002VS			|	527294			|
		|	CONS_LATAM		|	498.004VS			|	87766			|
		|	CONS_LATAM		|	498.012VS			|	58034			|
		|	CONS_LATAM		|	498.013VS			|	60209			|
		|	CONS_LATAM		|	498.015VS			|	529391			|
		|	CONS_LATAM		|	498.016VS			|	69515			|
		|	CONS_LATAM		|	498.018VS			|	87116			|
		|	CONS_LATAM		|	498.023VS			|	60953			|
		|	CONS_LATAM		|	498.024VS			|	55883			|
		|	CONS_LATAM		|	498.025VS			|	441505			|
		|	CONS_LATAM		|	498.027VS			|	87836			|
		|	CONS_LATAM		|	498.028VS			|	6007700			|
		|	CONS_LATAM		|	498.030VS			|	57941			|
		|	CONS_LATAM		|	498.031VS			|	66983			|
		|	CONS_LATAM		|	498.160VS			|	58055			|
		|	CONS_LATAM		|	498.215VS			|	88259			|
		|	CONS_LATAM		|	498.220VS			|	60854			|
		|	CONS_LATAM		|	498.225VS			|	56228			|
		|	CONS_LATAM		|	498.250VS			|	56322			|
		|	CONS_LATAM		|	498.251VS			|	83563			|
    And I wait "/dev/ngems/material_linkage" Async Queue complete

    Given I import "/dev/ngems/golden_material" by keyFields "materialNumber"
		|	materialNumber	|	materialDescription							|	primaryPlanningCode	|	materialType	|	baseUom	|	parentCode				|	globalDpParentCode		|	form	|	category	|	subBrand	|	brand	|	franchise	|	globalBusinessUnit	 	|	productFamily	|	manufTechnology	|	materialGroup	|
		|	99131			|	New_SUNDOWN REGULAR FPS 60 STARCK			|	ppc-99131			|	FERT			|	EA		|	New_8105080005140012	|	GLB_8105080005140012	|	250		|	1120		|	5E			|	BRD010	|	FCH002		|	GFO001					|	prod_fam_1		|	manu_tech_1		|	matl_grp_1		|
		|	97424			|	New_J'S BABY WIPES COOLING TOUCH BIL		|						|	FERT			|	EA		|	New_8105080005070012	|	GLB_8105080005070012	|	100048	|	215			|	4Z			|	BRD002	|	FCH001		|	GFO001					|	prod_fam_1		|	manu_tech_2		|	matl_grp_2		|
		|	53942			|	New_LISTERINE ZERO PORT 6X1,5L				|	ppc-53942			|					|	EA		|	New_8105080004150024	|	GLB_8105080004150024	|	112245	|	5507		|	4G			|	BRD008	|	FCH003		|	GFO002					|	prod_fam_1		|	manu_tech_3		|	matl_grp_3		|
		|	528207			|	New_RoC Clarifiant Eyes 6X15G				|	ppc-528207			|	FERT			|	EA		|	New_78962124225240003	|	GLB_78962124225240003	|	112122	|	132			|	5O			|	BRD003	|	FCH002		|	GFO001					|	prod_fam_1		|	manu_tech_4		|	matl_grp_4		|
		|	61144			|	New_SUNDOWN REGULAR FPS50 STARCK 12X120ML	|	ppc-61144			|	FERT			|	EA		|	New_78962124225240003	|	GLB_78962124225240003	|	250		|	1120		|	5E			|	BRD010	|	FCH002		|	GFO001					|	prod_fam_1		|	manu_tech_5		|	matl_grp_5		|
		|	97489			|	New_NTG ACNE PROOFING TONER 6X 200 ML BIL	|	ppc-97489			|	FERT			|			|	New_78962124225170036	|	GLB_78962124225170036	|	100851	|	239			|	3F			|	BRD006	|	FCH002		|	GFO001					|	prod_fam_1		|	manu_tech_6		|	matl_grp_6		|
		|	56428			|	New_ROC RETIN-OX CORREXI SERUM A-RUGA 6X30ML|	ppc-56428			|	FERT			|	EA		|	New_78962124156180024	|	GLB_78962124156180024	|	100261	|	132			|	5O			|	BRD003	|	FCH002		|	GFO001					|	prod_fam_2		|	manu_tech_8		|	matl_grp_8		|
		|	56681			|												|	ppc-56681			|	FERT			|	EA		|	New_78962124135600024	|	GLB_78962124135600024	|	116400	|	132			|	5O			|	BRD003	|	FCH002		|	GFO001					|	prod_fam_2		|	manu_tech_9		|	matl_grp_9		|
		|	89278			|	New_PR BAR SOAP BABY SPECIAL PRICE			|	ppc-89278			|	FERT			|	EA		|							|	GLB_78888888899999990	|	100011	|	1811		|	1K			|	BRD002	|	FCH001		|	GFO001					|	prod_fam_2		|	manu_tech_10	|	matl_grp_10		|
		|	60563			|	New_JS BABY SAB HORA DE BRINCAR NAC 72X80G	|	ppc-60563			|	FERT			|	EA		|	New_78962124060430006	|	GLB_78962124060430006	|			|	1811		|	1K			|	BRD002	|	FCH001		|	GFO001					|	prod_fam_2		|	manu_tech_11	|	matl_grp_11		|
		|	60072			|	New_ROC PRO CLEANSE 6X200 ML				|	ppc-60072			|	FERT			|	EA		|	New_78920316074490012	|	GLB_78920316074490012	|	100234	|				|	5O			|	BRD003	|	FCH002		|	GFO001					|	prod_fam_2		|	manu_tech_12	|	matl_grp_12		|
		|	527294			|	New_LIST GUM DEFENSE 12X250ML				|	ppc-527294			|	FERT			|	EA		|	New_78920316074490012	|	GLB_78920316074490012	|	101722	|	5507		|				|	BRD008	|	FCH003		|	GFO002					|	prod_fam_3		|	manu_tech_13	|	matl_grp_13		|
		|	87766			|	New_BABAB FLEX FABRIC XL 10â€™S				|	ppc-87766			|	FERT			|	CA		|	New_78920316074320012	|	GLB_78920316074320012	|	100418	|	142			|	1U			|			|	FCH003		|	GFO002					|	prod_fam_3		|	manu_tech_14	|	matl_grp_14		|
		|	58034			|	New_CREME PENTEAR JS BABY HIDR INT 12X150ML	|	ppc-58034			|	FERT			|	EA		|	New_78912684040360024	|	GLB_78912684040360024	|	112316	|	37			|	1N			|	BRD002	|				|	GFO001					|	prod_fam_3		|	manu_tech_15	|	matl_grp_15		|
		|	60209			|	New_NEUT SUN FRESH FPS 70 CORPO 6X200ML		|	ppc-60209			|	FERT			|	EA		|	New_78912684040360024	|	GLB_78912684040360024	|	112684	|	280			|	3G			|	BRD006	|	FCH002		|							|	prod_fam_3		|	manu_tech_16	|	matl_grp_16		|
		|	529391			|	New_J'S COTTON SWABS COTONETES - TRILINGUAL	|	ppc-529391			|	FERT			|	EA		|	New_78912684040290024	|	GLB_78912684040290024	|	100076	|	1068		|	2B			|	BRD032	|	FCH003		|	GFO002					|					|	manu_tech_17	|	matl_grp_17		|
		|	69515			|	New_NAPKIN SL UF COTTON WIN 48x8			|	ppc-69515			|	FERT			|	EA		|	New_78912684040290024	|	GLB_78912684040290024	|	100726	|	21			|	5G			|	BRD004	|	FCH005		|	GFO002					|	prod_fam_4		|					|	matl_grp_18		|
		|	87116			|	New_P LINER CF EVERY DAY FLEXI UNSC 64X15	|	ppc-87116			|	FERT			|	EA		|	New_78912684020250024	|	GLB_78912684020250024	|	100767	|	163			|	3Q			|	BRD007	|	FCH005		|	GFO002					|	prod_fam_4		|	manu_tech_1		|					|
		|	60953			|	New_PR OB MEDIO L20P16 20X20UN				|	ppc-60953			|	FERT			|	EA		|	New_78912684020180024	|	GLB_78912684020180024	|	250		|	1120		|	5E			|	BRD010	|	FCH002		|	GFO001					|	prod_fam_4		|	manu_tech_2		|	matl_grp_1		|
		|	55883			|	New_COTONETES CARTRIDGE 30X150				|	ppc-55883			|	FERT			|	EA		|	New_8105080004150024	|							|	100048	|	215			|	4Z			|	BRD002	|	FCH001		|	GFO001					|	prod_fam_4		|	manu_tech_3		|	matl_grp_2		|
		|	441505			|	New_PR SHAMP 200ML + SAB LIQ HTT 200ML		|	ppc-441505			|	FERT			|	EA		|							|							|	253		|	1120		|	5E			|	BRD010	|	FCH002		|	GFO001					|	prod_fam_4		|	manu_tech_4		|	matl_grp_3		|
		|	87836			|	New_PR CURAT B-AID TRANSP L10P8 144X10UN	|	ppc-87836			|	FERT			|	EA		|	New_8105080004150024	|	GLB_8105080004150024	|	112245	|	5507		|	4G			|	BRD008	|	FCH003		|	GFO002					|	prod_fam_5		|	manu_tech_5		|	matl_grp_4		|
		|	6007700			|	New_COND OGX BRAZIL KERAT 6X385ML BIL		|	ppc-6007700			|	FERT			|	EA		|	New_8105080004150024	|	GLB_8105080004150024	|	112122	|	132			|	5O			|	BRD003	|	FCH002		|	GFO001					|	prod_fam_5		|	manu_tech_6		|	matl_grp_5		|
		|	57941			|	New_J'S SOFT PELE DOS SONHOS 12X200ML		|	ppc-57941			|	FERT			|	EA		|	New_8105080004150024	|	GLB_8105080004150024	|	250		|	1120		|	5E			|	BRD010	|	FCH002		|	GFO001					|	prod_fam_5		|	manu_tech_7		|	matl_grp_6		|
		|	66983			|	New_NEUT DEEP CLEAN ADSTR FACIAL 6X200ML BIL|	ppc-66983			|	FERT			|	EA		|	New_78962124060430006	|	GLB_78962124060430006	|	100851	|	239			|	3F			|	BRD006	|	FCH002		|	GFO001					|	prod_fam_5		|	manu_tech_8		|	matl_grp_7		|
		|	58055			|	New_PROT DIARIO CAREFREE PROT S/PERF 48X15UN|	ppc-58055			|	FERT			|	EA		|	New_78962124225240003	|	GLB_78962124225240003	|	101465	|	113			|	1A			|	BRD009	|	FCH002		|	GFO001					|	prod_fam_5		|	manu_tech_9		|	matl_grp_8		|
		|	88259			|	New_AVEENO SKIN RELIEF COCO BODY LOT 354mL	|	ppc-88259			|	FERT			|	EA		|	New_78962124225240003	|	GLB_78962124225240003	|	100261	|	132			|	5O			|	BRD003	|	FCH002		|	GFO001					|	prod_fam_5		|	manu_tech_10	|	matl_grp_9		|
		|	60854			|	New_PR ROC MI OIL CONT FP30 GR ROC C-SUP 6CJ|	ppc-60854			|	FERT			|	EA		|	New_8105080004150024	|	GLB_8105080004150024	|	116400	|	132			|	5O			|	BRD003	|	FCH002		|	GFO001					|	prod_fam_6		|	manu_tech_11	|	matl_grp_10		|
		|	56228			|	New_ABS HIG SL TRI-PR MAX SV S/ABA 4X12X8UN	|	ppc-56228			|	FERT			|	EA		|	New_8105080004150024	|	GLB_8105080004150024	|	100011	|	1811		|	1K			|	BRD002	|	FCH001		|	GFO001					|	prod_fam_6		|	manu_tech_12	|	matl_grp_11		|
		|	56322			|	New_ROC MINESOL ACT UNIFY FPS60 AG 240X5G	|	ppc-56322			|	FERT			|	EA		|	New_8105080004150024	|	GLB_8105080004150024	|	101450	|	1811		|	1K			|	BRD002	|	FCH001		|	GFO001					|	prod_fam_6		|	manu_tech_13	|	matl_grp_12		|
		|	83563			|	New_NTG SUNFRESH RADIANCE SPF60 200ML		|	ppc-83563			|	FERT			|	EA		|	New_8105080004150024	|	GLB_8105080004150024	|	101450	|	1811		|	1K			|	BRD002	|	FCH001		|	GFO001					|	prod_fam_6		|	manu_tech_14	|	matl_grp_13		|
    And I wait "/dev/ngems/golden_material" Async Queue complete
    
    Given I import "/dev/plan/edm_mat_input" by keyFields "sourceSystem,localMaterialNumber"
		|	sourceSystem	|	localMaterialNumber	|	localTechnology		|
		|	CONS_LATAM		|	414.740V			|	Toothbrushes		|
		|	CONS_LATAM		|	456.770S			|	Toothbrushes		|
		|	CONS_LATAM		|	456.771S			|	Toothbrushes		|
		|	CONS_LATAM		|	456.772S			|	Toothbrushes		|
		|	CONS_LATAM		|	456.773S			|	ImpoC				|
		|	CONS_LATAM		|	456.774S			|	ImpoC				|
		|	CONS_LATAM		|	456.775S			|	Soaps				|
		|	CONS_LATAM		|	456.776S			|	Soaps				|
		|	CONS_LATAM		|	456.777S			|	Soaps				|
		|	CONS_LATAM		|	456.778S			|	Cologne/Oil			|
		|	CONS_LATAM		|	456.779S			|	Cologne/Oil			|
		|	CONS_LATAM		|	456.781S			|	ImpoC				|
		|	CONS_LATAM		|	456.782S			|	ImpoC				|
		|	CONS_LATAM		|	498.002VS			|	Toiletries BRA		|
		|	CONS_LATAM		|	498.004VS			|	ImpoC				|
		|	CONS_LATAM		|	498.012VS			|	Toiletries BRA		|
		|	CONS_LATAM		|	498.013VS			|	Toiletries BRA		|
		|	CONS_LATAM		|	498.015VS			|	Listerne			|
		|	CONS_LATAM		|	498.016VS			|	ImpoC				|
		|	CONS_LATAM		|	498.018VS			|	Hair Care /Baths	|
		|	CONS_LATAM		|	498.023VS			|	Hair Care /Baths	|
		|	CONS_LATAM		|	498.024VS			|	ImpoC				|
		|	CONS_LATAM		|	498.025VS			|	ImpoC				|
		|	CONS_LATAM		|	498.027VS			|						|
		|	CONS_LATAM		|	498.028VS			|	Hair Care /Baths	|
		|	CONS_LATAM		|	498.030VS			|	Cologne/Oil			|
		|	CONS_LATAM		|	498.031VS			|	Cologne/Oil			|
		|	CONS_LATAM		|	498.160VS			|	Cologne/Oil			|
		|	CONS_LATAM		|	498.215VS			|	Toiletries BRA		|
		|	CONS_LATAM		|	498.220VS			|	Toiletries BRA		|
		|	CONS_LATAM		|	498.225VS			|	Listerne			|
		|	CONS_LATAM		|	498.250VS			|	Listerne			|
		|	CONS_LATAM		|	498.251VS			|	Listerne			|
    And I wait "/dev/plan/edm_mat_input" Async Queue complete

    When I submit task with xml file "xml/edm/EDMMaterialGlobal.xml" and execute file "jar/pangea-view.jar"

    Then I check region data "/dev/edm/material_global_v1" by keyFields "sourceSystem,localMaterialNumber"
		|	sourceSystem	|	localMaterialNumber	|	localRefDescription							|	localMaterialType	|	localBaseUom	|	materialNumber	|	refDescription									|	materialType	|	baseUom	|	localDpParentCode	|	parentCode				|	globalDpParentCode		|	form	|	category	|	subBrand	|	brand	|	franchise	|	globalBusinessUnit	|	productFamily	|	localManufacturingTechnology	|	manufacturingTechnology	|	localMaterialGroup	|	materialGroup	|	flagForDeletion	|	materialStatus	|	division	|	batchManageIndicator	|	minRemShelfLife	|	totalShelfLife	|	primaryPlanningCode	|				
		|	CONS_LATAM		|	414.740V			|	SUNDOWN REGULAR FPS 60 STARCK				|	FERT				|	ST				|	99131			|	New_SUNDOWN REGULAR FPS 60 STARCK				|	FERT			|	EA		|	8105080005140012	|	New_8105080005140012	|	GLB_8105080005140012	|	250		|	1120		|	5E			|	BRD010	|	FCH002		|	GFO001				|	prod_fam_1		|	Toothbrushes					|	manu_tech_1				|	999999				|	matl_grp_1		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-99131			|				
		|	CONS_LATAM		|	456.770S			|	J'S BABY WIPES COOLING TOUCH BIL			|	FERT				|	ST				|	97424			|	New_J'S BABY WIPES COOLING TOUCH BIL			|	FERT			|	EA		|	8105080005070012	|	New_8105080005070012	|	GLB_8105080005070012	|	100048	|	215			|	4Z			|	BRD002	|	FCH001		|	GFO001				|	prod_fam_1		|	Toothbrushes					|	manu_tech_2				|	999999				|	matl_grp_2		|	X				|	I				|	10			|	X						|	36				|	0				|	97424				|				
		|	CONS_LATAM		|	456.771S			|	SUNDOWN GOLD LOCAO FPS15 STARCK 12X120ML	|	FERT				|	ST				|					|													|					|			|	8105080005070012	|							|							|			|				|				|			|				|						|					|	Toothbrushes					|							|	999999				|					|	X				|	L				|	10			|	X						|	18				|	0				|						|				
		|	CONS_LATAM		|	456.772S			|	LISTERINE ZERO PORT 6X1,5L					|	FERT				|	ST				|	53942			|	New_LISTERINE ZERO PORT 6X1,5L					|					|	EA		|	8105080004150024	|	New_8105080004150024	|	GLB_8105080004150024	|	112245	|	5507		|	4G			|	BRD008	|	FCH003		|	GFO002				|	prod_fam_1		|	Toothbrushes					|	manu_tech_3				|	999999				|	matl_grp_3		|	X				|	L				|	10			|	X						|	180				|	0				|	ppc-53942			|				
		|	CONS_LATAM		|	456.773S			|	RoC Clarifiant Eyes 6X15G					|	FERT				|	ST				|	528207			|	New_RoC Clarifiant Eyes 6X15G					|	FERT			|	EA		|	78962124225240003	|	New_78962124225240003	|	GLB_78962124225240003	|	112122	|	132			|	5O			|	BRD003	|	FCH002		|	GFO001				|	prod_fam_1		|	ImpoC							|	manu_tech_4				|	999999				|	matl_grp_4		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-528207			|				
		|	CONS_LATAM		|	456.774S			|	SUNDOWN REGULAR FPS50 STARCK 12X120ML		|	FERT				|	ST				|	61144			|	New_SUNDOWN REGULAR FPS50 STARCK 12X120ML		|	FERT			|	EA		|	78962124225240003	|	New_78962124225240003	|	GLB_78962124225240003	|	250		|	1120		|	5E			|	BRD010	|	FCH002		|	GFO001				|	prod_fam_1		|	ImpoC							|	manu_tech_5				|	999999				|	matl_grp_5		|	X				|	L				|	10			|	X						|	180				|	0				|	ppc-61144			|				
		|	CONS_LATAM		|	456.775S			|	NTG ACNE PROOFING TONER 6X 200 ML BIL		|	FERT				|	ST				|	97489			|	New_NTG ACNE PROOFING TONER 6X 200 ML BIL		|	FERT			|			|	78962124225170036	|	New_78962124225170036	|	GLB_78962124225170036	|	100851	|	239			|	3F			|	BRD006	|	FCH002		|	GFO001				|	prod_fam_1		|	Soaps							|	manu_tech_6				|	999999				|	matl_grp_6		|	X				|	I				|	10			|	X						|	36				|	0				|	ppc-97489			|				
		|	CONS_LATAM		|	456.776S			|	SActive Triple Firming Neck Cream 2g		|	FERT				|	ST				|					|													|					|			|	78962124225170036	|							|							|			|				|				|			|				|						|					|	Soaps							|							|	999999				|					|	X				|	L				|	10			|	X						|	18				|	0				|						|				
		|	CONS_LATAM		|	456.777S			|	ROC RETIN-OX CORREXI SERUM A-RUGA 6X30ML	|	FERT				|	ST				|	56428			|	New_ROC RETIN-OX CORREXI SERUM A-RUGA 6X30ML	|	FERT			|	EA		|						|	New_78962124156180024	|	GLB_78962124156180024	|	100261	|	132			|	5O			|	BRD003	|	FCH002		|	GFO001				|	prod_fam_2		|	Soaps							|	manu_tech_8				|	999999				|	matl_grp_8		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-56428			|				
		|	CONS_LATAM		|	456.778S			|	ROC C-SUPÃ‰RIEUR CONCENTRADO 6% 6X15G		|	FERT				|	ST				|	56681			|													|	FERT			|	EA		|	78962124135600024	|	New_78962124135600024	|	GLB_78962124135600024	|	116400	|	132			|	5O			|	BRD003	|	FCH002		|	GFO001				|	prod_fam_2		|	Cologne/Oil						|	manu_tech_9				|	999999				|	matl_grp_9		|	X				|	L				|	10			|	X						|	180				|	0				|	ppc-56681			|				
		|	CONS_LATAM		|	456.779S			|	PR BAR SOAP BABY SPECIAL PRICE				|	FERT				|	ST				|	89278			|	New_PR BAR SOAP BABY SPECIAL PRICE				|	FERT			|	EA		|	78962124135600024	|							|	GLB_78888888899999990	|	100011	|	1811		|	1K			|	BRD002	|	FCH001		|	GFO001				|	prod_fam_2		|	Cologne/Oil						|	manu_tech_10			|	999999				|	matl_grp_10		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-89278			|				
		|	CONS_LATAM		|	456.781S			|	JS BABY SAB HORA DE BRINCAR NAC 72X80G		|	FERT				|	ST				|	60563			|	New_JS BABY SAB HORA DE BRINCAR NAC 72X80G		|	FERT			|	EA		|	78962124060430006	|	New_78962124060430006	|	GLB_78962124060430006	|			|	1811		|	1K			|	BRD002	|	FCH001		|	GFO001				|	prod_fam_2		|	ImpoC							|	manu_tech_11			|	999999				|	matl_grp_11		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-60563			|				
		|	CONS_LATAM		|	456.782S			|	ROC PRO CLEANSE 6X200 ML					|	FERT				|	ST				|	60072			|	New_ROC PRO CLEANSE 6X200 ML					|	FERT			|	EA		|	78920316074490012	|	New_78920316074490012	|	GLB_78920316074490012	|	100234	|				|	5O			|	BRD003	|	FCH002		|	GFO001				|	prod_fam_2		|	ImpoC							|	manu_tech_12			|	999999				|	matl_grp_12		|	X				|	L				|	10			|	X						|	1				|	0				|	ppc-60072			|				
		|	CONS_LATAM		|	498.002VS			|	LIST GUM DEFENSE 12X250ML					|	FERT				|	ST				|	527294			|	New_LIST GUM DEFENSE 12X250ML					|	FERT			|	EA		|	78920316074490012	|	New_78920316074490012	|	GLB_78920316074490012	|	101722	|	5507		|				|	BRD008	|	FCH003		|	GFO002				|	prod_fam_3		|	Toiletries BRA					|	manu_tech_13			|	999999				|	matl_grp_13		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-527294			|				
		|	CONS_LATAM		|	498.004VS			|	BABAB FLEX FABRIC XL 10â€™S					|	FERT				|	ST				|	87766			|	New_BABAB FLEX FABRIC XL 10â€™S					|	FERT			|	CA		|	78920316074320012	|	New_78920316074320012	|	GLB_78920316074320012	|	100418	|	142			|	1U			|			|	FCH003		|	GFO002				|	prod_fam_3		|	ImpoC							|	manu_tech_14			|	999999				|	matl_grp_14		|	X				|	L				|	10			|	X						|	180				|	0				|	ppc-87766			|				
		|	CONS_LATAM		|	498.012VS			|	CREME PENTEAR JS BABY HIDR INT 12X150ML		|	FERT				|	ST				|	58034			|	New_CREME PENTEAR JS BABY HIDR INT 12X150ML		|	FERT			|	EA		|	78912684040360024	|	New_78912684040360024	|	GLB_78912684040360024	|	112316	|	37			|	1N			|	BRD002	|				|	GFO001				|	prod_fam_3		|	Toiletries BRA					|	manu_tech_15			|	999999				|	matl_grp_15		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-58034			|				
		|	CONS_LATAM		|	498.013VS			|	NEUT SUN FRESH FPS 70 CORPO 6X200ML			|	FERT				|	ST				|	60209			|	New_NEUT SUN FRESH FPS 70 CORPO 6X200ML			|	FERT			|	EA		|	78912684040360024	|	New_78912684040360024	|	GLB_78912684040360024	|	112684	|	280			|	3G			|	BRD006	|	FCH002		|						|	prod_fam_3		|	Toiletries BRA					|	manu_tech_16			|	999999				|	matl_grp_16		|	X				|	L				|	10			|	X						|	1				|	0				|	ppc-60209			|				
		|	CONS_LATAM		|	498.015VS			|	J'S COTTON SWABS COTONETES - TRILINGUAL		|	FERT				|	ST				|	529391			|	New_J'S COTTON SWABS COTONETES - TRILINGUAL		|	FERT			|	EA		|	78912684040290024	|	New_78912684040290024	|	GLB_78912684040290024	|	100076	|	1068		|	2B			|	BRD032	|	FCH003		|	GFO002				|					|	Listerne						|	manu_tech_17			|	999999				|	matl_grp_17		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-529391			|				
		|	CONS_LATAM		|	498.016VS			|	NAPKIN SL UF COTTON WIN 48x8				|	FERT				|	ST				|	69515			|	New_NAPKIN SL UF COTTON WIN 48x8				|	FERT			|	EA		|	78912684040290024	|	New_78912684040290024	|	GLB_78912684040290024	|	100726	|	21			|	5G			|	BRD004	|	FCH005		|	GFO002				|	prod_fam_4		|	ImpoC							|							|	999999				|	matl_grp_18		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-69515			|				
		|	CONS_LATAM		|	498.018VS			|	P LINER CF EVERY DAY FLEXI UNSC 64X15		|	FERT				|	ST				|	87116			|	New_P LINER CF EVERY DAY FLEXI UNSC 64X15		|	FERT			|	EA		|	78912684020250024	|	New_78912684020250024	|	GLB_78912684020250024	|	100767	|	163			|	3Q			|	BRD007	|	FCH005		|	GFO002				|	prod_fam_4		|	Hair Care /Baths				|	manu_tech_1				|	999999				|					|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-87116			|				
		|	CONS_LATAM		|	498.023VS			|	PR OB MEDIO L20P16 20X20UN					|	FERT				|	ST				|	60953			|	New_PR OB MEDIO L20P16 20X20UN					|	FERT			|	EA		|	78912684020180024	|	New_78912684020180024	|	GLB_78912684020180024	|	250		|	1120		|	5E			|	BRD010	|	FCH002		|	GFO001				|	prod_fam_4		|	Hair Care /Baths				|	manu_tech_2				|	999999				|	matl_grp_1		|	X				|	L				|	10			|	X						|	1				|	0				|	ppc-60953			|				
		|	CONS_LATAM		|	498.024VS			|	COTONETES CARTRIDGE 30X150					|	FERT				|	ST				|	55883			|	New_COTONETES CARTRIDGE 30X150					|	FERT			|	EA		|	8105080004150024	|	New_8105080004150024	|	New_8105080004150024	|	100048	|	215			|	4Z			|	BRD002	|	FCH001		|	GFO001				|	prod_fam_4		|	ImpoC							|	manu_tech_3				|	999999				|	matl_grp_2		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-55883			|				
		|	CONS_LATAM		|	498.025VS			|	PR SHAMP 200ML + SAB LIQ HTT 200ML			|	FERT				|	ST				|	441505			|	New_PR SHAMP 200ML + SAB LIQ HTT 200ML			|	FERT			|	EA		|	8105080004150024	|							|							|	253		|	1120		|	5E			|	BRD010	|	FCH002		|	GFO001				|	prod_fam_4		|	ImpoC							|	manu_tech_4				|	999999				|	matl_grp_3		|	X				|	L				|	10			|	X						|	36				|	0				|	ppc-441505			|				
		|	CONS_LATAM		|	498.027VS			|	PR CURAT B-AID TRANSP L10P8 144X10UN		|	FERT				|	ST				|	87836			|	New_PR CURAT B-AID TRANSP L10P8 144X10UN		|	FERT			|	EA		|	8105080004150024	|	New_8105080004150024	|	GLB_8105080004150024	|	112245	|	5507		|	4G			|	BRD008	|	FCH003		|	GFO002				|	prod_fam_5		|									|	manu_tech_5				|	999999				|	matl_grp_4		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-87836			|				
		|	CONS_LATAM		|	498.028VS			|	COND OGX BRAZIL KERAT 6X385ML BIL			|	FERT				|	ST				|	6007700			|	New_COND OGX BRAZIL KERAT 6X385ML BIL			|	FERT			|	EA		|	8105080004150024	|	New_8105080004150024	|	GLB_8105080004150024	|	112122	|	132			|	5O			|	BRD003	|	FCH002		|	GFO001				|	prod_fam_5		|	Hair Care /Baths				|	manu_tech_6				|	999999				|	matl_grp_5		|	X				|	L				|	10			|	X						|	180				|	0				|	ppc-6007700			|				
		|	CONS_LATAM		|	498.030VS			|	J'S SOFT PELE DOS SONHOS 12X200ML			|	FERT				|	ST				|	57941			|	New_J'S SOFT PELE DOS SONHOS 12X200ML			|	FERT			|	EA		|	8105080004150024	|	New_8105080004150024	|	GLB_8105080004150024	|	250		|	1120		|	5E			|	BRD010	|	FCH002		|	GFO001				|	prod_fam_5		|	Cologne/Oil						|	manu_tech_7				|	999999				|	matl_grp_6		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-57941			|				
		|	CONS_LATAM		|	498.031VS			|	NEUT DEEP CLEAN ADSTR FACIAL 6X200ML BIL	|	FERT				|	ST				|	66983			|	New_NEUT DEEP CLEAN ADSTR FACIAL 6X200ML BIL	|	FERT			|	EA		|	78962124060430006	|	New_78962124060430006	|	GLB_78962124060430006	|	100851	|	239			|	3F			|	BRD006	|	FCH002		|	GFO001				|	prod_fam_5		|	Cologne/Oil						|	manu_tech_8				|	999999				|	matl_grp_7		|	X				|	L				|	10			|	X						|	180				|	0				|	ppc-66983			|				
		|	CONS_LATAM		|	498.160VS			|	PROT DIARIO CAREFREE PROT S/PERF 48X15UN	|	FERT				|	ST				|	58055			|	New_PROT DIARIO CAREFREE PROT S/PERF 48X15UN	|	FERT			|	EA		|	78962124225240003	|	New_78962124225240003	|	GLB_78962124225240003	|	101465	|	113			|	1A			|	BRD009	|	FCH002		|	GFO001				|	prod_fam_5		|	Cologne/Oil						|	manu_tech_9				|	999999				|	matl_grp_8		|	X				|	L				|	10			|	X						|	36				|	0				|	ppc-58055			|				
		|	CONS_LATAM		|	498.215VS			|	AVEENO SKIN RELIEF COCO BODY LOT 354mL		|	FERT				|	ST				|	88259			|	New_AVEENO SKIN RELIEF COCO BODY LOT 354mL		|	FERT			|	EA		|	78962124225240003	|	New_78962124225240003	|	GLB_78962124225240003	|	100261	|	132			|	5O			|	BRD003	|	FCH002		|	GFO001				|	prod_fam_5		|	Toiletries BRA					|	manu_tech_10			|	999999				|	matl_grp_9		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-88259			|				
		|	CONS_LATAM		|	498.220VS			|	PR ROC MI OIL CONT FP30 GR ROC C-SUP 6CJ	|	FERT				|	ST				|	60854			|	New_PR ROC MI OIL CONT FP30 GR ROC C-SUP 6CJ	|	FERT			|	EA		|	8105080004150024	|	New_8105080004150024	|	GLB_8105080004150024	|	116400	|	132			|	5O			|	BRD003	|	FCH002		|	GFO001				|	prod_fam_6		|	Toiletries BRA					|	manu_tech_11			|	999999				|	matl_grp_10		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-60854			|				
		|	CONS_LATAM		|	498.225VS			|	ABS HIG SL TRI-PR MAX SV S/ABA 4X12X8UN		|	FERT				|	ST				|	56228			|	New_ABS HIG SL TRI-PR MAX SV S/ABA 4X12X8UN		|	FERT			|	EA		|	8105080004150024	|	New_8105080004150024	|	GLB_8105080004150024	|	100011	|	1811		|	1K			|	BRD002	|	FCH001		|	GFO001				|	prod_fam_6		|	Listerne						|	manu_tech_12			|	999999				|	matl_grp_11		|	X				|	L				|	10			|	X						|	180				|	0				|	ppc-56228			|				
		|	CONS_LATAM		|	498.250VS			|	ROC MINESOL ACT UNIFY FPS60 AG 240X5G		|	FERT				|	ST				|	56322			|	New_ROC MINESOL ACT UNIFY FPS60 AG 240X5G		|	FERT			|	EA		|	8105080004150024	|	New_8105080004150024	|	GLB_8105080004150024	|	101450	|	1811		|	1K			|	BRD002	|	FCH001		|	GFO001				|	prod_fam_6		|	Listerne						|	manu_tech_13			|	999999				|	matl_grp_12		|	X				|	L				|	10			|	X						|	18				|	0				|	ppc-56322			|				
		|	CONS_LATAM		|	498.251VS			|	NTG SUNFRESH RADIANCE SPF60 200ML			|	FERT				|	ST				|	83563			|	New_NTG SUNFRESH RADIANCE SPF60 200ML			|	FERT			|	EA		|	8105080004150024	|	New_8105080004150024	|	GLB_8105080004150024	|	101450	|	1811		|	1K			|	BRD002	|	FCH001		|	GFO001				|	prod_fam_6		|	Listerne						|	manu_tech_14			|	999999				|	matl_grp_13		|	X				|	L				|	10			|	X						|	36				|	0				|	ppc-83563			|				

    Then I check region data "/dev/plan/edm_failed_data" by keyFields "functionalArea,interfaceID,errorCode,sourceSystem,key1,key2,key3,key4,key5"
      | functionalArea | interfaceID | errorCode | sourceSystem | businessArea | key1 | key2 | key3 | key4 | key5 | errorValue |

    And I compare the number of records between "/project_one/mara" and "/edm/material_global_v1,/plan/edm_failed_data"

  Scenario: delete all test data

    Then I delete the test data

    And I will remove all data with region "/dev/edm/material_global_v1"

    And I will remove all data with region "/plan/edm_failed_data"

