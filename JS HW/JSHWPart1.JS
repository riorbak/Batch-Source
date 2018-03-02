// JS HW, part 1:

// Fill in the functions and submit them to your branch in a file called JSHWPart1.js
// gp
var homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function(n)
{
    var i = j = 0;
    while(i <= n)
    {
        j += i;
        i++;
    }
    return j;
};

/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/
homework.sort = function(array) 
{
    var swap;
    do
    {
        swap = false;
        for (var i = 0; i < array.length - 1; i++)
        {
            if(array[i] > array[i+1])
            {
                var temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                swap = true;
            }
        }

    }while(swap);
    return array;
};

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function(n)
{
    var i = j = 1;
    while(i <= n)
    {
        j *= i;
        i++;
    }
    return j;
};

/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
homework.rotateLeft = function(array, n) 
{
    if(n == 0)
    {
        for(var j = 0; j < array.length; j++)
            console.log(array[j] + " ");
    }
    else
    {
        var temp = array[0];

        for (var i = 0; i < array.length - 1; i++)
        {
            array[i] = array[i + 1];
        }

        array[array.length] = temp;

        n--;
        homework.rotateLeft(array, n);

    }

};

/*
 5. Balanced Brackets

 A bracket is any one of the following: (, ), {, }, [, or ]

 The following are balanced brackets:
    ()
    ()()
    (())
    ({[]})

 The following are NOT balanced brackets:
 (
 )
 (()
 ([)]

 Return true if balanced
 Return false if not balanced
*/
homework.balancedBrackets = function(bracketsString)
{
    var leftStack = [];
    var rightQueue = [];

    for (var i = 0; i < bracketsString.length; i++)
    {
        switch(bracketsString[i])
        {
            case '(':
            case '[':
            case '{':
                leftStack.push(bracketsString[i])
                break;
            case ')':
            case ']':
            case '}':
                rightQueue.push(bracketsString[i]);
                break;
            default:
                console.log("Invalid character. Not balanced.");
                return false;
        }  
    }

    if(leftStack.length != rightStack.length)
    {
        console.log("Uneven stacks. Not balanced.");
        return false;
    }
    else
    {
        for(var j = 0; j < leftStack.length; j)
        {
            var l = leftStack.pop();
            var r = rightQueue.shift();

            if (l === '(' && r === ')')
            {
                continue;
            }
            else if (l === '[' && r === ']')
            {
                continue;
            }
            else if (l === '{' && r === '}')
            {
                continue;
            }
            else
            {
                console.log("Type Mismatch. Not balanced.");
                return false;
            }
        }

        console.log("We got a winnah!");
        return true;
    }

};


//YOUR SOLUTIONS, NOT STACKOVERFLOW’S  ;)

