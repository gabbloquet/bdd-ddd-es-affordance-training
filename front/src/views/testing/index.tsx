import React, { useState } from 'react';

export const Testing = () => {
  const [username, setUsername] = useState('');

  const handleUserChange = (event: React.FormEvent<HTMLInputElement>) => {
    setUsername(event.currentTarget.value);
  };

  return (
    <div className="testing">
      <h1>Testing</h1>
      <p>
        Your name is <input value={username} onChange={handleUserChange} />
      </p>
      {username ? (
        <span data-testid="hello-user">Hello dear {username}!</span>
      ) : (
        <span>Please provide your name!</span>
      )}
    </div>
  );
};
