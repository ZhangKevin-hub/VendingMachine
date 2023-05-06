import candy1 from '../images/Candy/candy1.png';
import candy2 from '../images/Candy/candy2.png';
import candy3 from '../images/Candy/candy3.png';
import candy4 from '../images/Candy/candy4.png';
import drink1 from '../images/Drink/drink1.png';
import drink2 from '../images/Drink/drink2.png';
import drink3 from '../images/Drink/drink3.png';
import drink4 from '../images/Drink/drink4.png';
import chip1 from '../images/Munchy/chip1.png';
import chip2 from '../images/Munchy/chip2.png';
import chip3 from '../images/Munchy/chip3.png';
import chip4 from '../images/Munchy/chip4.png';
import gum1 from '../images/Gum/gum1.png';
import gum2 from '../images/Gum/gum2.png';
import gum3 from '../images/Gum/gum3.png';
import gum4 from '../images/Gum/gum4.png';
import style from "./ItemDisplay.css";
import React, { useState, useEffect } from 'react';
import api from '../api';

  const ItemDisplay = ({ items }) => {
    const selectRandomImage = (type) => {
      const numImages = 4;
      const randomNum = Math.floor(Math.random() * numImages) + 1;
      if(type == 'Candy'){
          switch (randomNum) {
              case 1:
              return candy1;
              case 2:
              return candy2;
              case 3:
              return candy3;
              case 4:
              return candy4;
              default:
              return candy1;
          }
      } else if(type == 'Drink'){
          switch (randomNum) {
              case 1:
              return drink1;
              case 2:
              return drink2;
              case 3:
              return drink3;
              case 4:
              return drink4;
              default:
              return drink1;
          }
      }else if(type == 'Munchy'){
          switch (randomNum) {
              case 1:
              return chip1;
              case 2:
              return chip2;
              case 3:
              return chip3;
              case 4:
              return chip4;
              default:
              return chip1;
          }
      }else if(type == 'Gum'){
          switch (randomNum) {
              case 1:
              return gum1;
              case 2:
              return gum2;
              case 3:
              return gum3;
              case 4:
              return gum4;
              default:
              return gum1;
          }
      }
    };
    return (
      <div className="display">
        {items.map((item) => (
          <div className="item" key={item.itemSlot}>
            {item.quantity > 1 ? (
              <div className="extraQuant">
                <img className="one" src={selectRandomImage(item.type)} alt={item.itemName} />
                <img className="two" src={selectRandomImage(item.type)} alt={item.itemName} />
              </div>
            ) : (
              <div className="extraQuant">
                <img src={selectRandomImage(item.type)} alt={item.itemName} />
              </div>
            )}
            <div className="floor">
              <div className="outlayer">
                <p>
                  {item.itemName}, Slot {item.itemSlot}, quantity: {item.quantity}
                </p>
              </div>
            </div>
          </div>
        ))}
      </div>
    );
  };
  

export default ItemDisplay;
