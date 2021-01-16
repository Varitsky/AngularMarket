 create table products (
     id             bigserial primary key,
     title          varchar(255),
     price          int,
     created_at     timestamp default current_timestamp,
     updated_at     timestamp default current_timestamp
 );
insert into products (title, price)
values
('Bread', 24),
('Milk', 65),
('Milk2', 66),
('Milk3', 67),
('Milk4', 68),
('Milk5', 69),
('Milk6', 56),
('Milk7', 57),
('Milk8', 58),
('Milk9', 59),
('Milk10', 47),
('Milk11', 46),
('Milk12', 45),
('Milk13', 48),
('Milk14', 49),
('Milk15', 34),
('Milk16', 35),
('Milk17', 36),
('Milk18', 37),
('Milk19', 38),
('Cheese8', 39);