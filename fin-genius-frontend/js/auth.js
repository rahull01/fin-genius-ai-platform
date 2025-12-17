
const BASE_URL = "http://localhost:8086";

function register() {
  fetch(`${BASE_URL}/api/users/register`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      fullName: document.getElementById("fullName").value,
      email: document.getElementById("email").value,
      password: document.getElementById("password").value
    })
  })
  .then(res => {
    if (!res.ok) throw new Error("Register failed");
    return res.json();
  })
  .then(() => {
    alert("Registration Successful 🎉");
    window.location.href = "index.html";
  })
  .catch(err => alert("Registration Error ❌"));
}
