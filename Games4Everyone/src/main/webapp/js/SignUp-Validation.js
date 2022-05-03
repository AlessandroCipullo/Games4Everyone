function FormValidation(){
    let specialChars = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
    let pwd = document.forms["SignUpForm"]["password"].value;
    
    if(specialChars.test(pwd)){
        return true;
    }
    alert("Inserisci almeno un carattere speciale nella password!");
    return false;
}