CREATE
  TABLE tb_user
  (
    id       INT NOT NULL PRIMARY KEY auto_increment,
    username CHAR(20) NOT NULL,
    password CHAR(32),
    role     CHAR(16)
  );
  INSERT INTO tb_user (id, username, password, role) VALUES (1, 'admin', 'admin', 'ROLE_USER');
  INSERT INTO tb_user (id, username, password, role) VALUES (2, 'zero', 'zero', 'ROLE_USER');
  select * from tb_user;