select res.first_name, res.second_name, res.last_name, coalesce(res.avg_value, 'there is 3 or 2')
from (
         select p.first_name, p.second_name, p.last_name, cast(best.avg_value as varchar(4))
         from students st
                  inner join people p on p.id = st.people_id
                  full outer join
              (
                  select best_st.people_id, avg(lm.value) as avg_value
                  from students best_st
                           inner join lesson_marks lm on best_st.people_id = lm.student_id
                  group by best_st.people_id
                  having min(lm.value) >= 4
              ) best on st.people_id = best.people_id
         order by -best.avg_value
     ) res