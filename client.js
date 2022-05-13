const XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;

function reqListener() {
  console.log(this.responseText);
}

console.log("foo");

const oReq = new XMLHttpRequest();
oReq.addEventListener("load", reqListener);
oReq.open("GET", "http://localhost:8080/api/insa/v3/todo/Christine/getall");
oReq.send();
