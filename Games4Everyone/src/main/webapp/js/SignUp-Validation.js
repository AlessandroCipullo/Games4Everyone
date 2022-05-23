function FormValidation(){
    if(passwordValidation() && nomeValidation() && cognomeValidation() && cellulareValidation() && emailValidation()){
        return true;
    }
    return false;
}

function passwordValidation(){
    let specialChars = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
    let pwd = document.forms["SignUpForm"]["password"].value;
    
    if(specialChars.test(pwd)){
        return true;
    }
    document.getElementById("password").focus();
    clearErrs();
    document.getElementById("pwdErr").innerHTML = "Inserisci almeno un carattere speciale nella password!";
    return false;
}

function emailValidation(){
	let specialChars = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    let email = document.forms["SignUpForm"]["email"].value;
    
    if(specialChars.test(email) && (document.getElementById("emailErr").innerText.length < 1)){
        return true;
    }
    document.getElementById("email").focus();
    clearErrs();
    document.getElementById("emailErr").innerHTML = "L'email inserita non e' valida!";
    return false;
}

function emailCheck(){
	const xhttp = new XMLHttpRequest();
		  xhttp.onload = function() {
			  clearErrs();
			  document.getElementById("emailErr").innerHTML = (this.responseText);
		  }
		  xhttp.open("GET", "SignUp?email=" + document.getElementById("email").value);
		  xhttp.send();	
}

function nomeValidation(){
    let specialChars = /^[a-zA-Z]+$/;
    let nome = document.forms["SignUpForm"]["nome"].value;

    if(specialChars.test(nome)){
        return true;
    }
    document.getElementById("nome").focus();
    clearErrs();
    document.getElementById("nomeErr").innerHTML = "Sono ammesse solo lettere nel nome!";
    return false;
}

function cognomeValidation(){
    let specialChars = /^[a-zA-Z]+$/;
    let cognome = document.forms["SignUpForm"]["cognome"].value;

    if(specialChars.test(cognome)){
        return true;
    }
    document.getElementById("cognome").focus();
    clearErrs();
    document.getElementById("cognomeErr").innerHTML = "Sono ammesse solo lettere nel cognome!";
    return false;
}

function cellulareValidation(){
    let specialChars = /^\d+$/;
    let cellulare = document.forms["SignUpForm"]["cellulare"].value;

    if(specialChars.test(cellulare)){
        return true;
    }
    document.getElementById("cellulare").focus();
    clearErrs();
    document.getElementById("cellErr").innerHTML = "Sono ammesse solo lettere nel cellulare!";
    return false;
}

function clearErrs(){
	document.getElementById("nomeErr").innerHTML = "";
	document.getElementById("cognomeErr").innerHTML = "";
	document.getElementById("cellErr").innerHTML = "";
	document.getElementById("usrErr").innerHTML = "";
	document.getElementById("pwdErr").innerHTML = "";
	document.getElementById("emailErr").innerHTML = "";
}