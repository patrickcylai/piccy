// Define API ROOT url
const API_ROOT = 'http://localhost:8080';

export const CALL_API = 'CALL API';

const callApi = endpoint => {
  const url =
    endpoint.indexOf(API_ROOT) === -1 ? API_ROOT + endpoint : endpoint;

  return fetch(url)
    .then(response => {
      response.json();
    })
    .then(json => {
      if (!response.ok) {
        return Promise.reject(json);
      }

      return json;
    });
};
