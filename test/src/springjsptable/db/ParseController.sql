------------------------------
-- 테이블 스키마
------------------------------
DROP TABLE TCOM1103 CASCADE CONSTRAINTS;

CREATE TABLE TCOM1103 (
FILE_NM      VARCHAR2(30)    NULL,
FILE_PATH_NM VARCHAR2(200)   NULL,
KIND         VARCHAR2(200)   NULL,
GRP_POS      INT            NULL,
POS          INT            NULL,
LEAD_POS     INT            NULL,
PAS_RSLT     VARCHAR2(4000)   NULL
);

COMMENT ON TABLE  TCOM1103            IS '소스파싱정보01';
COMMENT ON COLUMN TCOM1103.FILE_NM    IS '파일명';
COMMENT ON COLUMN TCOM1103.FILE_PATH_NM    IS '파일경로명';
COMMENT ON COLUMN TCOM1103.KIND         IS '설명';
COMMENT ON COLUMN TCOM1103.GRP_POS    IS '그룹순번';
COMMENT ON COLUMN TCOM1103.POS        IS '순번';
COMMENT ON COLUMN TCOM1103.LEAD_POS   IS '다음그룹순번';
COMMENT ON COLUMN TCOM1103.PAS_RSLT   IS '파싱결과';

ANALYZE TABLE TCOM1103 COMPUTE STATISTICS;

------------------------------
-- 테이블 기본분석
------------------------------

SELECT KIND, COUNT(*) CNT 
FROM TCOM1103
GROUP BY KIND
ORDER BY KIND
;


SELECT * 
FROM TCOM1103
WHERE 1=1 
AND UPPER(KIND) like UPPER('%JSP3%')
ORDER BY 1,2,3,4,5,6,7
;


SELECT * 
FROM TCOM1103
WHERE 1=1 
AND UPPER(KIND) like UPPER('%SQL2%')
ORDER BY 1,2,3,4,5,6,7
;

SELECT * 
FROM TCOM1103
WHERE 1=1 
AND UPPER(KIND) like UPPER('%TABLE1%')
ORDER BY 1,2,3,4,5,6,7
;


SELECT  DISTINCT PAS_RSLT 
,REGEXP_SUBSTR(PAS_RSLT,'[[:alnum:]]+(.)',1,1)
FROM TCOM1103
WHERE UPPER(KIND) like UPPER('%SQL1%')
AND LENGTH(PAS_RSLT)> 7
ORDER BY 1
;




-----------------------------------------------------------
-- parsing을 위해서 예약어는 토큰으로 만들어 질수 있도록 규칙을 가지고 있어야 한다.
-- 일반적인 SI 프로젝트에 AA가 통일성있는 개발표준을 잡고 가지 않으면 자바,SQL 파싱
-- 보다도 어려움점이 더 많을수 있다. 규칙이 완전하지 않으므로 계속된 예외처리를 해야 한다
-----------------------------------------------------------

-----------------------------------------------------------
-- 안보이는 특수문자가 썩여 있어 추출이 안되는 경우도 있다.
-----------------------------------------------------------

------------------------------
-- JSP 사용 테이블
------------------------------

INSERT INTO TCOM1103(FILE_NM,FILE_PATH_NM,KIND,GRP_POS,POS,LEAD_POS,PAS_RSLT)
WITH TEMP101 AS (
SELECT A.FILE_NM
      ,A.FILE_PATH_NM
      ,A.KIND
      ,A.PAS_RSLT AS SQLMAP_INFO
      ,B.FILE_NM AS XML_FILE_NM 
      ,B.FILE_PATH_NM AS XML_FILE_PATH_NM
      ,B.GRP_POS
      ,B.POS
      ,B.LEAD_POS
      ,B.PAS_RSLT AS TABLE_INFO
FROM   TCOM1103 A,
       TCOM1103 B
WHERE  UPPER(A.PAS_RSLT) = UPPER(B.PAS_RSLT) 
AND UPPER(A.KIND) like UPPER('%JSP3%')
AND UPPER(B.KIND) like UPPER('%SQL2%')
ORDER BY 1,2,3,4,5,6,7
),
TEMP102 AS (
SELECT A.*,B.PAS_RSLT
FROM   TEMP101 A,
       TCOM1103 B
WHERE  UPPER(A.GRP_POS) = UPPER(B.GRP_POS)
AND    B.PAS_RSLT NOT LIKE '%.%'
AND UPPER(B.KIND) like UPPER('%SQL2%')
)
SELECT  A.FILE_NM
       ,A.SQLMAP_INFO AS FILE_PATH_NM
       ,'TABLE2' KIND,NULL GRP_POS,NULL POS,NULL LEAD_POS
       ,A.PAS_RSLT AS PAS_RSLT
FROM TCOM1103 A 
WHERE UPPER(A.KIND) like UPPER('%TABLE2%')
ORDER BY 1,2,3,4 
;

/

COMMIT;
/

SELECT A.FILE_NM
      ,(SELECT MAX(MENU_NM) FROM COM040T WHERE PG_ID||'.jsp' = A.FILE_NM) MENU_NM 
      ,A.FILE_PATH_NM,A.PAS_RSLT
      ,(SELECT MAX(FILE_PATH_NM) FROM TCOM1103 WHERE KIND = 'TABLE1' AND FILE_NM = A.PAS_RSLT) TB_COMMENT 
FROM  TCOM1103 A
WHERE UPPER(A.KIND) like UPPER('%TABLE2%')
ORDER BY 1,2,3,4 

/


SELECT A.FILE_NM
      ,(SELECT MAX(MENU_NM) FROM COM040T WHERE PG_ID||'.jsp' = A.FILE_NM) MENU_NM 
      ,A.SQLMAP_INFO,A.PAS_RSLT
      ,(SELECT MAX(FILE_PATH_NM) FROM TCOM1103 WHERE KIND = 'TABLE1' AND FILE_NM = A.PAS_RSLT) TB_COMMENT 
FROM TEMP102 A 
GROUP BY A.FILE_NM,A.SQLMAP_INFO,A.PAS_RSLT
ORDER BY 1,2,3,4,5 

/


SELECT * FROM TCOM1103
;

/

SELECT * FROM COM040T;


/


SELECT * 
FROM TCOM1103
WHERE 1=1 
AND UPPER(KIND) like UPPER('%JSP3%')
ORDER BY 1,2,3,4,5,6,7
;


SELECT * 
FROM TCOM1103
WHERE 1=1 
AND UPPER(KIND) like UPPER('%TABLE1%')
ORDER BY 1,2,3,4,5,6,7
;


------------------------------
-- sqlmap 테이블 사용 빈도 분석
------------------------------

INSERT INTO TCOM1103(FILE_NM,FILE_PATH_NM,KIND,GRP_POS,POS,LEAD_POS,PAS_RSLT)
WITH TEMP301 AS (
SELECT UPPER(PAS_RSLT) TABLE_NAME,COUNT(*) CNT FROM TCOM1103
WHERE UPPER(KIND) like UPPER('%SQL1%')
AND LENGTH(PAS_RSLT) = 7
GROUP BY UPPER(PAS_RSLT)
ORDER BY UPPER(PAS_RSLT)
),
TEMP302 AS (
SELECT A.TABLE_NAME ,B.COMMENTS
FROM   SYS.ALL_TABLES A,
       SYS.ALL_TAB_COMMENTS B
WHERE  A.TABLE_NAME = B.TABLE_NAME(+)
AND    (REGEXP_LIKE(A.TABLE_NAME,'[a-zA-Z]{3}[0-9]{3}[a-zA-Z]') AND LENGTH(RTRIM(A.TABLE_NAME)) = 7)
AND    B.COMMENTS IS NOT NULL
ORDER BY A.TABLE_NAME
) 
SELECT  B.TABLE_NAME AS FILE_NM
       ,'ASAFE SQLMAP 분석 소스에서 사용하는 테이블 빈도 조사' AS FILE_PATH_NM
       ,'TABLE1' KIND,NULL GRP_POS,NULL POS,NULL LEAD_POS,A.CNT PAS_RSLT
FROM   TEMP301 A RIGHT OUTER JOIN 
       TEMP302 B
       ON A.TABLE_NAME = B.TABLE_NAME
WHERE B.TABLE_NAME LIKE 'COM%'
OR B.TABLE_NAME LIKE 'RBA%'
;

COMMIT;

------------------------------
-- JSP에서 DAO 흐름도
------------------------------
WITH TEMP201 AS (
    SELECT DISTINCT 
     A.FILE_NM       JSP_NM
    --,A.FILE_PATH_NM  JSP_PATH_NM
    ,A.POS           JSP_POS         
    ,A.PAS_RSLT      JSP_PAS_RSLT
    ,C.FILE_NM       CTRL_FILE_NM         
    ,C.FILE_PATH_NM  CTRL_PATH_NM         
    ,C.GRP_POS       CTRL_GRP_POS     
    ,C.POS           CTRL_POS         
    ,(CASE WHEN UPPER(C.PAS_RSLT) LIKE '%SERVICE.%' THEN REGEXP_SUBSTR(C.PAS_RSLT,'[[:alnum:]]+[.][[:alnum:]]+',1,1)
           --WHEN C.PAS_RSLT LIKE '%@RequestMapping%' THEN REGEXP_SUBSTR(C.PAS_RSLT,'[[:alnum:]]+[/][[:alnum:]]+[.][[:alnum:]]+',1,1)
           --WHEN UPPER(C.PAS_RSLT) LIKE '%PUBLIC %'  THEN REPLACE(REGEXP_SUBSTR(C.PAS_RSLT,'[[:alnum:]]+[(]',1,1),'(','')||'()'               
      END) AS  CTRL_PAS_RSLT1
    ,REPLACE(REGEXP_SUBSTR(C.PAS_RSLT,'[[:alnum:]]+[.]',1,1),'.','') AS CTRL_PAS_RSLT2
    ,REPLACE(REGEXP_SUBSTR(C.PAS_RSLT,'[.][[:alnum:]]+',1,1),'.','') AS CTRL_METHOD
    --,C.PAS_RSLT      PAS_RSLT2
    FROM   TCOM1103 A,
           TCOM1103 B,
           TCOM1103 C
    WHERE  A.KIND = 'JSP2'
    AND    B.KIND = 'CTRL2'
    AND    C.KIND = 'CTRL2'
    AND    B.PAS_RSLT LIKE '%'||A.PAS_RSLT||'%'
    AND    B.FILE_NM = C.FILE_NM 
    AND    B.GRP_POS = C.GRP_POS
    --AND    UPPER(C.PAS_RSLT) LIKE '%SERVICE.%'
    --AND    A.FILE_NM = 'RBA101MI00.jsp'
    ORDER BY 1,2,3,4,5
),
TEMP202 AS (
    SELECT DISTINCT 
           FILE_NM
          ,PAS_RSLT
          ,TRIM(REGEXP_SUBSTR(PAS_RSLT,'( )[[:alnum:]]+( )')) PAS_RSLT2       
          ,TRIM(REPLACE(REGEXP_SUBSTR(PAS_RSLT,'( )[[:alnum:]]+(;)'),';','')) PAS_RSLT3       
    FROM   TCOM1103
    WHERE KIND = 'CTRL1'
    --AND UPPER(PAS_RSLT) LIKE '%SERVICE%'
    --AND  REGEXP_LIKE(PAS_RSLT,'(?i)(private|public)+[[:alnum:]]+[[:alnum:]]+[;]')
    AND  REGEXP_LIKE(PAS_RSLT,'private')
    AND  UPPER(PAS_RSLT) LIKE UPPER('%service%')
    ORDER BY 1,2
),
TEMP203 AS ( 
    SELECT A.*,B.PAS_RSLT2||'Impl.java' AS SRV_FILE_NM 
    FROM   TEMP201 A,
           TEMP202 B
    WHERE  A.CTRL_FILE_NM = B.FILE_NM 
    AND    A.CTRL_PAS_RSLT2 = B.PAS_RSLT3
    AND    A.CTRL_PAS_RSLT1 > ' '
),
TEMP204 AS ( 
    SELECT DISTINCT 
      A.*
     ,B.GRP_POS
     ,B.POS
     ,B.PAS_RSLT
    ,CASE WHEN UPPER(C.PAS_RSLT) LIKE '%DAO%' THEN REGEXP_SUBSTR(C.PAS_RSLT,'[[:alnum:]]+[.][[:alnum:]]+',1,1) END SRV_PAS_RSLT1
    ,REPLACE(REGEXP_SUBSTR(C.PAS_RSLT,'[[:alnum:]]+[.]',1,1),'.','') AS SRV_PAS_RSLT2
    ,REPLACE(REGEXP_SUBSTR(C.PAS_RSLT,'[.][[:alnum:]]+',1,1),'.','') AS SRV_METHOD         
    --,C.PAS_RSLT      PAS_RSLT2
    FROM   TEMP203 A,
           TCOM1103 B,
           TCOM1103 C
    WHERE  B.KIND = 'SRV2'
    AND    B.PAS_RSLT LIKE '%'||A.CTRL_METHOD||'(%'
    AND    B.PAS_RSLT LIKE '%public%'
    AND    C.KIND = 'SRV2'
    AND    B.FILE_NM = C.FILE_NM 
    AND    B.GRP_POS = C.GRP_POS
    --AND    UPPER(C.PAS_RSLT) LIKE '%SERVICE.%'
    ORDER BY 1,2,3,4,5
),
TEMP205 AS (
    SELECT DISTINCT 
           FILE_NM
          ,PAS_RSLT
          ,TRIM(REGEXP_SUBSTR(PAS_RSLT,'( )[[:alnum:]]+( )')) DAO_CLASS       
          ,TRIM(REPLACE(REGEXP_SUBSTR(PAS_RSLT,'( )[[:alnum:]]+(;)'),';','')) DAO_METHOD       
    FROM   TCOM1103
    WHERE KIND = 'SRV1'
    AND  REGEXP_LIKE(PAS_RSLT,'private')
    AND  UPPER(PAS_RSLT) LIKE UPPER('%DAO%')
    ORDER BY 1,2
),
TEMP206 AS (
    SELECT DISTINCT
           A.JSP_NM
          ,A.JSP_POS         
            ,A.JSP_PAS_RSLT
            ,A.CTRL_FILE_NM         
            ,A.CTRL_POS         
            ,A.CTRL_PAS_RSLT1
            ,A.SRV_FILE_NM
            ,A.SRV_PAS_RSLT1
            ,B.DAO_CLASS
    FROM   TEMP204 A,
           TEMP205 B
    WHERE  UPPER(A.SRV_FILE_NM) = UPPER(B.FILE_NM) 
    AND    TRIM(A.SRV_PAS_RSLT2) = TRIM(B.DAO_METHOD)
    AND    A.SRV_PAS_RSLT1 > ' '
) 
SELECT * FROM TEMP206
--WHERE JSP_NM = 'RBA101MI00.jsp'
ORDER BY 1,2,3,4
;


------------------------------
-- SRV1 추출
------------------------------
--INSERT INTO TCOM1103(FILE_NM,FILE_PATH_NM,KIND,GRP_POS,POS,LEAD_POS,PAS_RSLT)
WITH TEMP101 AS (
    --[RequestMapping]이 들어간 라인을 표시한다.  
    SELECT A.*
    ,RANK() OVER (PARTITION BY FILE_NM ORDER BY FILE_NM,POS) as RK 
    FROM (
        SELECT 
         FILE_NM
        ,POS
        ,PAS_RSLT 
        ,CASE WHEN UPPER(PAS_RSLT) LIKE UPPER('%public%') THEN 1 ELSE 0 END AS REQ01 
        FROM TCOM1103
        WHERE FILE_NM IN (SELECT FILE_NM FROM TCOM1103 WHERE KIND = 'SRV1' AND UPPER(PAS_RSLT) LIKE UPPER('%dao%'))
        AND KIND = 'SRV1'
        ORDER BY FILE_NM,POS,PAS_RSLT
        ) A
    WHERE REQ01 = 1
    ORDER BY FILE_NM,POS,PAS_RSLT
),
TEMP102 AS (
    --[RequestMapping]이 들어간 다임 라인을 표시한다.
    SELECT
     A.FILE_NM
    ,A.POS
    ,LEAD(A.POS) OVER (ORDER BY A.FILE_NM,A.POS) LEAD_POS
    ,A.RK
    ,LEAD(A.RK) OVER (ORDER BY A.FILE_NM,A.RK) LEAD_RK 
    ,A.PAS_RSLT 
    FROM TEMP101 A
),
TEMP103 AS (
    --[RequestMapping] 자기 위치와 다음 [RequestMapping] 위치로 동일 로우에 나열한다
    SELECT 
     A.FILE_NM
    ,A.POS
    ,(CASE WHEN A.RK = A.LEAD_RK THEN A.POS+1000000 
           WHEN A.RK = B.MAX_RK THEN A.POS+1000000 
           ELSE A.LEAD_POS END)  LEAD_POS
    ,A.RK
    ,A.LEAD_RK 
    ,A.PAS_RSLT 
    FROM TEMP102 A,(SELECT FILE_NM, MAX(RK) MAX_RK FROM TEMP102 GROUP BY FILE_NM) B
    WHERE A.FILE_NM = B.FILE_NM
),
TEMP104 AS (
    -- 원본 테이블과 조인하여 필요한 필드를 가져온[RequestMapping] 자기 위치와 다음 [RequestMapping] 위치로 동일 로우에 나열한다
    SELECT DISTINCT A.* 
    FROM (
        SELECT A.FILE_NM,A.FILE_PATH_NM,A.KIND,B.POS GRP_POS,A.POS,B.LEAD_POS,A.PAS_RSLT
        FROM TCOM1103 A, TEMP103 B
        WHERE A.FILE_NM = B.FILE_NM
        AND   A.POS BETWEEN B.POS AND B.LEAD_POS
        AND   A.FILE_NM IN (SELECT FILE_NM FROM TCOM1103 WHERE KIND = 'SRV1' AND UPPER(PAS_RSLT) LIKE UPPER('%dao%'))
        AND   A.KIND = 'SRV1'
        AND   UPPER(A.PAS_RSLT) LIKE UPPER('%public%')
        UNION ALL
        SELECT A.FILE_NM,A.FILE_PATH_NM,A.KIND,B.POS GRP_POS,A.POS,B.LEAD_POS,A.PAS_RSLT
        FROM TCOM1103 A, TEMP103 B
        WHERE A.FILE_NM = B.FILE_NM
        AND   A.KIND = 'SRV1'
        AND   A.POS BETWEEN B.POS AND B.LEAD_POS
        AND   UPPER(A.PAS_RSLT) LIKE UPPER('%dao%')
    ) A
    ORDER BY A.FILE_NM,A.POS
),
TEMP105 AS (
    -- [Service] 존재하는 Method만 출력한다.
    SELECT DISTINCT A.FILE_NM,A.FILE_PATH_NM,'SRV2' KIND,A.GRP_POS,A.POS,A.LEAD_POS,A.PAS_RSLT
    FROM  TEMP104 A,
         (SELECT FILE_NM,GRP_POS FROM TEMP104 WHERE KIND = 'SRV1' AND UPPER(PAS_RSLT) LIKE UPPER('%dao%')) B
    WHERE A.FILE_NM = B.FILE_NM
    AND   A.KIND = 'SRV1'
    AND A.GRP_POS = B.GRP_POS 
) 
SELECT * FROM TEMP105
---WHERE FILE_NM = 'Com104Controller.java'
ORDER BY 1,2,3,4,5
;

------------------------------
-- CTRL2 추출
------------------------------


--CREATE TABLE TEMP104 AS
    INSERT INTO TCOM1103(FILE_NM,FILE_PATH_NM,KIND,GRP_POS,POS,LEAD_POS,PAS_RSLT)
WITH TEMP101 AS (
    --[RequestMapping]이 들어간 라인을 표시한다.  
    SELECT A.*
    ,RANK() OVER (PARTITION BY FILE_NM ORDER BY FILE_NM,POS) as RK 
    FROM (
        SELECT 
         FILE_NM
        ,POS
        ,PAS_RSLT 
        ,CASE WHEN UPPER(PAS_RSLT) LIKE UPPER('%RequestMapping%') THEN 1 ELSE 0 END AS REQ01 
        FROM TCOM1103
        WHERE FILE_NM IN (SELECT FILE_NM FROM TCOM1103 WHERE UPPER(PAS_RSLT) LIKE UPPER('%Service%'))
        ORDER BY FILE_NM,POS,PAS_RSLT
        ) A
    WHERE REQ01 = 1
    ORDER BY FILE_NM,POS,PAS_RSLT
),
TEMP102 AS (
    --[RequestMapping]이 들어간 다임 라인을 표시한다.
    SELECT
     A.FILE_NM
    ,A.POS
    ,LEAD(A.POS) OVER (ORDER BY A.FILE_NM,A.POS) LEAD_POS
    ,A.RK
    ,LEAD(A.RK) OVER (ORDER BY A.FILE_NM,A.RK) LEAD_RK 
    ,A.PAS_RSLT 
    FROM TEMP101 A
),
TEMP103 AS (
    --[RequestMapping] 자기 위치와 다음 [RequestMapping] 위치로 동일 로우에 나열한다
    SELECT 
     A.FILE_NM
    ,A.POS
    ,(CASE WHEN A.RK = A.LEAD_RK THEN A.POS+1000000 
           WHEN A.RK = B.MAX_RK THEN A.POS+1000000 
           ELSE A.LEAD_POS END)  LEAD_POS
    ,A.RK
    ,A.LEAD_RK 
    ,A.PAS_RSLT 
    FROM TEMP102 A,(SELECT FILE_NM, MAX(RK) MAX_RK FROM TEMP102 GROUP BY FILE_NM) B
    WHERE A.FILE_NM = B.FILE_NM
),
TEMP104 AS (
    -- 원본 테이블과 조인하여 필요한 필드를 가져온[RequestMapping] 자기 위치와 다음 [RequestMapping] 위치로 동일 로우에 나열한다
    SELECT A.* 
    FROM (
        SELECT A.FILE_NM,A.FILE_PATH_NM,A.KIND,B.POS GRP_POS,A.POS,B.LEAD_POS,A.PAS_RSLT
        FROM TCOM1103 A, TEMP103 B
        WHERE A.FILE_NM = B.FILE_NM
        AND   A.POS = B.POS
        AND   A.FILE_NM IN (SELECT FILE_NM FROM TCOM1103 WHERE UPPER(PAS_RSLT) LIKE UPPER('%Service%'))
        UNION ALL
        SELECT A.FILE_NM,A.FILE_PATH_NM,A.KIND,B.POS GRP_POS,A.POS,B.LEAD_POS,A.PAS_RSLT
        FROM TCOM1103 A, TEMP103 B
        WHERE A.FILE_NM = B.FILE_NM
        AND   A.POS BETWEEN B.POS AND B.LEAD_POS
        AND   A.FILE_NM IN (SELECT FILE_NM FROM TCOM1103 WHERE UPPER(PAS_RSLT) LIKE UPPER('%Service%'))
        AND   UPPER(A.PAS_RSLT) LIKE UPPER('%public%')
        UNION ALL
        SELECT A.FILE_NM,A.FILE_PATH_NM,A.KIND,B.POS GRP_POS,A.POS,B.LEAD_POS,A.PAS_RSLT
        FROM TCOM1103 A, TEMP103 B
        WHERE A.FILE_NM = B.FILE_NM
        AND   A.POS BETWEEN B.POS AND B.LEAD_POS
        AND   UPPER(A.PAS_RSLT) LIKE UPPER('%Service%')
    ) A
    ORDER BY A.FILE_NM,A.POS
),
TEMP105 AS (
    -- [Service] 존재하는 Method만 출력한다.
    SELECT A.FILE_NM,A.FILE_PATH_NM,'CTRL2' KIND,A.GRP_POS,A.POS,A.LEAD_POS,A.PAS_RSLT
    FROM  TEMP104 A,
         (SELECT FILE_NM,GRP_POS FROM TEMP104 WHERE UPPER(PAS_RSLT) LIKE UPPER('%Service%')) B
    WHERE A.FILE_NM = B.FILE_NM
    AND A.GRP_POS = B.GRP_POS 
) 
SELECT * FROM TEMP105
---WHERE FILE_NM = 'Com104Controller.java'
ORDER BY 1,2,3,4,5
/
SELECT * FROM TEMP105
--WHERE UPPER(PAS_RSLT) LIKE UPPER('%Service%')
ORDER BY 1,2,3,4,5,6
;




SELECT * FROM TCOM1103
WHERE 1=1
AND KIND = 'SQL1'


/


------------------------------
-- 테이블 추출
------------------------------

INSERT INTO TCOM1103(FILE_NM,FILE_PATH_NM,KIND,GRP_POS,POS,LEAD_POS,PAS_RSLT)
WITH TEMP101 AS (
    -- 그룹기준 라인을 표시한다.  
    SELECT A.*
    ,RANK() OVER (PARTITION BY FILE_NM ORDER BY FILE_NM,POS) as RK 
    FROM (
        SELECT 
         FILE_NM
        ,POS
        ,PAS_RSLT 
        ,CASE WHEN PAS_RSLT LIKE '%.%' THEN 1 ELSE 0 END AS REQ01 
        FROM TCOM1103
        WHERE 1=1
        AND KIND = 'SQL1'
        --AND FILE_NM = 'RBA101_SQL.xml'
        ORDER BY FILE_NM,POS,PAS_RSLT
        ) A
    WHERE REQ01 = 1
    ORDER BY FILE_NM,POS,PAS_RSLT
),
TEMP102 AS (
    --그룹기준의 다음그룹기준 라인을 표시한다.
    SELECT
     A.FILE_NM
    ,A.POS
    ,LEAD(A.POS) OVER (ORDER BY A.FILE_NM,A.POS) LEAD_POS
    ,A.RK
    ,LEAD(A.RK) OVER (ORDER BY A.FILE_NM,A.RK) LEAD_RK 
    ,A.PAS_RSLT 
    FROM TEMP101 A
),
TEMP103 AS (
    -- 그룹기준 자기 위치와 다음그룹기준 위치로 동일 로우에 나열한다
    SELECT 
     A.FILE_NM
    ,A.POS
    ,(CASE WHEN A.RK = A.LEAD_RK THEN A.POS+1000000 
           WHEN A.RK = B.MAX_RK THEN A.POS+1000000 
           ELSE A.LEAD_POS END)  LEAD_POS
    ,A.RK
    ,A.LEAD_RK 
    ,A.PAS_RSLT 
    FROM TEMP102 A,(SELECT FILE_NM, MAX(RK) MAX_RK FROM TEMP102 GROUP BY FILE_NM) B
    WHERE A.FILE_NM = B.FILE_NM
),
TEMP104 AS (
    -- 원본 테이블과 조인하여 필요한 필드를 가져온다 
    SELECT DISTINCT A.* 
    FROM (
        SELECT A.FILE_NM,A.FILE_PATH_NM,A.KIND,B.POS GRP_POS,A.POS,B.LEAD_POS,CASE WHEN LENGTH(A.PAS_RSLT) = 7 THEN UPPER(A.PAS_RSLT) ELSE A.PAS_RSLT END PAS_RSLT
        FROM TCOM1103 A, TEMP103 B
        WHERE A.FILE_NM = B.FILE_NM
        AND   A.POS BETWEEN B.POS AND B.LEAD_POS
        AND   A.KIND = 'SQL1'
    ) A
    ORDER BY A.FILE_NM,A.POS
),
TEMP105 AS (
    -- 저장 필드로 구성한다.
    SELECT A.FILE_NM,A.FILE_PATH_NM,'SQL2' KIND,A.GRP_POS,A.POS,A.LEAD_POS,A.PAS_RSLT
    FROM  TEMP104 A,
         (SELECT FILE_NM,GRP_POS FROM TEMP104 GROUP BY FILE_NM,GRP_POS HAVING COUNT(*) > 1) B
    WHERE A.FILE_NM = B.FILE_NM
    AND   A.KIND = 'SQL1'
    AND A.GRP_POS = B.GRP_POS 
) 
SELECT * FROM TEMP105
---WHERE FILE_NM = 'Com104Controller.java'
ORDER BY 1,2,3,4,5
;


COMMIT;

