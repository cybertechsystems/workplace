Customer service module
Develop a customer service module that allows users to process incoming 
messages from emails with POP3 access and eBay inbox.  This module is part 
of a greater platform being developed on the platform JEE. The web pages 
related to this use cases are designed using Java Servers Faces, specifically 
PrimeFaces. The entity classes that should be used in this module (presented 
in a class diagram later) are managed by a standard EntityManager instance.
There are 3 use cases associated to the functionality required.

Name
Check incoming messages
Completion Date
3 Weeks
Description
The messages from clients are generally received through the eBay Inbox or an 
email account from hotmail, gmail or yahoo. The message can contain 
questions, requests, claims, etc.
Actors
-	Client Service Agent
-	System
Precondition
The user has logged in the system
Normal Flux
1.	The software connects to mail servers and download the new messages using 
POP3 protocol; the system also downloads messages from eBay inbox. Some 
messages arrive by both, email and eBay inbox. The system should identify this 
cases and present just one message. The accounts are associated with every eBay 
seller (store).
o	The application communicates to eBay through an abstraction layer that 
uses webservices eBay API interface, check 
http://developer.ebay.com/DevZone/XML/docs/WebHelp/wwhelp/wwhimpl/js/h
tml/wwhelp.htm?context=eBay_XML_API&topic=CommunicationBetweenMemb
ers, you can use the methods referenced there from the platform with a class 
that encapsulates the communication, you just have to setup the needed 
parameters for the method and extract the data from the response.
o	Every account details are stored in a database table, and are 
represented by the Entity Account. A method will be provided, so given a user 
would return an Account List, the module should get message from these 
accounts.
o	Sender, date and content, this could change if you find a more trustable 
way
2.	The system presents to the user an inbox screen (such gmail, hotmail or 
yahoo) where all the messages will be shown uniformly.  It should show the 
sender, subject, date and source account.
3.	The system checks if the message can be associated with a buyer, looking for 
an id in the message content, if the message comes from eBay inbox the sender is 
the id.
4.	 
5.	The user selects an email to visualize.
6.	The systems changes to a view where the mail is presented widely.

7.	The system marks the message to avoid download them again. The system 
should allow the user to configure if a copy of the messages will be left on the 
servers.

8.	 All messages are stored in the database in a Message entity, what this point 
means is that the system should not get the same message twice. For example 
with eBay there is a method to mark as read, you should do something similar 
with POP mail accounts.

Alternative Flux
From point 2:
1.	User decides to delete a message from the list.
2.	Systems ask the user for the reason to delete the mail (that can be 
configured). 
Exceptions
-	There is not internet access: System notifies the user and use case ends.
-	Error while connecting any server: User is notified and execution continues (a 
subtle message could be shown)
Postcondition
True

Name
Process Message
Date
August
Description
Once the user has opened an email, it could be processed according to its 
topic, the customer service´s agents answer the email using a predefined 
template or writing the content for the message.
Actors
-	Client Service Agent
-	System
Precondition
User is Reading an email
Normal Flux
1.	User is viewing the mail
2.	The system shows a menu with configurable links to actions like, “Generate 
new order”, “Cancel order”, “Process Refund” 
o	These will be other JSF pages with other actions in the platform, you 
just should leave a page area for the buttons and allow them to be configured.

3.	User selects an email template according to the subject and change the 
necessary content
4.	The system sends the answer email using the same source where it came 
from by default, or another selected by the user but associated to the same 
store(seller) 
Alternative Flux
The user chooses not to use a template:
1.	The system presents a white message
2.	The user fills the message with the desired content
3.	The system sends the answer mail using to the same source where it came 
from by default, or another selected by the user but associated to the same 
store(seller) 
Exceptions
-	There is not internet connection: (when the message is being sent) the user is 
notified and the message is marked as pending
-	Error while sending message: The user is notified and the message is marked 
as pending
Postconditions
True

Name
Define Template
Date
August
Description
The user can define templates to answer to the client
Actors
-	Client Service Agent
-	System
Precondición
True
Normal Flux
1.	The customer service agent chooses to create a new template
2.	The user starts writing the email content
3.	The system shows a list of fields (like client name, ebay_id, sell date, client 
address) that the user can include in the email
4.	The user inserts the desired fields in the email
5.	The system asks for a name to the template
6.	The user writes a name for the template and saves it
Alternative Flux
The user selects a previous template to generate the new one:
1.	The system loads the previous template in the content
2.	Execution continues in step 3 of the normal flux
Exceptions
-	A template with the given name already exists: The system asks the user if it 
should overwrite the template or ask for another name 
Postconditions
True

The following is a diagram with the related classes for this implementation:
 
