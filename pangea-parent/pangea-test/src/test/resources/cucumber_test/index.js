var reporter = require('cucumber-html-reporter');
var fs = require('fs');

var projectName = 'Pangea'; // showing as the page title
var jsonDir = 'reports/json/';
var htmlDir = 'reports/html/';

var options = {
	theme: 'bootstrap',
	name: '',
	jsonFile: '',
	output: '',
	reportSuiteAsScenarios: true,
	launchReport: false,
	ignoreBadJsonFile: false,
	brandTitle: projectName,
	metadata: {
		"App Version":"0.3.2",
		"Test Environment": "STAGING",
		"Browser": "Chrome  54.0.2840.98",
		"Platform": "Windows 10",
		"Parallel": "Scenarios",
		"Executed": "Remote",
		"Executed at": new Date().toLocaleString()
	}
};

fs.readdir(jsonDir , function(error, files){

    files.forEach(function(file){

        if(file.indexOf('.json') > 0){
            // generate name
            var name = file.split('.')[0];
            // set property
            options.name = name;
            options.jsonFile = jsonDir + file;
            options.output = htmlDir + name + ".html";

            reporter.generate(options);
        }
    });
})