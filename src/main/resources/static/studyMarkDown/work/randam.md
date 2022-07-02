##Random Knowledge
This is for random knowledge earned from work or study, not sorted or categorized by any topic

1. orElseThrow(() -> new Exception("adsf"))

2. throw Exception을 할경우 반드시 예외 핸들링(try catch나 throws)을 해줘야하는데, @ControllerAdvice가 붙은 
GlobalExceptionHandler를 사용하는 경우 에러핸들링으로 간주해, 메소드 내외부에서 핸들링을 하지 않아도 된다.
3. 