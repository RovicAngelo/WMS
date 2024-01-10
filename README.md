# Warehouse Management System

A Java CRUD Application for managing orders, receiving entries and storage of products in warehouses.:heavy_exclamation_mark:

<br>

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Development](#development)
- [License](#license)

## Overview

This application is created using java and swing library. It has basic function like Create,Read,Update and Delete. Also it is connected to MySQL server.
		
<br>

## Features

- User Authentication
- CRUD
- Plain UI
- Print ❗
- Light-Dark Mode ❗
- Save as File ❗
- Real time Statistics ❗
- RBAC ❗
- search function ❗


> ❗ not yet implemented

<br>

## Requirements

- Java SE Development Kit (JDK) 8 or later
- MySQL Server
- Any java IDE

<br>

## Getting Started

1. Clone the repository: `git clone https://github.com/RovicAngelo/WMS.git`
2. Navigate to the project directory: `cd WMS`
3. Compile and run the application:

```bash
$ javac Main.java
$ java src/com/lanuza/wms/Main
```

<br>

## Usage
	
To use this application follow this guidelines.

<br><br>

> **Login View**

-  In the login view, use the admin accounts provided for user authentication:


					| Account | Username | Password |
					| ------- | -------- | -------- |
					| Admin 1 | admin1   | pass1    |
					| Admin 2 | admin2   | pass2    |
					| Admin 3 | admin3   | pass3    |

<img src="./WMS/src/com/lanuza/wms/ui/resources/images/loginview.PNG" alt="login view" width="600" height="400" >

<br><br>

> **Dashboard View**

- The dashboard view serves as an overview page displaying key warehouse statistics and metrics. It provides a concise and visual representation of important information about the warehouse, such as inventory levels, order status, or any other relevant metrics.

<img src="./WMS/src/com/lanuza/wms/ui/resources/images/dashboardview.PNG" alt="dashboard view" width="600" height="400" >

<br><br>

> **Product View**

- The product view is a section of the application where users can manage and view information related to products. This includes adding new products that will reflect to the stock and editing existing product details

<img src="./WMS/src/com/lanuza/wms/ui/resources/images/productview.PNG" alt="product view" width="600" height="400" >

<br><br>

> **Supplier View**

- The supplier view is where users can manage information about suppliers. It typically involves functionalities such as adding new suppliers, updating supplier details, and viewing a list of current suppliers.

<img src="./WMS/src/com/lanuza/wms/ui/resources/images/supplierview.PNG" alt="login view" width="600" height="400">

<br><br>

> **Customer View**

- The customer view allows users to manage customer information. This includes adding new customers and updating customer details.

<img src="./WMS/src/com/lanuza/wms/ui/resources/images/customerview.PNG" alt="login view" width="600" height="400">

<br><br>

> **Order View**

-  In the order view, users can manage and track orders. This includes creating new orders, updating order details, and viewing the status of existing orders

<img src="./WMS/src/com/lanuza/wms/ui/resources/images/orderview.PNG" alt="login view" width="600" height="400">

<br><br>

> **Receiving View**

- The receiving view is where users can manage the receiving process of goods or products. It involves functionalities such as confirming received shipments, updating inventory levels

<img src="./WMS/src/com/lanuza/wms/ui/resources/images/receivingview.PNG" alt="login view" width="600" height="400">

<br><br>

> **Stock View**

- The stock view provides an overview of the current stock levels in the warehouse. It may include information about stock quantities, low stock alerts, and other metrics related to inventory management.

<img src="./WMS/src/com/lanuza/wms/ui/resources/images/stockview.PNG" alt="login view" width="600" height="400">

<br><br>

> **Logout**

- The logout option is a feature that allows users to securely log out of the application. 

<br>

## Development

To set up a development environment:

1. Clone the repository: `git clone https://github.com/yourusername/SwingApp.git`
2. Open the project in your favorite Java IDE.
3. Make changes and test locally.

<br>

## Contributing

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/new-feature`.
3. Commit your changes: `git commit -am 'Add some feature'`.
4. Push to the branch: `git push origin feature/new-feature`.
5. Submit a pull request.

> Desired contribution are as follow:
> - Printing function
> - Statistics in dashboard
> - Change modes(dark/light)
> - Save as file
> - RBAC
> - Search function

<br>

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
