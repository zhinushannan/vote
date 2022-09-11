# vote


create user 'vote'@'%' IDENTIFIED by '09140727';
GRANT all privileges ON vote.* to 'vote'@'%';
CHANGE MASTER TO MASTER_HOST='172.20.0.2', MASTER_USER='root', MASTER_PASSWORD='09140727', MASTER_LOG_FILE='mysql-bin.000001', MASTER_LOG_POS=602, master_port=3306;