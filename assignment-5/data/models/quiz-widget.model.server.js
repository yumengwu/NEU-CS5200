const mongoose = require('mongoose');
const questionWidgetSchema = require('./quiz-widget.schema.server.js');

// const quizWidgetModel = mongoose.model('QuizWidgetModel', questionWidgetSchema);

module.exports = mongoose.model('QuizWidgetModel', questionWidgetSchema);