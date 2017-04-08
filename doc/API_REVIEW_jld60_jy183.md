# API Review

netids: jld60, jy183

## Part 1
*1. What about your API/design is intended to be flexible?

Since we are using an entity component system design, in order to add a new feature we need to add another component that implements that feature. Then any entity that is affected by that feature can just hold the new component. For example, if you want to make an enemy that disappears after some amount of time, you could create a component called DisappearAfterTime that holds a timer, and add that to the disappearing entity.

*2. How is your API/design encapsulating your implementation decisions?

Of the four main components: authoring environment, game engine, game player, and game data, we divided responsibilities further. Authoring environment and game engine are each divided into model, view, and controller packages. The authoring environment is designed to have as few public methods as possible. The only important public method is getGameData, which is called by the game engine to get the user's current game design.

*3. How is your part linked to other parts of the project?

I'm working on the authoring environment, so the main responsiblity for this part is to take the user input, represent it in some data class, and send it to the game engine to run the game. 

*4. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

The authoring environment should have as few exceptions as possible, since the user shouldn't be able to cause problems. I think most of the errors will occur in the backend for this project.

*5. Why do you think your API/design is good (also define what your measure of good is)?

I think our design is good because it can be used to make a variety of games such as breakout, mario, and doodlejump. We were even considering making chess, which technically should be possible to make in our authoring environment.

## Part 2
*1. What feature/design problem are you most excited to work on?

I'm looking forward to working on the physics engine and eventually connecting multiple computers with networking.

*2. What feature/design problem are you most worried about working on?

I'm most worried about making the rules feature more flexible. Currently we only allow a few sets of possible rules, but I think it should eventually be possible for the user to define more complicated rules.

*3. What major feature do you plan to implement this weekend?

This weekend I plan on implementing drag and drop in the authoring environment and the module that allows the user to set collision interactions between sprites.

*4. Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?

Our current use cases were descriptive and appropriate when we wrote them, but now we have already changed our design a lot so they are kind of invalid.

*5. Do you have use cases for errors that might occur?

No