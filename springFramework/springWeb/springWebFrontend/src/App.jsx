// src/App.jsx
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState } from "react";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import AddProduct from "./pages/AddProduct";
import Cart from "./pages/Cart";
import ProductDetail from "./pages/ProductDetail";
import UpdateProduct from "./pages/UpdateProduct";

export default function App() {
  const [cart, setCart] = useState([]);

  const addToCart = (product) => setCart([...cart, product]);

  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home addToCart={addToCart} />} />
        <Route path="/add" element={<AddProduct />} />
        <Route path="/cart" element={<Cart cart={cart} />} />
        <Route path="/products/:id" element={<ProductDetail />} />
        <Route path="/products/:id/edit" element={<UpdateProduct />} />
      </Routes>
    </BrowserRouter>
  );
}
