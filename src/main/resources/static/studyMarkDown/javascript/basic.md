# Data Type
* dynamic typing
* value has type, NOT variable -> 그래서 언제든지 문자열변수에 정수값을 넣었다가 변수로 바꿀 수 있다.
1. Number: all numbers are this , floating point numbers
2. String
3. Boolean
4. Undefined: not yet defined (ex - let your;) "empty value" -> both value and type as well.
5. Null: :) typeof null -> 'Object' bug!!
6. Symbol(ES2015) : value that is unique and cannot be changed 
7. BigInt: larger integers than the number type

# let, const, var
' reassign(mutate) a variable to 1991'
1. let: can be changed
2. const: cannot be changed. should be initialized. for clean code, use const.
3. var:
4. var vs let
   1. var is a block scoped, let is a function scoped.

# Operator
1. typeof 
2. **
x = y = 10-2  > console(x, y);  // 8, 8

# Template literals
1. backtick `I'm ${name}`;
2. "new line \n\ new line \n\ whowhowhoa!"; with backtick, you can just write down.

# Type Conversion, Type Coercion
1. TypeConversion: 강제형변환
   - manually convert one type to another
   - Number('123');
   - String('252');
2. TypeCoercion: 자동형변환
   - automatically convert type
   - 123 + 'asdfa';
   - '23' - '10' - 3  > 10
   - '23 + '10' + 3  >  23103
   - '23'*'2' > 46
   ```javascript
   let n = '1' + 1;
   n = n-1;
   console.log(n) //10
   ```

# falsy values, truthy values
 - falsy values
   - converted as false when we try to convert it to boolean
   - 0, undefined, '', null, NaN
 - truthy values
   - converted to true when we try to convert it to boolean
   - {} ~ object, others
# error value
1. NaN : Not a Number

# strict equal ===, loose equal ==
# prompt
```javascript
    const inputNo = Number(prompt("what is your favorite number?"));
```
using type conversion is important because return value of prompt is always String.

#Statement, Expression
1. Statement: not produce value by itself(if(){}else{})
2. Expression: produce value (!a&&b)

# Conditional
 if, else if, else
 ternary : a?b:c;
 
# Activate Strict Mode
'use strict';
> cannot use undefined variables, reserved word..
> let us know in advance.

#AnonymousFunction vs functionDeclaration
we can call **functional declaration** before it is defined (hoisting)
but anonymous function cannot
```javascript
   const age1 = calcAge1(1991);
   
   function calcAge1(birthYear){
      return 2023 - birthYear;
   }
   
   const calcAge2 = function(birthYear){
      return 2023 - birthYear;
   }
   
   const age2 = calcAge2(1991);
   
   console.log(age1, age2);
```
# ArrowFunction
1. one line function
```javascript
const calcAge = birthYear => 2037 - birthYear;
const age = calcAge("1991");
console.log(age);

const yearsUntilRetirement = birthYear => {
   const age = 2023- birthYear;
   const retirement = 65 - age;
   return retirement;
}
console.log(yearsUntilRetirement(1991));

const yearsUntilRetirement = (birthYear, givenRetireAge) => {
   const age = 2023- birthYear;
   const retirement = givenRetireAge - age;
   return retirement;
}
console.log(yearsUntilRetirement(1991, 65));

const cutFrutiPieces = fruit => fruit*4;
const fruitProcessor = (apples, oranges) => {
   console.log(`Juice with ${cutFrutiPieces(apples)} apples, ${cutFrutiPieces(oranges)} oranges`);
}
```


# Array
```javascript
const friends = ['ho', 'MK', 'DC'];
console.log(friends[2]);

const years = new Array(1991, 1987, 2555, 2151);
console.log(years);
console.log(friends.length);
```