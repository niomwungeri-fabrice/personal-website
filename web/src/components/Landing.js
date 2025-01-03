import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Landing.css';

const Landing = () => {
  const navigate = useNavigate();

  const navigateToContact = () => {
    navigate('/contact');
  };
  const navigateToProjects = () => {
    navigate('/projects');
  };

  return (
    <main className="landing">
      <div className="hero-section">
        <div className="hero-content">
          <h1>Hi, I'm <span className="highlight">Fabrice Niyomwungeri</span></h1>
          <p className="hero-subtext">
            I’m a <span className="highlight">Software Developer</span> based in Edmonton, CA.
            <br />Welcome to my personal website.
          </p>
          <div className="hero-buttons">
            <button className="cta-button-outline" onClick={navigateToProjects}>
              Projects
            </button>
            <button className="cta-button-outline" onClick={navigateToContact}>
              Getting In Touch
            </button>
          </div>
        </div>
      </div>
    </main>
  );
}

export default Landing;
