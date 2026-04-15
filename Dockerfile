# 1. Adım: Hangi işletim sistemini ve Java sürümünü kullanacağımızı seçiyoruz.
# Alpine, çok hafif ve küçük boyutlu bir Linux dağıtımıdır. İçinde Java 17 yüklü olanını seçtik.
FROM eclipse-temurin:17-jdk-alpine

# 2. Adım: Docker container'ının içinde çalışacağımız klasörü belirtiyoruz.
# Her şeyi bu "app" isimli klasörün içine koyacağız.
WORKDIR /app

# 3. Adım: Bilgisayarımızda (veya Jenkins'te) derlenmiş olan .jar dosyasını Container'ın içine kopyalıyoruz.
# "target/" klasöründeki jar dosyasını alıp, adını "app.jar" olarak değiştirerek container'ın içine yapıştırır.
COPY target/*.jar app.jar

# 4. Adım: Spring Boot varsayılan olarak 8080 portunda çalışır.
# Bu komut teknik olarak port açmaz ama projeyi okuyan kişilere ve sisteme "bu uygulama 8080 portunu dinliyor" bilgisini verir.
EXPOSE 8080

# 5. Adım: Makine (Container) başlatıldığında hangi komutun çalışacağını söylüyoruz.
# Konsola "java -jar app.jar" yazıp enter'a basmakla tamamen aynı şeydir.
ENTRYPOINT ["java", "-jar", "app.jar"]
