// src/pages/Home.jsx
import { useEffect, useState } from "react";
import axios from "axios";

export default function Home({ addToCart }) {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080")
      .then((res) => setProducts(res.data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div>
      <h2>Product List</h2>
      {products.map((p) => (
        <div key={p.productId} style={{ border: "1px solid #ccc", margin: "10px", padding: "10px" }}>
          <p><strong>ID:</strong> {p.productId}</p>
          <p><strong>Name:</strong> {p.productName}</p>
          <p><strong>Price:</strong> ${p.price}</p>
          <button onClick={() => addToCart(p)}>Add to Cart</button>
        </div>
      ))}
    </div>
  );
}
