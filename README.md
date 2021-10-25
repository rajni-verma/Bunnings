# Bunnings
This application is built in Spring Tool Suite(STS).

**Instructions to run this application:**
1. Clone this service into your system.
2. Put all input csv files at this location: **C:\Bunnings\codingskills\input**
3. Change the port info if required in application.properties file. Currently, port is set to 7000.
4. Right click on project -> Run As -> Spring Boot App
5. Open the browser and type: http://localhost:7000/v1/catalog-barcode/merge and press enter.
6. This will show the response in web browser and will create the output csv file in output folder with name "my_output.csv" at this location: 
    **C:\Bunnings\codingskills\output**
