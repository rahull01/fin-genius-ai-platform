const BASE = "http://localhost:9091/api/users";
/* --------------------------
   LOGIN
--------------------------- */
async function login() {
    try {
            const response = await fetch(BASE + "/login", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                email: email.value,
                password: password.value
            })
        });

        if (!response.ok) return alert("Invalid email or password!");

        const res = await response.json();

        // Save logged user
        localStorage.setItem("user", JSON.stringify(res));

        window.location = "dashboard.html";
    } catch (e) {
        alert("Login failed!");
    }
}


/* --------------------------
   REGISTER
--------------------------- */
async function registerUser() {
    try {
            const response = await fetch(BASE + "/register", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                fullName: regName.value,
                email: regEmail.value,
                password: regPassword.value,
                role: regRole.value,
                country: regCountry.value,
                currency: regCurrency.value
            })
        });

        if (!response.ok) return alert("Registration failed!");

        alert("Account created successfully!");
        window.location = "index.html";

    } catch (e) {
        alert("Something went wrong!");
    }
}


/* --------------------------
   DASHBOARD
--------------------------- */
if (location.pathname.includes("dashboard")) {
    let u = JSON.parse(localStorage.getItem("user"));

    if (!u) window.location = "index.html";

    userName.innerText = u.fullName;

    fetch(`${BASE}/analytics/user/${u.id}/summary`)
        .then(r => r.json())
        .then(s => {
            sumCredit.innerText = s.totalCredit;
            sumDebit.innerText = s.totalDebit;
            sumBalance.innerText = s.netBalance;
        });
}


/* --------------------------
   ADD TRANSACTION
--------------------------- */
async function addTransaction() {
    let u = JSON.parse(localStorage.getItem("user"));

    try {
        await fetch(BASE + "/transactions", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                userId: u.id,
                type: type.value,
                amount: amount.value,
                category: category.value,
                description: desc.value
            })
        });

        loadTx();
    } catch (e) {
        alert("Failed to add transaction");
    }
}


/* --------------------------
   LOAD TRANSACTIONS
--------------------------- */
function loadTx() {
    let u = JSON.parse(localStorage.getItem("user"));

    fetch(`${BASE}/transactions/user/${u.id}`)
        .then(r => r.json())
        .then(list => {
            txList.innerHTML = list.map(t => `
                <div class="card">
                    <b>${t.type} ₹${t.amount}</b><br>
                    ${t.category} — ${t.description}
                </div>
            `).join("");
        });
}

if (location.pathname.includes("transactions")) loadTx();


/* --------------------------
   AI ADVISOR
--------------------------- */
async function askAI() {
    let u = JSON.parse(localStorage.getItem("user"));
    let q = question.value;

    if (!q.trim()) return;

    chatBox.innerHTML += `<div class="msg"><b>You:</b> ${q}</div>`;

    try {
        const response = await fetch(`${BASE}/ai/advice`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({userId: u.id, question: q})
        });

        const out = await response.json();

        chatBox.innerHTML += `<div class="msg msg-ai"><b>AI:</b> ${out.advice}</div>`;
        chatBox.scrollTop = chatBox.scrollHeight;

    } catch (e) {
        chatBox.innerHTML += `<div class="msg msg-ai"><b>AI:</b> Error!</div>`;
    }

    question.value = "";
}
