window.onload = function() {
	  let acc = document.getElementById("accordion0");

	  acc.addEventListener("click", function() {
	    let panel1 = document.getElementById("panel1");
	    let panel2 = document.getElementById("panel2");
	    if (panel1.style.display === "block") {
	      panel1.style.display = "none";
	      panel2.style.display = "none";
	      document.getElementById("addForm").style.display = "none";
	    } else {
	      panel1.style.display = "block";
	      panel2.style.display = "block";
	    }
	  });
	  
	  let add = document.getElementById("panel1");

	  add.addEventListener("click", function() {
	    let addForm = document.getElementById("addForm");
	    if (addForm.style.display === "block") {
	      addForm.style.display = "none";
	    } else {
	      addForm.style.display = "block";
	    }
	  });	
	  
	
	  let view = document.getElementById("panel2");
	  
	  view.addEventListener("click", function() {
	    let addForm = document.getElementById("addForm");
	    if (addForm.style.display === "block") {
	      addForm.style.display = "none";
	    }
	    let viewTab = document.getElementById("tabellaMetodi");
	    if (viewTab.style.display === "block"){
			viewTab.style.display = "none";
		}else{
			viewTab.style.display = "block";
		}
	  });	
};

