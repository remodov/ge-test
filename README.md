# Java Test
Application - start class 
for Problem #1 and Problem #2
# Database test
##1
`delete from cities
where id in (
            select max(id)
            from cities
            group by name
            having count(1) > 1
            );`
##2
`select rs2.country,
       rs2.city,
       rs2.pr
from (
        select rs.*, row_number() over (partition by country order by country, pr desc ) pos
        from (select country,
                     city,
                     round((sum(citizen) / sum(citizen) over (partition by country)) * 100, 2) pr
              from population
              group by country,
                       city
             ) rs
    ) rs2
where rs2.pos <= 3
order by rs2.country, rs2.pos desc`