# Ob-havo Dashbordi 

## Loyiha haqida
Ushbu loyiha  foydalanuvchilarga dunyo bo‘ylab ob-havo ma‘lumotlarini real vaqtda vizual ko‘rinishda kuzatish imkoniyatini beradi.

## Imkoniyatlar
- **Foydalanuvchi ro‘yxatdan o‘tish** va **login** API.
- Bir yoki bir nechta joylashuv uchun **ob-havo ma‘lumotlarini olish**.
- Harorat, shamol tezligi va bulutlilik kabi ob-havo ko‘rsatkichlari rang kodlari orqali ko‘rsatiladi.
- Xatoliklarni qayta ishlash.
- Har kuni avtomatik ravishda barcha davlatlar uchun ob-havo ma‘lumotlarini olish va ma‘lumotlar bazasiga saqlash uchun **cron job**.

---

## Foydalanilgan texnologiyalar
- **Java** (Spring Boot)
- **Spring Security** (autentifikatsiya uchun)
- **WeatherAPI** (uchinchi tomon API orqali ob-havo ma‘lumotlari olish)
- **Spring Data JPA** (ma‘lumotlar bazasi bilan ishlash)
- **PostgreSQL** (ma‘lumotlar bazasi)
- **Maven** (loyihani boshqarish)

---

## O‘rnatish va sozlash

1. Loyihani klon qiling:
   ```bash
   git clone https://github.com/Abdulaziz8483/weatherDash.git
   ```

2. Loyihani yuklab oling va o‘rnating:
   ```bash
   mvn clean install
   ```

3. Ma‘lumotlar bazasi va API kalitlarini `application.yml` faylida sozlang:
   ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/weather_db
    spring.datasource.username=weather_user
    spring.datasource.password=12345
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
 
    ```

4. Loyihani ishga tushiring:
   ```bash
   mvn spring-boot:run
   ```

---

## API Hujjatlari

### **1. Autentifikatsiya APIlari**

#### **1.1 Ro‘yxatdan o‘tish**
- **Endpoint:** `api/v1/auth/registration`
- **Method:** POST
- **Tavsif:** Yangi foydalanuvchini ro‘yxatdan o‘tkazadi.
- **So‘rov:**
  ```json
  {
    "name": "John",
    "surname": "Doe",
    "username": "john_doe@gmail.com",
    "password": "password123"
  }
  ```
- **Javob:**
  ```json
  {
    "message": "Registration successful."
  }
  ```

#### **1.2 Login**
- **Endpoint:** `api/v1/auth/login`
- **Method:** POST
- **Tavsif:** Ro‘yxatdan o‘tgan foydalanuvchini tizimga kiritadi.
- **So‘rov:**
  ```json
  {
    "username": "john_doe",
    "password": "password123"
  }
  ```
- **Javob:**
  ```json
  {
    "message": "Login successful.",
    "token": "<JWT_TOKEN>"
  }
  ```

### **2. Ob-havo APIlari**

#### **2.1 Ob-havo ma‘lumotlarini olish**
- **Endpoint:** `/api/v1/weather/{city}`
- **Method:** GET
- **Tavsif:** Bir joylashuv uchun ob-havo ma‘lumotlarini qaytaradi.
- **So‘rov:**
  ```json
  {
    "locations": ["Tashkent"]
  }
  ```
- **Javob:**
  ```json
  [
    {
      "name": "Tashkent",
      "country": "Uzbekistan",
      "lat": 41.317,
      "lon": 69.25,
      "temp_c": 8.2,
      "temp_color": "#B3DFFD",
      "wind_kph": 8.6,
      "wind_color": "#E0F7FA",
      "cloud": 2,
      "cloud_color": "#FFF9C4"
    }
  ]
  ```

---



## Cron Job implementatsiyasi
- **Tavsif:** Har kuni tongda barcha joylashuvlar uchun ob-havo ma‘lumotlarini olib, bazaga saqlaydi.
- **Implementatsiya:** Spring'ning `@Scheduled` annotatsiyasi orqali.


---

## Sinov
1. API'larni sinash uchun `Postman` dan foydalaning.
---

## Muallif
- ** Komilov Lazizbek ** ((https://github.com/Abdulaziz8483))

---

