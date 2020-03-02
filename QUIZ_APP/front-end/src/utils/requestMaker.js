function deleteAuthToken() {
    localStorage.removeItem('token');
    window.location.reload();
}

function getAuthToken() {
    return localStorage.getItem('token');
}
const baseUrl = 'http://localhost:8090';
function getHeaders() {
    const auth = getAuthToken();
    if (!auth) {
        return {
            'Content-Type': 'application/json',
        };
    }
    return {
        'Content-Type': 'application/json',
        Authorization: `Bearer_${auth}`,
    };
}
async function request(url, headers = {}, method, body = {}) {
    const options = {
        method,
        headers: {
            ...getHeaders(),
            ...headers,
        },
        body,
    };
    const response = await fetch(baseUrl + url, options);
    if (response.status === 401) {
        throw new Error('401');
    }
    try {
      const data = await response.json();
      return data;
    }
    catch (e) {
        return response.message;
    }
}
export const makeGet = async (url, headers, body) => request(url, headers, 'GET', body);
export const makePost = async (url, headers, body) => request(url, headers, 'POST', body);