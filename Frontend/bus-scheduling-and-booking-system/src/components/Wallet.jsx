import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { Modal, Button, InputNumber } from 'antd'; // Import InputNumber from antd
import WalletService from '../services/Wallet.Service';
import UserService from '../services/User.service';

import '../css/Wallet.css';

const Wallet = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [balance, setBalance] = useState(0); // Initialize balance
  const [addAmount, setAddAmount] = useState(0); // Initialize addAmount state
  const username = location.state?.username;
  const [wid, setWid] = useState(0);

  useEffect(() => {
    const fetchWalletDetails = async () => {
      try {
        if (!username) {
          // Show a popup if username is null
          Modal.error({
            title: 'Error',
            content: 'Please log in.',
            onOk: () => navigate('/login'), // Navigate to login page
          });
          return;
        }

        const user = await UserService.getUserByUserName(username);
        if (!user?.data?.wallet?.walletId) {
          // Show a popup if walletId is null
          Modal.error({
            title: 'Error',
            content: 'Wallet not found. Please create a wallet first.',
            onOk: () => navigate('/'), // Navigate to home or login page
          });
          return;
        }
        setWid(user.data.wallet.walletId);
        const response = await WalletService.getWalletById(
          user.data.wallet.walletId
        );
        setBalance(response.data.walletBalance);
      } catch (error) {
        console.error('Error fetching wallet details:', error);
      }
    };

    fetchWalletDetails();
  }, [username, navigate]);

  const handleAddMoney = async () => {
    try {
      if (addAmount <= 0) {
        // Validate that the addAmount is greater than zero
        Modal.error({
          title: 'Invalid Amount',
          content: 'Please enter a valid amount to add to your wallet.',
        });
        return;
      }

    Modal.confirm({
        title: 'Confirm',
        content: `Are you sure you want to add ₹${addAmount} to your wallet?`,
        okText: 'Yes', // Customize the "Yes" button text
        cancelText: 'No',
        onOk: async () => {
          const response = await WalletService.updateWallet(wid, {
            walletBalance: balance + addAmount,
          });
          setBalance(response.data.walletBalance);
          setAddAmount(0);
        },
        onCancel() {
          // Do nothing on cancel
        },
      });
    } catch (error) {
      console.error('Error adding money to the wallet:', error);
    }
  };

  return (
    <div className="wallet-container">
      <h2>My Wallet</h2>
      <div className="balance">Balance: ₹{balance}</div>
      <div className="add-money">
        <InputNumber
        //   min={100} // Set a minimum value for the input
        //   step={100} // Set the step value for the input
          placeholder="Add Amount"
          value={addAmount}
          onChange={(value) => setAddAmount(value)}
        />
        <Button type="primary" onClick={handleAddMoney}>
          Add Money
        </Button>
      </div>
    </div>
  );
};

export default Wallet;
