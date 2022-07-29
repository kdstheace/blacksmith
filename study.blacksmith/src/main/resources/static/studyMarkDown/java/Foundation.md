# 자바의 정석

- 7 객체지향 프로그래밍

  ### 8.5 익명클래스

   1. 클래스 선언과 객체의 생성을 동시에 하는, 하나의 객체만 생성할 수 있는 일회용 클래스
   2. new 조상클래스이름(){}  또는 new 구현인터페이스이름(){}
   3. 오직 단하나의 클래스를 상속받거나 단 하나의 인터페이스만 구현할 수 있다.
   4. 이름이 없으니 ‘외부클래스명$숫자.class’형식으로 클래스파일명이 결정된다.
   5. 익명객체는 타입이 없다!!

- 9 유용한 클래스
   1. Object
      1. public boolean equals(Object obj)
         - String, Date, File, Wrapper클래스 는 주소값이 아닌 내용을 비교하도록 오버라이딩 되어 있다.
      2. public int hashCode()
         - 객체주소값을 해시코드로 만들어 반환한다.
         - 64비트에서는 8바이트 주소값으로 4바이트의 해시코드를 만들기 때문에 중복될 수 있다.
         - 따라서 HashMap, HashSet클래스에 저장할 객체라면 equals뿐만 아니라 반드시 hashCode()메서드를 오버라이딩 해줘야 한다.
         - String의 경우 문자열 내용이 같으면 동일한 해시코드를 반환한다.
         - System.identityHashCode(Object x)는 hashCode()메서드 처럼 주소로 해시코드 생성하므로 항상 다른 해시코드를 반환한다.
      3. public String toString()
         - 기본적으로 getClass().getName()+”@”+Integer.toHexString(hashCode());
      4. protected Object clone()
         - Cloneable 인터페이스를 구현한 클래스에서만 clone()을 호출할 수 있다.
         - 인스턴스 변수의 값만 복사하기 때문에 인스턴스 변수까지의 완전한 복제는 이루어지지 않는다.
         - 오버라이딩하면서 접근제어자를 public으로 변경해주는 것 추천
      5. 공변반환타입
         1. 조상메서드의 반환타입을 자손클래스의 타입으로 허용하는 것
   2.
- 11. 컬렉션프레임워크

# 11. 컬렉션프레임워크

## 1. 컬렉션프레임워크

   1. 컬렉션 프레임웍: 컬렉션(데이터군) 프레임웍(표준화된 프로그래밍방식)
   2. 컬렉션프레임워크 계층도
    ### 1.1 핵심인터페이스
    - Collection
        - 메서드
            - 조회 (4)
                - boolean contains(Object o)
                - boolean containsAll(Collection c)
                - boolean isEmpty() ⇒ 실제로는 CollectionUtils.isEmpty(List)를 사용하자.
                - int size() ⇒ size() = 0보다 isEmpty()가 시간복잡도 효율적이다.
            - 추가 (2)
                - boolean add(Object o)
                - boolean addAll(Collection c)
            - 삭제 (4)
                - void clear()
                - boolean remove(Object o)
                - boolean removeAll(Collection c)
                - boolean retainAll(Collection c)
            - 비교 (1)
                - boolean equals(Object o)
            - 반환 (4)
                - int hashCode()
                - Iterator iterator()
                - Object[] toArray()
                - Object[] toArray(Object[] a) - 인수로 넣어준 배열에 넣어서 반환한다.
    - List: 순서O, 중복O
        - 메서드
            - 조회 (3)
                - Object get(int index)
                - int indexOf(Object o)
                - int lastIndexOf(Object o) 역방향으로 찾음
            - 추가 (3)
                - void add(int index, Object o)
                - Object set(int index, Object element)
                - boolean addAll(int index, Collection c)
            - 삭제 (1)
                - Object remove(int index)
            - 정렬 (1)
                - void sort(Comparator c)
            - 반환 (3)
                - List subList(int from, int to)
                - ListIterator listIterator()
                - ListIterator listIterator(int index)
    - Set: 순서X, 중복X
    - Map: 키-값, 순서X, 중복X
        - 메서드
            - 조회 (4)
                - boolean containsKey(Object key)
                - boolean containsValue(Object value)
                - boolean isEmpty()
                - int size() ⇒ size() = 0보다 isEmpty()가 시간복잡도 효율적이다.
                - Object get(Object key)
                - 
            - 추가 (2)
                - Object put(Object key, Object value)
                - void putAll(Map t)
            - 삭제 (4)
                - void clear()
                - Object remove(Object key)
            - 비교 (1)
                - boolean equals(Object o)
            - 반환 (4)
                - int hashCode()
                - Set entrySet() : 저장된 키값쌍을 Map.Entry타입의 객체로 저장한 Set으로 반환
                - Set keySet()
                - Collection values()
        - Map.Entry 인터페이스
            - Map의 내부 인터페이스.
            - Map인터페이스를 구현하는 클래스에서는 Map.Entry인터페이스도 함께 구현해야한다.
            - 메소드
                
                boolean equals(Object o);
                
                Object getKey();
                
                Object getValue();
                
                int hashCode();
                
                Object setValue(Object value);
                
            - 예시
                
                ```java
                Set<Entry<String, String>> entries = mapper.entrySet();
                for (Entry<String, String> entry : entries) {
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());
                }
                ```
                
        - Vector, HashTable, Properties는 컬프 이전부터 존재했으나 가능하면 사용하지 말자
    
    ### 1.2 ArrayList
    
    - Object배열을 만들어 순차적으로 저장하고 공간이 차면, 새로운 배열을 생성해서 기존 배열에 저장된 내용을 새로운 배열로 복사해 저장한다.
    - 메서드
        - ArrayList() → 크기가 10인 ArrayList생성
        - ArrayList(Collection c) 주어진 컬렉션이 저장된 ArrayList생성
    
    ## 1.1 핵심인터페이스

- 14. 람다식, 스트림

# 14. 람다식과 스트림

## 1. 람다식

### 1.1. 람다식이란

   1. 메서드를 하나의 식으로 표현한 것.
   2. 메서드의 이름과 반환값이 없어지므로 익명함수라고도 한다.
   3. 메서드를 변수처럼 다룰 수 있고, 예전처럼 메서드 하나때문에 클래스를 만들어서 인스턴스를 생성할 필요도 없어졌다.  - 함수vs메서드(객체에 포함되어야 함)

### 1.2 람다식 작성

   1. 매개변수 → {몸통} , (매개변수1, 매개변수2) → {몸통}
   2. 매개변수 타입은 추론 가능한 경우 생략할 수 있다.  2개 이상인 경우 모두 생략해야함. 하나만 생략 X. 타입을 선언한 경우 괄호를 생략할 수 없다.
   3. 매개변수가 하나뿐인 경우 () 생략 가능.
   4. 몸통에 문장이 하나일 때는 {} 생략가능. 이때 문장끝에 ;을 안 붙인다.
   5. return 문이 있는 메서드는 return 문대신 식(expression)으로 대신할 수 있다. 연산결과가 자동으로 반환값이 되며, 끝에 ;을 안 붙인다.
   6. return 문일 경우 {}과 ;를 절대 생략할 수 없다.
   7. 외부 지역변수와 같은 이름의 람다식 매개변수는 허용되지 않는다.

### 1.3 함수형 인터페이스(Functional Interface)

   1. 람다식은 메서드와 동등한 것이 아니라, 익명 클래스의 객체와 동등하다.
      - 코드

          ```java
          (int a, int b) -> a > b ? a : b
          
          new Object{
              int max(int a, int b){
                  return a > b ? a : b;	
              }
          }
          ```

   2. 따라서 익명객체를 람다식으로 대체해 ‘하나의 메소드를 가진 인터페이스’를 구현할 수 있다.
      - 코드

          ```java
          @FunctionalInterface
          interface MyFunction{
              public abstract int max(int a, int b);
          }
          
          MyFunction mf = (a, b) -> a > b ? a : b;
          
          @FunctionalInterface
          public interface MyFunction{
              public int max(int a, int b);
              boolean equals(Object obj);  // Object의 상속받은 메소드를 써주는건되나보다..
          }
          ```

   3. 람다식을 다루기 위한 인터페이스를 “함수형 인터페이스”라고 한다.
   4. 제약사항
      1. 오직 하나의 추상 메서드만 정의되어야 한다. 그래야 람다식과 인터페이스의 메서드가 1:1 대응 가능
      2. 단, static메서드와 default 메서드 개수에는 제약이 없다.
      3. @FunctionalInterface는 필수는 아니지만, 컴파일러가 함수형 인터페이스를 올바르게 정의했는지 확인해주므로 꼭 붙이자.
   5. 함수형 인터페이스 타입의 “매개변수”와 “반환타입”
      - 코드

          ```java
          @FunctionalInterface
          interface MyFunction{
              void run();
          }
          
          public class Main {
              static void execute(MyFunction f){ // 매개변수로서 함수형 인터페이스
                  f.run();
              }
          
              static MyFunction getMyFunction(){ // 반환타입으로서 함수형 인터페이스
                  MyFunction f = () -> System.out.println("f3.run()");
                  return f;
              }
          
              public static void main(String[] args) {
                  MyFunction f1 = () -> System.out.println("f3.run()"); //람다식으로 구현
                  MyFunction f2 = new MyFunction(){  //익명클래스로 구현
                      public void run(){
                          System.out.println("f2.run()");
                      }
                  };
                  MyFunction f3 = getMyFunction();
                  f1.run();
                  f2.run();
                  f3.run();
                  execute(f1);
                  execute(() -> System.out.println("f4.run"));
              }
          }
          ```

   6. 람다식의 형변환
      1. 람다식은 익명객체이고 익명객체는 타입이 없으므로, 형변환을 해줘야하나, 생략이 가능할 뿐이다.

         Myfunction f = (MyFunction)(() → {});

      2. 람다식은 Object타입으로 형변환 할 수 없다. 오직 함수형 인터페이스로만 형변환 가능하다. 굳이 형변환 하고 싶으면 일단 함수형 인터페이스로 형변환하고 해야한다.

         Object obj = (MyFunction)(() → {});   ~(Object)생략됨


### 1.4 java.util.function 패키지

1. 자주쓰이는 형식의 메서드를 함수형 인터페이스로 미리 정의해두었다.

- 미분류
   - Spring Apache Commons 라이브러리
      - ObjectUtils
         - BooleanUtils, CharUtils, ClassUtils, CharSetUtils, LocaleUtils 을 포괄하는 클래스
         - StringUtils와 달리 모든 객체에 대응가능하다.
         - defaultIfNull: Null일 경우 디폴트값을 지정해준다.
         - equlas
         - isEmpty
      - transient

