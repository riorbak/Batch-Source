// ----------------------------------------------------------------------------------
// PART II

// Part II will focus on Javascript's ability to manipulate the DOM.
// Use the provided HTML page.
// Put your Javascript in the provided <script> element at the bottom of the page.
// Please put the question itself as a comment above each answer.

// -----------------------------------------------------------------------------------

// 1. USA
// Define function getUSA()
// Find the html element that contains "USA".
// Print that element's contents.

console.log("\nQ 01");
function getUSA()
{
    //find all elements
    var nds = getAllTags();

    //for each element
    for(var i = 0; i < nds.length; i++)
    {   
        //check if innterHTML == USA and output results
        if (nds.item(i).innerHTML == ("USA"))
        {
            console.log("Contents: " + nds.item(i).textContent);
        }
    }
}

function getAllTags()
{
    return document.getElementsByTagName("*");
}

getUSA();

// 2. Sales
// Define function getPeopleInSales()
// Print the names of all the people in the sales department.

console.log("\nQ 02");
function getPeopleInSales()
{
    //get all table rows
    var employees = document.getElementsByTagName("tr");

    //for each table row
    for (var i = 0; i < employees.length; i++)
    {   
        //check data for "Sales" status (child 1) and print name (child 0)
        if(employees[i].children[1].textContent == "Sales")
            console.log(employees[i].children[0].textContent);
    }
}

getPeopleInSales();

// 3. Click Here
// Define function getAnchorChildren()
// Find all anchor elements with a <span> child.
// Print the contents of <span>

console.log("\nQ 03");

function getAnchorChildren(srchTg)
{
    //find all anchors
    var anchors = document.getElementsByTagName("a");
    
    var anchorKids = [];        //stores anchor children
    var results = [];           //stores search result

    //for each anchor
    for(var i = 0; i < anchors.length; i++)
    {   
        //store their children
        anchorKids = anchors[i].children;
        
        //for each stored child
        for(var j = 0; j < anchorKids.length; j++)
        {   
            //check if child Node Name matches search parameter
            if(anchorKids[j].nodeName == srchTg)
            {   
                //output and store result
                console.log(anchorKids[j].textContent);
                results.push(anchorKids[j]);
            }
            //otherwise, output failure
            else
                console.log("fail");
        }
    }    
}

getAnchorChildren("SPAN");

// 4. Hobbies
// Define function getSkills()
// Find all checked options in the 'skills' select element.
// Print the value and the contents.

console.log("\nQ 04");

function getSkills()
{
    //find all select HTML objects
    var selects = document.getElementsByTagName("select");

    var skillsSelect = [];
    
    //for all select objects
    for(var i = 0; i < selects.length; i++)
    {
        //if its the skills select, get it.
        if(selects[i].name == "skills")
        {
            skillsSelect = selects[i];
        }
    }
    //return skills select options
    return skillsSelect;
}

var skills = getSkills();

//for all skills
for(var j = 0; j < skills.length; j++)
{
    //if its selected, output it
    if(skills[j].selected == true)
        console.log(j + ": " + skills[j].value);
}

// 5. Custom Attribute
// Define function getCustomAttribute()
// Find all elements with "data-customAttr" attribute
// Print the value of the attribute.
// Print the element that has the attribute.

console.log("\nQ 05");

function getCustomAttribute()
{
    //varables
    var custAtr = [];
    var allTags = getAllTags();     //list of all tags
    
    //for all tags
    for(var i = 0; i < allTags.length; i++)
    {
        //if its a custom attribute, store it
        if(allTags[i].hasAttribute("data-customAttr"))
            custAtr.push(allTags[i]);
    }

    //output all stored custom attributes
    for(var j = 0; j < custAtr.length; j++)
    {
        console.log(custAtr[j].getAttribute("data-customAttr"))
        console.log(custAtr[j]);
    }

}

getCustomAttribute();

// 6. Sum Event
// NOTE: Write unobtrusive Javascript
// Regarding these elements:	
//  <input id="num1" class="nums" type="text"/>	
//  <input id="num2" class="nums" type="text"/>	
//  <h3>Sum: span id="sum"></span></h3>
// Define onchange event handler.
// Add <input> element values.
// Put the sum in the <span> element.
// If values cannot be added, put "Cannot add" in the <span> element

console.log("\nQ 06 \n add nums to num1 and num2 fields");

/**************************************************************************
//Added [onchange="sumOfTwoFields()"] to the <input> fields for num1 & num2
    // Uncomment this out and comment out the stuff below if you want.
function sumOfTwoFields()
{   
    // fields used to calcualte sum
    var sumElems = [];
    sumElems.push(document.getElementById("num1"));
    sumElems.push(document.getElementById("num2"));

    //get num1 & num2 and parse as integers
    var num1 = parseInt(sumElems[0].value);
    var num2 = parseInt(sumElems[1].value);

    //calculate sum
    var sum = num1 + num2;

    //if sum is not a number
    if( isNaN(sum) )
    {
        //output "can't add"
        document.getElementById("sum").textContent = "Cannot add";
    }
    else
    {
        //otherwise, output the sum
        document.getElementById("sum").textContent = sum;            
    }
}
***************************************************************************/

// fields used to calcualte sum
var sumElems = [];
sumElems.push(document.getElementById("num1"));
sumElems.push(document.getElementById("num2"));

//not really sure how the function inside the loop gets called.
// JavaSCRRIIPPT! Roll Tide!
for(var i = 0; i < sumElems.length; i++)
{
    //if one of the 
    sumElems[i].onchange = function()
    {
        //get num1 & num2 and parse as integers
        var num1 = parseInt(sumElems[0].value);
        var num2 = parseInt(sumElems[1].value);

        //calculate sum
        var sum = num1 + num2;

        if( isNaN(sum) )
        {
            document.getElementById("sum").textContent = "Cannot add";
            //updates where it says Sum at the top of the page
            //when putting stuff into the last 2 input boxes
        }
        else
        {
            document.getElementById("sum").textContent = sum;            
        }
    };
}


// 7. Skills Event
// NOTE: Write unobtrusive Javascript
// When user selects a skill, create an alert with a message similar to:	
// "Are you sure CSS is one of your skills?"
// NOTE: no alert should appear when user deselects a skill.

console.log("\nQ 07");

/*********************************************************************
 //Added [onchange="skillChange()"] and [id="skills"] tags to the select element for skills
    //feel free to uncomment and check if they work. Cause they do. And its waaaaay easier than
    // the garbage below it.
function skillChange()
{
    var skill = document.getElementById("skills").value;
    alert("Are you sure | " + skill + " | is one of your skills?");     
}
**********************************************************************/

// get all skill selects
var skills = getSkills();

//for all skills,
for(var i = 0; i < skills.length; i++)
{
    //if skills change
    skills.onchange = function()
    {
        //find the one that changed and output alert
        for(var i = 0; i < skills.length; i++)
           if(skills[i].selected == true)
                alert("Are you sure | " + skills[i].value + " | is one of your skills?");
    };
}


// 8. Favorite Color Event
// NOTE: Write unobtrusive Javascript
// NOTE: This is regarding the favoriteColor radio buttons.
// When a user selects a color, create an alert with a message similar to:	
// "So you like green more than blue now?"
// In this example, green is the new value and blue is the old value.
// Make the background color (of all favoriteColor radio buttons) 
// the newly selected favoriteColor

console.log("\nQ 08");

var colorList = document.getElementsByName("favoriteColor");
var prevChoice, newChoice;

//once again, not sure why the onchange functions work inside of a loop...
// #javascRIPt;
for(var i = 0; i < colorList.length; i++)
{
    //new color selection
    colorList[i].onmousedown = function() 
    {
        //check each color in colorList and store the old color value
        for(var j = 0; j < colorList.length; j++)
            if(colorList[j].checked == true)
                prevChoice = colorList[j].value;
    }
    //changing the color
    colorList[i].onchange = function() 
    {
        //check each color to find the selected color
        for(var j = 0; j < colorList.length; j++)
            if(colorList[j].checked == true)
                newChoice = colorList[j].value;
          
        //changes the panel background color the radio buttons are in
        this.parentNode.style.backgroundColor = newChoice;
        
        //if a previous choice exists, show alert window
        if(prevChoice != null)
           alert("So you like " + newChoice + " more than " + prevChoice + " now?");
    }
}

// 9. Show/Hide Event
// NOTE: Write unobtrusive Javascript
// When user hovers over an employees name:	
// Hide the name if shown.
// 	Show the name if hidden.

console.log("\nQ 09");

//list of employees
var employees = document.getElementsByClassName("empName");

//YOLO Swag JavaScript Shenanigans... Roll Tide;
for(var i = 0;  i < employees.length; i++)
{
    //if mouseover event detected
    employees[i].onmouseover = function()
    {
        //if color is black, set it to white,
        if(this.style.color == "black" || this.style.color == "")
            this.style.color = "white";
        //otherwise, vice-versa;
        else if(this.style.color == "white")
            this.style.color = "black";
    }
}

// 10. Current Time
// Regarding this element:
// 	<h5 id="currentTime"></h5>
// Show the current time in this element in this format: 9:05:23 AM
// The time should be accurate to the second without having to reload the page.

console.log("\nQ 10 \n adds current time to the page. Look under 'Sum:' ");

function updateTime()
{
    //get location to display time
    var currTimeElement = document.getElementById("currentTime");

    //get the date;
    var timestamp = new Date();

    //get the hour
    var hr, AMPM;
    hr = timestamp.getHours();

    // convert from 24hr display
    if(hr >= 12)
    {
        AMPM = "PM";

        if(hr != 12)
            hr -= 12;
    }
    else
        AMPM = "AM";

    //get minutes
    var min = timestamp.getMinutes();
    //keep minutes locked to two digits
    if(min<10)
        min="0"+min;

    //get secs
    var sec = timestamp.getSeconds();
    //keep seconds locked to two digits
    if(sec<10)
        sec="0"+sec;
    
    //display time at location found above
    currTimeElement.innerHTML = hr + ":" + min + ":" + sec + " " + AMPM;

    //update it every 1000ms (aka - 1 sec);
    setTimeout(updateTime, 1000); 
}

updateTime();

// 11. Delay
// Regarding this element:	
// <p id="helloWorld">Hello, World!</p>
// Three seconds after a user clicks on this element, change the text to a random color.

console.log("\nQ 11");

var helloWorld = document.getElementById("helloWorld");
var color;

//generates a random RGB value in the form of '#
function getRandomColor() 
{
    //all 16 hex character to generate 2 digit hex color
    var letters = '0123456789ABCDEF';
    //color codes start with '#'
    var color = '#';

    //for each of the 6 rgb numbers
    for (var i = 0; i < 6; i++) 
    {
        //pick one of the letters at random
        color += letters[Math.floor(Math.random() * 16)];
    }

    //return generated color
    return color;
}

//when "Hello, World!" is clicked, randomly change text color after 3000ms (or 3 secs)
helloWorld.onclick = function()
{
    color = getRandomColor();
    setTimeout((function() { helloWorld.style.color = color; }), 3000);   
}


// 12. Walk the DOM
// Define function walkTheDOM(node, func)
// This function should traverse every node in the DOM. 
// Use recursion.
// On each node, call func(node).

console.log("\nQ 12");

function walkTheDOM(node, func)
{
    func(node);
    node = node.firstChild;
    while (node) 
    {
        walkTheDOM(node, func);
        node = node.nextSibling;
    }
}

function printTextContents(x)
{
    console.log(x.nodeName + " : " + x.textContent);
}

var x = document.body;
walkTheDOM(x, printTextContents);