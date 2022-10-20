### Kollus Play Url Creator for Java

---

## 기능

- Player URL 생성

* UrlFactory.create 함수 사용
* QueryString.create 함수 사용

- VOD Player URL 생성

* VodTokenGenerator 클래스 사용

- Live Player URL 생성

* LiveTokenGenerator 클래스 사용

- 3rd 모듈 지원 (Akamai, Pallycon)

## Environment & Dependency

- JDK8 153 이상 사용(AES256 지원)
- Maven

```xml

<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.13.3</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.12.0</version>
    </dependency>
    <dependency>
        <groupId>commons-codec</groupId>
        <artifactId>commons-codec</artifactId>
        <version>1.15</version>
    </dependency>
</dependencies>
``` 
