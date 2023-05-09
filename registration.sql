create database Account
use Account
CREATE TABLE registration (
  username VARCHAR(50) NOT NULL PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  fullName VARCHAR(100) NOT NULL,
  role bit NOT NULL
);


create table tbl_product(
	sku int PRIMARY KEY,
	nameproduct nvarchar(50),
	description nvarchar(200),
	price float,
	quantity int,
	status bit
)


create table tbl_Order(
	id varchar(5) primary key,
	name nvarchar(50),
	address nvarchar(250),
	date datetime,
	total float
)

create table tbl_detail(
	id int identity(1,1) primary key,
	order_id varchar(5),
	sku int,
	quantity int,
	price float,
	total int,
	FOREIGN KEY (sku) REFERENCES tbl_product (sku),
	FOREIGN KEY (order_id) REFERENCES tbl_order (id)
)

INSERT INTO registration (username, password, fullName, role)
VALUES ('anh', '123', N'Nguyen Duc Anh', 1);
INSERT INTO registration (username, password, fullName, role)
VALUES ('quynh', '456', N'Phan Manh Quynh', 0);
INSERT INTO registration (username, password, fullName, role)
VALUES ('tung', '001', N'Nguyen Thanh Tung', 1);
INSERT INTO registration (username, password, fullName, role)
VALUES ('trung', '0175', N'Phan Tan Trung', 0);
INSERT INTO registration (username, password, fullName, role)
VALUES ('Huong', '456', N'Phan Manh Huong', 1);
INSERT INTO registration (username, password, fullName, role)
VALUES ('Nui', '001', N'Nguyen Thanh Nui', 0);
INSERT INTO registration (username, password, fullName, role)
VALUES ('Hoang', '0175', N'Phan Tan Hoang', 1);
select * from registration
select username from registration
drop table registration
Select username From  registration Where username = 'anh' And password = '123';
update dbo.registration set 
password = '123', fullName = N'Nguyen Thanh Tung Chua', role = 'false' 
where username = 'tung'
update registration set password = 123, role = 1 where username = 'quynh'
--------------------------------------------
CREATE TABLE Product
(
	SKU varchar(20) NOT NULL,
	Name nvarchar(50) NOT NULL,
	Price money NOT NULL,
	Description nvarchar(200) NOT NULL,
	Quantity int NOT NULL,
	CONSTRAINT PK_Product PRIMARY KEY (SKU)
)
GO

CREATE TABLE Orders
(
	OrderID int IDENTITY,
	Name nvarchar(50) NOT NULL,
	Address nvarchar(50) NOT NULL,
	[Date] datetime DEFAULT GetDate() NOT NULL,
	Total money NOT NULL,
	CONSTRAINT PK_Order PRIMARY KEY (OrderID)
)
GO

CREATE TABLE OrderDetails
(
	SKU varchar(20) NOT NULL,
	Name nvarchar(50) NOT NULL,
	Price money NOT NULL,
	Quantity int NOT NULL,
	Total money NOT NULL,
	OrderID int NOT NULL,

	CONSTRAINT FK_OrderDetails_Orders 
	FOREIGN KEY (OrderID) 
	REFERENCES Orders(OrderID),

	CONSTRAINT FK_OrderDetails_Product 
	FOREIGN KEY (SKU) 
	REFERENCES Product(SKU),
)
GO

INSERT INTO Registration (username, password, FirstName, MiddleName, LastName, isAdmin)
VALUES 
	('tien123', 'Tien123@', N'Tiên', N'Lê Thủy', N'Huỳnh', 1),
	('phuong123', 'Phuong123@', N'Phượng', N'Lâm Thúy', N'Nguyễn', 1),
	('quan123', 'Quan123@', N'Quân', N'Đào Đức', N'Nguyễn', 0),
	('dat123', 'Dat123@', N'Đạt', N'Thành', N'Trần', 0),
	('trung123', 'Trung123@', N'Trung', N'Duy Hiếu', N'Trần', 0),
	('linh123', 'Linh123@', N'Linh', N'Anh', N'Đỗ', 0),
	('thang123', 'Thang123@', N'Thắng', N'Ngọc', N'Trần', 0),
	('minh123', 'Minh123@', N'Minh', N'Đặng Gia', N'Lê', 0),
	('huy123', 'Huy123@', N'Huy', N'Minh', N'Trần', 0),
	('tuan123', 'Tuan123@', N'Tuấn', N'Vũ Anh', N'Lưu', 0),
	('admin123', 'Admin123@', N'Project', 'MVC2', N'Admin', 1)
GO

INSERT INTO Product (SKU, Name, Price, Description, Quantity)
VALUES 
	('BOOK00001', 'Java', '100000', 'Java Fundamental Book', 90),
	('BOOK00002', 'MVC2', '90000', 'MVC2 Fundamental Book', 80),
	('BOOK00003', 'Tomcat', '80000', 'Tomcat Fundamental Book', 70),
	('BOOK00004', 'JDK', '70000', 'JDK Fundamental Book', 60),
	('BOOK00005', 'Servlet', '60000', 'Servlet Fundamental Book', 50),
	('BOOK00006', 'JavaBeans', '50000', 'JavaBeans Fundamental Book', 40),
	('BOOK00007', 'JSP', '40000', 'JSP Fundamental Book', 30),
	('BOOK00008', 'JDBC', '30000', 'JDBC Fundamental Book', 20),
	('BOOK00009', 'Scripting Elements', '20000', 'Scripting Elements Fundamental Book', 10),
	('BOOK00010', 'EL', '10000', 'EL Fundamental Book', 0)
GO

CREATE TRIGGER TR_OderDetails_Insert ON OrderDetails AFTER INSERT AS 
BEGIN
	DECLARE @orderID INT;
	DECLARE @SKU VARCHAR(20);
	DECLARE @orderDetailsQuantity INT;

	SELECT @orderID = OrderID, @SKU = SKU, @orderDetailsQuantity = Quantity 
	FROM inserted;

	IF ((SELECT Quantity FROM Product WHERE SKU = @SKU) >= @orderDetailsQuantity)
	BEGIN
	UPDATE Product
		SET Quantity = Quantity - @orderDetailsQuantity
		WHERE SKU = @SKU
	END
	ELSE 
		ROLLBACK TRAN;
END
GO


ALTER LOGIN sa WITH PASSWORD = '12345';


