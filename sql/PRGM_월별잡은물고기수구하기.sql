SELECT COUNT(*) AS FISH_COUNT, MONTH(TIME) AS MONTH
FROM FISH_INFO
GROUP BY MONTH(TIME) -- GROUP BY 절에서는 SELECT 절과는 달리 ALIAS 명을 사용 불가
ORDER BY MONTH; 