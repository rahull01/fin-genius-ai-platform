fetch(`${BASE_URL}/api/notifications/user/${user.id}`)
  .then(res => res.json())
  .then(data => {
    notifications.innerHTML = "";
    data.forEach(n =>
      notifications.innerHTML += `<li>${n.message}</li>`
    );
  });
