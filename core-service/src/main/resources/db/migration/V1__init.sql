create table categories (
    id              bigserial primary key,
    title           varchar(255) unique,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into categories (title) values ('Food'), ('Others');

create table products
(
    id              bigserial primary key,
    title           varchar(255),
    category_id     bigint references categories (id),
    price           numeric,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into products (title, price, category_id) values
('Milk', 80.50, 1),
('Bread', 25.50, 1),
('Butter', 200.10, 1),
('Fish', 500.90, 1),
('Juice', 100.00, 1),
('Cheese', 300.80, 1);

create table orders
(
    id          bigserial primary key,
    username    varchar(255) not null,
    total_price numeric not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product numeric    not null,
    price             numeric    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);


