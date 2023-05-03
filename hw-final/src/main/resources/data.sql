INSERT INTO users(id, login, password) VALUES (1, 'Sergey', '$2a$10$fAhhMrd4fKSawqNjJGse5ej/.zwAIFnsDv4JVLrchTWtBmPFliuJG'),
                                              (2, 'Artem', '$2a$10$Jz/fkQ8zhUxIa0ANiDQJzuAwijen4gUPWN72Bv0QjWgP.c8B9c2Ue'),
                                              (3, 'Anna', '$2a$10$9IDpH95MdvX0oxFWM92w1.qRpzoaNNunEqq5jD2ovXcps9IPCYA1G'),
                                              (4, 'Vitaly', '$2a$10$BtSb1vIX7synMgLIKximAeNZlpTAae4kZlZMR9xx7wxXZK2s0B4dC');

SELECT setval('user_id_seq', (SELECT MAX(id) from users));

INSERT INTO goods(id, title, price, user_id)
VALUES (1, 'Bread', 5.5, 1),
       (2, 'Water', 100, 2),
       (3, 'Juice', 2, 4),
       (4, 'Milk', 200, 2),
       (5, 'Kvas', 1.5, 1),
       (6, 'Banana', 500, 2),
       (7, 'Cheese', 4.2, 2);

INSERT INTO orders(id, user_id, total_price) VALUES (1, 2, 75), (2, 3, 134.56), (3, 1, 54), (4, 1, 175), (5, 2, 25), (6, 3, 63), (7, 1, 88);
SELECT setval('order_id_seq', (SELECT MAX(id) from orders));

-- DO $$
-- BEGIN
--   IF NOT EXISTS (SELECT 1 FROM pg_tables WHERE schemaname = 'public' AND tablename = 'users') THEN
-- CREATE TABLE users (
--                        id SERIAL PRIMARY KEY,
--                        login VARCHAR(100) NOT NULL UNIQUE,
--                        password VARCHAR(255) NOT NULL
-- );
-- ALTER SEQUENCE users_id_seq RESTART WITH 5;
-- INSERT INTO users(id, login, password) VALUES (1, 'Sergey', '$2a$10$fAhhMrd4fKSawqNjJGse5ej/.zwAIFnsDv4JVLrchTWtBmPFliuJG'),
--                                               (2, 'Artem', '$2a$10$Jz/fkQ8zhUxIa0ANiDQJzuAwijen4gUPWN72Bv0QjWgP.c8B9c2Ue'),
--                                               (3, 'Anna', '$2a$10$9IDpH95MdvX0oxFWM92w1.qRpzoaNNunEqq5jD2ovXcps9IPCYA1G'),
--                                               (4, 'Vitaly', '$2a$10$BtSb1vIX7synMgLIKximAeNZlpTAae4kZlZMR9xx7wxXZK2s0B4dC');
-- SELECT setval('users_id_seq', (SELECT MAX(id) from users));
-- END IF;
-- END $$;

-- DO $$
-- BEGIN
--   IF NOT EXISTS (SELECT 1 FROM pg_tables WHERE schemaname = 'public' AND tablename = 'goods') THEN
-- CREATE TABLE goods (
--                        id SERIAL PRIMARY KEY,
--                        title VARCHAR(100) NOT NULL,
--                        price DECIMAL(10,2) NOT NULL,
--                        user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE
-- );
-- ALTER SEQUENCE goods_id_seq RESTART WITH 8;
-- INSERT INTO goods(id, title, price, user_id) VALUES (1, 'Хлеб', 5.5, 1),
--                                                     (2, 'Вода', 100, 2),
--                                                     (3, 'Сок', 2, 4),
--                                                     (4, 'Молоко', 200, 2),
--                                                     (5, 'Квас', 1.5, 1),
--                                                     (6, 'Ноутбук', 500, 2),
--                                                     (7, 'Сыр', 4.2, 2);
-- SELECT setval('goods_id_seq', (SELECT MAX(id) from goods));
-- END IF;
-- END $$;

-- DO $$
-- BEGIN
--   IF NOT EXISTS (SELECT 1 FROM pg_tables WHERE schemaname = 'public' AND tablename = 'orders') THEN
-- CREATE TABLE orders (
--                         id SERIAL PRIMARY KEY,
--                         user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
--                         total_price DECIMAL(10,2) NOT NULL
-- );
-- ALTER SEQUENCE orders_id_seq RESTART WITH 8;
-- INSERT INTO orders(id, user_id, total_price) VALUES (1, 2, 75),
--                                                     (2, 3, 134.56),
--                                                     (3, 1, 54),
--                                                     (4, 1, 175),
--                                                     (5, 2, 25),
--                                                     (6, 3, 63),
--                                                     (7, 1, 88);
-- SELECT setval('orders_id_seq', (SELECT MAX(id) from orders));
-- END IF;
-- END $$;




-- INSERT INTO users(id, login, password) VALUES (1, 'Sergey', '$2a$10$fAhhMrd4fKSawqNjJGse5ej/.zwAIFnsDv4JVLrchTWtBmPFliuJG');
-- INSERT INTO users(id, login, password) VALUES (2, 'Artem', '$2a$10$Jz/fkQ8zhUxIa0ANiDQJzuAwijen4gUPWN72Bv0QjWgP.c8B9c2Ue');
-- INSERT INTO users(id, login, password) VALUES (3, 'Anna', '$2a$10$9IDpH95MdvX0oxFWM92w1.qRpzoaNNunEqq5jD2ovXcps9IPCYA1G');
-- INSERT INTO users(id, login, password) VALUES (4, 'Vitaly', '$2a$10$BtSb1vIX7synMgLIKximAeNZlpTAae4kZlZMR9xx7wxXZK2s0B4dC');
--
-- SELECT setval('user_id_seq', (SELECT MAX(id) from users));
--
-- INSERT INTO goods(id, title, price, user_id) VALUES (1, 'Хлеб', 5.5, 1);
-- INSERT INTO goods(id, title, price, user_id) VALUES (2, 'Вода', 100, 2);
-- INSERT INTO goods(id, title, price, user_id) VALUES (3, 'Сок', 2, 4);
-- INSERT INTO goods(id, title, price, user_id) VALUES (4, 'Молоко', 200, 2);
-- INSERT INTO goods(id, title, price, user_id) VALUES (5, 'Квас', 1.5, 1);
-- INSERT INTO goods(id, title, price, user_id) VALUES (6, 'Ноутбук', 500, 2);
-- INSERT INTO goods(id, title, price, user_id) VALUES (7, 'Сыр', 4.2, 2);
--
-- INSERT INTO orders(id, user_id, total_price) VALUES (1, 2, 75);
-- INSERT INTO orders(id, user_id, total_price) VALUES (2, 3, 134.56);
-- INSERT INTO orders(id, user_id, total_price) VALUES (3, 1, 54);
-- INSERT INTO orders(id, user_id, total_price) VALUES (4, 1, 175);
-- INSERT INTO orders(id, user_id, total_price) VALUES (5, 2, 25);
-- INSERT INTO orders(id, user_id, total_price) VALUES (6, 3, 63);
-- INSERT INTO orders(id, user_id, total_price) VALUES (7, 1, 88);
--
-- SELECT setval('order_id_seq', (SELECT MAX(id) from orders));
