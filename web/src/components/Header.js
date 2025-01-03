import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Header.css';
import logo from '../assets/logo.jpg'; // Import the logo image

const Header = () => {
  return (
    <header className="header">
      <nav className="navbar">
        <div className="logo">
          <Link to="/" className="logo-container">
            <img src={logo} alt="FN Logo" className="logo-image" />
            <div className="logo-text">Fabrice Niyomwungeri</div>
          </Link>
        </div>
        <ul className="nav-links">
          <li><Link to="/about">About</Link></li>
          <li><a href="#projects">Projects</a></li>
          <li><a href="#resume">Resume</a></li>
          <li><Link to="/contact">Contact</Link></li>
        </ul>
      </nav>
    </header>
  );
}

export default Header;
