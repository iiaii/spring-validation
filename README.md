# spring-validation

### BindingResult

- `@ModelAttribute` 바로뒤에 위치해야한다
- 필드 타입이 안맞는 경우 스프링이 `FieldError` 를 생성해서 넣어준다


### Validator

- `@Validated`, `@ModelAttribute` [필드] -> 이렇게 사용하면 검증이 된다 (`@Valid` 도 가능)
- 검증로직을 분리


### Bean Validation

- `@ModelAttribute` 각각의 필드에 타입 변환 시도
- 성공하면 다음으로, 실패하면 `typeMismatch`로 `FieldError` 추가
- Validator 적용 (바인딩에 성공한 필드만 BeanValidation 적용, 타입 미스매치인 경우 검증 X)