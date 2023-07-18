-- Creating the database
drop schema EZDB;
create schema if not exists EZDB;
use EZDB;
-- drop table if exists inventory, address, job, creditInfo, customer, employee, rentals, phone_number;


create table user (
userID integer primary key auto_increment,
userName varchar(25) not null,
firstName varchar(25) not null,
lastName varchar(25) not null,
userType enum('ADMIN', 'USER', 'DRIVER') not null,
password varchar(25) not null);

Insert into `user`(`userName`,`firstName`, `lastName`, `userType`, `password`)
values ('user', 'Wally', 'McCustomer', 'USER', 'user');

Insert into `user`(`userName`,`firstName`, `lastName`, `userType`, `password`)
values ('user2', 'Megan', 'Buystuff', 'USER', 'user2');

Insert into `user`(`userName`,`firstName`, `lastName`, `userType`, `password`)
values ('admin', 'Sally', 'De Admin', 'ADMIN', 'admin');

Insert into `user`(`userName`,`firstName`, `lastName`, `userType`, `password`)
values ('driver', 'Dale', 'Earnhardt', 'DRIVER', 'driver');

Insert into `user`(`userName`,`firstName`, `lastName`, `userType`, `password`)
values ('driver2', 'Pete', 'Timely', 'DRIVER', 'driver2');

-- create table customer (
-- customerID integer primary key auto_increment,
-- userID integer not null,
-- foreign key (userID) references user(userID));

-- Insert into `employee`(`userID`)
-- values(1);

-- Insert into `employee`(`userID`)
-- values(2);

-- create table employee (
-- employeeID integer primary key auto_increment,
-- userID integer not null,
-- foreign key (userID) references user(userID));

-- Insert into `employee`(`userID`)
-- values(3);

-- Insert into `employee`(`userID`)
-- values(4);

-- Insert into `employee`(`userID`)
-- values(5);

create table product (
productID integer primary key auto_increment,
productName varchar(50) not null,
description varchar(50) not null,
price decimal(6, 2) not null);

insert into `product`(`productName`, `description`, `price`)
values ('Black linens', '2x2 blk polyester napkins (10)', 8.00);

insert into `product`(`productName`, `description`, `price`)
values ('white linens', '2x2 wht polyester napkins (10)', 10.00);

insert into `product`(`productName`, `description`, `price`)
values ('sm chef coat', 'White 4 button cotten', 12.00);

create table invoiceItem (
itemID integer primary key auto_increment,
invoiceID integer not null,
userID integer not null,
invoiceDate date not null,
productID integer,
quantity integer not null,
foreign key (userID) references user(userID),
foreign key (productID) references product(productID));

insert into `invoiceItem`(`invoiceID`, `userID`, `invoiceDate`, `productID`, `quantity`)
values (1, 1, '2023-12-01', 1, 50);

insert into `invoiceItem`(`invoiceID`, `userID`, `invoiceDate`, `productID`, `quantity`)
values (1, 1, '2023-12-01', 3, 10);

insert into `invoiceItem`(`invoiceID`, `userID`, `invoiceDate`, `productID`, `quantity`)
values (2, 2, '2023-12-01', 3, 50);

insert into `invoiceItem`(`invoiceID`, `userID`, `invoiceDate`, `productID`, `quantity`)
values (2, 2, '2023-12-01', 2, 10);

insert into `invoiceItem`(`invoiceID`, `userID`, `invoiceDate`, `productID`, `quantity`)
values (2, 2, '2023-12-01', 1, 10);


create table deliveries (
deliveryID integer primary key auto_increment,
status enum('delivered', 'canceled', 'in progress'),
employeeID integer not null,
itemID integer not null,
foreign key (employeeID) references user(userID),
foreign key (itemID) references invoiceItem(itemID));


