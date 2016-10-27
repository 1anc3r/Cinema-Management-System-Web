var clickHandlevar = {
	useElement : {},
	clickButton : function(e) {
		var e = e || window.event, t = e.target || e.targetElement;
		switch (t.getAttribute("name")) {
		case "login":
			this.useElement.shadow.style.display = "block",
					this.useElement.modelBox[0].style.display = "block",
					this.useElement.modelBox[1].style.display = "none";
			break;
		default:
			return
		}
	},
	shadowOtherArea : function(e) {
		var e = e || window.event, t = e.target || e.targetElement, n = t.id;
		if ("shadow" == n) {
			this.useElement.shadow.style.display = "none";
			for (var i = 0; i < this.useElement.errText.length; i++)
				this.useElement.errText[i].innerHTML = "",
						this.useElement.inputUsername[i].value = "",
						this.useElement.inputPassword[i].value = "";
			this.useElement.inputName[0].value = ""
		}
	},
	submitHandle : function(e) {
		var e = e || window.event, t = e.target || e.targetElement, n = t.id;
		"login" == n ? this.checkImpty(0) : "regisit" == n
				&& this.checkImpty(1)
	},
	checkImpty : function(e) {
		var t = /^[a-zA-z]\w{3,15}$/;
		if (0 == e) {
			if (!this.useElement.inputUsername[0].value
					|| !this.useElement.inputPassword[0].value)
				return void (this.useElement.errText[0].innerHTML = "用户名或者密码不允许为空")
		}
		var i = t.test(this.useElement.inputUsername[e].value);
		if (!i) {
			return void (this.useElement.errText[e].innerHTML = "用户名可由字母、数字、下划线组成，且必须由字母开头，4-16位");
		}
		var form = document.createElement("form"); 
	    var url = "LoginSlt"; 
	    form.action = url; 
	    form.method = "post"; 
	    var inputUserName = document.createElement("input");
	    inputUserName.setAttribute("name", "user_name") ; 
	    inputUserName.setAttribute("value", this.useElement.inputUsername[0].value); 
	    form.appendChild(inputUserName) ; 
	    var inputPassWord = document.createElement("input");
	    inputPassWord.setAttribute("name", "password") ; 
	    inputPassWord.setAttribute("value", this.useElement.inputPassword[0].value); 
	    form.appendChild(inputPassWord) ; 
	    document.body.appendChild(form); 
	    form.submit();
	    document.body.removeChild(form); 
	}
}, init = function() {
	clickHandlevar.useElement = {
		shadow : document.getElementById("shadow"),
		modelBox : document.getElementsByClassName("modelBox"),
		bigButton : document.getElementsByClassName("bigButton"),
		errText : document.getElementsByClassName("errText"),
		inputUsername : document.getElementsByClassName("inputUsername"),
		inputPassword : document.getElementsByClassName("inputPassword"),
		inputName : document.getElementsByClassName("inputName")
	};
	var e = document.getElementById("buttonBox");
	e.addEventListener("click",
			clickHandlevar.clickButton.bind(clickHandlevar), !1),
			clickHandlevar.useElement.shadow.addEventListener("click",
					clickHandlevar.shadowOtherArea.bind(clickHandlevar), !1);
	for (var t = 0; t < clickHandlevar.useElement.bigButton.length; t++) {
		clickHandlevar.useElement.bigButton[t].addEventListener("click",
				clickHandlevar.submitHandle.bind(clickHandlevar), !1)
	}
};
window.onload = function() {
	init()
};