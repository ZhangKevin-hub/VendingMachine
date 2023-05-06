import { useState, useEffect } from 'react';
import logo from './logo.svg';
import './App.css';
import ItemDisplay from './Components/ItemDisplay';
import UserPanel from './Components/UserPanel';
import { getAllItems } from "./api";

function App() {
  const [items, setItems] = useState([]);

  const [started, setStarted] = useState(false);

  const handleStart = () => {
    setStarted(true);
  };

  return (
    <div className="App">
      <div className='Vending'>
        <button onClick={handleStart} >Start</button>
        <UserPanel isDisabled={!started} />
      </div>
      <div className='ground'>

      </div>

    </div>
  );
}

export default App;
