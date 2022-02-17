TABLE OF CONTENT

How to clone and use this repository for editing

1) Get the repository url by clicking on Clone and copy the HTTPS link
2) In Eclipse, go to Git Repositories and click on Clone a Git Repository
3) Paste the link copied previously from Git and input your Git credentials 
4) On the Branch Selection panel, ensure all checkboxes are ticked and press Finish to proceed. 
5) Once done, go to your Package Explorer and click on Import Project > Existing Maven Project > Select the recently cloned repository
6) After that, you will be in the master branch of the repository and you're good to go.

RUNNING THE APPLICATION

1) Open the Server panel from Window > Show View
2) Create a server with Tomcat 8.0 and add in the project 
3) If you already have Tomcat 8.0 installed, just drag and drop your project onto the server panel
4) Once done, open up your XAMPP Control Panel and run both Apache and MySQL.
5) Start up your Tomcat on your Eclipse's Server Panel and input the following url:

htto://localhost:8090/devopscrowd/index.jsp


Do take note that the port number may differ from your end so do make the respective changes before entering the URL



IMPORTANT

Do not edit on the master branch itself as it may cause conflicts within the existing branches. Instead, create a new branch by right clicking on your project in 
Package Explorer and then click on > Team > Switch To > New Branch to create a new branch for you to work on your respective files.
