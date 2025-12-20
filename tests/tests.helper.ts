export function makePostRequest(body: unknown) {
  return new Request('http://localhost/api/shopping-lists', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(body)
  })
}