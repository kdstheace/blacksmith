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

## QueryParams
th:href = "@{/holidays(festival='true',federal='true')}"

## if
```html
<div class="col-lg-6" th:if="${federal}==true">
    <h5 class="sub-title-timeline"><i class="fas fa-snowman"></i> Federal Holidays</h5>
    <div class="timeline">
        <div class="timeline">
            <div th:each="holiday : ${FEDERAL}" class="column">
                <div class="title">
                    <h2 th:text="${holiday.reason}"></h2>
                </div>
                <div class="description">
                    <h6 th:text="${holiday.day}" class="fas fa-calendar-alt"><i class="fas fa-calendar-alt"></i></h6>
                </div>
            </div>
        </div>
    </div>
</div>
```





