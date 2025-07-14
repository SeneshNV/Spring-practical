import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

export default function ProductDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const [imageUrl, setImageUrl] = useState("");

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/products/${id}`)
      .then((res) => setProduct(res.data))
      .catch((err) => console.error("Error loading product:", err));

    axios
      .get(`http://localhost:8080/api/products/${id}/image`, {
        responseType: "blob",
      })
      .then((res) => {
        const url = URL.createObjectURL(res.data);
        setImageUrl(url);
      })
      .catch(() => {
        setImageUrl("https://via.placeholder.com/250x150?text=No+Image");
      });
  }, [id]);

  const handleDelete = () => {
    if (!window.confirm("Are you sure you want to delete this product?")) return;

    axios
      .delete(`http://localhost:8080/api/product/${id}`)
      .then(() => {
        alert("Product deleted successfully");
        navigate("/");
      })
      .catch((err) => {
        console.error("Delete error:", err);
        alert("Failed to delete product.");
      });
  };

  if (!product) return <p>Loading...</p>;

  return (
    <div style={{ padding: "20px", maxWidth: "600px", margin: "0 auto" }}>
      <h2>Product Details</h2>
      <img
        src={imageUrl}
        alt={product.name}
        style={{ width: "100%", height: "250px", objectFit: "cover", borderRadius: "10px" }}
      />
      <p><strong>ID:</strong> {product.id}</p>
      <p><strong>Name:</strong> {product.name}</p>
      <p><strong>Description:</strong> {product.description}</p>
      <p><strong>Brand:</strong> {product.brand}</p>
      <p><strong>Category:</strong> {product.category}</p>
      <p><strong>Price:</strong> ${product.price}</p>
      <p><strong>Quantity:</strong> {product.quantity}</p>
      <p><strong>Available:</strong> {product.available ? "Yes" : "No"}</p>
      <p><strong>Release Date:</strong> {new Date(product.releaseDate).toLocaleDateString()}</p>

      <div style={{ marginTop: "20px", display: "flex", gap: "10px" }}>
        <button onClick={() => navigate(`/products/${id}/edit`)} style={buttonStyle}>Update</button>
        <button onClick={handleDelete} style={{ ...buttonStyle, backgroundColor: "#ff4d4d" }}>Delete</button>
      </div>
    </div>
  );
}

const buttonStyle = {
  padding: "10px 20px",
  backgroundColor: "#1e90ff",
  color: "#fff",
  border: "none",
  borderRadius: "5px",
  cursor: "pointer",
};
