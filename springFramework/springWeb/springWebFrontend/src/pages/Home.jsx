import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function Home({ addToCart }) {
  const [products, setProducts] = useState([]);
  const [imageUrls, setImageUrls] = useState({});
  const navigate = useNavigate();

  // Fetch products once
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/products")
      .then((res) => setProducts(res.data))
      .catch(console.error);
  }, []);

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
    <div style={styles.container}>
      <h2 style={styles.heading}>Product List</h2>
      <div style={styles.grid}>
        {products.map((p) => (
          <div
            key={p.id}
            onClick={() => handleCardClick(p.id)}
            style={styles.card}
          >
            <img
              src={imageUrls[p.id]}
              alt={p.name}
              style={styles.image}
              onError={(e) => {
                const fallback =
                  "https://via.placeholder.com/250x150?text=No+Image";
                if (e.target.src !== fallback) {
                  e.target.src = fallback;
                }
              }}
            />

            <p>
              <strong>Name:</strong> {p.name}
            </p>
            <p>
              <strong>Price:</strong> ${p.price}
            </p>
            <button
              style={styles.button}
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

// ...styles as before

const styles = {
  container: {
    padding: "30px",
    fontFamily: "Arial, sans-serif",
  },
  heading: {
    textAlign: "center",
    marginBottom: "30px",
    fontSize: "28px",
  },
  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fill, minmax(250px, 1fr))",
    gap: "20px",
  },
  card: {
    backgroundColor: "#fff",
    border: "1px solid #ddd",
    borderRadius: "10px",
    padding: "20px",
    color: "#000",
    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.05)",
    transition: "transform 0.2s, box-shadow 0.2s",
    cursor: "pointer",
  },
  image: {
    width: "100%",
    height: "150px",
    objectFit: "cover",
    borderRadius: "8px",
    marginBottom: "10px",
  },
  button: {
    marginTop: "10px",
    padding: "10px 15px",
    backgroundColor: "#1e90ff",
    color: "#fff",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
    fontWeight: "bold",
  },
};
