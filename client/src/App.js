import { useState } from 'react';
import './App.css';
import UserPanel from './Components/UserPanel';

function App() {
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
