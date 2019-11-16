const mongoose = require('mongoose');
const studentSchema = require('./student.schema.server.js');

module.exports = mongoose.model('StudentModel', studentSchema);