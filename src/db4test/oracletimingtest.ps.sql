DECLARE
  starttime TIMESTAMP;
  endtime TIMESTAMP;
  results NUMBER;
BEGIN
  dbms_output.enable;
  starttime := SYSTIMESTAMP;
  SELECT COUNT( ol.location_id)--,
 -- l.latitude,
 -- l.longitude,
 -- OXYGEN_TANK_ID AS "Tank ID",
 -- o.percent_full AS "O2 level % "
INTO results
FROM oxygen_location ol,
  location l,
  oxygen_tank o
WHERE EXISTS
  (SELECT DISTINCT id,
    percent_full
  FROM oxygen_tank
  WHERE o.PERCENT_FULL <= 50.0
  AND o.DATE_INSPECTED  < TRUNC(sysdate) - 7
  AND ol.OXYGEN_TANK_ID = o.ID
  AND l.location_id     = ol.LOCATION_ID
  ) ;
  endtime := SYSTIMESTAMP;
  DBMS_OUTPUT.put_line('Total time to execute query was:');
  DBMS_OUTPUT.put_line(endtime - starttime);
END;


--RESULTS: 279 nanoseconds
