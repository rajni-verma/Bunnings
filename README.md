# Bunnings
This application is built in Spring Tool Suite(STS).

**Instructions to run this application:**
1. Clone this service into your system.
2. Open Spring Tool Suite. Click on File Menu. Import as -> Existing Maven projects and choose the location on file system where project was cloned earlier.
3. Select catalog-barcode-service and then click check box next to pom.xml and Finish. Project is now imported into STS.
4. Put all input csv files at this location: **C:\Bunnings\codingskills\input**
5. Change the port info if required in application.properties file. Currently, port is set to 7000.
6. Right click on project -> Run As -> Spring Boot App
7. Open the browser and type: http://localhost:7000/v1/catalog-barcode/merge and press enter.
8. This will show the response in web browser and will create the output csv file in output folder with name "**my_output.csv**" at this location: 
    **C:\Bunnings\codingskills\output**
