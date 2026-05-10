INSERT INTO categories (id, name) VALUES
    (1, 'Books'),
    (2, 'Tools');


INSERT INTO suppliers (id, name) VALUES
   (1, 'Editions Palemon'),
   (2, 'Bricomarché');


INSERT INTO products (id, description, name, price, quantity, category_id, supplier_id) VALUES
    (1, 'Mary Lester', 'Mary Lester', 9.00, 1, 1, 1),
    (2, 'Tondeuse', 'Tondeuse', 100.00, 1, 2, 2);