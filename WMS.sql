

 Table structure for table `tblaccount`

CREATE TABLE `tblaccount` (
  `AccountId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Role` varchar(50) NOT NULL,
  PRIMARY KEY (`AccountId`)
) 


 Table structure for table `tblcustomer`

CREATE TABLE `tblcustomer` (
  `CustomerId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `PhoneNo` varchar(11) NOT NULL,
  PRIMARY KEY (`CustomerId`)
)


 Table structure for table `tblproduct`

CREATE TABLE `tblproduct` (
  `ProductId` int(11) NOT NULL AUTO_INCREMENT,
  `ProductDescription` varchar(50) NOT NULL,
  `ProductPrice` double NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Total` double NOT NULL,
  `SupplierName` varchar(50) NOT NULL,
  PRIMARY KEY (`ProductId`)
)


 Table structure for table `tblpurchasedorder`

CREATE TABLE `tblpurchasedorder` (
  `OrderId` int(11) NOT NULL AUTO_INCREMENT,
  `ProductDescription` varchar(50) NOT NULL,
  `ProductPrice` double NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Total` double NOT NULL,
  `CustomerName` varchar(50) NOT NULL,
  `Order_Date` date NOT NULL,
  PRIMARY KEY (`OrderId`)
) 


 Table structure for table `tblreceivingentry`

CREATE TABLE `tblreceivingentry` (
  `ReceivingId` int(11) NOT NULL AUTO_INCREMENT,
  `ProductDescription` varchar(50) NOT NULL,
  `ProductPrice` double NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Total` double NOT NULL,
  `ExpDate` date NOT NULL,
  `SupplierName` varchar(50) NOT NULL,
  `ReceivedDate` date NOT NULL,
  PRIMARY KEY (`ReceivingId`)
) 


 Table structure for table `tblstock`

CREATE TABLE `tblstock` (
  `StockId` int(11) NOT NULL AUTO_INCREMENT,
  `ProductDescription` varchar(50) NOT NULL,
  `ProductPrice` double NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Total` double NOT NULL,
  `SupplierName` varchar(50) NOT NULL,
  PRIMARY KEY (`StockId`)
) 


 Table structure for table `tblsupplier`

CREATE TABLE `tblsupplier` (
  `SupplierId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `PhoneNo` varchar(11) NOT NULL,
  PRIMARY KEY (`SupplierId`)
) 