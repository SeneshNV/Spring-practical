import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

export default function UpdateProduct() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [product, setProduct] = useState(null);
  const [imageFile, setImageFile] = useState(null);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/products/${id}`)
      .then((res) => setProduct(res.data))
      .catch(console.error);
  }, [id]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setProduct((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append(
      "product",
      new Blob([JSON.stringify(product)], { type: "application/json" })
    );
    if (imageFile) formData.append("imageFile", imageFile);

    axios
      .put(`http://localhost:8080/api/product/${id}`, formData, {
        headers: { "Content-Type": "multipart/form-data" },
      })
      .then(() => {
        alert("Product updated successfully!");
        navigate(`/products/${id}`);
      })
      .catch((err) => {
        console.error("Update error:", err);
        alert("Failed to update product.");
      });
  };

  if (!product) return <p>Loading...</p>;

  return (
    <form onSubmit={handleSubmit} style={formStyle}>
      <h2>Update Product</h2>
      <input
        name="name"
        value={product.name}
        onChange={handleChange}
        placeholder="Name"
        required
      />
      <input
        name="description"
        value={product.description}
        onChange={handleChange}
        placeholder="Description"
        required
      />
      <input
        name="brand"
        value={product.brand}
        onChange={handleChange}
        placeholder="Brand"
        required
      />
      <input
        name="category"
        value={product.category}
        onChange={handleChange}
        placeholder="Category"
        required
      />
      <input
        name="price"
        type="number"
        value={product.price}
        onChange={handleChange}
        placeholder="Price"
        required
      />
      <input
        name="quantity"
        type="number"
        value={product.quantity}
        onChange={handleChange}
        placeholder="Quantity"
        required
      />
      <input
        name="releaseDate"
        type="date"
        value={product.releaseDate}
        onChange={handleChange}
        required
      />
      <label>
        <input
          type="checkbox"
          name="available"
          checked={product.available}
          onChange={handleChange}
        />
        Available
      </label>
      <input
        type="file"
        accept="image/*"
        onChange={(e) => setImageFile(e.target.files[0])}
      />
      <button type="submit" style={buttonStyle}>
        Update Product
      </button>
    </form>
  );
}

const formStyle = {
  display: "flex",
  flexDirection: "column",
  maxWidth: "400px",
  margin: "0 auto",
  gap: "10px",
};

const buttonStyle = {
  padding: "10px",
  backgroundColor: "#1e90ff",
  color: "white",
  border: "none",
  borderRadius: "5px",
  cursor: "pointer",
};
