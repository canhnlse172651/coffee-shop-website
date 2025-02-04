<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="image/logo.png" type="image/png">
        <title>GroceryMart</title>
        <link rel="stylesheet" href="web/view/ProductPgae/productPage.css">
        <link href='https://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <style>
            .navigation{
                gap: 50px;
            }
          
            a{
                text-decoration: none;
                color: black;
            }
        </style>

    </head>
    <body>
        <header class="header row">
            <div class="container Logo" style="margin: 0;" >
                <img src="web/view/HomePage/image/logo.png" alt="logo">
                <div class="Logo-word">grocerymart</div>
            </div>
            <div class="navigation">
                <a href="/Grocery/HomeServlet"> Home </a>
                <a href="/Grocery/AccountServlet">
                    Manage
                </a>
                <a href="/Grocery/Contact">Contact</a>

            </div>
            <div class="user-info">
                <span class="user-profile">
                    <div class="cart">
                        <a href="MainServlet?btn=viewCart" > <img src="web/view/HomePage/image/cart.png" alt=cart></a>
                    </div>
                    <img src="web/view/HomePage/image/user.png" alt="User"/> 
                </span>
                <a href="/Grocery/Logout"><div class="back-logout" ><i class="fa-solid fa-right-from-bracket fa-xl" style=" color: #000000; margin-left: 10px; cursor : pointer" title="Logout"></i></div> </a>
            </div>
        </header>
        <div class="container">
            <div class="product-page" style="background-color:#fff;" >
                <div class="product-image" style="margin:0 100px" >
                    <img style = "max-width: none; margin-top: 70px"  src=${product.image} alt="${product.name}">
                </div>
                <div class="product-details" style="background-color:#fff;" >
                    <h1 style="font-size: 28px; text-align: start;">
                        ${product.name} - ${product.status ? "In Stock" : "Out of Stock"}
                    </h1>                
                    <div class="size-additional" style="display: flex; align-items: center  " >
                        <div class="size-weight">
                            <h2>Size/Weight</h2>

                            <div class="sizes" style="padding : 0" >
                                <button style="padding : 10px 30px" class="size-btn">${product.size}</button>

                            </div>
                        </div>
                        <div class="additional-options">
                            <div><span></span> Compare Product</div>
                            <div><span></span> Delivery 
                                <span>From $6 for 1-3 days</span></div>
                            <div><span></span> Pickup 
                                <span>Out of 2 stores, today</span></div>
                        </div>
                    </div>
                    <form action="MainServlet" method="post">
                        <div class="price-box" style="margin: 0;padding: 20px 40px;">
                            <div class="price-discount">
                                <div class="original-price">${product.price}</div>
                                <div class="discount">0% off</div>
                            </div>
                            <div class="current-price">$${product.price}</div>
                            <input type="hidden" name="productId" value="${product.id}">
                            <button type="submit" class="add-to-cart"  name="btn" value="addToCart"  >Add to cart</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="tabs-head">
            <div class="description-section">
                <div class="tabs">
                    <button class="tab-button active" data-tab="description">Description</button>
                    <button class="tab-button" data-tab="features">Features</button>
                    <button class="tab-button" data-tab="reviews">Reviews (1100)</button>
                    <button class="tab-button" data-tab="similar">Similar</button>
                </div>
                <div class="tab-content active" id="description">

                    <p>${product.description}</p>
                    <div class="img-des">
                        <img style="width:200px" src=${product.image} alt="${product.name}">
                    </div>
                </div>
                <div class="tab-content" id="features">

                    <p style="font-size: 16px; font-family: 'Arial', sans-serif; line-height: 1.6;">Coffee packaging function: The process of coffee packaging involves carefully selecting
                        and preparing freshly roasted coffee beans. These beans are then measured, <br/> sealed, and labeled according to specific quantities and types. Packaging ensures 
                        the preservation of coffee's aroma, flavor, and freshness, maintaining quality until it reaches consumers</p>
                </div>
                <div class="tab-content" id="reviews">
                    <h2>Customer Reviews</h2>
                    <p>Read what our customers are saying...</p>
                </div>
                <div class="tab-content" id="similar">
                    <h2>Similar Products</h2>
                    <p>Check out similar products...</p>
                </div>
            </div>
        </div>
        <script src="web/view/ProductPgae/js.js"></script>
    </body>
    <footer>
        <div class="footer-container">
            <div class="footer-column">
                <div class="footer-logo">
                    <img src="web/view/HomePage/image/logo.png" alt="Grocery Mart Logo">
                    <h2>grocerymart</h2>
                </div>
                <p>We are one and only</p>
                <div class="newsletter">
                    <p>Receive product news and updates.</p>
                    <form action="#">
                        <input type="email" placeholder="Email address" required>
                        <button type="submit">SEND</button>
                    </form>
                </div>
            </div>
            <div class="footer-column">
                <h3>SHOP</h3>
                <ul>
                    <li><a href="#">All Departments</a></li>
                    <li><a href="#">Fashion Deals</a></li>
                    <li><a href="#">Electronics Discounts</a></li>
                    <li><a href="#">Home & Living Specials</a></li>
                    <li><a href="#">Beauty Bargains</a></li>
                </ul>
            </div>
            <div class="footer-column">
                <h3>SUPPORT</h3>
                <ul>
                    <li><a href="#">Store Locator</a></li>
                    <li><a href="#">Order Status</a></li>
                </ul>
            </div>
            <div class="footer-column">
                <h3>COMPANY</h3>
                <ul>
                    <li><a href="#">Customer Service</a></li>
                    <li><a href="#">Terms of Use</a></li>
                    <li><a href="#">Privacy</a></li>
                    <li><a href="#">Careers</a></li>

                </ul>
            </div>
            <div class="footer-column">
                <h3>CONTACT US</h3>

                <p style="margin-bottom: 10px;">Email: contact@grocerymart.com</p>
                <p style="margin-bottom: 10px;">Hotline: 18008888</p>

                <div class="social-icons">

                    <a href="#"><img src="web/view/HomePage/image/youtube.png" alt="YouTube"></a>

                    <a href="#"><img src="web/view/HomePage/image/twet.png" alt="Twitter"></a>
                    <a href="#"><img src="web/view/HomePage/image/link.jpeg" alt="LinkedIn"></a>
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2010 - 2025 Grocery Mart. All rights reserved.</p>
        </div>
    </footer>
</html>
