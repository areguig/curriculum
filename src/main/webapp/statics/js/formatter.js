/*
 * ViewJSON
 * Version 1.0
 * A Google Chrome extension to display JSON in a user-friendly format
 *
 * This is a chromeified version of the JSONView Firefox extension by Ben Hollis: 
 * http://jsonview.com 
 * http://code.google.com/p/jsonview
 *
 * Also based on the XMLTree Chrome extension by Moonty & alan.stroop
 * https://chrome.google.com/extensions/detail/gbammbheopgpmaagmckhpjbfgdfkpadb
 *
 * port by Jamie Wilkinson (@jamiew) | http://jamiedubs.com | http://github.com/jamiew
 * MIT license / copyfree (f) F.A.T. Lab http://fffff.at
 * Speed Project Approved: 2h
 */

// ghetto detect if this is JSON until Google Chrome can get mimetype info
// Perhaps do some simple URL parsing instead to save time regexing the whole document all the time :x
// e.g. if it's .html/.css/.php/etc assume it's not JSON (though w/ PHP that might not be the case..)
this.data = document.getElementById("toFormat").innerHTML;

console.log("1 going to format "+ this.data);
// Test if what remains is JSON or JSONp
var json_regex = /^\s*([\[\{].*[\}\]])\s*$/; // Ghetto, but it works
var jsonp_regex = /^[\s\u200B\uFEFF]*([\w$\[\]\.]+)[\s\u200B\uFEFF]*\([\s\u200B\uFEFF]*([\[{][\s\S]*[\]}])[\s\u200B\uFEFF]*\);?[\s\u200B\uFEFF]*$/;
var jsonp_regex2 = /([\[\{][\s\S]*[\]\}])\);/ // more liberal support... this
												// allows us to pass the
												// jsonp.json & jsonp2.json
												// tests

console.log("JSONView: sexytime!");

// JSONFormatter json->HTML prototype straight from Firefox JSONView
// For reference: http://code.google.com/p/jsonview
function JSONFormatter() {
	// No magic required.
}
JSONFormatter.prototype = {
	htmlEncode : function(t) {
		return t != null ? t.toString().replace(/&/g, "&amp;").replace(/"/g,
				"&quot;").replace(/</g, "&lt;").replace(/>/g, "&gt;") : '';
	},

	decorateWithSpan : function(value, className) {
		return '<span class="' + className + '">' + this.htmlEncode(value)
				+ '</span>';
	},

	// Convert a basic JSON datatype (number, string, boolean, null, object,
	// array) into an HTML fragment.
	valueToHTML : function(value) {
		var valueType = typeof value;

		var output = "";
		if (value == null) {
			output += this.decorateWithSpan('null', 'null');
		} else if (value && value.constructor == Array) {
			output += this.arrayToHTML(value);
		} else if (valueType == 'object') {
			output += this.objectToHTML(value);
		} else if (valueType == 'number') {
			output += this.decorateWithSpan(value, 'num');
		} else if (valueType == 'string') {
			if (/^(http|https):\/\/[^\s]+$/.test(value)) {
				value = this.htmlEncode(value);
				output += '<a href="' + value + '">' + value + '</a>';
			} else {
				output += this.decorateWithSpan('"' + value + '"', 'string');
			}
		} else if (valueType == 'boolean') {
			output += this.decorateWithSpan(value, 'bool');
		}

		return output;
	},

	// Convert an array into an HTML fragment
	arrayToHTML : function(json) {
		var output = '[<ul class="array collapsible">';
		var hasContents = false;
		for ( var prop in json) {
			hasContents = true;
			output += '<li>';
			output += this.valueToHTML(json[prop]);
			output += '</li>';
		}
		output += '</ul>]';

		if (!hasContents) {
			output = "[ ]";
		}

		return output;
	},

	// Convert a JSON object to an HTML fragment
	objectToHTML : function(json) {
		var output = '{<ul class="obj collapsible">';
		var hasContents = false;
		for ( var prop in json) {
			hasContents = true;
			output += '<li>';
			output += '<span class="prop">' + this.htmlEncode(prop)
					+ '</span>: ';
			output += this.valueToHTML(json[prop]);
			output += '</li>';
		}
		output += '</ul>}';

		if (!hasContents) {
			output = "{ }";
		}

		return output;
	},

	// Convert a whole JSON object into a formatted HTML document.
	jsonToHTML : function(json, callback, uri) {
		var output = '';
		if (callback) {
			output += '<div class="callback">' + callback + ' (</div>';
			output += '<div id="json">';
		} else {
			output += '<div id="json">';
		}
		output += this.valueToHTML(json);
		output += '</div>';
		if (callback) {
			output += '<div class="callback">)</div>';
		}
		return output;
	},

	// Produce an error document for when parsing fails.
	errorPage : function(error, data, uri) {
		// var output = '<div id="error">' +
		// this.stringbundle.GetStringFromName('errorParsing') + '</div>';
		// output += '<h1>' + this.stringbundle.GetStringFromName('docContents')
		// + ':</h1>';
		var output = '<div id="error">Error parsing JSON: ' + error.message
				+ '</div>';
		output += '<h1>' + error.stack + ':</h1>';
		output += '<div id="json">' + this.htmlEncode(data) + '</div>';
		return this.toHTML(output, uri + ' - Error');
	},

	// Wrap the HTML fragment in a full document. Used by jsonToHTML and
	// errorPage.
	toHTML : function(content, title) {
		return '<doctype html>' + '<html><head><title>' + title + '</title>'
				+ '<link rel="stylesheet" type="text/css" href="'
				+ "default.css"+ '">'
				+ '<script type="text/javascript" src="'
				+ "default.js" + '"></script>'
				+ '</head><body>' + content + '</body></html>';
	}
};

// Sanitize & output -- all magic from JSONView Firefox
this.jsonFormatter = new JSONFormatter();

// This regex attempts to match a JSONP structure:
// * Any amount of whitespace (including unicode nonbreaking spaces) between the
// start of the file and the callback name
// * Callback name (any valid JavaScript function name according to ECMA-262
// Edition 3 spec)
// * Any amount of whitespace (including unicode nonbreaking spaces)
// * Open parentheses
// * Any amount of whitespace (including unicode nonbreaking spaces)
// * Either { or [, the only two valid characters to start a JSON string.
// * Any character, any number of times
// * Either } or ], the only two valid closing characters of a JSON string.
// * Any amount of whitespace (including unicode nonbreaking spaces)
// * A closing parenthesis, an optional semicolon, and any amount of whitespace
// (including unicode nonbreaking spaces) until the end of the file.
// This will miss anything that has comments, or more than one callback, or
// requires modification before use.
var outputDoc = '';
// text = text.match(jsonp_regex)[1];
var cleanData = '', callback = '';

console.log("going to format "+ this.data);

var callback_results = jsonp_regex.exec(this.data);
if (callback_results && callback_results.length == 3) {
	console.log("THIS IS JSONp");
	callback = callback_results[1];
	cleanData = callback_results[2];
} else {
	console.log("Vanilla JSON");
	cleanData = this.data;
}
console.log(cleanData);

// Covert, and catch exceptions on failure
try {
	// var jsonObj = this.nativeJSON.decode(cleanData);
	var jsonObj = JSON.parse(cleanData);
	if (jsonObj) {
		outputDoc = this.jsonFormatter.jsonToHTML(jsonObj, callback, this.uri);
	} else {
		throw "There was no object!";
	}
} catch (e) {
	console.log(e);
	outputDoc = this.jsonFormatter.errorPage(e, this.data, this.uri);
}

console.log("formatted data "+ outputDoc);
document.body.innerHTML = outputDoc;
