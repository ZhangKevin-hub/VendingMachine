import axios from 'axios';

const api = axios.create({
  baseURL:'http://localhost:8080'
});

console.log(api.defaults.baseURL);

export default api;
