## Menambahkan Data Awal ke SQL Wallet

Sebelum menjalankan aplikasi, pastikan untuk menambahkan beberapa data awal ke database. Berikut adalah contoh instruksi SQL untuk menambahkan data ke tabel `t_banner` dan `t_services`:

### Menambahkan Data ke Tabel `t_banner`

```sql
INSERT INTO t_banner (banner_image, banner_name, description)
VALUES
('http://localhost:8080/api/v1/banners/Banner-1.png', 'Banner 1','Lerem Ipsum Dolor sit amet'),
('http://localhost:8080/api/v1/banners/Banner-2.png','Banner 2','Lerem Ipsum Dolor sit amet'),
('http://localhost:8080/api/v1/banners/Banner-3.png','Banner 3','Lerem Ipsum Dolor sit amet'),
('http://localhost:8080/api/v1/banners/Banner-4.png','Banner 4','Lerem Ipsum Dolor sit amet'),
('http://localhost:8080/api/v1/banners/Banner-5.png','Banner 5','Lerem Ipsum Dolor sit amet');
