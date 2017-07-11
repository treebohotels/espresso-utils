# README #

This README should help you setup and use this library in your project

# UseCase #

* The main aim of this library is to use macro functions while writing Espresso test cases in your
project and avoid a whole lot of boiler plate code.

# How do I get set up? #

### Adding dependencies ###

Add the following line in your `build.gradle` file
```
compile 'com.treebo:espresso-utils:1.0.9'
```

# Usage #

The list of all the functions that can be used are below -


| Function Name | Params | Return Value | Description |
| --- | --- | --- | --- |
| getErrorText |  *matcher* -  view which you want to match | String - error text | This method returns the error text of an edittext |
| verifyTextFieldErrorMessage | *View* - edittext to be checked <br/> *errorText* - Expected error text string | boolean - true/false | This method checks if the error text on an edittext is as expected|
| replaceText |  *matcher* -  view which you want to match<br/> *replaceString* - String you want the view to be updated with| `void` | This method replaces the text of a view with the specified text |
| swipeToElement | *matcher* - view on which swipe is performed (e.g. RecyclerView) <br/>*elementToFind* - elementToFind is view till which swipe will be performed <br/> *swipeDirection* - direction in which swipe will be performed (top/down/left/right) <br/> *maxNoOfSwipes* - no of maximum swipe attempts| boolean - true/false if element found | This method swipes to find the final element|
| isViewVisible | *matcher* - view on which swipe is performed | boolean - true/false | Checks if a view is visible |
| checkElementAtPosition | *matcher* - view which you want to match<br/> *position* - position of the element in the recyclerview | Matcher<View> - Matched view if found | Use this method to check for a particular element in a recyclerview |
| clickChildViewWithText | *listOfViews* -  List of Views in which you want to search<br/> str - String you want the view to be checked against with | ViewAction | Use this method to check if a list of views has a view with the given text |
| clickChildViewWithId | *viewID* -  Id of the view you want to click | `void` | Use this method to click a view given its id |
| getText | matcher -  view which you want to match | String - text | Use this method to get the text from a view |
| enterText | *matcher* -  view which you want to match<br/> *inputString* - String you want to enter | `void` | Use this method to enter text into a view |
| tapButton | *matcher* -  view which you want to match | `void` | Use this method to get the tap a button |

*WIP*


# Contribution guidelines #

Want to contribute? Great!
Just fork and raise a PR!

### Who do I talk to? ###

* You can reach out to: [@Abhi1Nair](https://twitter.com/Abhi1Nair) or [abhishek.nair@treebohotels.com]


### Licence ###

GNU GENERAL PUBLIC LICENSE