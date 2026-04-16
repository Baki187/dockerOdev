pipeline {
    agent any

    // Bu kısımlar Jenkins'e işlemleri hangi sırayla ve nasıl yapacağını söyler
    stages {
        stage('Git Kodlarını Çekme') {
            steps {
                // GitHub deposundaki en güncel kodları Jenkins'in içine alır
                checkout scm
            }
        }
        
        stage('Projeyi Derleme (Maven)') {
            steps {
                // Kodları birleştirip çalıştırılabilir .jar dosyasına dönüştürür
                // Windows kullandığımız için 'bat' komutuyla çalıştırıyoruz
                bat 'mvnw.cmd clean package -DskipTests'
            }
        }
        
        stage('Docker İmajı Üretme') {
            steps {
                // Derlenen o kodlarla yeni bir Docker kalıbı inşa eder
                bat 'docker build -t sehirsaati .'
            }
        }
        
        stage('Canlıya Alma (Deploy)') {
            steps {
                // Arabanın eski modeli çalışıyorsa önce onu radyodan kapatıp siler
                bat 'docker rm -f canli_app || echo "Eski uygulama yok, yenisi calistiriliyor"'
                // Gıcır gıcır yeni Docker arabamızı 8080 portundan trafiğe çıkartır
                bat 'docker run -d -p 8080:8080 --name canli_app sehirsaati'
            }
        }
    }
}
