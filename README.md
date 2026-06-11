# Bazaaro - Complete E-Commerce Platform Documentation

## 📋 Table of Contents
1. [Project Overview](#project-overview)
2. [Complete Project Structure](#complete-project-structure)
3. [Technology Stack](#technology-stack)
4. [How the System Works](#how-the-system-works)
5. [Features and Services A-Z](#features-and-services-a-z)
6. [Database Schema](#database-schema)
7. [API Endpoints](#api-endpoints)
8. [Authentication Flow](#authentication-flow)
9. [Payment Integration](#payment-integration)
10. [Setup Instructions](#setup-instructions)
11. [Configuration Details](#configuration-details)
12. [Security Implementation](#security-implementation)

---

## 🎯 Project Overview

**Bazaaro** is a comprehensive multi-role e-commerce platform built with Spring Boot (Backend) and React.js (Frontend). The platform supports three distinct user roles: **Customers**, **Sellers**, and **Admins**, each with specific functionalities and permissions.

### Key Highlights
- **Multi-Role Architecture**: Customer, Seller, and Admin roles with dedicated features
- **OTP-Based Authentication**: Secure login/signup using email OTP verification
- **JWT Token Security**: Stateless authentication with JWT tokens
- **Dual Payment Gateway**: Integration with both Razorpay and Stripe
- **Email Service**: Automated email notifications for OTP and verification
- **Complete E-Commerce Flow**: From product browsing to order delivery
- **Seller Management**: Seller registration, verification, and product management
- **Admin Dashboard**: Complete control over users, sellers, and platform management

---

## 📁 Complete Project Structure

```
Bazaaro-app/
├── src/
│   ├── main/
│   │   ├── java/com/rishi/
│   │   │   ├── BazaaroappApplication.java          # Main Spring Boot Application
│   │   │   │
│   │   │   ├── config/                             # Configuration Classes
│   │   │   │   ├── AppConfig.java                  # Security & CORS Configuration
│   │   │   │   ├── JwtProvider.java                # JWT Token Generation
│   │   │   │   ├── JwtTokenValidator.java          # JWT Token Validation Filter
│   │   │   │   └── JWT_CONSTANT.java               # JWT Constants
│   │   │   │
│   │   │   ├── controller/                         # REST API Controllers (18)
│   │   │   │   ├── AdminController.java           # Admin operations
│   │   │   │   ├── AdminCouponController.java     # Coupon management
│   │   │   │   ├── AuthController.java            # Authentication endpoints
│   │   │   │   ├── CartController.java            # Shopping cart operations
│   │   │   │   ├── DealController.java            # Deal management
│   │   │   │   ├── HomeCategoryController.java    # Home page categories
│   │   │   │   ├── HomeController.java            # Home page data
│   │   │   │   ├── OrderController.java           # Order management
│   │   │   │   ├── PaymentController.java         # Payment processing
│   │   │   │   ├── ProductController.java         # Product operations
│   │   │   │   ├── ReviewController.java          # Product reviews
│   │   │   │   ├── SellerController.java          # Seller management
│   │   │   │   ├── SellerOrderController.java      # Seller order operations
│   │   │   │   ├── SellerProductController.java    # Seller product management
│   │   │   │   ├── TransactionController.java      # Transaction tracking
│   │   │   │   ├── UserController.java            # User operations
│   │   │   │   └── WishlistController.java        # Wishlist operations
│   │   │   │
│   │   │   ├── domain/                             # Enum Definitions (7)
│   │   │   │   ├── AccountStatus.java             # Account status states
│   │   │   │   ├── HomeCategorySection.java       # Home section types
│   │   │   │   ├── OrderStatus.java               # Order status states
│   │   │   │   ├── PaymentMethod.java             # Payment methods
│   │   │   │   ├── PaymentOrderStatus.java        # Payment order status
│   │   │   │   ├── PaymentStatus.java             # Payment status states
│   │   │   │   └── USER_ROLE.java                 # User roles (ADMIN, CUSTOMER, SELLER)
│   │   │   │
│   │   │   ├── modal/                              # Entity Models (22)
│   │   │   │   ├── Address.java                   # User address
│   │   │   │   ├── BankDetails.java               # Seller bank details
│   │   │   │   ├── BusinessDetails.java           # Seller business details
│   │   │   │   ├── Cart.java                      # Shopping cart
│   │   │   │   ├── CartItem.java                  # Cart items
│   │   │   │   ├── Category.java                  # Product categories
│   │   │   │   ├── Coupon.java                    # Discount coupons
│   │   │   │   ├── Deal.java                      # Special deals
│   │   │   │   ├── Home.java                      # Home page configuration
│   │   │   │   ├── HomeCategory.java              # Home category items
│   │   │   │   ├── Order.java                     # Order details
│   │   │   │   ├── OrderItem.java                 # Order items
│   │   │   │   ├── PaymentDetails.java            # Payment information
│   │   │   │   ├── PaymentOrder.java              # Payment order
│   │   │   │   ├── Product.java                   # Product details
│   │   │   │   ├── Review.java                    # Product reviews
│   │   │   │   ├── Seller.java                    # Seller profile
│   │   │   │   ├── SellerReport.java              # Seller analytics
│   │   │   │   ├── Transaction.java               # Payment transactions
│   │   │   │   ├── User.java                      # User profile
│   │   │   │   ├── VerificationCode.java          # OTP verification codes
│   │   │   │   └── Wishlist.java                  # User wishlist
│   │   │   │
│   │   │   ├── repository/                         # JPA Repositories (18)
│   │   │   │   ├── AddressRepository.java
│   │   │   │   ├── CartItemRepository.java
│   │   │   │   ├── CartRepository.java
│   │   │   │   ├── CategoryRepository.java
│   │   │   │   ├── CouponRepository.java
│   │   │   │   ├── DealRepository.java
│   │   │   │   ├── HomeCategoryRepository.java
│   │   │   │   ├── OrderItemRepository.java
│   │   │   │   ├── OrderRepository.java
│   │   │   │   ├── PaymentOrderRepository.java
│   │   │   │   ├── ProductRepository.java
│   │   │   │   ├── ReviewRepository.java
│   │   │   │   ├── SellerReportRepository.java
│   │   │   │   ├── SellerRepository.java
│   │   │   │   ├── TransactionRepository.java
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── VerificationCodeRepository.java
│   │   │   │   └── WishlistRepository.java
│   │   │   │
│   │   │   ├── request/                            # Request DTOs (5)
│   │   │   │   ├── AddItemRequest.java            # Add item to cart
│   │   │   │   ├── CreateProductRequest.java      # Create product
│   │   │   │   ├── CreateReviewRequest.java       # Create review
│   │   │   │   ├── LoginOtpRequest.java           # OTP login request
│   │   │   │   └── LoginRequest.java              # Login request
│   │   │   │
│   │   │   ├── response/                           # Response DTOs (3)
│   │   │   │   ├── ApiResponse.java               # Generic API response
│   │   │   │   ├── AuthResponse.java              # Authentication response
│   │   │   │   ├── PaymentLinkResponse.java       # Payment link response
│   │   │   │   └── SignupRequest.java             # Signup request
│   │   │   │
│   │   │   ├── service/                            # Service Interfaces (17)
│   │   │   │   ├── AuthService.java               # Authentication service
│   │   │   │   ├── CartItemService.java           # Cart item operations
│   │   │   │   ├── CartService.java               # Cart operations
│   │   │   │   ├── CouponService.java             # Coupon management
│   │   │   │   ├── DealService.java               # Deal management
│   │   │   │   ├── EmailService.java              # Email operations
│   │   │   │   ├── HomeCategoryService.java       # Home category service
│   │   │   │   ├── HomeService.java               # Home page service
│   │   │   │   ├── OrderService.java              # Order operations
│   │   │   │   ├── PaymentService.java            # Payment processing
│   │   │   │   ├── ProductService.java            # Product operations
│   │   │   │   ├── ReviewService.java             # Review operations
│   │   │   │   ├── SellerReportService.java       # Seller reports
│   │   │   │   ├── SellerService.java             # Seller operations
│   │   │   │   ├── TransactionService.java        # Transaction operations
│   │   │   │   ├── UserService.java               # User operations
│   │   │   │   └── WishlistService.java           # Wishlist operations
│   │   │   │
│   │   │   ├── service/impl/                       # Service Implementations (17)
│   │   │   │   ├── AuthServiceImpl.java
│   │   │   │   ├── CartItemServiceImpl.java
│   │   │   │   ├── CartServiceImpl.java
│   │   │   │   ├── CouponServiceImpl.java
│   │   │   │   ├── CustomUserServiceImpl.java
│   │   │   │   ├── DealServiceImpl.java
│   │   │   │   ├── HomeCategoryServiceImpl.java
│   │   │   │   ├── HomeServiceImpl.java
│   │   │   │   ├── OrderServiceImpl.java
│   │   │   │   ├── PaymentServiceImpl.java
│   │   │   │   ├── ProductServiceImpl.java
│   │   │   │   ├── ReviewServiceImpl.java
│   │   │   │   ├── SellerServiceImpl.java
│   │   │   │   ├── TransactionServiceImpl.java
│   │   │   │   ├── UserServiceImpl.java
│   │   │   │   ├── WishlistServiceImpl.java
│   │   │   │   └── sellerReportServiceImpl.java
│   │   │   │
│   │   │   ├── exceptions/                         # Exception Handling (4)
│   │   │   │   ├── ErrorDetails.java              # Error details
│   │   │   │   ├── GlobleException.java           # Global exception handler
│   │   │   │   ├── ProductException.java          # Product exceptions
│   │   │   │   └── SellerException.java           # Seller exceptions
│   │   │   │
│   │   │   └── utils/                              # Utility Classes
│   │   │       └── OtpUtil.java                  # OTP generation utility
│   │   │
│   │   └── resources/
│   │       ├── application.properties              # Application configuration
│   │       ├── static/                            # Static resources
│   │       └── templates/                         # Thymeleaf templates
│   │
│   └── test/                                      # Test classes
│
├── pom.xml                                        # Maven dependencies
├── mvnw, mvnw.cmd                                 # Maven wrapper scripts
├── .gitignore                                     # Git ignore rules
└── README.md                                      # This file
```

---

## 🛠️ Technology Stack

### Backend Technologies
- **Spring Boot 3.2.3** - Main framework
- **Java 21** - Programming language
- **Spring Security** - Security framework
- **Spring Data JPA** - Database ORM
- **MySQL** - Relational database
- **JWT (jjwt 0.11.5)** - Token-based authentication
- **Lombok 1.18.38** - Reduce boilerplate code
- **Razorpay Java SDK 1.4.8** - Payment gateway
- **Stripe Java SDK 31.3.0** - Payment gateway
- **Spring Boot Mail** - Email service
- **Spring Boot Validation** - Input validation
- **Spring Boot Actuator** - Application monitoring

### Frontend Technologies (Expected)
- **React.js** - Frontend framework
- **TypeScript** - Type-safe JavaScript
- **Tailwind CSS** - Styling
- **Axios** - HTTP client

### Development Tools
- **Maven** - Build tool
- **Git** - Version control
- **MySQL Workbench** - Database management

---

## 🔄 How the System Works

### System Architecture Overview

The Bazaaro platform follows a **three-tier architecture**:

1. **Presentation Layer (Controllers)**
   - Handles HTTP requests and responses
   - Validates incoming data
   - Returns appropriate HTTP status codes
   - Manages JWT token validation

2. **Business Logic Layer (Services)**
   - Implements core business rules
   - Processes data transformations
   - Manages transaction boundaries
   - Coordinates between multiple repositories

3. **Data Access Layer (Repositories)**
   - Interacts with MySQL database
   - Uses Spring Data JPA for CRUD operations
   - Handles custom queries using @Query annotations
   - Manages entity relationships

### Request Flow

```
Client (React/Frontend)
    ↓
HTTP Request (with JWT Token)
    ↓
Controller Layer (Validates Token)
    ↓
Service Layer (Business Logic)
    ↓
Repository Layer (Database Operations)
    ↓
MySQL Database
    ↓
Response (JSON)
    ↓
Client
```

### Authentication Flow

1. **User Registration/Signup**
   - User provides email and full name
   - System generates OTP and sends to email
   - User verifies OTP
   - System creates user account
   - System generates JWT token
   - Token returned to client

2. **User Login**
   - User requests OTP for login
   - System generates and sends OTP to email
   - User provides email and OTP
   - System validates OTP
   - System generates JWT token
   - Token returned to client

3. **JWT Token Validation**
   - Every protected request includes JWT token in Authorization header
   - JwtTokenValidator filter intercepts request
   - Token is validated using JwtProvider
   - User details extracted from token
   - Request proceeds to controller if valid

### Order Processing Flow

```
User browses products
    ↓
Adds items to cart
    ↓
Applies coupon (optional)
    ↓
Proceeds to checkout
    ↓
Selects shipping address
    ↓
Selects payment method (Razorpay/Stripe)
    ↓
System creates order
    ↓
System creates payment order
    ↓
Payment link generated
    ↓
User completes payment
    ↓
Order status updated to PLACED
    ↓
Seller confirms order
    ↓
Order shipped
    ↓
Order delivered
```

---

## ✨ Features and Services A-Z

### A - Authentication & Authorization
- **OTP-Based Login/Signup**: Secure authentication using email OTP
- **JWT Token Authentication**: Stateless token-based security
- **Role-Based Access Control**: Three roles - ADMIN, CUSTOMER, SELLER
- **Password Encryption**: BCrypt password encoding
- **Email Verification**: Seller email verification system

### B - Business Management
- **Seller Registration**: Complete seller onboarding
- **Business Details**: Store business information
- **Bank Details**: Store seller bank information
- **Pickup Address**: Seller pickup location management
- **GSTIN Management**: GST number handling
- **Account Status**: PENDING_VERIFICATION, ACTIVE, SUSPENDED, DEACTIVATED, BANNED, CLOSED

### C - Cart Management
- **Add to Cart**: Add products to shopping cart
- **Update Cart Item**: Modify quantity in cart
- **Remove from Cart**: Delete items from cart
- **Cart Calculation**: Automatic price calculation
- **Coupon Application**: Apply discount coupons
- **Total Price Calculation**: MRP, selling price, discount calculation

### D - Deal Management
- **Create Deals**: Admin can create special deals
- **Deal Duration**: Set deal validity period
- **Deal Discounts**: Configure discount percentages
- **Active Deals**: View currently active deals

### E - Email Services
- **OTP Sending**: Send OTP for login/signup
- **Verification Emails**: Send verification emails to sellers
- **SMTP Configuration**: Gmail SMTP integration
- **Email Templates**: Structured email formatting

### F - Filtering & Search
- **Product Search**: Search products by query
- **Category Filter**: Filter by product category
- **Brand Filter**: Filter by brand
- **Color Filter**: Filter by color
- **Size Filter**: Filter by size
- **Price Range**: Filter by min/max price
- **Discount Filter**: Filter by minimum discount
- **Stock Filter**: Filter by availability
- **Sorting**: Sort by price, rating, etc.
- **Pagination**: Paginated product listing

### G - Order Management
- **Create Order**: Place orders from cart
- **Order History**: View past orders
- **Order Status Tracking**: PENDING, PLACED, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
- **Order Cancellation**: Cancel orders before shipping
- **Order Details**: View complete order information
- **Order Item Details**: View individual order items
- **Delivery Date**: Automatic delivery date calculation (7 days)

### H - Home Page Management
- **Home Categories**: Manage home page category sections
- **Home Configuration**: Configure home page layout
- **Category Sections**: Different sections for different categories
- **Dynamic Home Content**: Customizable home page

### I - Inventory Management
- **Product Quantity**: Track product stock
- **Stock Updates**: Update product availability
- **Low Stock Alerts**: Monitor inventory levels

### J - JWT Security
- **Token Generation**: Generate JWT tokens for authenticated users
- **Token Validation**: Validate tokens on each request
- **Token Expiration**: Automatic token expiration
- **Refresh Mechanism**: Token refresh capability

### K - Key Features
- **Multi-Role Support**: Admin, Customer, Seller roles
- **RESTful APIs**: Clean REST API design
- **Stateless Authentication**: No session management
- **CORS Configuration**: Cross-origin resource sharing
- **Exception Handling**: Global exception handling

### L - Location Services
- **Address Management**: Multiple addresses per user
- **Shipping Address**: Select shipping address for orders
- **Pickup Address**: Seller pickup location
- **Address CRUD**: Create, read, update, delete addresses

### M - Payment Integration
- **Razorpay Integration**: Razorpay payment gateway
- **Stripe Integration**: Stripe payment gateway
- **Payment Links**: Generate payment links
- **Payment Status Tracking**: PENDING, COMPLETED, FAILED
- **Payment Order Management**: Track payment orders
- **Transaction History**: View all transactions
- **Payment Methods**: RAZORPAY, STRIPE

### N - Notification System
- **Email Notifications**: OTP and verification emails
- **Order Status Updates**: Email notifications for order updates

### O - Order Processing
- **Cart to Order**: Convert cart to order
- **Order Confirmation**: Confirm order placement
- **Order Fulfillment**: Process order delivery
- **Order Cancellation**: Handle order cancellations
- **Refund Processing**: Process refunds for cancelled orders

### P - Product Management
- **Product Creation**: Sellers can create products
- **Product Updates**: Modify product details
- **Product Deletion**: Remove products
- **Product Images**: Multiple product images
- **Product Categories**: Categorize products
- **Product Pricing**: MRP, selling price, discount
- **Product Variants**: Color, size options
- **Product Reviews**: Customer reviews and ratings
- **Product Search**: Advanced search functionality

### Q - Quality Control
- **Review System**: Customer reviews on products
- **Rating System**: Star ratings for products
- **Review Moderation**: Admin can moderate reviews

### R - Reporting & Analytics
- **Seller Reports**: Sales analytics for sellers
- **Order Reports**: Order statistics
- **Transaction Reports**: Payment transaction reports
- **Revenue Tracking**: Track seller revenue
- **Cancellation Tracking**: Track cancelled orders
- **Refund Tracking**: Track refund amounts

### S - Seller Management
- **Seller Registration**: New seller signup
- **Seller Verification**: Email verification process
- **Seller Profile**: Manage seller information
- **Seller Products**: Manage seller's products
- **Seller Orders**: View and manage seller orders
- **Seller Reports**: View seller analytics
- **Seller Status**: Active, suspended, banned status

### T - Transaction Management
- **Transaction Recording**: Record all transactions
- **Transaction History**: View transaction history
- **Payment Links**: Generate payment links
- **Transaction Status**: Track transaction status

### U - User Management
- **User Registration**: Customer signup
- **User Login**: Customer login with OTP
- **User Profile**: Manage user profile
- **User Addresses**: Manage multiple addresses
- **User Wishlist**: Save favorite products
- **User Orders**: View order history
- **User Reviews**: Write product reviews

### V - Verification System
- **OTP Generation**: Generate secure OTPs
- **OTP Validation**: Validate OTPs
- **Email Verification**: Verify seller emails
- **One-Time Use**: OTPs are single-use

### W - Wishlist Management
- **Add to Wishlist**: Save products to wishlist
- **View Wishlist**: View saved products
- **Remove from Wishlist**: Remove from wishlist
- **Move to Cart**: Move wishlist items to cart

### X - XML Configuration
- **Maven Configuration**: pom.xml for dependencies
- **Application Properties**: application.properties for configuration

### Y - YAML Support
- **Configuration Management**: Externalized configuration

### Z - Zero Downtime
- **Hot Reload**: Spring Boot DevTools for development
- **Database Migration**: JPA auto-update for schema changes

---

## 🗄️ Database Schema

### User Table
- **id** (Long, Primary Key, Auto-generated)
- **email** (String, Unique)
- **password** (String, BCrypt encrypted)
- **fullName** (String)
- **mobile** (String)
- **role** (USER_ROLE Enum: ROLE_CUSTOMER, ROLE_SELLER, ROLE_ADMIN)
- **addresses** (One-to-Many relationship with Address)
- **usedCoupons** (Many-to-Many relationship with Coupon)

### Seller Table
- **id** (Long, Primary Key, Auto-generated)
- **sellerName** (String)
- **mobile** (String)
- **email** (String, Unique, Not Null)
- **password** (String)
- **businessDetails** (Embedded: BusinessDetails)
- **bankDetails** (Embedded: BankDetails)
- **pickupAddress** (One-to-One with Address)
- **GSTIN** (String)
- **role** (USER_ROLE: ROLE_SELLER)
- **isEmailVerified** (Boolean)
- **accountStatus** (AccountStatus Enum)

### Product Table
- **id** (Long, Primary Key, Auto-generated)
- **title** (String)
- **description** (String)
- **mrpPrice** (Integer)
- **sellingPrice** (Integer)
- **discountPercent** (Integer)
- **quantity** (Integer)
- **color** (String)
- **images** (ElementCollection: List<String>)
- **numRatings** (Integer)
- **category** (ManyToOne with Category)
- **seller** (ManyToOne with Seller)
- **createdAt** (LocalDateTime)
- **Sizes** (String)
- **reviews** (OneToMany with Review)

### Order Table
- **id** (Long, Primary Key, Auto-generated)
- **orderId** (String)
- **user** (ManyToOne with User)
- **sellerId** (Long)
- **orderItems** (OneToMany with OrderItem)
- **shippingAddress** (ManyToOne with Address)
- **paymentDetails** (Embedded: PaymentDetails)
- **totalMrpPrice** (Double)
- **totalSellingPrice** (Integer)
- **discount** (Integer)
- **orderStatus** (OrderStatus Enum)
- **totalItem** (Integer)
- **paymentStatus** (PaymentStatus Enum)
- **orderDate** (LocalDateTime)
- **deliverDate** (LocalDateTime)

### Cart Table
- **id** (Long, Primary Key, Auto-generated)
- **user** (OneToOne with User)
- **cartItems** (OneToMany with CartItem)
- **totalSellingPrice** (Double)
- **totalItem** (Integer)
- **totalMrpPrice** (Integer)
- **discount** (Integer)
- **couponCode** (String)

### Category Table
- **id** (Long, Primary Key, Auto-generated)
- **name** (String)
- **description** (String)
- **level** (Integer)
- **parentCategory** (ManyToOne with Category - self-referencing)
- **image** (String)

### Coupon Table
- **id** (Long, Primary Key, Auto-generated)
- **code** (String, Unique)
- **discountPercent** (Integer)
- **minimumOrderValue** (Integer)
- **maximumUses** (Integer)
- **expiryDate** (LocalDateTime)
- **users** (Many-to-Many with User)

### Address Table
- **id** (Long, Primary Key, Auto-generated)
- **user** (ManyToOne with User)
- **street** (String)
- **city** (String)
- **state** (String)
- **pincode** (String)
- **country** (String)
- **mobile** (String)

### Review Table
- **id** (Long, Primary Key, Auto-generated)
- **product** (ManyToOne with Product)
- **user** (ManyToOne with User)
- **rating** (Integer)
- **review** (String)
- **createdAt** (LocalDateTime)

### PaymentOrder Table
- **id** (Long, Primary Key, Auto-generated)
- **user** (ManyToOne with User)
- **amount** (Integer)
- **paymentLinkId** (String)
- **paymentStatus** (PaymentOrderStatus Enum)
- **createdAt** (LocalDateTime)

### Transaction Table
- **id** (Long, Primary Key, Auto-generated)
- **user** (ManyToOne with User)
- **seller** (ManyToOne with Seller)
- **amount** (Integer)
- **paymentMethod** (PaymentMethod Enum)
- **status** (String)
- **createdAt** (LocalDateTime)

### VerificationCode Table
- **id** (Long, Primary Key, Auto-generated)
- **email** (String)
- **otp** (String)
- **expiryTime** (LocalDateTime)

### Wishlist Table
- **id** (Long, Primary Key, Auto-generated)
- **user** (ManyToOne with User)
- **product** (ManyToOne with Product)

### SellerReport Table
- **id** (Long, Primary Key, Auto-generated)
- **seller** (ManyToOne with Seller)
- **totalEarnings** (Integer)
- **totalOrders** (Integer)
- **cancelledOrders** (Integer)
- **totalRefunds** (Integer)
- **createdAt** (LocalDateTime)

---

## 🔌 API Endpoints

### Authentication Endpoints

#### POST /auth/signup
- **Description**: Register a new customer
- **Request Body**: SignupRequest (email, fullName, otp)
- **Response**: AuthResponse (jwt, message, role)
- **Authentication**: Not required

#### POST /auth/sent/login-signup-otp
- **Description**: Send OTP for login/signup
- **Request Body**: LoginOtpRequest (email, role)
- **Response**: ApiResponse (message)
- **Authentication**: Not required

#### POST /auth/signing
- **Description**: Login with OTP
- **Request Body**: LoginRequest (email, otp)
- **Response**: AuthResponse (jwt, message, role)
- **Authentication**: Not required

### Seller Endpoints

#### POST /sellers
- **Description**: Create a new seller
- **Request Body**: Seller (sellerName, mobile, email, password, businessDetails, bankDetails, pickupAddress, GSTIN)
- **Response**: Seller object
- **Authentication**: Not required

#### POST /sellers/request-login-otp
- **Description**: Request OTP for seller login
- **Request Body**: LoginOtpRequest (email, role)
- **Response**: ApiResponse (message)
- **Authentication**: Not required

#### POST /sellers/login
- **Description**: Login as seller
- **Request Body**: LoginRequest (email, otp)
- **Response**: AuthResponse (jwt, message, role)
- **Authentication**: Not required

#### PATCH /sellers/verify/{otp}
- **Description**: Verify seller email with OTP
- **Path Variable**: otp (String)
- **Response**: Seller object
- **Authentication**: Not required

#### GET /sellers/profile
- **Description**: Get seller profile by JWT token
- **Headers**: Authorization (JWT token)
- **Response**: Seller object
- **Authentication**: Required (SELLER role)

#### PATCH /sellers
- **Description**: Update seller profile
- **Headers**: Authorization (JWT token)
- **Request Body**: Seller object
- **Response**: Updated Seller object
- **Authentication**: Required (SELLER role)

#### GET /sellers
- **Description**: Get all sellers (optional filter by status)
- **Query Param**: status (AccountStatus enum)
- **Response**: List of Seller objects
- **Authentication**: Required (ADMIN role)

#### GET /sellers/{id}
- **Description**: Get seller by ID
- **Path Variable**: id (Long)
- **Response**: Seller object
- **Authentication**: Required

#### DELETE /sellers/{id}
- **Description**: Delete seller by ID
- **Path Variable**: id (Long)
- **Response**: Void (204 No Content)
- **Authentication**: Required (ADMIN role)

### Product Endpoints

#### GET /products
- **Description**: Get all products with filtering and pagination
- **Query Params**: 
  - category (String)
  - brand (String)
  - color (String)
  - size (String)
  - minPrice (Integer)
  - maxPrice (Integer)
  - minDiscount (Integer)
  - sort (String)
  - stock (String)
  - pageNumber (Integer, default: 0)
- **Response**: Page<Product>
- **Authentication**: Not required

#### GET /products/{productId}
- **Description**: Get product by ID
- **Path Variable**: productId (Long)
- **Response**: Product object
- **Authentication**: Not required

#### GET /products/search
- **Description**: Search products by query
- **Query Param**: query (String)
- **Response**: List<Product>
- **Authentication**: Not required

### Cart Endpoints

#### GET /api/cart/user
- **Description**: Get user's cart
- **Headers**: Authorization (JWT token)
- **Response**: Cart object
- **Authentication**: Required (CUSTOMER role)

#### PUT /api/cart/add
- **Description**: Add item to cart
- **Headers**: Authorization (JWT token)
- **Request Body**: AddItemRequest
- **Response**: Cart object
- **Authentication**: Required (CUSTOMER role)

### Order Endpoints

#### POST /api/orders
- **Description**: Create order from cart
- **Headers**: Authorization (JWT token)
- **Request Body**: Address (shippingAddress)
- **Query Param**: paymentMethod (PaymentMethod enum)
- **Response**: PaymentLinkResponse (payment_link_url, payment_link_id)
- **Authentication**: Required (CUSTOMER role)

#### GET /api/orders/user
- **Description**: Get user's order history
- **Headers**: Authorization (JWT token)
- **Response**: List<Order>
- **Authentication**: Required (CUSTOMER role)

#### GET /api/orders/{orderId}
- **Description**: Get order by ID
- **Headers**: Authorization (JWT token)
- **Path Variable**: orderId (Long)
- **Response**: Order object
- **Authentication**: Required

#### GET /api/orders/item/{orderItemId}
- **Description**: Get order item by ID
- **Headers**: Authorization (JWT token)
- **Path Variable**: orderItemId (Long)
- **Response**: OrderItem object
- **Authentication**: Required

#### PUT /api/orders/{orderId}/cancel
- **Description**: Cancel order
- **Headers**: Authorization (JWT token)
- **Path Variable**: orderId (Long)
- **Response**: Order object
- **Authentication**: Required (CUSTOMER role)

### Wishlist Endpoints

#### POST /api/wishlist
- **Description**: Add product to wishlist
- **Headers**: Authorization (JWT token)
- **Request Body**: Product object
- **Response**: Wishlist object
- **Authentication**: Required (CUSTOMER role)

#### GET /api/wishlist
- **Description**: Get user's wishlist
- **Headers**: Authorization (JWT token)
- **Response**: List<Wishlist>
- **Authentication**: Required (CUSTOMER role)

### Review Endpoints

#### POST /api/products/{productId}/reviews
- **Description**: Create product review
- **Headers**: Authorization (JWT token)
- **Path Variable**: productId (Long)
- **Request Body**: CreateReviewRequest
- **Response**: Review object
- **Authentication**: Required (CUSTOMER role)

#### GET /api/products/{productId}/reviews
- **Description**: Get product reviews
- **Path Variable**: productId (Long)
- **Response**: List<Review>
- **Authentication**: Not required

### Payment Endpoints

#### POST /api/payments/{paymentId}
- **Description**: Process payment
- **Headers**: Authorization (JWT token)
- **Path Variable**: paymentId (Long)
- **Response**: PaymentLinkResponse
- **Authentication**: Required

### Admin Endpoints

#### GET /api/admin/all-users
- **Description**: Get all users
- **Headers**: Authorization (JWT token)
- **Response**: List<User>
- **Authentication**: Required (ADMIN role)

#### GET /api/admin/all-sellers
- **Description**: Get all sellers
- **Headers**: Authorization (JWT token)
- **Response**: List<Seller>
- **Authentication**: Required (ADMIN role)

### Home Endpoints

#### GET /api/home/active-deals
- **Description**: Get active deals
- **Response**: List<Deal>
- **Authentication**: Not required

#### GET /api/home/pages
- **Description**: Get home page data
- **Response**: Home object
- **Authentication**: Not required

---

## 🔐 Authentication Flow

### Step-by-Step Authentication Process

#### 1. Customer Signup Flow
```
1. Customer submits signup request with email and full name
   POST /auth/signup
   Body: { "email": "user@example.com", "fullName": "John Doe" }

2. System generates 6-digit OTP using OtpUtil.generateOtp()

3. System stores OTP in VerificationCode table with email

4. System sends OTP email using EmailService.sendVerificationOtpEmail()

5. Customer receives OTP in email

6. Customer submits OTP verification
   POST /auth/signup
   Body: { "email": "user@example.com", "fullName": "John Doe", "otp": "123456" }

7. AuthServiceImpl validates OTP from VerificationCode table

8. If valid, system creates User entity with ROLE_CUSTOMER

9. System creates Cart entity for the user

10. System generates JWT token using JwtProvider.generateToken()

11. System deletes OTP from VerificationCode table (one-time use)

12. JWT token returned to client
```

#### 2. Customer Login Flow
```
1. Customer requests OTP for login
   POST /auth/sent/login-signup-otp
   Body: { "email": "user@example.com", "role": "ROLE_CUSTOMER" }

2. System checks if user exists in UserRepository

3. If user exists, system generates OTP

4. System stores OTP in VerificationCode table

5. System sends OTP email

6. Customer receives OTP

7. Customer submits login with OTP
   POST /auth/signing
   Body: { "email": "user@example.com", "otp": "123456" }

8. AuthServiceImpl.authenticate() validates:
   - User exists in database
   - OTP exists in VerificationCode table
   - OTP matches

9. If valid, system generates JWT token

10. System deletes OTP from VerificationCode table

11. JWT token returned to client
```

#### 3. Seller Registration Flow
```
1. Seller submits registration
   POST /sellers
   Body: { "sellerName": "Store Name", "email": "seller@example.com", 
           "password": "password", "businessDetails": {...}, 
           "bankDetails": {...}, "pickupAddress": {...}, "GSTIN": "GSTIN123" }

2. SellerService creates Seller entity with ROLE_SELLER

3. System sets accountStatus to PENDING_VERIFICATION

4. System generates OTP for email verification

5. System stores OTP in VerificationCode table

6. System sends verification email with OTP

7. Seller receives verification email

8. Seller verifies email
   PATCH /sellers/verify/{otp}
   Path: otp

9. System validates OTP

10. If valid, system updates seller.isEmailVerified = true

11. System updates seller.accountStatus = ACTIVE

12. Seller can now login and manage products
```

#### 4. Seller Login Flow
```
1. Seller requests OTP for login
   POST /sellers/request-login-otp
   Body: { "email": "seller@example.com", "role": "ROLE_SELLER" }

2. System checks if seller exists in SellerRepository

3. If seller exists, system generates OTP

4. System stores OTP in VerificationCode table

5. System sends OTP email

6. Seller receives OTP

7. Seller submits login with OTP (email prefixed with "seller_")
   POST /sellers/login
   Body: { "email": "seller@example.com", "otp": "123456" }

8. AuthServiceImpl.authenticate() validates:
   - Seller exists in database
   - OTP exists in VerificationCode table
   - OTP matches
   - Removes "seller_" prefix if present

9. If valid, system generates JWT token with ROLE_SELLER

10. System deletes OTP from VerificationCode table

11. JWT token returned to client
```

#### 5. JWT Token Validation Flow
```
1. Client makes request to protected endpoint
   Headers: Authorization: Bearer <jwt_token>

2. JwtTokenValidator filter intercepts request

3. Filter extracts JWT token from Authorization header

4. JwtProvider validates token:
   - Checks token signature
   - Checks token expiration
   - Extracts user email from token

5. CustomUserServiceImpl loads user details from database

6. SecurityContextHolder sets authentication context

7. Request proceeds to controller if valid

8. Controller can access user details from SecurityContext
```

---

## 💳 Payment Integration

### Razorpay Integration

#### Configuration
- **Razorpay Key ID**: Configured in application properties
- **Razorpay Key Secret**: Configured in application properties
- **Payment Link Generation**: Dynamic payment link creation

#### Payment Flow
```
1. User places order with payment method RAZORPAY
   POST /api/orders
   Query: paymentMethod=RAZORPAY

2. OrderService creates order(s) from cart

3. PaymentService creates PaymentOrder entity

4. PaymentService calls Razorpay API to create payment link
   PaymentLink payment = razorpayClient.paymentLink.create(params)

5. Payment link URL extracted from response
   String paymentUrl = payment.get("short_url")
   String paymentUrlId = payment.get("id")

6. PaymentOrder updated with paymentLinkId

7. PaymentLinkResponse returned to client
   { "payment_link_url": "https://rzp.io/...", "payment_link_id": "link_..." }

8. User redirected to Razorpay payment page

9. User completes payment on Razorpay

10. Razorpay webhook notifies backend (if configured)

11. Payment status updated in database
```

### Stripe Integration

#### Configuration
- **Stripe API Key**: Configured in application properties
- **Payment Link Generation**: Dynamic payment link creation

#### Payment Flow
```
1. User places order with payment method STRIPE
   POST /api/orders
   Query: paymentMethod=STRIPE

2. OrderService creates order(s) from cart

3. PaymentService creates PaymentOrder entity

4. PaymentService calls Stripe API to create payment link

5. Payment link URL extracted from response

6. PaymentLinkResponse returned to client
   { "payment_link_url": "https://stripe.com/..." }

7. User redirected to Stripe payment page

8. User completes payment on Stripe

9. Stripe webhook notifies backend (if configured)

10. Payment status updated in database
```

### Payment Status Tracking

#### PaymentOrder Status
- **PENDING**: Payment link generated, awaiting payment
- **COMPLETED**: Payment successfully completed
- **FAILED**: Payment failed

#### Order Payment Status
- **PENDING**: Payment not yet initiated
- **COMPLETED**: Payment completed successfully
- **FAILED**: Payment failed
- **REFUNDED**: Payment refunded

---

## ⚙️ Setup Instructions

### Prerequisites
- **Java 21** or higher
- **Maven 3.6+** or use included mvnw wrapper
- **MySQL 8.0+**
- **Git**

### Database Setup

#### 1. Create MySQL Database
```sql
CREATE DATABASE BazaaroApp;
```

#### 2. Configure Database Connection
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/BazaaroApp
spring.datasource.username=root
spring.datasource.password=your_password
```

### Email Configuration

Configure Gmail SMTP in `application.properties`:
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

**Note**: Use Gmail App Password, not regular password. Generate from Google Account settings.

### Payment Gateway Configuration

#### Razorpay Configuration
Add to `application.properties`:
```properties
razorpay.key.id=your_razorpay_key_id
razorpay.key.secret=your_razorpay_key_secret
```

#### Stripe Configuration
Add to `application.properties`:
```properties
stripe.api.key=your_stripe_api_key
```

### JWT Configuration

Configure JWT in `JWT_CONSTANT.java`:
```java
public class JWT_CONSTANT {
    public static final String SECRET_KEY = "your_256_bit_secret_key_minimum_32_characters_long";
    public static final long JWT_EXPIRATION = 86400000; // 24 hours in milliseconds
}
```

### Running the Application

#### Using Maven Wrapper (Recommended)
```bash
# On Windows
mvnw.cmd spring-boot:run

# On Linux/Mac
./mvnw spring-boot:run
```

#### Using Installed Maven
```bash
mvn spring-boot:run
```

#### Build JAR and Run
```bash
# Build
mvn clean package

# Run JAR
java -jar target/Bazaaro-app-0.0.1-SNAPSHOT.jar
```

### Accessing the Application

- **Application URL**: http://localhost:8180
- **Default Port**: 8180 (configurable in application.properties)

### Testing the Application

#### Test Customer Signup
```bash
curl -X POST http://localhost:8180/auth/sent/login-signup-otp \
  -H "Content-Type: application/json" \
  -d '{"email":"test@example.com","role":"ROLE_CUSTOMER"}'
```

#### Test Customer Login
```bash
curl -X POST http://localhost:8180/auth/signing \
  -H "Content-Type: application/json" \
  -d '{"email":"test@example.com","otp":"123456"}'
```

#### Test Seller Registration
```bash
curl -X POST http://localhost:8180/sellers \
  -H "Content-Type: application/json" \
  -d '{
    "sellerName":"Test Store",
    "email":"seller@example.com",
    "password":"password",
    "mobile":"1234567890",
    "GSTIN":"GSTIN123"
  }'
```

---

## 📝 Configuration Details

### Application Properties (application.properties)

```properties
# Application Configuration
spring.application.name=Ecommerce-App
server.port=8180

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/BazaaroApp
spring.datasource.username=root
spring.datasource.password=Rishi@9838

# JPA Configuration
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.open-in-view=false

# Email Configuration (Gmail SMTP)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=rishicoding9838@gmail.com
spring.mail.password=jtnu vjoz wiso txjx
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Maven Dependencies (pom.xml)

#### Core Dependencies
- **Spring Boot Starter Web**: REST API support
- **Spring Boot Starter Data JPA**: Database ORM
- **Spring Boot Starter Security**: Security framework
- **Spring Boot Starter Validation**: Input validation
- **Spring Boot Starter Mail**: Email service
- **Spring Boot Starter Actuator**: Application monitoring

#### Database
- **MySQL Connector Java**: MySQL driver
- **Jakarta Persistence API**: JPA API

#### Security
- **JWT (jjwt)**: JWT token generation and validation
  - jjwt-api: 0.11.5
  - jjwt-impl: 0.11.5
  - jjwt-jackson: 0.11.5

#### Payment Gateways
- **Razorpay Java**: 1.4.8
- **Stripe Java**: 31.3.0

#### Utilities
- **Lombok**: 1.18.38 (Reduce boilerplate code)
- **Spring Boot DevTools**: Development tools (hot reload)

---

## 🔒 Security Implementation

### Spring Security Configuration

#### Security Filter Chain (AppConfig.java)
```java
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.sessionManagement(management -> management
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/api/**").authenticated()
            .requestMatchers("/auth/**", "/sellers/**").permitAll()
            .requestMatchers("/api/products/*/reviews").permitAll()
            .anyRequest().permitAll())
        .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
        .addFilterBefore(openEntityManagerInViewFilter(), JwtTokenValidator.class)
        .csrf(AbstractHttpConfigurer::disable)
        .cors(cors -> cors.configurationSource(corsConfigurationSource()));
    
    return http.build();
}
```

#### Security Features
- **Stateless Session Management**: No server-side sessions
- **JWT Token Validation**: Custom filter for token validation
- **Role-Based Access Control**: Different permissions for different roles
- **CORS Configuration**: Cross-origin resource sharing enabled
- **CSRF Disabled**: For stateless API architecture

#### Protected Endpoints
- `/api/**`: Requires authentication (JWT token)
- `/auth/**`: Public endpoints (signup, login, OTP)
- `/sellers/****: Public endpoints (seller registration, login)
- `/api/products/*/reviews`: Public endpoint (view reviews)

#### Password Encryption
- **BCryptPasswordEncoder**: Used for password hashing
- **Bean Configuration**: Defined in AppConfig.java

### JWT Token Implementation

#### JWT Provider (JwtProvider.java)
- **Token Generation**: Creates JWT tokens with user claims
- **Token Validation**: Validates token signature and expiration
- **Email Extraction**: Extracts user email from token

#### JWT Token Validator (JwtTokenValidator.java)
- **Filter Implementation**: Extends OncePerRequestFilter
- **Token Extraction**: Extracts token from Authorization header
- **Token Validation**: Validates token using JwtProvider
- **Authentication Setting**: Sets authentication in SecurityContext

#### JWT Claims
- **Email**: User's email address
- **Role**: User's role (ROLE_CUSTOMER, ROLE_SELLER, ROLE_ADMIN)
- **Expiration**: Token expiration time (24 hours)
- **Issued At**: Token creation timestamp

### Exception Handling

#### Global Exception Handler (GlobleException.java)
- **@ControllerAdvice**: Global exception handling
- **Exception Response**: Consistent error response format
- **Error Details**: Structured error information

#### Custom Exceptions
- **ProductException**: Product-related exceptions
- **SellerException**: Seller-related exceptions

---

## 📊 Project Statistics

### Code Metrics
- **Total Controllers**: 18
- **Total Services**: 17 (interfaces + implementations)
- **Total Entities**: 22
- **Total Repositories**: 18
- **Total Enums**: 7
- **Total DTOs**: 8 (request + response)
- **Total Configuration Classes**: 4
- **Total Exception Classes**: 4

### Database Tables
- **User Management**: User, Seller, Address
- **Product Management**: Product, Category, Review
- **Order Management**: Order, OrderItem, Cart, CartItem
- **Payment Management**: PaymentOrder, PaymentDetails, Transaction
- **Marketing**: Coupon, Deal, Wishlist
- **Admin**: Home, HomeCategory, SellerReport
- **Security**: VerificationCode

### API Endpoints
- **Authentication**: 3 endpoints
- **Seller Management**: 8 endpoints
- **Product Management**: 3 endpoints
- **Cart Management**: 2 endpoints
- **Order Management**: 5 endpoints
- **Wishlist Management**: 2 endpoints
- **Review Management**: 2 endpoints
- **Payment Management**: 1 endpoint
- **Admin Management**: 2 endpoints
- **Home Management**: 2 endpoints

---

## 🚀 Future Enhancements

### Planned Features
- **Real-time Order Tracking**: WebSocket integration for live tracking
- **Chat System**: User-seller communication
- **Push Notifications**: Mobile and web push notifications
- **Advanced Analytics**: Business intelligence dashboard
- **AI Recommendations**: Product recommendation engine
- **Multi-language Support**: Internationalization (i18n)
- **Mobile Application**: React Native mobile app
- **Cloud Storage**: AWS S3 for file storage
- **Social Login**: Google, Facebook OAuth integration
- **Advanced Search**: Elasticsearch integration
- **Review Moderation**: AI-based review filtering
- **Dynamic Pricing**: Automated pricing algorithms
- **Inventory Forecasting**: Predictive inventory management

### Performance Improvements
- **Caching**: Redis caching for frequently accessed data
- **Database Optimization**: Query optimization and indexing
- **Load Balancing**: Multiple instance deployment
- **CDN Integration**: Content delivery network for static assets
- **Database Sharding**: Horizontal scaling for database

### Security Enhancements
- **Two-Factor Authentication**: Additional security layer
- **Rate Limiting**: API rate limiting
- **Audit Logging**: Comprehensive audit trail
- **Data Encryption**: Encryption at rest
- **Security Headers**: Enhanced security headers

---

## 👨‍💻 Author

**Rishi Singh**
- **Project**: Bazaaro E-Commerce Platform
- **Technology**: Spring Boot, React.js, MySQL
- **Email**: rishicoding9838@gmail.com

---

## 📄 License

This project is for educational purposes. Please use responsibly and respect the terms of service of integrated third-party services (Razorpay, Stripe, Gmail SMTP).

---

## 🙏 Acknowledgments

- **Spring Boot Team** - Excellent framework
- **Razorpay** - Payment gateway integration
- **Stripe** - Payment gateway integration
- **MySQL** - Database management
- **Lombok** - Boilerplate code reduction

---

## 📞 Support

For issues, questions, or contributions, please contact:
- **Email**: rishicoding9838@gmail.com
- **GitHub**: [Repository URL]

---

## 📝 Version History

- **Version 0.0.1-SNAPSHOT** - Initial release with core e-commerce features

---

**End of Documentation**


