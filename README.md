# WaveAccess-Test-Work
### A test app for WaveAccess company junior job

Task:

language: java, kotlin, Xamarin.Android (C#)
android version: 5.0+
design according to material

Must contain screens:
1. User list screen:
- presented as a list
- each element of the list contains texts: "name", "email" and an indicator of isActive
- the transition to users profile is only available to active (isActive = true) users

2. User details screen
- contains textfields: "name", "age", "company", "email", "phone", "address", "about"
- pressing on "email" opens an email service and add the content as a destination
- pressing on a "phone" opens a phone call dialog
- the value of "eyeColor" is presented as a colored dot. Possible variants: brown, green, blue
- the value of "favoriteFruit" is presented as an icon according to one of the three variants: apple, banana, strawberry
- the value of "registered" is formatted to HH:mm dd.MM.yy
- values of firlds "latitude" and "longitude" are written in a single string according to standart coordinate format and pressing on it opens a map of the respective place
- "friends" list is presented like a vertical list that looks and acts similarly to the list in item 1.
- pressing "back" button translates back through the whole hierarchy not just stright to the list screen
