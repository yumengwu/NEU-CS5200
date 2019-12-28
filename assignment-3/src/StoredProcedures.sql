use cs5200_wu_yumeng_assignment3;

delimiter $
create procedure getUnansweredQuestions()
begin
select question.text, sq.answer_number as answers from question join
(
    select question.id as qid, question.module as module, max(selected_question.answer_number) as answer_number from
    (
        select question_id, count(id) as answer_number from answer where question_id not in
        (
            select distinct question_id from answer where correct_answer=1
        )
        group by question_id
    ) as selected_question left join question on selected_question.question_id = question.id
    group by question.module
) as sq on question.id = sq.qid;
end$
delimiter ;

delimiter $
create procedure endorsedUsersForWeek(in start_date date, in end_date date)
begin
select person.username, top_user.answer_number as answers from
(
    select posted_by, count(id) as answer_number from answer where correct_answer=1 and (posted_on between start_date and end_date) group by posted_by
) as top_user left join person on top_user.posted_by = person.id order by answers desc, person.firstname limit 5;
end$
delimiter ;