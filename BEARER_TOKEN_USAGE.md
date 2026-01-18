# Bearer Token Usage Guide

## Login के बाद Bearer Token कैसे Use करें

### Step 1: Login करें और Token प्राप्त करें
```http
POST http://localhost:8180/sellers/login
Content-Type: application/json

{
    "email": "seller@example.com",
    "otp": "123456"
}
```

**Response में मिलेगा:**
```json
{
    "jwt": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "message": "Login successful",
    "role": "ROLE_SELLER"
}
```

---

## Bearer Token के साथ Protected Endpoints

### Format:
Header में add करें:
```
Authorization: Bearer <your_jwt_token_here>
```

---

## 1. Seller Profile देखने के लिए

```http
GET http://localhost:8180/sellers/profile
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**या Postman/Thunder Client में:**
- Headers tab में
- Key: `Authorization`
- Value: `Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`

---

## 2. Seller Profile Update करने के लिए

```http
PATCH http://localhost:8180/sellers
Authorization: Bearer <your_token>
Content-Type: application/json

{
    "sellerName": "Updated Name",
    "mobile": "9876543210"
}
```

---

## 3. Seller के Products देखने के लिए

```http
GET http://localhost:8180/seller/products
Authorization: Bearer <your_token>
```

---

## 4. Product Create करने के लिए

```http
POST http://localhost:8180/seller/products
Authorization: Bearer <your_token>
Content-Type: application/json

{
    "title": "Product Name",
    "description": "Product Description",
    "price": 1000,
    "discountedPrice": 800,
    "discountPercent": 20,
    "quantity": 100,
    "brand": "Brand Name",
    "color": "Red",
    "sizes": ["S", "M", "L"],
    "imageUrl": "https://example.com/image.jpg",
    "topLevelCategory": "MEN",
    "secondLevelCategory": "CLOTHING",
    "thirdLevelCategory": "SHIRTS"
}
```

---

## 5. User Cart देखने के लिए (Customer के लिए)

```http
GET http://localhost:8180/api/cart
Authorization: Bearer <your_token>
```

---

## 6. User Profile देखने के लिए (Customer के लिए)

```http
GET http://localhost:8180/users/profile
Authorization: Bearer <your_token>
```

---

## Important Notes:

1. **Token Format:** हमेशा `Bearer ` prefix के साथ use करें (Bearer के बाद space जरूरी है)
   ```
   ✅ Correct: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
   ❌ Wrong: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
   ```

2. **Token Expiry:** Token expire हो सकता है, फिर से login करना होगा

3. **Endpoints जिन्हें Token नहीं चाहिए:**
   - `GET /sellers` - सभी sellers देखने के लिए
   - `POST /sellers` - नया seller create करने के लिए
   - `POST /sellers/login` - Login के लिए
   - `POST /sellers/request-login-otp` - OTP request के लिए
   - `PATCH /sellers/verify/{otp}` - Email verification के लिए

---

## Postman में Bearer Token Set करना:

1. Request में **Headers** tab पर जाएं
2. Key में: `Authorization`
3. Value में: `Bearer <your_token>`
4. या **Authorization** tab में:
   - Type: **Bearer Token** select करें
   - Token field में token paste करें

---

## Thunder Client (VS Code Extension) में:

1. Request में **Headers** section में
2. `+ Add Header` click करें
3. Name: `Authorization`
4. Value: `Bearer <your_token>`

---

## cURL Example:

```bash
curl -X GET "http://localhost:8180/sellers/profile" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

---

## JavaScript/Fetch Example:

```javascript
fetch('http://localhost:8180/sellers/profile', {
  method: 'GET',
  headers: {
    'Authorization': 'Bearer ' + yourTokenHere,
    'Content-Type': 'application/json'
  }
})
.then(response => response.json())
.then(data => console.log(data));
```
