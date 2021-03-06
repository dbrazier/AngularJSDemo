--------------------------------------------------------
--  DDL for Table PCA_WORK
--------------------------------------------------------

  CREATE TABLE "MOSAIC"."PCA_WORK"
   (	"PYID" VARCHAR2(32 CHAR),
	"PZINSKEY" VARCHAR2(255 CHAR),
	"BRANCH" VARCHAR2(64 CHAR),
	"ACCOUNTNO" VARCHAR2(64 CHAR),
	"ACCOUNTNAME" VARCHAR2(150 CHAR),
	"BASECURRENCY" VARCHAR2(3 CHAR),
	"BASENUMBER" VARCHAR2(64 CHAR),
	"GFCID" VARCHAR2(64 CHAR),
	"GFCNAME" VARCHAR2(64 CHAR),
	"GFPID" VARCHAR2(64 CHAR),
	"GFCPARENTNAME" VARCHAR2(64 CHAR),
	"WORLDLINKCLIENTID" VARCHAR2(64 CHAR),
	"WORLDLINKGCNCODE" VARCHAR2(64 CHAR),
	"CLIENTID" VARCHAR2(100 CHAR),
	"SAFEWORDID" VARCHAR2(100 CHAR),
	"SERVICEMODEL" VARCHAR2(30 CHAR),
	"TIER" VARCHAR2(64 CHAR),
	"CLIENTSECTOR" VARCHAR2(64 CHAR),
	"PXCREATEDATETIME" DATE,
	"PXUPDATEDATETIME" DATE,
	"PYLABEL" VARCHAR2(64 CHAR),
	"PYSLANAME" VARCHAR2(32 CHAR),
	"PYSLADEADLINE" DATE,
	"PYSTATUSWORK" VARCHAR2(32 CHAR),
	"CONTACTNAME" VARCHAR2(100 CHAR),
	"CASEOWNER" VARCHAR2(128 CHAR),
	"CASEOWNERNAME" VARCHAR2(64 CHAR),
	"INQUIRYTYPES" VARCHAR2(64 CHAR),
	"PXUPDATEOPERATOR" VARCHAR2(128 CHAR)
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into MOSAIC.PCA_WORK
SET DEFINE OFF;
Insert into MOSAIC.PCA_WORK (PYID,PZINSKEY,BRANCH,ACCOUNTNO,ACCOUNTNAME,BASECURRENCY,BASENUMBER,GFCID,GFCNAME,GFPID,GFCPARENTNAME,WORLDLINKCLIENTID,WORLDLINKGCNCODE,CLIENTID,SAFEWORDID,SERVICEMODEL,TIER,CLIENTSECTOR,PXCREATEDATETIME,PXUPDATEDATETIME,PYLABEL,PYSLANAME,PYSLADEADLINE,PYSTATUSWORK,CONTACTNAME,CASEOWNER,CASEOWNERNAME,INQUIRYTYPES,PXUPDATEOPERATOR) values ('S-160929-000117','-WORK-CPM S-160929-000117','100','103180015','GALAXIAS TOYRIST   OIKODOM  A E','EUR','103180','1013495994','FEXCO CORPORATE PAYMENTS','1005738292','GROUP HOLDINGS (FEXCO)',null,null,null,null,'Standard','Silver',null,to_date('29-SEP-16','DD-MON-RR'),to_date('29-SEP-16','DD-MON-RR'),'General Service Item','3Days',to_date('04-OCT-16','DD-MON-RR'),'Pending','DRRUMASHFKASJFLK, TESTE/Accounting Manager/37081606/N /YV@.COM','MANAGERCASHSECALL1','Manager Cash and Securities All Branches','FX Deal Amendment Request','MANAGERCASHSECALL1');
Insert into MOSAIC.PCA_WORK (PYID,PZINSKEY,BRANCH,ACCOUNTNO,ACCOUNTNAME,BASECURRENCY,BASENUMBER,GFCID,GFCNAME,GFPID,GFCPARENTNAME,WORLDLINKCLIENTID,WORLDLINKGCNCODE,CLIENTID,SAFEWORDID,SERVICEMODEL,TIER,CLIENTSECTOR,PXCREATEDATETIME,PXUPDATEDATETIME,PYLABEL,PYSLANAME,PYSLADEADLINE,PYSTATUSWORK,CONTACTNAME,CASEOWNER,CASEOWNERNAME,INQUIRYTYPES,PXUPDATEOPERATOR) values ('I-160929-000127','-WORK-CPM-INTERACTION I-160929-000127','100',null,'GALAXIAS TOYRIST   OIKODOM  A E',null,'103180','1013495994',null,'1005738292',null,null,null,null,null,null,null,null,to_date('29-SEP-16','DD-MON-RR'),to_date('29-SEP-16','DD-MON-RR'),'Phone Call','8Hours',to_date('29-SEP-16','DD-MON-RR'),'Resolved-Completed',null,'MANAGERCASHSECALL1','Manager Cash and Securities All Branches','Initiated','MANAGERCASHSECALL1');
Insert into MOSAIC.PCA_WORK (PYID,PZINSKEY,BRANCH,ACCOUNTNO,ACCOUNTNAME,BASECURRENCY,BASENUMBER,GFCID,GFCNAME,GFPID,GFCPARENTNAME,WORLDLINKCLIENTID,WORLDLINKGCNCODE,CLIENTID,SAFEWORDID,SERVICEMODEL,TIER,CLIENTSECTOR,PXCREATEDATETIME,PXUPDATEDATETIME,PYLABEL,PYSLANAME,PYSLADEADLINE,PYSTATUSWORK,CONTACTNAME,CASEOWNER,CASEOWNERNAME,INQUIRYTYPES,PXUPDATEOPERATOR) values ('S-160929-000001','-WORK-CPM S-160929-000001','100','103180015','GALAXIAS TOYRIST   OIKODOM  A E','EUR','103180','1013495994','FEXCO CORPORATE PAYMENTS','1005738292','GROUP HOLDINGS (FEXCO)',null,null,null,null,'Standard','Silver',null,to_date('29-SEP-16','DD-MON-RR'),to_date('29-SEP-16','DD-MON-RR'),'General Service Item','3Days',to_date('04-OCT-16','DD-MON-RR'),'Pending','15~KS, KAVYA/Accounting Manager/37079821/N /','EBSSriLankaSvcCases','EBS Sri Lanka Svc Cases','Rate Inquiry','MANAGERSFS');
Insert into MOSAIC.PCA_WORK (PYID,PZINSKEY,BRANCH,ACCOUNTNO,ACCOUNTNAME,BASECURRENCY,BASENUMBER,GFCID,GFCNAME,GFPID,GFCPARENTNAME,WORLDLINKCLIENTID,WORLDLINKGCNCODE,CLIENTID,SAFEWORDID,SERVICEMODEL,TIER,CLIENTSECTOR,PXCREATEDATETIME,PXUPDATEDATETIME,PYLABEL,PYSLANAME,PYSLADEADLINE,PYSTATUSWORK,CONTACTNAME,CASEOWNER,CASEOWNERNAME,INQUIRYTYPES,PXUPDATEOPERATOR) values ('I-160929-000002','-WORK-CPM-INTERACTION I-160929-000002','100',null,'GALAXIAS TOYRIST   OIKODOM  A E',null,'103180','1013495994',null,'1005738292',null,null,null,null,null,null,null,null,to_date('29-SEP-16','DD-MON-RR'),to_date('29-SEP-16','DD-MON-RR'),'Phone Call','8Hours',to_date('29-SEP-16','DD-MON-RR'),'Resolved-Completed',null,'MANAGERCASHSECALL1','Manager Cash and Securities All Branches','Initiated','MANAGERCASHSECALL1');
Insert into MOSAIC.PCA_WORK (PYID,PZINSKEY,BRANCH,ACCOUNTNO,ACCOUNTNAME,BASECURRENCY,BASENUMBER,GFCID,GFCNAME,GFPID,GFCPARENTNAME,WORLDLINKCLIENTID,WORLDLINKGCNCODE,CLIENTID,SAFEWORDID,SERVICEMODEL,TIER,CLIENTSECTOR,PXCREATEDATETIME,PXUPDATEDATETIME,PYLABEL,PYSLANAME,PYSLADEADLINE,PYSTATUSWORK,CONTACTNAME,CASEOWNER,CASEOWNERNAME,INQUIRYTYPES,PXUPDATEOPERATOR) values ('I-160929-000001','-WORK-CPM-INTERACTION I-160929-000001','100',null,'GALAXIAS TOYRIST   OIKODOM  A E',null,'103180','1013495994',null,'1005738292',null,null,null,null,null,null,null,null,to_date('29-SEP-16','DD-MON-RR'),to_date('29-SEP-16','DD-MON-RR'),'Phone Call','8Hours',to_date('29-SEP-16','DD-MON-RR'),'Resolved-Completed',null,'MANAGERCASHSECALL1','Manager Cash and Securities All Branches','Initiated','MANAGERCASHSECALL1');
Insert into MOSAIC.PCA_WORK (PYID,PZINSKEY,BRANCH,ACCOUNTNO,ACCOUNTNAME,BASECURRENCY,BASENUMBER,GFCID,GFCNAME,GFPID,GFCPARENTNAME,WORLDLINKCLIENTID,WORLDLINKGCNCODE,CLIENTID,SAFEWORDID,SERVICEMODEL,TIER,CLIENTSECTOR,PXCREATEDATETIME,PXUPDATEDATETIME,PYLABEL,PYSLANAME,PYSLADEADLINE,PYSTATUSWORK,CONTACTNAME,CASEOWNER,CASEOWNERNAME,INQUIRYTYPES,PXUPDATEOPERATOR) values ('S-160919-000005','-WORK-CPM S-160919-000005','100','103180015','GALAXIAS TOYRIST   OIKODOM  A E','EUR','103180','1013495994','FEXCO CORPORATE PAYMENTS','1005738292','GROUP HOLDINGS (FEXCO)',null,null,null,null,'Standard','Access',null,to_date('19-SEP-16','DD-MON-RR'),to_date('19-SEP-16','DD-MON-RR'),'General Service Item','3Days',to_date('22-SEP-16','DD-MON-RR'),'Pending','1~AHMEDTESTFF, NISHATESTFF/Accounting Manager/37081561/N /','AM00003','AM00003','Rate Inquiry','MANAGERCASHSECALL1');
--------------------------------------------------------
--  Constraints for Table PCA_WORK
--------------------------------------------------------

  ALTER TABLE "MOSAIC"."PCA_WORK" MODIFY ("PZINSKEY" NOT NULL ENABLE);

--------------------------------------------------------
--  DDL for Table PC_DATA_WORKATTACH_NOTE
--------------------------------------------------------

  CREATE TABLE "MOSAIC"."PC_DATA_WORKATTACH_NOTE"
   (	"PXCREATEDATETIME" DATE,
	"PXCREATEOPNAME" VARCHAR2(256 CHAR),
	"PXCREATEOPERATOR" VARCHAR2(256 CHAR),
	"PXCREATESYSTEMID" VARCHAR2(64 CHAR),
	"PXINSNAME" VARCHAR2(256 CHAR),
	"PXOBJCLASS" VARCHAR2(192 CHAR),
	"PXUPDATEDATETIME" DATE,
	"PXUPDATEOPNAME" VARCHAR2(256 CHAR),
	"PXUPDATEOPERATOR" VARCHAR2(256 CHAR),
	"PXUPDATESYSTEMID" VARCHAR2(64 CHAR),
	"PYLABEL" VARCHAR2(128 CHAR),
	"PZINSKEY" VARCHAR2(510 CHAR),
	"NOTETEXT" VARCHAR2(256 CHAR),
	"CUSTOMERPERCEPTIBLEINDICATOR" VARCHAR2(10 CHAR),
	"FILETYPE" VARCHAR2(64 CHAR),
	"MSGCASEID" VARCHAR2(512 CHAR),
	"PXREFOBJECTKEY" VARCHAR2(512 CHAR),
	"PXATTACHEDBY" VARCHAR2(512 CHAR),
	"PXATTACHKEY" VARCHAR2(512 CHAR),
	"PYRESOLVEDTIMESTAMP" DATE,
	"UPDATEDATETIME" DATE DEFAULT SYSDATE
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into MOSAIC.PC_DATA_WORKATTACH_NOTE
SET DEFINE OFF;
Insert into MOSAIC.PC_DATA_WORKATTACH_NOTE (PXCREATEDATETIME,PXCREATEOPNAME,PXCREATEOPERATOR,PXCREATESYSTEMID,PXINSNAME,PXOBJCLASS,PXUPDATEDATETIME,PXUPDATEOPNAME,PXUPDATEOPERATOR,PXUPDATESYSTEMID,PYLABEL,PZINSKEY,NOTETEXT,CUSTOMERPERCEPTIBLEINDICATOR,FILETYPE,MSGCASEID,PXREFOBJECTKEY,PXATTACHEDBY,PXATTACHKEY,PYRESOLVEDTIMESTAMP,UPDATEDATETIME) values (to_date('29-SEP-16','DD-MON-RR'),null,null,null,null,null,null,null,null,null,null,'-WORK-CPM S-160919-000005','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla venenatis mollis convallis. Fusce porttitor orci diam, eu auctor tellus aliquam nec. Integer id odio at lectus rhoncus blandit. Etiam justo turpis, lobortis at leo eu, rhoncus interdum risus.',null,null,null,'-WORK-CPM S-160919-000005',null,null,null,to_date('30-SEP-16','DD-MON-RR'));
Insert into MOSAIC.PC_DATA_WORKATTACH_NOTE (PXCREATEDATETIME,PXCREATEOPNAME,PXCREATEOPERATOR,PXCREATESYSTEMID,PXINSNAME,PXOBJCLASS,PXUPDATEDATETIME,PXUPDATEOPNAME,PXUPDATEOPERATOR,PXUPDATESYSTEMID,PYLABEL,PZINSKEY,NOTETEXT,CUSTOMERPERCEPTIBLEINDICATOR,FILETYPE,MSGCASEID,PXREFOBJECTKEY,PXATTACHEDBY,PXATTACHKEY,PYRESOLVEDTIMESTAMP,UPDATEDATETIME) values (to_date('29-SEP-16','DD-MON-RR'),null,null,null,null,null,null,null,null,null,null,'-WORK-CPM S-160929-0001175','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla venenatis mollis convallis. Fusce porttitor orci diam, eu auctor tellus aliquam nec. Integer id odio at lectus rhoncus blandit. Etiam justo turpis, lobortis at leo eu, rhoncus interdum risus.',null,null,null,'-WORK-CPM S-160929-000117',null,null,null,to_date('30-SEP-16','DD-MON-RR'));
--------------------------------------------------------
--  Constraints for Table PC_DATA_WORKATTACH_NOTE
--------------------------------------------------------

  ALTER TABLE "MOSAIC"."PC_DATA_WORKATTACH_NOTE" MODIFY ("PZINSKEY" NOT NULL ENABLE);
