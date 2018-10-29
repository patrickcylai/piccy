// Define API ROOT url
const API_ROOT = 'http://localhost:8080';

// const callApi = endpoint => {
//   const url =
//     endpoint.indexOf(API_ROOT) === -1 ? API_ROOT + endpoint : endpoint;

//   return fetch(url)
//     .then(response => {
//       response.json();
//     })
//     .then(json => {
//       if (!response.ok) {
//         return Promise.reject(json);
//       }

//       return json;
//     });
// };

export const postApi = (data, endpoint) => {
  const url = API_ROOT + endpoint;

  return fetch(url, {
    method: 'POST',
    body: JSON.stringify(data)
  })
    .then(response => response.json())
    .catch(function(error) {
      console.log(error);
    });
};

export const postFormDataApi = (data, endpoint) => {
  const url = API_ROOT + endpoint;

  return fetch(url, {
    method: 'POST',
    body: data
  })
    .then(response => response.json())
    .catch(function(error) {
      console.log(error);
    });
};

export const getApi = endpoint => {
  const url = API_ROOT + endpoint;

  return fetch(url).then(response => response.json());
};
