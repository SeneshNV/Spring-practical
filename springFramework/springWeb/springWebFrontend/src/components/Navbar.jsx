// src/components/Navbar.jsx
import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav style={{ padding: "10px", background: "#eee" }}>
      <Link to="/" style={{ marginRight: "10px" }}>
        Home
      </Link>
      <Link to="/add" style={{ marginRight: "10px" }}>
        Add Product
      </Link>
      <Link to="/cart">Cart</Link>
    </nav>
  );
}
