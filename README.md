# Tip Calculator 

## Joseph Ngo

**Tippy** computes the tip and total amount for a bill. The app uses the base amount and tip percentage to calculate the amount owed, and it also describes the quality of service based on the tip.

Time spent: 3 hours spent in total

## Functionality 

The following **required** functionality is completed:

* [x] User can enter in a bill amount (total amount to tip on)
* [x] User can enter a tip percentage (what % the user wants to tip).
* [x] The tip and total amount are updated immediately when any of the inputs changes.
* [x] The user sees a label or color update based on the tip amount. 

The following **extensions** are implemented:

* [x] Custom colors palette selected
* [x] Preset Tip Percentages
* [x] Split Amount Calculator

## Video Walkthrough

Here's a walkthrough of implemented user stories:  
Demo: https://imgur.com/r0WIz4e

<img src='https://imgur.com/r0WIz4e' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

So challenges I had while building this app include: making view constraints align relative to each other and making sure all amount values continuously update after an input has changed. I made the 3 tip preset buttons 10%, 15%, 20% because these are the most common tip percentages left at restaurants in the United States. The user may find it very annoying to have to try to adjust the Tip Percentage Seek Bar to the precise spot for %20 everytime, so the preset buttons solve this obstacle in user experience. I also changed the layout of the tip/total so that it isn't vertically linear. Instead, I formated the tip, total, number split, and split amount in a 2 by 2 grid so while you are typing in the base or number split, you can still see the tip, total, and split cost numbers dynamically change while the user updates the inputs. Before, these values were hidden by the keyboard when typing in the input.

## License

    Copyright [2021] [Joseph Ngo]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
