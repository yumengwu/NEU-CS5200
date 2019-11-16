require('./data/db.js')();
const assert = require('assert');
const daos = require('./data/daos/university.dao.server.js');

testStudentsInitialCount = () => daos.findAllStudents().then(res => assert.equal(res.length, 2))

testQuestionsInitialCount = () => daos.findAllQuestions().then(res => assert.equal(res.length, 4))

testAnswersInitialCount = () => daos.findAllAnswers().then(res => assert.equal(res.length, 8))

testDeleteAnswer = () => daos.deleteAnswer(890).then(() => Promise.all([
    daos.findAllAnswers().then(res => assert.equal(res.length, 7)),
    daos.findAnswersByStudent(234).then(res => assert.equal(res.length, 3))
]))

testDeleteQuestion = () => daos.deleteQuestion(321)
    .then(() => findAllQuestions())
    .then(res => assert.equal(res.length, 3))

testDeleteStudent = () => daos.deleteStudent(234).then(() => daos.findAllStudents())
    .then(res => assert.equal(res.length, 1))

daos.truncateDatabase()
.then(() => daos.populateDatabase())
.then(() => testStudentsInitialCount())
.then(() => testQuestionsInitialCount())
.then(() => testAnswersInitialCount())
.then(() => testDeleteAnswer())
.then(() => testDeleteQuestion())
.then(() => testDeleteStudent())