# railway-management-system
Railway management system created with java, Tomcat server and MySQL. 

It has the following functionalities 
1. Adding, updating and deleting stations and trains.
2. Adding multiple passengers at once
3. Viewing all bookings, filtering and searching for data.
4. Printing of receipts for train tickets.

# How to run code
Clone project or download zip file and paste in NetbeansProjects 

# Issues
You'll have to re configure the jdbc with the latest driver

# Adding database
Check the src/railway, you'll see an sql file. 

1. Create a database in your phpMyAdmin or MySQL workbench with the name "railway".
2. Import the railway.sql file
3. Correct / Configure the issue for your jdbc driver, you are good to go.
4. If you have an issue with port find and replace the port in the files, look for localhost:3306 and replace with localhost:<your_port_number>
