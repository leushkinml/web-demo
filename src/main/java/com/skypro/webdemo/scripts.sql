select * from student;
select * from faculty;
select * from student where age > 15 and age < 25;
select name from student;
select * from student where name like '%a%' or name like '%A%';
select * from student where age < student.id;
select * from student ORDER BY age;

select * from expenses where date >= '2021-01-01' and date <= '2021-01-31';

SELECT COUNT(*) FROM expenses;
SELECT COUNT(category) FROM expenses;
SELECT COUNT(DISTINCT(category) FROM expenses;

select * count(*) from expenses where date >= '2021-01-01' and date <= '2021-01-31';


select sum(amount) as total
from expenses
where date >= '2021-01-01' and date <= '2021-01-31';

SELECT MIN(amount), MAX(amount), AVG(amount) from expenses;

select min(amount) as min, max(amount) as max, avg(amount) as avg
from expenses
where date >= '2021-01-01' and date <= '2021-01-31';

SELECT SUM(amount) FROM expenses;

SELECT category, COUNT(*) FROM expenses GROUP BY category;

select category, sum(amount) as amount
from expenses
group by category;

SELECT category, min(amount), max(amount), avg(amount) FROM expenses GROUP BY category;

SELECT category, SUM(amount) FROM expenses GROUP BY category;

select category, sum(amount) as amount
from expenses
group by category
having sum(amount) > 1000;

SELECT category, COUNT(*) FROM expenses GROUP BY category HAVING COUNT(*) > 1;
SELECT category, SUM(amount) FROM expenses GROUP BY category HAVING SUM(amount) > 1000;

select * from expenses limit 4;
select * from expenses limit 4 offset 4;
select * from expenses offset 8;


SELECT * FROM expenses ORDER BY amount DESC