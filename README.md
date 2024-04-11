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
```

### Menambahkan Data ke Tabel `t_transaction`

```sql
INSERT INTO t_services (service_code, service_icon, service_name,service_amount)
VALUES
('PAJAK', 'http://localhost:8080/api/v1/service/PBB.png','Pajak PBB', 40000),
('PLN', 'http://localhost:8080/api/v1/service/Listrik.png','Listrik', 10000),
('PDAM', 'http://localhost:8080/api/v1/service/PDAM.png','PDAM Berlangganan', 40000),
('PULSA', 'http://localhost:8080/api/v1/service/Pulsa.png','Pulsa', 40000),
('PGN', 'http://localhost:8080/api/v1/service/PGN.png','PGN Berlangganan', 50000),
('MUSIK', 'http://localhost:8080/api/v1/service/Musik.png','Musik Berlangganan', 50000),
('TV', 'http://localhost:8080/api/v1/service/Televisi.png','TV Berlangganan', 50000),
('PAKET_DATA', 'http://localhost:8080/api/v1/service/Paket-Data.png','Paket Data', 50000),
('VOUCHER_GAME', 'http://localhost:8080/api/v1/service/Game.png','Voucher Game', 100000),
('VOUCHER_MAKANAN', 'http://localhost:8080/api/v1/service/Voucher-Makanan.png','Voucher Makanan', 100000),
('QURBAN', 'http://localhost:8080/api/v1/service/Qurban.png','Qurban', 200000),
('ZAKAT', 'http://localhost:8080/api/v1/service/Zakat.png','Zakat', 300000);
```