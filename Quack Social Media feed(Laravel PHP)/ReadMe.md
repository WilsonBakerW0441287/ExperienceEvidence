FINAL ASSIGNMENT – SIMPLE, CUSTOM SOCIAL MEDIA FEED IN PHP & mySql

You will create a web application in PHP that will serve as a custom Social Media Feed site. The site will consist of two areas: 
1) An administrative back-end area, the basic structure (i.e. users, posts, themes) of a website can be dynamically created and modified by only authorized users with the appropriate permissions. The additions and changes to the site’s administrative structure will all be stored in the custom SQLite/MySQL databases that you will create to support your Social Media Feed site.
2) A front-end site area which will dynamically display posts based on the structure stored in the database. The front-end site will be viewable by all users including guests, but users can register and on subsequent visits log in in order to be able to contribute and make some changes to feed content. 
The site will be built using the LARAVEL (Version 9) Application Framework and styled using BOOTSTRAP.

Main page for users, Showing the most recent post. When a user is signed in The create post button is enabled. when a user is signed in they can edit or delete their post.
<img src="https://github.com/WilsonBakerW0441287/ExperienceEvidence/blob/main/Quack%20Social%20Media%20feed(Laravel%20PHP)/Images/Example1.PNG" >

User moderation page: This site is only accessible to admins and allows them to change the permissions of elevated users. 
<img src="https://github.com/WilsonBakerW0441287/ExperienceEvidence/blob/main/Quack%20Social%20Media%20feed(Laravel%20PHP)/Images/Example2.PNG" >

Theme management page: This allows users with the ThemeManager role to CRUD (create, read, update, delete) Bootstrap themes for users to choose from. 
<img src="https://github.com/WilsonBakerW0441287/ExperienceEvidence/blob/main/Quack%20Social%20Media%20feed(Laravel%20PHP)/Images/Example3.PNG" >


User role implementation: Not allowing a user who doesn't have the right roles to access certain pages, giving them a pop-up telling them why. 
<img src="https://github.com/WilsonBakerW0441287/ExperienceEvidence/blob/main/Quack%20Social%20Media%20feed(Laravel%20PHP)/Images/Example4.PNG" > 


The database entity-relationship diagram (ERD).                                                    
<img src="https://github.com/WilsonBakerW0441287/ExperienceEvidence/blob/main/Quack%20Social%20Media%20feed(Laravel%20PHP)/Images/Example5.PNG" >


The data for users, with password hashing. 
<img src="https://github.com/WilsonBakerW0441287/ExperienceEvidence/blob/main/Quack%20Social%20Media%20feed(Laravel%20PHP)/Images/Example6.PNG" >
