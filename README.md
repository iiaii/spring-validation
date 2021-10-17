# spring-validation

### BindingResult

- @ModelAttribute 바로뒤에 위치해야한다
- 필드 타입이 안맞는 경우 스프링이 FieldError 를 생성해서 넣어준다


### Validator

- @Validated @ModelAttribute [필드] -> 이렇게 사용하면 검증이 된다 (@Valid 도 가능)
- 검증로직을 분리