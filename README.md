# ChineduTest

The Sample app allows a user view Video games on their device and allows them to add new games to the list. The Data is cached in the Sqlite on the device. The user can also rate and indicated if they have completed the game.

Answers to Question
------------------------

1. The advantages of View Holder pattern in List Views include;
a. Prevents finding view elements which also impacts performance (expensive operation on CPU); view holders, hold references to the views to speed up listview scrolling experience.
b. Prevents subsequent inflations of the View which affects performance on the listview while scrolling.

2. The disadvantages of Viewholder pattern may only mean a little more heap memory foot print. I can't think of a substantial major disadvantage of the ViewHolder pattern.

3. Uses of Fragments include:
a. Creating a multipane window representing different parts of the application
b. Reusing views instead of creating more Activities
c. ViewPagers for swipe tranisitions for better UI experience.

4. Fragments are overkill;
a. When Fragments are used in creating subsequent fragments several levels deep in the application structure.
b. When mutiple fragments are embedded as views in a Layout hierarchy
