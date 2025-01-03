import axios from 'axios';

const apiRequest = async (url, payload) => {
  console.log('apiRequest:', url, payload);
  try {
    const response = await axios.post(url, payload);
    return response.data;
  } catch (error) {
    console.error('api request error:', error);
    throw error;
  }
};


export default apiRequest