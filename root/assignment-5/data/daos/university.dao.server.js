const studentModel = require('../models/student.model.server.js');
const questionModel = require('../models/question.model.server.js');
const quizWidgetModel = require('../models/quiz-widget.model.server.js');
const answerModel = require('../models/answer.model.server.js');

truncateDatabase = () => answerModel.remove()
        .then(res => quizWidgetModel.remove())
        .then(res => questionModel.remove())
        .then(res => studentModel.remove());

createStudent = student => studentModel.create(student);

deleteStudent = id => studentModel.remove({_id: id});

createQuestion = question => questionModel.create(question);

deleteQuestion = id => questionModel.remove({_id: id});

answerQuestion = (studentId, questionId, answer) => {
    answer.student = studentId;
    answer.question = questionId;
    return answerModel.create(answer);
}

deleteAnswer = id => answerModel.remove({_id: id});

findAllStudents = () => studentModel.find();

findStudentById = id => studentModel.findById(id);

findAllQuestions = () => questionModel.find();

findQuestionById = id => questionModel.findById(id);

findAllAnswers = () => answerModel.find();

findAnswerById = id => answerModel.findById(id);

findAnswersByStudent = studentId => answerModel.find({student: studentId});

findAnswersByQuestion = questionId => answerModel.find({question: questionId});

populateDatabase = () => Promise.all([
    createStudent({
        _id: 123,
        firstName: 'Alice',
        lastName: 'Wonderland',
        username: 'alice',
        password: 'alice',
        gradYear: 2020, 
        scholarship: 15000
    }),
    createStudent({
        _id: 234,
        firstName: 'Bob',
        lastName: 'Hope',
        username: 'bob',
        password: 'bob',
        gradYear: 2021, 
        scholarship: 12000
    }),
    createQuestion({
        _id: 321,
        question: 'Is the following schema valid?',
        points: 10,
        questiontype: 'TRUE_FALSE',
        trueFalse: {
            isTrue: false
        }
    }),
    createQuestion({
        _id: 432,
        question: 'DAO stands for Dynamic Access Object.',
        points: 10,
        questiontype: 'TRUE_FALSE',
        trueFalse: {
            isTrue: false
        }
    }),
    createQuestion({
        _id: 543,
        question: 'What does JPA stand for?',
        points: 10,
        questiontype: 'MULTIPLE_CHOICE',
        multipleChoice: {
            choices: 'Java Persistence API,Java Persisted Application,JavaScript Persistence API,JSON Persistent Associations',
            correct: 1
        }
    }),
    createQuestion({
        _id: 654,
        question: 'What does ORM stand for?',
        points: 10,
        questiontype: 'MULTIPLE_CHOICE',
        multipleChoice: {
            choices: 'Object Relational Model,Object Relative Markup,Object Reflexive Model,Object Relational Mapping',
            correct: 1
        }
    }),
    answerQuestion(123, 321, {
        _id: 123,
        trueFalseAnswer: true
    }),
    answerQuestion(123, 432, {
        _id: 234,
        trueFalseAnswer: false
    }),
    answerQuestion(123, 543, {
        _id: 345,
        multipleChoiceAnswer: 1
    }),
    answerQuestion(123, 654, {
        _id: 456,
        multipleChoiceAnswer: 2
    }),
    answerQuestion(234, 321, {
        _id: 567,
        trueFalseAnswer: false
    }),
    answerQuestion(234, 432, {
        _id: 678,
        trueFalseAnswer: true
    }),
    answerQuestion(234, 543, {
        _id: 789,
        multipleChoiceAnswer: 3
    }),
    answerQuestion(234, 654, {
        _id: 890,
        multipleChoiceAnswer: 4
    })
]);

module.exports = {
    truncateDatabase,
    populateDatabase,
    createStudent,
    deleteStudent,
    createQuestion,
    answerQuestion,
    deleteQuestion,
    deleteAnswer,
    findAllStudents,
    findStudentById,
    findAllQuestions,
    findQuestionById,
    findAllAnswers,
    findAnswerById,
    findAnswersByStudent,
    findAnswersByQuestion
};