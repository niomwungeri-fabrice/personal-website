import React, { useState, useEffect } from 'react';
import '../styles/ContactForm.css';
import apiRequest from '../api/contactAPI';
import { API_URL } from '../api/constants';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'; 

const ContactForm = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    title: '',
    message: '',
  });
  const [isLoading, setIsLoading] = useState(false); 

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      const response = await apiRequest(`${API_URL}/api/contacts`, formData);
      console.log('form submitted successfully:', response);
      setFormData({
        name: '',
        email: '',
        title: '',
        message: '',
      });
      toast.success('Success! Your message has been sent.', {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        toastId: 'successToast',
      });
    } catch (error) {
      if (error.response && error.response.status === 500) {
        console.error('error submitting form:', error);
        toast.error('Error submitting form. Please try again later.', {
          position: "top-center",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          toastId: 'errorToast',
        });
      } else {
        toast.error(error.response.data.error, {
          position: "top-center",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          toastId: 'errorToast',
        });
      }
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    // Clear any existing toasts on component mount or unmount
    return () => toast.dismiss();
  }, []);

  return (
    <div className="contact-form" id="contact-form">
      <h2>Contact Me</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name">Full Name</label>
          <input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            placeholder="Your Full Name"
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email Address</label>
          <input
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            placeholder="Your Email Address"
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="title">Message Title</label>
          <input
            type="text"
            id="title"
            name="title"
            value={formData.title}
            onChange={handleChange}
            placeholder="Message Title"
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="message">Your Message</label>
          <textarea
            id="message"
            name="message"
            value={formData.message}
            onChange={handleChange}
            placeholder="Your Message"
            rows="5"
            required
          ></textarea>
        </div>
        <div className="form-group button-group">
          <button type="submit" className="submit-button" disabled={isLoading}>
            {isLoading ? 'Sending...' : 'Send Message'}
          </button>
        </div>
      </form>
      <ToastContainer /> {/* Render the ToastContainer component */}
    </div>
  );
}

export default ContactForm;