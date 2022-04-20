INSERT INTO users
    (email, name, password, phone, username, created_time, updated_time)
VALUES ('user@gmail.com', 'User Hoa Nguyen', '$2a$10$LdEl1Grx7Kt.xatZHcV7p.zLqRLzEa0Ig1qnLQjoH0JgpTEE3IJsy',
        '0568974532', 'user', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('admin@gmail.com', 'Admin Hoa Nguyen', '$2a$10$2xdKVsZ58eH4q4yi4IBtDO5finMa4jO6Z9RtKe7D401nJ3/VH9ry.',
        '09999999', 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('super_admin@gmail.com', 'Super Admin Hoa Nguyen',
        '$2a$10$jcE83LlqUw.TuSSIMDDub.vpRwByt4yStLZy4e80dZHToDs.3E60m', '0988989899', 'super_admin', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);