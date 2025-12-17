function askAI() {
  fetch(`${BASE_URL}/api/ai/advice`, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({question: question.value})
  })
  .then(res => res.json())
  .then(data => aiResult.innerText = data.answer);
}
