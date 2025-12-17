const user = JSON.parse(localStorage.getItem("user"));

function createTransaction() {
  fetch(`${BASE_URL}/api/transactions`, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({
      userId: user.id,
      amount: amount.value,
      category: category.value
    })
  }).then(loadTransactions);
}

function loadTransactions() {
  fetch(`${BASE_URL}/api/transactions/user/${user.id}`)
    .then(res => res.json())
    .then(data => {
      transactions.innerHTML = "";
      data.forEach(t =>
        transactions.innerHTML += `<li>${t.amount} - ${t.category}</li>`
      );
    });
}

loadTransactions();
