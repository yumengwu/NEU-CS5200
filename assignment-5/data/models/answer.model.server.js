const mongoose = require('mongoose');
const answerSchema = require('./answer.schema.server.js');

module.exports = mongoose.model('AnswerModel', answerSchema);