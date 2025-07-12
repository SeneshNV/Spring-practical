// src/pages/AddProduct.jsx
import { useState } from "react";
import axios from "axios";

export default function AddProduct() {
  const [productName, setName] = useState("");
  const [price, setPrice] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post("http://localhost:8080/products", { productName, price })
      .then(() => alert("Product added"))
      .catch((err) => console.error(err));
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Add Product</h2>
      <input type="text" placeholder="Name" value={productName} onChange={(e) => setName(e.target.value)} required />
      <input type="number" placeholder="Price" value={price} onChange={(e) => setPrice(e.target.value)} required />
      <button type="submit">Add</button>
    </form>
  );
}
