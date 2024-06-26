SELECT E1.ID AS ID, COUNT(E2.ID) AS CHILD_COUNT
FROM ECOLI_DATA E1 LEFT OUTER JOIN ECOLI_DATA E2 -- 자식 없는 ID도 출력하기 위해 LEFT OUTER JOIN 사용
ON E1.ID = E2.PARENT_ID -- ID별 자식 구해야 함
GROUP BY ID
ORDER BY ID;