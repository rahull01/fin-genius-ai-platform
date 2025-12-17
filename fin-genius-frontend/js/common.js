// js/common.js
const API = "http://localhost:8086";

const user = JSON.parse(localStorage.getItem("user"));
if(!user) location.href="auth.html";

document.addEventListener("DOMContentLoaded", ()=>{
  document.getElementById("pName").innerText = user.fullName;
  document.getElementById("pEmail").innerText = user.email;
});

function logout(){
  localStorage.clear();
  location.href="auth.html";
}

function toggleProfile(){
  document.getElementById("profileMenu").classList.toggle("hidden");
}
