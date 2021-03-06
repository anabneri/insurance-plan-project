# Insurance plan
Origin offers its users an insurance package personalized to their specific needs without requiring the user to understand anything about insurance. This allows Origin to act as their de facto insurance advisor.

Origin determines the user’s insurance needs by asking personal & risk-related questions and gathering information about the user’s vehicle and house. Using this data, Origin determines their risk profile for each line of insurance and then suggests an insurance plan ("economic", "regular", "responsible") corresponding to her risk profile.

For this assignment, you will create a simple version of that application by coding a simple API endpoint that receives a JSON payload with the user information and returns her risk profile (JSON again) – you don’t have to worry about the frontend of the application.

## The input
First, the would-be frontend of this application asks the user for her personal information. Then, it lets her add her house and vehicle. Finally, it asks her to answer 3 binary risk questions. The result produces a JSON payload, posted to the application’s API endpoint, like this example:

```
{
  "age": 35,
  "dependents": 2,
  "house": {"ownership_status": "owned"},
  "income": 0,
  "marital_status": "married",
  "risk_questions": [0, 1, 0],
  "vehicle": {"year": 2018}
}
```

## User attributes
All user attributes are required:

* Age (an integer equal or greater than 0).
* The number of dependents (an integer equal or greater than 0).
* Income (an integer equal or greater than 0).
* Marital status ("single" or "married").
* Risk answers (an array with 3 booleans).
* **House**
Users can have 0 or 1 house. When they do, it has just one attribute: ownership_status, which can be "owned" or "mortgaged".

* **Vehicle**
Users can have 0 or 1 vehicle. When they do, it has just one attribute: a positive integer corresponding to the year it was manufactured.

### The risk algorithm
The application receives the JSON payload through the API endpoint and transforms it into a risk profile by calculating a risk score for each line of insurance (life, disability, home & auto) based on the information provided by the user.

First, it calculates the base score by summing the answers from the risk questions, resulting in a number ranging from 0 to 3. Then, it applies the following rules to determine a risk score for each line of insurance.

If the user doesn’t have income, vehicles or houses, she is ineligible for disability, auto, and home insurance, respectively.

If the user is over 60 years old, she is ineligible for disability and life insurance.

If the user is under 30 years old, deduct 2 risk points from all lines of insurance. If she is between 30 and 40 years old, deduct 1.

If her income is above $200k, deduct 1 risk point from all lines of insurance.

If the user's house is mortgaged, add 1 risk point to her home score and add 1 risk point to her disability score.

If the user has dependents, add 1 risk point to both the disability and life scores.

If the user is married, add 1 risk point to the life score and remove 1 risk point from disability.

If the user's vehicle was produced in the last 5 years, add 1 risk point to that vehicle’s score.

This algorithm results in a final score for each line of insurance, which should be processed using the following ranges:

0 and below maps to **“economic”**.
1 and 2 maps to **“regular”**.
3 and above maps to **“responsible”**.


## The output
Considering the data provided above, the application should return the following JSON payload:

```
{
    "auto": "regular",
    "disability": "ineligible",
    "home": "economic",
    "life": "regular"
}
```


