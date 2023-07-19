/**
 * 
 */

let upload = document.getElementById('upload');

let output = document.getElementById('fileContents');

// invoice = [];

upload.addEventListener('change', () => {
	let fr = new FileReader();
	
	fr.readAsText(upload.files[0]);
	
	fr.onload = function() {
		output.innerHTML = fr.result;
	}
/*
	fr.onload = function() {
		let data = fr.result;
		let previousIndex = 0;
		
		for (i = 0; i < data.length; i++) {
			if (data[i] == ",") {
				item = data.slice(previousIndex, i);
				invoice.push(item);
				previousIndex = i + 1;
			}
		}
		
		console.log(invoice);
	};
	*/
});