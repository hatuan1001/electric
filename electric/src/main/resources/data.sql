-- 1. Tắt kiểm tra khóa ngoại để có thể TRUNCATE các bảng có liên kết với nhau
SET FOREIGN_KEY_CHECKS = 0;

-- 2. Xóa dữ liệu cũ (Dùng TRUNCATE để reset cả auto-increment nếu có)
-- Lưu ý: Nên xóa cả bảng electricity_history vì nó chứa khóa ngoại trỏ về user
TRUNCATE TABLE electricity_history;
TRUNCATE TABLE electricity_tier;
TRUNCATE TABLE user;

-- 3. Bật lại kiểm tra khóa ngoại sau khi đã dọn dẹp xong
SET FOREIGN_KEY_CHECKS = 1;

-- 4. Insert dữ liệu cho bảng electricity_tier
INSERT INTO electricity_tier (id, min_kwh, max_kwh, price_per_kwh, description) VALUES
                                                                                    ('01', 0, 50, 1806, 'Bậc 1: Cho kWh từ 0 - 50'),
                                                                                    ('02', 50, 100, 1866, 'Bậc 2: Cho kWh từ 51 - 100'),
                                                                                    ('03', 100, 200, 2167, 'Bậc 3: Cho kWh từ 101 - 200'),
                                                                                    ('04', 200, 300, 2729, 'Bậc 4: Cho kWh từ 201 - 300'),
                                                                                    ('05', 300, 400, 3050, 'Bậc 5: Cho kWh từ 301 - 400'),
                                                                                    ('06', 400, NULL, 3151, 'Bậc 6: Cho kWh từ 401 trở lên');

-- 5. Insert dữ liệu cho bảng user
-- Lưu ý: Tên cột 'Role' trong SQL nên viết thường nếu Entity của bạn không chỉ định @Column(name="Role")
INSERT INTO user (id, username, password, role) VALUES
                                                    ('1', 'electrian1', '123456', 'ELECTRIAN'),
                                                    ('2', 'electrian2', '123456', 'ELECTRIAN'),
                                                    ('3', 'user1', '123456', 'USER'),
                                                    ('4', 'user2', '123456', 'USER');