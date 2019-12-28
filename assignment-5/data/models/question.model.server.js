const mongoose = require('mongoose');

const questionSchema = require('./question.schema.server.js');

module.exports = mongoose.model('QuestionModel', questionSchema);