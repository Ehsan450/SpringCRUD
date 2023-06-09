create table products
(
    sku   varchar(12) primary key not null,
    name  varchar(120)            not null,
    stock int                     not null
        check ( stock >= 0 ),
    price double                  not null
        check ( price >= 0.0 )
);
