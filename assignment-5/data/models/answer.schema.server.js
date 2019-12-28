const mongoose = require('mongoose');
const studentSchema = require('./student.schema.server.js');
const questionSchema = require('./question.schema.server.js');

module.exports = mongoose.Schema({
    _id: Number,
    trueFalseAnswer: Boolean,
    multipleChoiceAnswer: Number,
    student: {
        type: mongoose.Schema.Types.Number,
        ref: 'StudentModel'
    },
    question: {
        type: mongoose.Schema.Types.Number,
        ref: 'QuestionModel'
    }
}, {collection: 'answers'});