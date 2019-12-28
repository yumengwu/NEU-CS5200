module.exports = () => {
    const mongoose = require('mongoose');
    const connectingString = 'mongodb://localhost/white-board';
    mongoose.connect(connectingString);
}