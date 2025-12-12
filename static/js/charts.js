let u = JSON.parse(localStorage.getItem("user"));

fetch(`${BASE}/analytics/user/${u.id}/categories`)
.then(r => r.json())
.then(d => {
    new Chart(categoryChart, {
        type: "pie",
        data: {
            labels: d.map(x => x.category),
            datasets: [{ data: d.map(x => x.totalAmount) }]
        }
    });
});

fetch(`${BASE}/analytics/user/${u.id}/monthly`)
.then(r => r.json())
.then(d => {
    new Chart(monthChart, {
        type: "bar",
        data: {
            labels: d.map(x => x.month),
            datasets: [{ data: d.map(x => x.total) }]
        }
    });
});
