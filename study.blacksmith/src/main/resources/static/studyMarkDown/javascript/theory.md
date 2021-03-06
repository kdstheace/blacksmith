# Key features of JS
### High-level
- JS engine ? V8 => Chrome, nodeJs
- Every JS engine contains "CallStack", "HEAP'
- CallStack: where our code is executed (execution context)
- HEAP: objects are stored

### Garbage-collected
### Interpreted of just-in-time compiled
- Just-in-Time Compilation: Entire code is converted into machine code at once, then executed immediately.
- 그냥 컴파일의 경우 소스코드를 머신코드로 컴파일 하고 하나의 파일로 가지고 있다가 그 파일을 실행했는데, 이 방식은 전체 코드를 머신코드로 컴파일 하는
것은 같지만, 컴파일된 것이 파일로 있는 것이 아니라 컴파일 되자마자 실행된다.
- 코드가 Parsing 되면 AST(Abstract Syntax Tree)로 변환됨. 
- AST를 Compilation 과정으로 머신코드로 바뀐다.
- 콜스택에서 Execution을 한다.
- 실행되는 동안 Optimization을 한다. 다시 컴파일되고 Execution된다. - 메인쓰레드가 아닌 우리가 접근할 수 있는 내부 쓰레드에서 계속 처리함.
### Multi-paradigm
- procedural programming
- Object-oriented programming
- Functional programming
### Prototype-based object-oriented
### First-class functions
- functions can be treated as variable
### Dynamic
- Dynamically-typed language 
- let a = 2; a = '2';
### Single-threaded
- Concurrency model: JS runs in one single thread. so how the JS engine handles multiple tasks? using event loop
### Non-blocking event loop

# Execution Context
1. Execution Context: Environment where a piece of JS is executed. Stores all the necessary info for code to be executed.
2. Steps
   1. Compilation
   2. Creation of global execution context
      * not inside a function
      * exactly one global EC. default context.
   3. Execution of top-level code inside global EC
   4. Execution of functions and waiting for callbacks
      * One EC per function. for each function call, a new EC is created.
3. What's inside execution context?
   1. Variable Environment
      1. let, const, var
      2. functions
      3. arguments object - not in arrow function!
   2. Scope chain
   3. this keyword - not in arrow function!

# Scope Chain
1. Scoping: how our program's variables are organized and accessed. variable lifecycle, accessibility
2. Lexical Scoping: Scoping is controlled by placement of functions and blocks in the code. 
   1. 부모 스코프 안에 들어간 자식스코프에선 부모스코프의 변수에 접근할 수 있지만, sibling스코프 끼리는 변수를 공유하지 않음
즉 어떤 함수나 블럭에 위치했느냐에 따라 스코프가 결정된다.
3. Scope: space or env where a certain variable is declared.
   1. Global Scope: accessible everywhere, outside of any function or block
   2. Function Scope: accessible only inside function. = local scope
   3. Block Scope: accessible only inside block, this only applies to let and const variables. 
      * functions are also block scoped only in strict mode
      * e.g. if, for loop.
      * var 는 if, for 내부에 선언했어도 외부에서 접근가능함. var은 블록을 벗어난 바로 윗단계 스코프에 만들어진다.
4. cf) Scope of a variable : Region of our code where a certain variable can be accessed

# Strict mode
1. simple function에서 this 를 사용하면 window객체를 불러와 버리는데, 이를 방지하기 위함.
2. function 이 블럭 밖으로 나