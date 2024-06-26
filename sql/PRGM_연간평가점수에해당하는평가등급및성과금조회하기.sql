WITH HR_EMPLOYEES_WITH_GRADE AS(
    SELECT T1.EMP_NO, T1.EMP_NAME, CASE
                                    WHEN AVG(T2.SCORE) >= 96 THEN 'S'
                                    WHEN AVG(T2.SCORE) >= 90 THEN 'A'
                                    WHEN AVG(T2.SCORE) >= 80 THEN 'B'
                                    ELSE 'C'
                                   END AS GRADE
                                 , CASE
                                    WHEN AVG(T2.SCORE) >= 96 THEN T1.SAL * 0.2
                                    WHEN AVG(T2.SCORE) >= 90 THEN T1.SAL * 0.15
                                    WHEN AVG(T2.SCORE) >= 80 THEN T1.SAL * 0.1
                                    ELSE T1.SAL * 0
                                   END AS BONUS
    FROM HR_EMPLOYEES T1 JOIN HR_GRADE T2
    ON T1.EMP_NO = T2.EMP_NO
    GROUP BY T1.EMP_NO, T1.EMP_NAME
)

SELECT EMP_NO, EMP_NAME, GRADE, BONUS
FROM HR_EMPLOYEES_WITH_GRADE
ORDER BY EMP_NO;