// src/pages/Home.jsx
import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useLocation } from "react-router-dom";

export default function Home({ addToCart }) {
  const [products, setProducts] = useState([]);
  const [imageUrls, setImageUrls] = useState({});
  const navigate = useNavigate();
  const location = useLocation();

  const searchParams = new URLSearchParams(location.search);
  const keyword = searchParams.get("search");

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const res = keyword
          ? await axios.get(`http://localhost:8080/api/product/search?keyword=${keyword}`)
          : await axios.get("http://localhost:8080/api/products");
        setProducts(res.data);
      } catch (err) {
        console.error(err);
      }
    };
    fetchProducts();
  }, [keyword]);

  useEffect(() => {
    if (products.length === 0) return;
    let isMounted = true;
    const images = {};

    (async () => {
      for (const product of products) {
        try {
          const response = await axios.get(
            `http://localhost:8080/api/products/${product.id}/image`,
            { responseType: "blob" }
          );
          const imageUrl = URL.createObjectURL(response.data);
          images[product.id] = imageUrl;
        } catch {
          images[product.id] =
            "https://via.placeholder.com/250x150?text=No+Image";
        }
      }
      if (isMounted) setImageUrls(images);
    })();

    return () => {
      isMounted = false;
      Object.values(images).forEach((url) => {
        if (url.startsWith("blob:")) URL.revokeObjectURL(url);
      });
    };
  }, [products]);

  const handleCardClick = (id) => {
    navigate(`/products/${id}`);
  };

  return (
    <div className="home-container">
      <h2 className="home-heading">
        {keyword ? `Search Results for "${keyword}"` : "Product List"}
      </h2>
      <div className="product-grid">
        {products.map((p) => (
          <div
            key={p.id}
            onClick={() => handleCardClick(p.id)}
            className="product-card"
          >
            <img
              src={imageUrls[p.id]}
              alt={p.name}
              className="product-image"
              onError={(e) => {
                const fallback =
                  "https://via.placeholder.com/250x150?text=No+Image";
                if (e.target.src !== fallback) {
                  e.target.src = fallback;
                }
              }}
            />
            <p><strong>Name:</strong> {p.name}</p>
            <p><strong>Price:</strong> ${p.price}</p>
            <button
              className="add-to-cart-btn"
              onClick={(e) => {
                e.stopPropagation();
                addToCart(p);
              }}
            >
              Add to Cart
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
