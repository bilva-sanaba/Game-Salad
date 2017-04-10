#Part 1

##What about your API/design is intended to be flexible?
	One idea used is that all logic will be passed in externally of the class which evaluate data through passing in lamdas. This allows  flexibility for additional behavior definitions for classes by adding new lamda expressions rather than creating entirely new classes for behavior. 
	Another design we explored which uses this is one that stores only data and has all processing of the data in an engine. In order to  then add new behavior a new data class would have to be made and a new engine would have to be created to process this data, but no modification to the GameObject class would be needed. 
	
##How is your API/design encapsulating your implementation decisions?
	There are several levels of encapsulation within both our projects. For example, our engines update GameObjects so that certain properties of it are changed. Certain Engines such as movement should not receive properties of a gameobject such as its image and due to this encapsulated versions of a gameobject are passed in (via interfaces) so that only an engines necessary data can be received. 
	Another high level encapsulation used was that authoring and gameplay utilize the same code however each mode is enapsulated with interfaces so that each version can only use certain methods. 
##How is your part linked to other parts of the project?
Both our projects utilize a MVC design and work by creating classes which store data, classes which process data, and class which draw this data. The main difference in communication is that one project stores lamdas in data for behavior while one has engines which process primitive data. 
##What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
Some issues may result from how the user creates new unit or entities. Essentially, in the authoring environment error checking must be conducted so that the methods and data stored is valid and if not, on the user display, the user must be alerted to know their input for game authoring was invalid.
##Why do you think your API/design is good (also define what your measure of good is)?
Holistically, our API's highlight good design because they avoid certain classes having access to lots of data and instead work in a way so small components can be changed in implementation without too much issue. 
#Part 2

##What feature/design problem are you most excited to work on?
	I am most interested in working on the communication between the front end and backend data in a way that encapsulates the GameObjects well.
##What feature/design problem are you most worried about working on?
	We are worried on spending an entire night making a resource file.

##What major feature do you plan to implement this weekend?
	By the end of this weekend I want to be able to interpret XMLData into entities and have engines which can process and send data to the front end. 

##Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?
	Many are useful in the case but the pseudocode written avoids discussion ono several more challenging design problems.

##Do you have use cases for errors that might occur?
	Several of our teams use cases ignored casting issues with the initial design stated in our PLAN.md
