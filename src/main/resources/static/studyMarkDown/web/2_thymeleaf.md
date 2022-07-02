# 0. Setting
```html 
<html lang="eng" xmlns:th = "http://www.thymeleaf.org">
```
# 1. Grammar
## include
```html 
<div th:replace="header :: headerFileName"></div>
<div th:replace="footer :: footer"></div>
```
## text

## breadCrumb
home > courses

## Loop
```html
<div th:each="holiday : ${FESTIVAL}" class="column">
    <div class="title">
        <h2 th:text="${holiday.reason}"></h2>
    </div>
    <div class="description">
        <h6 th:text="${holiday.day}" class="fas fa-calendar-alt"><i class="fas fa-calendar-alt"></i></h6>
    </div>
</div>
```


