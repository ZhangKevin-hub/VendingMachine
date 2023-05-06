import React, { useState, useEffect } from 'react';
import axios from 'axios';
import api from '../api';
import style from './UserPanel.css';
import ItemDisplay from './ItemDisplay';

const UserPanel = ({ isDisabled }) => {
  const [currentFund, setCurrentFund] = useState(0);
  const [inputMoney, setInputMoney] = useState(0);
  const [message, setMessage] = useState('');
  const [items, setItems] = useState([]);
  const [itemsUpdated, setItemsUpdated] = useState(false);

  useEffect(() => {
    api.get('/vending-machine/items')
      .then(response => {
        setItems(response.data);
        setItemsUpdated(true);
      })
      .catch(console.error);
      
    api.get('/vending-machine/money')
      .then(response => setCurrentFund(response.data))
      .catch(console.error);
  }, [itemsUpdated]);

  const handleAddMoney = () => {
    const moneyToAdd = parseInt(inputMoney);
    api.post(`/vending-machine/add-money?amount=${moneyToAdd}`)
      .then(response => {
        setCurrentFund(currentFund + moneyToAdd);
        setInputMoney(0);
        setMessage(response.data);
      })
      .catch(console.error);
  };
  
  const handleSlotButtonClick = (slot) => {
    api.post(`/vending-machine/purchase?slot=${slot}`)
      .then(response => {
        if (response.data === "Item purchased successfully") {
          setMessage(response.data);
          api.get('/vending-machine/money')
          .then(response => setCurrentFund(response.data))
          .catch(console.error);
          setItemsUpdated(false);
        } else if (response.data === "Item out of stock") {
          setMessage(response.data);
        } else if (response.data === "Not enough money to purchase") {
          setMessage(response.data);
        }
      })
      .catch(console.error);
  };
  const cash = [10000, 5000, 2000, 1000, 500, 100, 25, 10, 5];
  const labels = [
    "Hundred dollar bill",
    "Fifty dollar bill",
    "Twenty dollar bill",
    "Ten dollar bill",
    "Five dollar bill",
    "One dollar bill",
    "Quarter",
    "Dime",
    "Nickel",
  ];
  const handlePurchaseFinish = () => {
    api.get(`/vending-machine/return-money`)
      .then((response) => {
        setCurrentFund(0);
        if (response.data === 0) {
          return "";
        }
        let fund = Math.round(response.data * 100);
        let change = "Your change is: ";
        for (let i = 0; i < cash.length; i++) {
          if (fund >= cash[i]) {
            const amount = Math.floor(fund / cash[i]);
            fund -= amount * cash[i];
            change += `${amount} ${labels[i]}${amount > 1 ? "s" : ""}, `;
          }
        }
        setTimeout(() => {
          setMessage(change);
        }, 0);
      })
      .catch(console.error);
  };
  
  
  const handleRestockClick = () => {
    api.get('/vending-machine/restock')
      .then(response => {
        setMessage(response.data);
        setItemsUpdated(false);
      })
      .catch(console.error);
  };

  return (
    <div className='userpan'>
      <div className='panel'>
        <h3>Current fund: ${currentFund.toFixed(2)}</h3>
        <label>
          Insert money:
          <input type="number" value={inputMoney} onChange={(e) => setInputMoney(e.target.value)} min="0" disabled={isDisabled} />
        </label>
        <button onClick={handleAddMoney} disabled={isDisabled}>Add money</button>
        <button onClick={handlePurchaseFinish} disabled={isDisabled}>Finish Purchase</button>
      </div>
      <div className='slots'>
      {items.map(item => (
          <div className="item" key={item.slot}>
            <button disabled={isDisabled || item.cost > currentFund} onClick={() => handleSlotButtonClick(item.itemSlot)}>
              Slot {item.itemSlot} - {item.iteName} (${item.cost})
            </button>
          </div>
        ))}
      </div>

      <p>|-{message}-|</p>
      <button onClick={handleRestockClick} disabled={isDisabled}>Restock</button>
      {isDisabled ? <div className="screen-gray"/> : <ItemDisplay className="screen" items={items}/>}
    </div>
  );
};

export default UserPanel;
