INSERT INTO users(id, login, password) VALUES(1, 'Sergey', '$2a$10$fAhhMrd4fKSawqNjJGse5ej/.zwAIFnsDv4JVLrchTWtBmPFliuJG'),
      (2, 'Artem', '$2a$10$Jz/fkQ8zhUxIa0ANiDQJzuAwijen4gUPWN72Bv0QjWgP.c8B9c2Ue'),
      (3, 'Anna', '$2a$10$9IDpH95MdvX0oxFWM92w1.qRpzoaNNunEqq5jD2ovXcps9IPCYA1G'),
      (4, 'Vitaly', '$2a$10$BtSb1vIX7synMgLIKximAeNZlpTAae4kZlZMR9xx7wxXZK2s0B4dC');

SELECT setval('user_id_seq', (SELECT MAX(id) from users));

INSERT INTO goods(id, title, price, user_id)
VALUES (1, 'Хлеб', 5.5, 1),
       (2, 'Вода', 100, 2),
       (3, 'Сок', 2, 4),
       (4, 'Молоко', 200, 2),
       (5, 'Квас', 1.5, 1),
       (6, 'Ноутбук', 500, 2),
       (7, 'Сыр', 4.2, 2);

INSERT INTO orders(id, user_id, total_price) VALUES (1, 2, 75), (2, 3, 134.56), (3, 1, 54), (4, 1, 175), (5, 2, 25), (6, 3, 63), (7, 1, 88);
SELECT setval('order_id_seq', (SELECT MAX(id) from orders));
