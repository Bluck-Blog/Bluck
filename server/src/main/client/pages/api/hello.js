// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
const ip = "http://localhost:8080/";

export function post() {
  return fetch(`${ip}`);
}
