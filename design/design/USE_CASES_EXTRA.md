1. A user clicks load Game on opening splash screen

    1. Button is clicked

    2. LoadCommand: execute(Stage s)

    3. New GameChooser

    4. GameChooser launches new stage with scrollable interface to list all possible game choices that are located in voogosalad/games and waits until user selects a game

    5. Passes selected file path to ICommandView.loadGame(filepath)

    6. controller.loadGame() adds a game to communicator, then passes new communicator to gameengine to load proper data

    7. GameEngine returns RestrictedEntityManager and controller returns manager to UIView. 

    8. UIView passes manager to WorldAnimator to position and add all elements to the game and scene transitions into GameScene with loaded entities

2. In gameScreen, user presses Play

    9. PlayCommand.execute(Stage s)

    10. Calls ICommandView.runGame()

    11. In GameView: myAnimation.runGame(), which starts the timeline

3. In gameScreen, the user presses Pause

    12. PauseCommand.execute(Stage s)

    13. Calls ICommandView.pauseGame()

    14. In GameView: myAnimation.pauseGame(), which pauses the timeline

4. User opens preference button, brings up settings view, and use clicks on a component to add

    15. Preferences button is clicked: PreferenceCommand.execute()

    16. Launches new preference view by using ScrollablePopup

    17. Retrieves all displays using getView().getComponents()

    18. Goes through each component and checks if it is active, if active, checks box. If not, leaves box unchecked

    19. Establishes observer for each checkbox, which removes or adds displays to manager through DisplayManager.add() or DisplayManager.remove()

5. A user clicks load game in GameScreen

    20. Button is clicked

    21. LoadCommand: execute(Stage s)

    22. New GameChooser

    23. GameChooser launches new stage with scrollable interface to list all possible game choices that are located in voogosalad/games and waits until user selects a game

    24. Passes selected file path to ICommandView.loadGame(filepath)

    25. Unlike splash screen, loadGame calls loadGame() in GameScreen, which clears all entities from GameScreen and world animator, then calls super method in AbstractViewer.loadGame()

    26. controller.loadGame() adds a game to communicator, then passes new commuincator to gameengine to load proper data

    27. GameEngine returns RestrictedEntityManager and controller returns manager to UIView. 

    28. UIView passes manager to WorldAnimator to position and add all elements to the game and scene transitions into GameScene with loaded entities

	1. User presses key to jump

1. WorldAnimator receives action event for key pressed

2. WorldAnimator adds key pressed to a set

3. WorldAnimator passes the set to the game engine

4. Game engine uses reflection to access the specific player movement engine

5. Player movement engine updates image appropriately

6. New restricted entity is passed to WorldAnimator

7. WorldAnimator compares new entity to matching old one in the Group by checking ID, and transfers property to change location each frame

	2. Game loads user created splash screen

1. Engine uses accessor to get entity containing splash screen data (title of game, instructions blurb, background image)

2. Entity is passed to SpecificGameSplashView where view is created

3. Scene is set in UIView

4. Button on splash screen calls getGameScene() to load WorldAnimator when clicked

3. User’s player loses a life by falling off the screen

1. On step in WorldAnimator, when the player’s location reaches 0, Game Engine calls method to reset the player’s image to starting location and subtract life

    1. Image is reset

        1. Game engine uses reflection to access the specific player movement engine

        2. Player movement engine updates image

        3. New restricted entity is passed to WorldAnimator

        4. Image is repositioned

    2. Subtract life

        5. Game engine updates HUDComponent 

        6. Game screen observer notices the change through a game object that contains information like score, lives, etc.

        7. Image is updated on the game screen to reflect a decreased value for live

	4. Pause screen pops up over the game screen

1. User clicks pause button

2. PauseCommand.execute(Stage s) is called

3. In command, animation is paused and new window is created

4. Window accesses the background image from the splash screen and sets the window’s background as such. Does same with title

5. Window contains "play" button which closes window and calls PlayCommand.execute(Stage s) when clicked. 

1. A user saves a file

    1. A user clicks on the save file button in the upper tool bar

    2. This button triggers the SaveEvent method public void event()

    3. This event puts into a list all the entities retrieved from its instance of ViewData from the methods public Entity getLevelEntity()/getSplashEntity() and public Map<Integer, Entity> getDefinedEntities()/getPlacedEntities().

    4. The event opens a TextInputDialogueBox which prompts the user for the name of the game

    5. It then uses a created XMLWriter and calls writeFile(fileName, l) where fileName is the name from the TextInputDialogueBox and l is the list from the ViewData methods previously

    6. XMLWriter then proceeds to use a DOMParser to output the list of entities.

2. A user loads a file from authoring environment

    7. A user clicks on the load file button in the upper tool bar

    8. This button triggers the LoadEvent method public void event()

    9. A FileChooser is made to select the XML file to load.

    10. Initialize a Communicator with the filePath you just got from the FileChooser

    11. Load into a Collection the data by calling the public Collection<Entity> getData() method

    12. Clear the data that is currently in the passed ViewData.

    13. Sort the Entities into the right kinds and add them to the ViewData object

    14. Refresh the ViewData object so that this is all displayed visually

3. A user makes a new splash screen

    15. A user clicks the splash screen design button for their game.

    16. The Splash Screen customization window appears and lets the user input their desired features.

    17. The desired fields are taken and used to initilialize a SplashEntity object

    18. The splash entity object is passed to ViewData and saved

    19. The splash entity object is passed with the rest of the data when a user loads a game and read by the GamePlayer

4. A user loads a game into the Game Playing environment

    20. The user launches a game

    21. The Game Engine is passed the Communicator object

    22. The Communicator goes through the same methods which it did to load a file to the authoring environment

    23. The Game Engine receives the data and gives proper access to the Player if needed and the game appears on screen

1. User loads in pre-set entities seen in the view

    1. User click load button under the list view

    2. Button action calls a load file chooser

    3. The file gets parsed with xml and gets put into the view data

    4. The listVIew listener updates the changes in the view data

2. User chooses and sets a win item

    5. User clicks make entity and chooses an image

    6. User chooses the item radio button and hits next

    7. User sets the label and sets the size of the image

    8. Then chooses win from a listView of different possibilities for items

    9. Then hits create button and it is populated in the list on the side

3. User clicks a placed entity and changes its components

    10. User clicks ctrl+clicks on an entity on the grid and a pop up window appears

    11. The window has the attributes of the entity chosen

    12. The user can move the slider or change any component

    13. Then the user clicks update and the attributes are updated

4. User chooses an infinite scroller

    14. User presses button with the infinity sign in the toolbar

    15. The user selects images for collision losing

    16. User selects difficulties

    17. User selects direction

    18. User selects buttons and image for Hero

1. User lands on a regular block

    1. The collision handler in the backend detects a collision through checking location components

    2. Collision handler tells subengine to deal with collision

    3. Subengine calls objects’ collisionComponent to execute its pre-determined action

    4. Action modifies objects’ components in some way

    5. Movement engine uses this information to update location/image and passes info to the frontend

2. User wants to modify the game engine to have "layers"

    6. Similar to the functionality of one of the other platforming groups

    7. User comes in and creates a new Entity component that specifies which layer an entity is on

    8. User creates a new collisionEngine that implements the ICollision Interface and that checks for layer type before handling collision

    9. New functionality works

3. User wants to create and play tower-defense game

    10. User would need to create a new class that extends the AbstractEngine superclass. This new engine would be responsible for movement in the game.

    11. A new component would be added, called PathComponent or something similar, which specifies the different locations an entity must go to on its path.

    12. User would need to create a view on the frontend that allows path to be created and this information to be stored in the PathComponent

    13. The new movement engine would use PathComponents to figure out locations for entities on the screen

    14. User can now create a tower-defense game

4. User wants enemy to die on collision with block

    15. User adds a block to the game in G.A.E. and adds a CollisionComponent

    16. In the component-creation screen, user adds killOnContact action to all sides of the block

    17. CollisionHandler detects a collision and tells subengine to handle collision

    18. Subengine tells CollisionComponent to execute its action, which kills the other object

5. User wants to add a powerup from a block when hit

    19. User adds powerUpAbility to a block and specifies actions of powerup and image for powerup

    20. CollisionHandler detects a collision with block

    21. Subengine tells block to execute its given action

    22. Action creates a new entity (powerup) with positioning relative to the block

1. User hits a block and a powerup is created.

    1. The collision handler uses a previously stated use case to determine that a collision has occured

    2. Both entities colliding are looked at to determine if they have the objectCreationComponent via (Entitiy.getComponent(ComponentType.ObjectCreation)

    3. If they do the entities object creation component is sent to the ObjectEngine

    4. The ObjectEngine runs the lamda stored in the objectCreationComponent which creates an Entity. This is added to the entity list and an observer is flagged

2. User hits a powerup and changes image

    5. See previous a

    6. Determined if imagechange component is present

    7. ImageChange Engine changes image of object and notifies observer

3. Enemy dies

    8. Health engine checks enemies health

    9. If health is below 0, sets entity to null

    10. Notifies observer

4. User dies

    11. Health engine checks users health

    12. Determines that health is below 0 and that entity is player controller

    13. If life component is present lowers life count and resets entites

    14. else : calls EndEngine which finds end displayEntityComponent and displays it

5. Author wants to assign a key to  cause the character to move faster but there is no default action for this

    15. Author goes to keyinput panel and types desired key which is stored

    16. Author types groovy expression in form vc.setX(vc.getX()+y)

    17. The two are mapped and stored in KeyInputComponent

    18. When the key is pressed the input engine sees this

    19. It checks the string mapped to key, and runs groovy script on this

    20. The x direction velocity is increased

1. User drags an entity from the tab pane to a location on the grid

    1. The event handler for mouse dragged inside of TabView is triggered

    2. The current Entity that is stored by SelectedEntity is cloned

    3. The new cloned Entity object is added to the PlacedEntity list in ViewData

    4. That object is given a location component

    5. The PlacedEntity list triggers the update method in ViewController

    6. ViewController calls the addEntityToGrid method to update the front end

2. User clicks an entity in the tab pane to edit the preset

    7. A new EntityConfigurationWindow is instantiated and the entity that was clicked is passed in as a parameter.

    8. The components of the entity are loaded and the current values are displayed in the window

    9. The components in the window can be changed however desired

    10. The OK button alters the entity object components to have the new values and it remains in the DefinedEntity list

3. User wants to resize an entity placed on the grid

    11. User clicks on the image and drags in our out

    12. The Entity’s EventHandler on mouse dragged is triggered

    13. The ImageView that displays the image on the front end is taken from the list in GridView

    14. The ImageView is resized dynamically until the user unclicks

    15. The Entitys size component is edited to the new values once the user unclicks

4. User wants to define a monster entity

    16. User clicks the add entity button

    17. A new entityBuilderWindow is created

    18. The "monster" radio button is selected and the user clicks OK

    19. A new MonsterConfigurationWindow is instantiated with a blank template

    20. The user can select values for every Component of this new Entity

    21. The new entity is added to the DefinedEntity list in ViewData, and it triggers the front end update to add the image to the TabView

1. User changes the background for the game

    1. User presses the change background button, which initializes a BackgroundEvent

    2. BackgroundEvent initializes an ImageChoose, which the user uses to choose the image file path

    3. The image file path is saved in a level entity object as a string, which is inside a ViewData object

    4. Once modified, the ViewData object notifies its observer, ViewController

    5. ViewController calls update in GridView, which calls updateBackground in GridView

    6. GridView redraws the background canvas using the image path in its ViewData instance to display the user’s background image

2. User resizes grid

    7. User clicks addHo or addVert

    8. Button event handler launches a GrowEvent, which updates ViewData to have the right number of rows and columns

    9. ViewController is notified as an observer, and calls the addRows and addColumns methods in GridView

3. User deletes an entity from the grid

    10. User selects an entity by clicking on it, which sets the entity userSelectedGridEntity in ViewData

    11. User clicks on trash button in toolbar, which launches a TrashEvent

    12. TrashEvent removes the entity from the placedEntitiesList in ViewData and notifies ViewController

    13. ViewController calls update in GridView, which redraws the grid

4. User wants to close authoring environment

    14. User presses x at top right (top left for mac)

    15. Window closes

1. User holds the left key down and releases (accelerated motion)

    1. Each step the PlayerMovementEngine increases the velocity based on the acceleration

    2. The MovementEngine increases the location based on the velocity at that instant

    3. When the user releases a key, the PlayerMovementEngine registers that the key being pressed is no longer in the keyPressed Collection of keys that each engine gets passed and starts to decrement velocity based on some friction value

    4. The MovementEngine still updates location based on the instantaneous velocity so that a sliding effect occurs

2. Player reaches a goal 

    5. The CollisionEngine registers that a collision occured

    6. The CollisionEngine passes to the LevelEngine that a collision between a player and something with a goal component occured

    7. The LevelEngine checks to see what the current level is and sees what kind of goal component was hit (a warp to a special level or just a simple next level)

    8. The LevelEngine tells the world animator to load up a new level

    9. (Will require game authoring environment to give users the options to specify different levels not sure how they’ll implement that)

3. AI collides with a wall

    10. The CollisionEngine will register that a collision occured

    11. The CollisionEngine passes to the AIEngine that a collision between an enemy and a block has occured

    12. The AIEngine checks if the entity that is colliding has the appropriate AI component to turn around when hitting a wall (smartAI component vs dumbAI component)

    13. If it does, the AIEngine reverses the velocity of the entity so that the enemy will start moving the other way when updated by the MovementEngine

4. Users collides with AI

    14. The CollisionEngine will register that a collision occurred

    15. The CollisionEngine informs the AIEngine about the type of collision (just in case it’s a type of collision that kills the AI instead of the enemy)

    16. If it’s a type of collision that kills the AI, the AIEngine tells the DeathEngine to kill the AI

    17. If not, the AIEngine tells the HealthEngine to lower the health of the player

    18. If the health gets to 0, the HealthEngine tells the DeathEngien to kill the Player

5. Player picks up something that gives player health back

    19. The CollisionEngine detects that a collision has occurred

    20. The CollisionEngine can tell that the collision isn’t one that will the player

    21. The CollisionEngine tells the HealthEngine to increase the player’s health by a given amount depending on what the player collided with

    22. The CollisionEngine tells the DeathEngine to kill off whatever is that the player picked up to get health

