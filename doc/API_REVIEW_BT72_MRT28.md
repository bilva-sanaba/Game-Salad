**Part 1:**

*1) What about your API/design is intended to be flexible?*

The way our engines work is designed to be very flexible. At a very high level, our program follows the entity component model. This basically means that every entity (an entity is basically anything that exists in the game like an enemy, block, player, tree, e.t.c) has a collection of components that holds information about it. Some examples of components are health, location, velocity, acceleration, e.t.c. Essentially, our design is such that the engines take in components from the entities and edit them accordingly. And since components are objects, the engines editing the components still makes sure the entities have the edited versions (objects use references instead of passing the actual data). This design is very flexible because it can handle any kind of component with any kind of engine, as long as the engine is implemented correctly.

*2) How is your API/design encapsulating your implementation decisions?*

The fact that we're using engines that all deal with separate functions (i.e. collision engine deals with collision, movement engine deals with movement, e.t.c) encapsulates the design. This design choice is, by default, encapsulating since it compartmentalizes all the functions in the appropriate way. 

*3) How is your part linked to other parts of the project?*

The component information is used to tell the front end how to update accordingly. For example, if the player's health component drops from 100 to 80, that should be visually reflected by the front end. Also, the engines are what figure out how the front end should update the sprite. Essentially, the front end is linked to our part of the project in the sense that it should accurately reflect all the changes that occur on the back end.

*4) What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?*

The way our engines are currently designed, they hold a list of lists in order to keep track of all the entities' required components. For example, the movement engine has a list that holds a list of all the entities location components, all the entities velocity components, and all the entities acceleration components. This might lead to an error because we have to make sure that a certain index of all the lists corresponds to the same entity. For example, the third index of the velocity, location, and acceleration list should correspond to the same entity. This might lead to an issue if we pass an entity to an engine that doesn't have all the required components to operate correctly. This could also happen if the entity has all the necessary components for that engine but doesn't have some extra parts (for example, the movement engine handles accelerated and non-accelerated motion so some entities will have an acceleration component while some will not). To tackle this, we should approach the way we decide what entities have components differently. Instead of doing it based on what the entity does, we should do it based on which engines the entity will interact with. If we know an entity will be being passed to the movement engine, it should have all the necessary components, even if their values are set to 0. This will prevent most issues.

*Why do you think your API/design is good (also define what your measure of good is)?*

To me, how good a design is is how flexible it is coupled with how understandable it is. I think our design is lacking a bit in terms of how understandable it is. Using a list of a list is not very intuitive. However, the way our engines and entities interact (i.e. the fact that they edit components) is very flexible because this approach is able to tackle so many different kinds of problems (i.e. it can tackle so many different kinds of engine and component(s) combinations).

**Part 2:**

*1) What feature/design problem are you most excited to work on?*

I'm most excited to work on all the different kinds of engines that will be involved in this project. The reason I'm so excited for this because it'll be a very nice feeling to see our hierarchy of engines able to tackle so many different kinds of problems. I think the aspect of this problem that I'm most excited for is figuring out a good way to pass information from the entities to the engines. Essentially, I want to solve the design problem of how to better send components to the engines in a manner such that the engines function regardless of what the entity that is trying to be passed (i.e. if the engine shouldn't affected the entity, it won't instead of crashing the program). 

*2) What feature/design problem are you most worried about working on?*

I'm not excited to work on taking all the information from the game authoring environment and creating the appropriate game. I've never worked with XML parsing successfully before, and this seems like a very inappropriately hard challenge to try and develop an understanding. We might not choose to do XML, but as of now, that is the approach we are planning to go with and that is a daunting challenge to me.

*3) What major feature do you plan to implement this weekend?*

I plan to basically finish all the necessary engines this weekend. This is huge because the engines are basically how the entire back end works. Entities get passed to engines and the engines give back updated versions. This is necessary for the entire program to work. 

*4) Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?*

I think they were good. They were descriptive, appropriate, and reasonably-sized. They also helped point us in the right direction. 

*5) Do you have use cases for the errors that might occur?*

We didn't make any use cases for errors that might occur. All of our use cases only had to do with the basic functionality of the engines. Essentially, they were just used to mentally refine our scaffolding of our design. However, it would have been extremely helpful to consider errors because we're now realizing how prevalent of an issue it could be if we don't consider it.

> Written with [StackEdit](https://stackedit.io/).