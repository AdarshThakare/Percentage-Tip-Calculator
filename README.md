# Percentage-Tip-Calculator
- This basic app built using Kotlin and Jetpack Compose lets the user calculate the user inputted function of any number and display the resultant tip value on the Activity Screen.
- This app also gives the user an optional feature to round up the percentage tip value.

---
## *Things that I learned* :-
* `Text Field` and `switch` Composable
* `State composition` and `State Hoisting`
* `Lambda Handling`

---
## Logic

```kotlin
fun calculateTip(amount: Double, tipPercent: Double, roundUp: Boolean): String {
    var tip = (tipPercent / 100) * amount
    if (roundUp) {
        tip = kotlin.math.round(tip)
    }
    return tip.toString()
}
```
---
## Demo

https://github.com/AdarshThakare/Percentage-Tip-Calculator/assets/112002059/178ac7fd-4196-4870-8a7d-5513711bd66b
