// src/pages/Cart.jsx
export default function Cart({ cart }) {
  return (
    <div>
      <h2>Cart</h2>
      {cart.length === 0 ? (
        <p>No items in cart.</p>
      ) : (
        cart.map((p, i) => (
          <div key={i} style={{ border: "1px solid #ccc", margin: "10px", padding: "10px" }}>
            <p><strong>{p.productName}</strong> - ${p.price}</p>
          </div>
        ))
      )}
    </div>
  );
}
