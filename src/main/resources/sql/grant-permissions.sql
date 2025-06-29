-- lostarkDB 데이터베이스에 대한 loadb_user 권한 부여

-- loadb_user에게 lostarkDB 데이터베이스의 모든 권한 부여
GRANT ALL PRIVILEGES ON lostarkDB.* TO 'loadb_user'@'localhost';
GRANT ALL PRIVILEGES ON lostarkDB.* TO 'loadb_user'@'%';

-- 특별히 필요한 권한들 명시적으로 부여
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER, INDEX ON lostarkDB.* TO 'loadb_user'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER, INDEX ON lostarkDB.* TO 'loadb_user'@'%';

-- 권한 적용
FLUSH PRIVILEGES;

-- 권한 확인
SHOW GRANTS FOR 'loadb_user'@'localhost';
SHOW GRANTS FOR 'loadb_user'@'%';
