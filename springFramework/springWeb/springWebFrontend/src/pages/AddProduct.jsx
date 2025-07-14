import { useState } from "react";
import axios from "axios";

export default function AddProduct() {
  const [product, setProduct] = useState({
    name: "",
    description: "",
    brand: "",
    price: "",
    category: "",
    releaseDate: "",
    available: false,
    quantity: "",
  });

  const [imageFile, setImageFile] = useState(null);

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
    formData.append("product", new Blob([JSON.stringify(product)], { type: "application/json" }));
    formData.append("imageFile", imageFile);

    axios
      .post("http://localhost:8080/api/products", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then(() => {
        alert("Product added successfully!");
        setProduct({
          name: "",
          description: "",
          brand: "",
          price: "",
          category: "",
          releaseDate: "",
          available: false,
          quantity: "",
        });
        setImageFile(null);
      })
      .catch((err) => {
        console.error("Error uploading product:", err);
        alert("Failed to add product.");
      });
  };

  return (
    <form onSubmit={handleSubmit} style={styles.form}>
      <h2>Add Product</h2>

      <input name="name" value={product.name} onChange={handleChange} placeholder="Name" required />
      <input name="description" value={product.description} onChange={handleChange} placeholder="Description" required />
      <input name="brand" value={product.brand} onChange={handleChange} placeholder="Brand" required />
      <input name="category" value={product.category} onChange={handleChange} placeholder="Category" required />
      <input name="price" type="number" value={product.price} onChange={handleChange} placeholder="Price" required />
      <input name="quantity" type="number" value={product.quantity} onChange={handleChange} placeholder="Quantity" required />
      <input name="releaseDate" type="date" value={product.releaseDate} onChange={handleChange} required />
      <label>
        <input type="checkbox" name="available" checked={product.available} onChange={handleChange} />
        Available
      </label>

      <input type="file" accept="image/*" onChange={(e) => setImageFile(e.target.files[0])} required />

      <button type="submit" style={styles.button}>Add Product</button>
    </form>
  );
}

const styles = {
  form: {
    display: "flex",
    flexDirection: "column",
    maxWidth: "400px",
    margin: "0 auto",
    gap: "10px",
  },
  button: {
    padding: "10px",
    backgroundColor: "#1e90ff",
    color: "white",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
  },
};
