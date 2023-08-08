# Shop

This is a non-working sample shop for code review.

The scenario is that we run a webshop. In this webshop users can put products in their shopping cart and remove them if they don't want to buy it. Users should  be able to only put products in the shopping cart if they are in stock.

We serve multiple users at the same time. For performance reasons we have a
ProductInventoryCache which is filled initially from a database. The
ProductInventoryCache holds the product as key and the available amount in stock as value.

In the review we focus only on the Product and ProductInventoryCache classes.