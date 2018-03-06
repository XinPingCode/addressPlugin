var exec = require('cordova/exec');
exports.address = function(arg0, arg1, arg2, success, error) {
    exec(success, error, "addressPlugin", "address", [arg0,arg1,arg2]);
};
