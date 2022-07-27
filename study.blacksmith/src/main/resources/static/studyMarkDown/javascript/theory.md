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
