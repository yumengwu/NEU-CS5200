const mongoose = require('mongoose');
const multipleChoiceSchema = require('./multiple-choice.schema.server.js');
const trueFalseSchema = require('./true-false.schema.server.js');

module.exports = mongoose.Schema({
    _id: Number,
    question: String,
    points: Number,
    questiontype: {type: String, enum: ['TRUE_FALSE', 'MULTIPLE_CHOICE']},
    multipleChoice: multipleChoiceSchema,
    trueFalse: trueFalseSchema
}, {collection: 'questions'});